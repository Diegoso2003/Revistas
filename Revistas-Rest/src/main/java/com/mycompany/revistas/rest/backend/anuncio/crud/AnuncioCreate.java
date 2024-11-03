/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.revistas.rest.backend.anuncio.crud;

import com.mycompany.revistas.rest.backend.anuncio.Anuncio;
import com.mycompany.revistas.rest.backend.anuncio.TipoAnuncio;
import com.mycompany.revistas.rest.backend.base_de_datos.PoolConnections;
import com.mycompany.revistas.rest.backend.excepciones.DatosInvalidosException;
import com.mycompany.revistas.rest.backend.excepciones.DatosUsuarioException;
import com.mycompany.revistas.rest.backend.usuario.Usuario;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.SQLException;

/**
 *
 * @author rafael-cayax
 */
public class AnuncioCreate {
    private final Anuncio anuncio;
    private Usuario usuario;

    public AnuncioCreate(Anuncio anuncio) {
        this.anuncio = anuncio;
    }
    
    public void subirAnuncio(Usuario usuario) throws DatosUsuarioException{
        this.usuario = usuario;
        TipoAnuncio tipo = anuncio.getTipo();
        switch(tipo){
            case VIDEO:
            case TEXTO:
                subirAnuncioTextoOVideo();
                break;
            case TEXTO_E_IMAGEN:
                subirAnuncioTextoEImagen();
                break;
        }
    }

    private void subirAnuncioTextoOVideo() throws DatosUsuarioException {
        String statement = "insert into anuncio(nombre_usuario, precio, fecha_pago, tipo_anuncio, vigencia, texto, url_video) values"
                + "(?, ?, ?, ?, ?, ?, ?)";
        Connection coneccion = null;
        try {
            coneccion = PoolConnections.getInstance().getConnection();
            coneccion.setAutoCommit(false);
            PreparedStatement st = coneccion.prepareStatement(statement);
            st.setString(1, usuario.getNombre());
            st.setDouble(2, anuncio.getPrecio());
            Date fecha = Date.valueOf(anuncio.getFecha());
            st.setDate(3, fecha);
            st.setString(4, anuncio.getTipo().name());
            st.setInt(5, anuncio.getVigencia().dias());
            st.setString(6, anuncio.getTextoAnuncio());
            st.setString(7, anuncio.getUrlVideo());
            st.executeUpdate();
            actualizarUsuario(coneccion);
        } catch (SQLException | DatosInvalidosException e) {
            try {
            coneccion.rollback();
            } catch (SQLException ex) {
            }
            throw new DatosUsuarioException("ingrese correctamente los datos del anuncio" + e.toString());
        }
    }

    private void subirAnuncioTextoEImagen() throws DatosUsuarioException {
        String statement = "insert into anuncio(nombre_usuario, precio, fecha_pago, tipo_anuncio, vigencia, media, extension) "
                + "values(?, ?, ?, ?, ?, ?, ?)";
        Connection coneccion = null;
        try {
            coneccion = PoolConnections.getInstance().getConnection();
            coneccion.setAutoCommit(false);
            PreparedStatement st = coneccion.prepareStatement(statement);
            st.setString(1, usuario.getNombre());
            st.setDouble(2, anuncio.getPrecio());
            Date fecha = Date.valueOf(anuncio.getFecha());
            st.setDate(3, fecha);
            st.setString(4, anuncio.getTipo().name());
            st.setInt(5, anuncio.getVigencia().dias());
            st.setBlob(6, anuncio.getImagen());
            st.setString(7, anuncio.getExtension());
            st.executeUpdate();
            actualizarUsuario(coneccion);
        } catch (SQLException | DatosInvalidosException e) {
            try {
                coneccion.rollback();
            } catch (Exception ex) {
            }
            throw new DatosUsuarioException("ingrese correctamente los datos del anuncio" + e.toString());
        }
    }

    private void actualizarUsuario(Connection coneccion) throws SQLException, DatosInvalidosException {
        String statement = "update usuario set cartera = ? where nombre = ?";
        PreparedStatement st = coneccion.prepareStatement(statement);
        double total = usuario.getCartera() - anuncio.getPrecio();
        st.setDouble(1, total);
        st.setString(2, usuario.getNombre());
        int col = st.executeUpdate();
        if (col != 1) {
            throw new DatosInvalidosException();
        }
        coneccion.commit();
        coneccion.setAutoCommit(true);
    }
}
