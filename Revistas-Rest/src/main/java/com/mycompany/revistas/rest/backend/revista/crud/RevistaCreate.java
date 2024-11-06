/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.revistas.rest.backend.revista.crud;

import com.mycompany.revistas.rest.backend.base_de_datos.PoolConnections;
import com.mycompany.revistas.rest.backend.excepciones.DatosUsuarioException;
import com.mycompany.revistas.rest.backend.revista.Revista;
import com.mycompany.revistas.rest.backend.usuario.Usuario;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Set;

/**
 *
 * @author rafael-cayax
 */
public class RevistaCreate {

    public void subirRevista(Revista revista, Usuario usuario) throws DatosUsuarioException {
        String revistast = "insert into revista (nombre, nombre_editor, nombre_categoria, fecha, descripcion) "
                + "values (?, ?, ?, ?, ?)";
        String etiquetador = "insert into etiquetador (nombre_etiqueta, ID_revista) values (?, ?)";
        Connection coneccion = null;
        try {
            coneccion = PoolConnections.getInstance().getConnection();
            PreparedStatement st = coneccion.prepareStatement(revistast, Statement.RETURN_GENERATED_KEYS);
            PreparedStatement st2 = coneccion.prepareStatement(etiquetador);
            coneccion.setAutoCommit(false);
            st.setString(1, revista.getNombre());
            st.setString(2, usuario.getNombre());
            st.setString(3, revista.getNombreCategoria());
            Date sqlDate = Date.valueOf(revista.getFecha());
            st.setDate(4, sqlDate);
            st.setString(5, revista.getDescripcion());
            st.executeUpdate();
            ResultSet result = st.getGeneratedKeys();
            if (result.next()) {
                int id = result.getInt(1);
                revista.setID(id);
            }
            verificadorYCreadorDeEtiquetas(revista, coneccion);
            Set<String> etiquetas = revista.getEtiquetas();
            for (String et : etiquetas) {
                st2.setString(1, et);
                st2.setInt(2, revista.getID());
                st2.executeUpdate();
            }
            coneccion.commit();
            coneccion.setAutoCommit(true);
        } catch (SQLException e) {
            try {
                coneccion.rollback();
            } catch (SQLException ex) {
            }
            throw new DatosUsuarioException("Ingrese los datos correctamente" + e.toString());
        } catch (DatosUsuarioException e) {
            try {
                coneccion.rollback();
            } catch (SQLException ex) {
            }
            throw new DatosUsuarioException(e.getMessage());
        }
    }

    private void verificadorYCreadorDeEtiquetas(Revista revista, Connection coneccion) throws DatosUsuarioException {
        String select = "select nombre from etiqueta where nombre = ?";
        String insert = "insert into etiqueta value (?)";
        try {
            PreparedStatement st = coneccion.prepareStatement(select);
            PreparedStatement st2 = coneccion.prepareStatement(insert);
            Set<String> etiquetas = revista.getEtiquetas();
            for (String etiqueta : etiquetas) {
                st.setString(1, etiqueta);
                ResultSet result = st.executeQuery();
                if (!result.next()) {
                    st2.setString(1, etiqueta);
                    st2.executeUpdate();
                }
            }
        } catch (SQLException e) {
            try {
                coneccion.rollback();
            } catch (SQLException ex) {
            }
            throw new DatosUsuarioException("Se ingreso una etiqueta invalida");
        }
    }
    
    public void subirPDF(Revista revista, Usuario usuario, InputStream archivo) throws DatosUsuarioException{
        String statement = "insert into pdf(ID_revista_principal, nombre, archivo) "
                + "values(?, ?, ?)";
        
        try {
            Connection coneccion = PoolConnections.getInstance().getConnection();
            PreparedStatement st = coneccion.prepareStatement(statement);
            st.setInt(1, revista.getID());
            st.setString(2, revista.getNombre());
            st.setBlob(3, archivo);
            st.executeUpdate();
        } catch (SQLException e) {
            
            throw new DatosUsuarioException("se ingreso un pdf invalido" + e.toString());
        }
    }
}
