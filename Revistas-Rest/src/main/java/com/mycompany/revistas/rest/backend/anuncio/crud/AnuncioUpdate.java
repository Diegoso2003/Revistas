/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.revistas.rest.backend.anuncio.crud;

import com.mycompany.revistas.rest.backend.anuncio.Anuncio;
import com.mycompany.revistas.rest.backend.anuncio.PreciosDTO;
import com.mycompany.revistas.rest.backend.anuncio.RegistroAnuncio;
import com.mycompany.revistas.rest.backend.anuncio.TipoAnuncio;
import com.mycompany.revistas.rest.backend.base_de_datos.PoolConnections;
import com.mycompany.revistas.rest.backend.excepciones.DatosUsuarioException;
import com.mycompany.revistas.rest.backend.usuario.Usuario;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

/**
 *
 * @author rafael-cayax
 */
public class AnuncioUpdate {
    
    public void desactivarAnuncio(int codigo) throws DatosUsuarioException{
        String statement = "update anuncio set estado = ? where ID = ?";
        try {
            Connection coneccion = PoolConnections.getInstance().getConnection();
            PreparedStatement st = coneccion.prepareStatement(statement);
            st.setBoolean(1, false);
            st.setInt(2, codigo);
            int query = st.executeUpdate();
            if (query != 1) {
                throw new DatosUsuarioException();
            }
        } catch (SQLException e) {
            throw new DatosUsuarioException();
        }
    }
    
    public void actualizarVideo(Anuncio anuncio, Usuario usuario) throws DatosUsuarioException{
        String statement = "update anuncio set url_video = ? where ID = ? and estado = ? and nombre_usuario = ?"
                + " and tipo_anuncio = ?";
        try {
            Connection coneccion = PoolConnections.getInstance().getConnection();
            PreparedStatement st = coneccion.prepareStatement(statement);
            st.setString(1, anuncio.getUrlVideo());
            st.setInt(2, anuncio.getID());
            st.setBoolean(3, true);
            st.setString(4, usuario.getNombre());
            st.setString(5, TipoAnuncio.VIDEO.name());
            int result = st.executeUpdate();
            if (result != 1) {
                throw new DatosUsuarioException("No se pudo actualizar el anuncio");
            }
        } catch (SQLException e) {
        }
    }
    
    public void actualizarImagen(Anuncio imagen, Usuario usuario) throws DatosUsuarioException{
        String statement = "update anuncio set media = ? where ID = ? and estado = ? and nombre_usuario = ?"
                + " and tipo_anuncio = ?";
        try {
            Connection coneccion = PoolConnections.getInstance().getConnection();
            PreparedStatement st = coneccion.prepareStatement(statement);
            st.setBlob(1, imagen.getImagen());
            st.setInt(2, imagen.getID());
            st.setBoolean(3, true);
            st.setString(4, usuario.getNombre());
            st.setString(5, TipoAnuncio.TEXTO_E_IMAGEN.name());
            int result = st.executeUpdate();
            if (result != 1) {
                throw new DatosUsuarioException("No se pudo actualizar el anuncio");
            }
            
        } catch (SQLException e) {
        }
    }
    
    public void actualizarTexto(Anuncio anuncio, Usuario usuario) throws DatosUsuarioException{
        String statement = "update anuncio set texto = ? where ID = ? and estado = ? and nombre_usuario = ?"
                + " and (tipo_anuncio = ? or tipo_anuncio = ?)";
        
        try {
            Connection coneccion = PoolConnections.getInstance().getConnection();
            PreparedStatement st = coneccion.prepareStatement(statement);
            st.setString(1, anuncio.getTextoAnuncio());
            st.setInt(2, anuncio.getID());
            st.setBoolean(3, true);
            st.setString(4, usuario.getNombre());
            st.setString(5, TipoAnuncio.TEXTO.name());
            st.setString(6, TipoAnuncio.TEXTO_E_IMAGEN.name());
            int result = st.executeUpdate();
            if (result != 1) {
                throw new DatosUsuarioException("No se pudo actualizat el anuncio");
            }
        } catch (SQLException e) {
        }
    }
    
    public void actualizarAnuncios(PreciosDTO precios) throws DatosUsuarioException{
        String statement = "update precio_de_anuncios set texto = ?, texto_imagen = ?,"
                + " video = ?, dia_1 = ?, dia_3 = ?, semana_1 = ?, semana_2 = ?";
        
        try {
            Connection coneccion = PoolConnections.getInstance().getConnection();
            PreparedStatement st = coneccion.prepareStatement(statement);
            st.setDouble(1, precios.getTexto());
            st.setDouble(2, precios.getImagen());
            st.setDouble(3, precios.getVideo());
            st.setDouble(4, precios.getDia1());
            st.setDouble(5, precios.getDia3());
            st.setDouble(6, precios.getSemana1());
            st.setDouble(7, precios.getSemana2());
            st.executeUpdate();
        } catch (SQLException e) {
            throw new DatosUsuarioException("No se pudo actualizar los anuncios intente mas tarde");
        }
    }
    
    public void actualizarContador(RegistroAnuncio registro){
        String statement = "update anuncio set contador = 1 + contador where ID = ? and estado = ?";
        Connection coneccion = null;
        try {
            coneccion = PoolConnections.getInstance().getConnection();
            coneccion.setAutoCommit(false);
            PreparedStatement st = coneccion.prepareStatement(statement);
            st.setInt(1, registro.getIdAnuncio());
            st.setBoolean(2, true);
            st.executeUpdate();
            AnuncioCreate a = new AnuncioCreate();
            a.subirRegistro(registro, coneccion);
            coneccion.commit();
            coneccion.setAutoCommit(true);
        } catch (SQLException e) {
            System.out.println(e.toString());
            try {
                coneccion.rollback();
            } catch (SQLException ex) {
            }
        }
    }
}
