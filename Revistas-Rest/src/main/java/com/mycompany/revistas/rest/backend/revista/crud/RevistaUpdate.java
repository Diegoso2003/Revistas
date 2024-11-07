/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.revistas.rest.backend.revista.crud;

import com.mycompany.revistas.rest.backend.base_de_datos.PoolConnections;
import com.mycompany.revistas.rest.backend.revista.BloqueosRevista;
import com.mycompany.revistas.rest.backend.revista.Revista;
import com.mycompany.revistas.rest.backend.usuario.Usuario;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

/**
 *
 * @author rafael-cayax
 */
public class RevistaUpdate {
    
    public void desactivarBloqueo(Revista revista){
        String statement = "update revista set bloqueo_anuncios = ? where ID = ?";
        try {
            Connection coneccion = PoolConnections.getInstance().getConnection();
            PreparedStatement st = coneccion.prepareStatement(statement);
            st.setBoolean(1, false);
            st.setInt(2, revista.getID());
            st.executeUpdate();
        } catch (SQLException e) {
        }
    }
    
    public void actualizarBloqueos(BloqueosRevista bloqueos, Usuario usuario){
        String statement = "update revista set bloqueo_comentario = ?, bloqueo_suscripcion = ? where ID = ?"
                + " and nombre_editor = ?";
        try {
            Connection coneccion = PoolConnections.getInstance().getConnection();
            PreparedStatement st = coneccion.prepareStatement(statement);
            st.setBoolean(1, bloqueos.isBloqueoComentarios());
            st.setBoolean(2, bloqueos.isBloqueoSuscripcion());
            st.setInt(3, bloqueos.getIdRevista());
            st.setString(4, usuario.getNombre());
            st.executeUpdate();
        } catch (Exception e) {
        }
    }
}
