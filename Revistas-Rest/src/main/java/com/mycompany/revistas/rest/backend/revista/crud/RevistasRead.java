/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.revistas.rest.backend.revista.crud;

import com.mycompany.revistas.rest.backend.base_de_datos.PoolConnections;
import com.mycompany.revistas.rest.backend.dtos.ReportePago;
import com.mycompany.revistas.rest.backend.dtos.ResultadoPago;
import com.mycompany.revistas.rest.backend.excepciones.DatosUsuarioException;
import com.mycompany.revistas.rest.backend.revista.Pdf;
import com.mycompany.revistas.rest.backend.revista.Revista;
import com.mycompany.revistas.rest.backend.usuario.Usuario;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

/**
 *
 * @author rafael-cayax
 */
public class RevistasRead {
    private double precio;

    public List<String> conseguirCategoria() throws DatosUsuarioException {
        List<String> categorias = new LinkedList<>();
        String statement = "select * from categoria";
        try {
            Connection coneccion = PoolConnections.getInstance().getConnection();
            Statement st = coneccion.createStatement();
            ResultSet result = st.executeQuery(statement);
            while (result.next()) {
                String categoria = result.getString("nombre");
                categorias.add(categoria);
            }
        } catch (SQLException e) {
            throw new DatosUsuarioException();
        }
        return categorias;
    }

    public List<Revista> obtenerRevistas(String statement, boolean admin, String nombre) {
        List<Revista> revistas = new LinkedList<>();
        try {
            Connection coneccion = PoolConnections.getInstance().getConnection();
            PreparedStatement st = coneccion.prepareStatement(statement);
            if (!admin) {
                st.setString(1, nombre);
            }
            ResultSet result = st.executeQuery();
            while (result.next()) {
                Revista revista = new Revista();
                revista.setFecha(result.getDate("fecha").toLocalDate());
                revista.setID(result.getInt("ID"));
                revista.setNombre(result.getString("nombre"));
                revista.setNombreEditor(result.getString("nombre_editor"));
                revista.setNombreCategoria(result.getString("nombre_categoria"));
                revista.setBloqueoComentario(result.getBoolean("bloqueo_comentario"));
                revista.setBloqueoAnuncios(result.getBoolean("bloqueo_anuncios"));
                revista.setDescripcion(result.getString("descripcion"));
                revista.setPrecio(result.getDouble("precio"));
                revista.setPrecioBloqueo(result.getDouble("precio_bloqueo"));
                revista.setBloqueoSuscripcion(result.getBoolean("bloqueo_suscripcion"));
                revista.setNumeroLikes(result.getInt("numero_likes"));
                obtenerPDFS(revista, coneccion);
                obtenerEtiquetas(revista, coneccion);
                if (revista.isBloqueoAnuncios()) {
                    validarBloqueo(revista, coneccion);
                }
                revistas.add(revista);
            }

        } catch (SQLException e) {
            System.out.println(e.toString());
        }
        return revistas;
    }

    public Revista conseguirRevistaPorID(int id) {
        Revista revista = new Revista();
        String statement = "select * from revista where id = ?";
        try {
            Connection coneccion = PoolConnections.getInstance().getConnection();
            PreparedStatement st = coneccion.prepareStatement(statement);
            st.setInt(1, id);
            ResultSet result = st.executeQuery();

            if (result.next()) {
                revista.setFecha(result.getDate("fecha").toLocalDate());
                revista.setID(result.getInt("ID"));
                revista.setNombre(result.getString("nombre"));
                revista.setNombreEditor(result.getString("nombre_editor"));
                revista.setNombreCategoria(result.getString("nombre_categoria"));
                revista.setBloqueoComentario(result.getBoolean("bloqueo_comentario"));
                revista.setBloqueoAnuncios(result.getBoolean("bloqueo_anuncios"));
                revista.setDescripcion(result.getString("descripcion"));
                revista.setPrecio(result.getDouble("precio"));
                revista.setPrecioBloqueo(result.getDouble("precio_bloqueo"));
                revista.setBloqueoSuscripcion(result.getBoolean("bloqueo_suscripcion"));
                revista.setNumeroLikes(result.getInt("numero_likes"));
                obtenerPDFS(revista, coneccion);
                obtenerEtiquetas(revista, coneccion);
                if (revista.isBloqueoAnuncios()) {
                    validarBloqueo(revista, coneccion);
                }
            }
        } catch (SQLException e) {
        }
        return revista;
    }

    private void obtenerPDFS(Revista revista, Connection coneccion) throws SQLException {
        List<Pdf> pdfs = new LinkedList<>();
        String statement = "select ID_pdf, nombre from pdf where ID_revista_principal = ?";
        PreparedStatement st = coneccion.prepareStatement(statement);
        st.setInt(1, revista.getID());
        ResultSet result = st.executeQuery();
        while (result.next()) {
            Pdf pdf = new Pdf();
            pdf.setIdRevistaPrincipal(revista.getID());
            pdf.setNombre(result.getString("nombre"));
            pdf.setIdPdf(result.getInt("ID_pdf"));
            pdfs.add(pdf);
        }
        revista.setPdfs(pdfs);
    }

    private void obtenerEtiquetas(Revista revista, Connection coneccion) throws SQLException {
        Set<String> etiquetas = new HashSet<>();
        String statement = "select nombre_etiqueta from etiquetador where ID_revista = ?";
        PreparedStatement st = coneccion.prepareStatement(statement);
        st.setInt(1, revista.getID());
        ResultSet result = st.executeQuery();
        while (result.next()) {
            String etiqueta = result.getString("nombre_etiqueta");
            etiquetas.add(etiqueta);
        }
        revista.setEtiquetas(etiquetas);
    }

    private void validarBloqueo(Revista revista, Connection coneccion) throws SQLException {
        String statement = "select fecha_pago, dias_vigentes, DATEDIFF(CURRENT_DATE, fecha_pago) AS dias_diferencia"
                + " from registro_bloqueo_anuncio where ID_revista_bloqueo = ? Order By(fecha_pago) DESC limit 1;";
        PreparedStatement st = coneccion.prepareStatement(statement);
        st.setInt(1, revista.getID());
        ResultSet result = st.executeQuery();
        if (result.next()) {
            int diasVigentes = result.getInt("dias_vigentes");
            int diasActuales = result.getInt("dias_diferencia");
            if (diasActuales > diasVigentes) {
                RevistaUpdate r = new RevistaUpdate();
                r.desactivarBloqueo(revista);
            }
        } else {
            revista.setBloqueoAnuncios(false);
            RevistaUpdate r = new RevistaUpdate();
            r.desactivarBloqueo(revista);
        }
    }

    public double conseguirPrecioBloqueo(int id) throws DatosUsuarioException {
        double d = 0;
        String statement = "select precio_bloqueo, bloqueo_anuncios from revista where ID = ?";
        try {
            Connection coneccion = PoolConnections.getInstance().getConnection();
            PreparedStatement st = coneccion.prepareStatement(statement);
            st.setInt(1, id);
            ResultSet result = st.executeQuery();
            if (result.next()) {
                Revista revista = new Revista();
                d = result.getDouble("precio_bloqueo");
                revista.setPrecioBloqueo(d);
                revista.setBloqueoAnuncios(result.getBoolean("bloqueo_anuncios"));
                if (revista.isBloqueoAnuncios()) {
                    validarBloqueo(revista, coneccion);
                }
                if (revista.isBloqueoAnuncios()) {
                    throw new DatosUsuarioException("la revista ya tiene un bloqueo de anuncios activo");
                }
            }
        } catch (SQLException e) {
            System.out.println(e);
        }
        return d;
    }

    public List<ResultadoPago> conseguirResultado(String statement, ReportePago reporte, Usuario usuario) {
        List<ResultadoPago> resultado = new LinkedList<>();
        this.precio = 0;
        try {
            Connection coneccion = PoolConnections.getInstance().getConnection();
            PreparedStatement st = coneccion.prepareStatement(statement);
            st.setString(1, usuario.getNombre());
            if (reporte.rangoFecha()) {
                Date sqlDate = Date.valueOf(reporte.getFechaFin());
                Date sql = Date.valueOf(reporte.getFechaInicio());
                st.setDate(2, sqlDate);
                st.setDate(3, sql);
            }
            if (reporte.tieneID() && reporte.rangoFecha()) {
                st.setInt(4, reporte.getId());
            } else if (reporte.tieneID()) {
                st.setInt(2, reporte.getId());
            }
            ResultSet result = st.executeQuery();
            while (result.next()) {
                ResultadoPago r = new ResultadoPago();
                r.setFechaPago(result.getDate("fecha_pago").toLocalDate());
                r.setDias(result.getInt("dias_vigentes"));
                r.setIdRevista(result.getInt("ID_revista_bloqueo"));
                r.setPago(result.getDouble("pago"));
                this.precio += r.getPago();
                resultado.add(r);
            }
        } catch (Exception e) {
        }
        
        return resultado;
    }

    public double getPrecio() {
        return precio;
    }
    
    
}
