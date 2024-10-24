/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.revistas.rest.backend.anuncio.crud;

import com.mycompany.revistas.rest.backend.base_de_datos.PoolConnections;
import com.mycompany.revistas.rest.backend.excepciones.DatosUsuarioException;
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
}
