/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.revistas.rest.backend.revista.crud;

import com.mycompany.revistas.rest.backend.base_de_datos.PoolConnections;
import com.mycompany.revistas.rest.backend.excepciones.DatosUsuarioException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.LinkedList;
import java.util.List;

/**
 *
 * @author rafael-cayax
 */
public class RevistasRead {
    
    
    public List<String> conseguirCategoria() throws DatosUsuarioException{
        List<String> categorias = new LinkedList<>();
        String statement = "select * from categoria";
        try {
            Connection coneccion = PoolConnections.getInstance().getConnection();
            Statement st = coneccion.createStatement();
            ResultSet result = st.executeQuery(statement);
            while(result.next()){
                String categoria = result.getString("nombre");
                categorias.add(categoria);
            }
        } catch (SQLException e) {
            throw new DatosUsuarioException();
        }
        return categorias;
    }
}
