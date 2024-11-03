/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.revistas.rest.backend.anuncio.crud;

import com.mycompany.revistas.rest.backend.anuncio.Anuncio;
import com.mycompany.revistas.rest.backend.anuncio.FiltroAnuncio;
import com.mycompany.revistas.rest.backend.anuncio.PreciosDTO;
import com.mycompany.revistas.rest.backend.anuncio.TipoAnuncio;
import com.mycompany.revistas.rest.backend.anuncio.Vigencia;
import com.mycompany.revistas.rest.backend.base_de_datos.PoolConnections;
import com.mycompany.revistas.rest.backend.excepciones.DatosUsuarioException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;


/**
 *
 * @author rafael-cayax
 */
public class AnuncioRead {
    
    public PreciosDTO obtenerPrecios() throws DatosUsuarioException{
        PreciosDTO precios= new PreciosDTO();
        String statement = "select texto, texto_imagen, video, dia_1, dia_3, semana_1, semana_2 from precio_de_anuncios";
        try {
            Connection coneccion = PoolConnections.getInstance().getConnection();
            Statement st = coneccion.createStatement();
            ResultSet result = st.executeQuery(statement);
            if (result.next()) {
                precios.setImagen(result.getDouble("texto_imagen"));
                precios.setTexto(result.getDouble("texto"));
                precios.setVideo(result.getDouble("video"));
                precios.setDia1(result.getDouble("dia_1"));
                precios.setDia3(result.getDouble("dia_3"));
                precios.setSemana1(result.getDouble("semana_1"));
                precios.setSemana2(result.getDouble("semana_2"));
            }
        } catch (SQLException e) {
            throw new DatosUsuarioException("No se pudo guardar el anuncio pruebe mas tarde");
        }
        return precios;
    }

    public List<Anuncio> obtenerAnuncios(String statement, boolean obtenerTodos, String nombre) throws DatosUsuarioException {
        List<Anuncio> anuncios = new ArrayList<>();
        try {
            Connection coneccion = PoolConnections.getInstance().getConnection();
            PreparedStatement st = coneccion.prepareStatement(statement);
            st.setBoolean(1, true);
            if (!obtenerTodos) {
                st.setString(2, nombre);
            }
            ResultSet result = st.executeQuery();
            while(result.next()){
                Anuncio anuncio = new Anuncio();
                anuncio.setID(result.getInt("ID"));
                anuncio.setNombreUsuario(result.getString("nombre_usuario"));
                anuncio.setPrecio(result.getDouble("precio"));
                anuncio.setFecha(result.getDate("fecha_pago").toLocalDate());
                anuncio.setTipo(TipoAnuncio.valueOf(result.getString("tipo_anuncio")));
                anuncio.setTextoAnuncio(result.getString("texto"));
                anuncio.setUrlVideo(result.getString("url_video"));
                anuncio.setVigencia(Vigencia.evaluarVigencia(result.getInt("vigencia")));
                FiltroAnuncio filtro = new FiltroAnuncio(anuncio);
                if (filtro.esVigente()) {
                    anuncios.add(anuncio);
                } else{
                    AnuncioUpdate delete = new AnuncioUpdate();
                    delete.desactivarAnuncio(anuncio.getID());
                }
            }
        } catch (SQLException e) {
            throw new DatosUsuarioException("algo salio mal" + e.toString());
        }
        return anuncios;
    }

    public InputStream conseguirImagen(int id) {
        String statement = "select media from anuncio where ID = ?";
        
        try {
            Connection coneccion = PoolConnections.getInstance().getConnection();
            PreparedStatement st = coneccion.prepareStatement(statement);
            st.setInt(1, id);
            ResultSet result = st.executeQuery();
            if (result.next()) {
                return result.getBinaryStream("media");
            }
        } catch (Exception e) {
        }
        return null;
    }
}
