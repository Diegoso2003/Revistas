/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.revistas.rest.backend.usuario.querys;

import com.mycompany.revistas.rest.backend.base_de_datos.PoolConnections;
import com.mycompany.revistas.rest.backend.excepciones.DatosInvalidosException;
import com.mycompany.revistas.rest.backend.usuario.TipoRol;
import com.mycompany.revistas.rest.backend.usuario.Usuario;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Optional;

/**
 *
 * @author rafael-cayax
 */
public class UsuarioDB {
    private Usuario usuario;
    
    public Optional<Usuario> obtenerDatosUsuario(String usuarioNombre) throws DatosInvalidosException{
        String statement = "select contraseña, rol, cartera from usuario where nombre = ?";
        try {
            Connection coneccion = PoolConnections.getInstance().getConnection();
            PreparedStatement st = coneccion.prepareStatement(statement);
            st.setString(1, usuarioNombre);
            ResultSet result = st.executeQuery();
            if (result.next()) {
                usuario = new Usuario();
                usuario.setNombre(usuarioNombre);
                usuario.setContraseña(result.getString("contraseña"));
                usuario.setRol(TipoRol.valueOf(result.getString("rol")));
                usuario.setCartera(result.getDouble("cartera"));
                return Optional.of(usuario);
            }
        } catch (SQLException e) {
            throw new DatosInvalidosException();
        }
        return Optional.empty();
    }
    
    public void actualizarSaldo(double cartera, String usuario) throws DatosInvalidosException{
        String statement = "update usuario set cartera = ? where nombre = ?";
        try {
            Connection coneccion = PoolConnections.getInstance().getConnection();
            PreparedStatement st = coneccion.prepareStatement(statement);
            st.setDouble(1, cartera);
            st.setString(2, usuario);
            int x = st.executeUpdate();
            if (x != 1) {
                throw new DatosInvalidosException();
            }
        } catch (SQLException e) {
            throw new DatosInvalidosException();
        }
    }
}
