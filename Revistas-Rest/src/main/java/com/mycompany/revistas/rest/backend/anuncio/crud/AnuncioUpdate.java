/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.revistas.rest.backend.anuncio.crud;

import com.mycompany.revistas.rest.backend.anuncio.Anuncio;
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
}
