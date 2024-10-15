/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.revistas.rest.backend.usuario.querys;

import com.mycompany.revistas.rest.backend.base_de_datos.PoolConnections;
import com.mycompany.revistas.rest.backend.excepciones.DatosUsuarioException;
import com.mycompany.revistas.rest.backend.usuario.EnumRol;
import com.mycompany.revistas.rest.backend.usuario.Usuario;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Optional;

/**
 *
 * @author rafael-cayax
 */
public class UsuarioDB {
    private Usuario usuario;
    
    public Optional<Usuario> obtenerDatosUsuario(String usuarioNombre) throws DatosUsuarioException{
        usuario = null;
        Optional<Usuario> c = Optional.of(usuario);
        String statement = "select contraseña, rol from usuario where nombre = ?";
        try {
            Connection coneccion = PoolConnections.getInstance().getConnection();
            PreparedStatement st = coneccion.prepareStatement(statement);
            st.setString(1, usuarioNombre);
            ResultSet result = st.executeQuery();
            if (result.next()) {
                usuario = new Usuario();
                usuario.setContraseña(result.getString("contraseña"));
                usuario.setRol(EnumRol.valueOf(result.getString("rol")));
            }
        } catch (Exception e) {
            throw new DatosUsuarioException("Nombre o Contraseña incorrectos");
        }
        return c;
    }
}
