/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.revistas.rest.backend.revista.crud;

import com.mycompany.revistas.rest.backend.base_de_datos.PoolConnections;
import com.mycompany.revistas.rest.backend.revista.Revista;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

/**
 *
 * @author rafael-cayax
 */
public class RevistaDelete {
    
    public void borrarRevista(Revista revista){
        String statement = "Delete from revista where ID = ?";
        
        try {
            Connection coneccion = PoolConnections.getInstance().getConnection();
            PreparedStatement st = coneccion.prepareStatement(statement);
            st.setInt(1, revista.getID());
            st.executeUpdate();
        } catch (SQLException e) {
        }
    }
}
