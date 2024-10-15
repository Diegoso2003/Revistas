/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.revistas.rest.backend.usuario;

import com.mycompany.revistas.rest.backend.base_de_datos.PoolConnections;
import com.mycompany.revistas.rest.backend.excepciones.DatosUsuarioException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

/**
 *
 * @author rafael-cayax
 */
public class RegistroUsuario {

    private Usuario usuario;

    public RegistroUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public void registrarUsuario() throws DatosUsuarioException {
        validarRegistro();
        usuario.encriptarContraseña();
        guardarUsuario();
    }

    private void guardarUsuario() throws DatosUsuarioException {
        String statement = "insert into usuario(nombre, contraseña, rol) values(?, ?, ?)";
        try {
            Connection coneccion = PoolConnections.getInstance().getConnection();
            PreparedStatement st = coneccion.prepareStatement(statement);
            st.setString(1, usuario.getNombre());
            st.setString(2, usuario.getContraseña());
            st.setString(3, usuario.getRol().name());
            st.executeUpdate();
        } catch (SQLException e) {
            if (e.getErrorCode() == 1062) {
                throw new DatosUsuarioException("Nombre de usuario ya registrado");
            }
            throw new DatosUsuarioException("Porfavor ingrese los datos correctamente" + e.toString());
        }
    }

    private void validarRegistro() throws DatosUsuarioException {
        if (usuario == null) {
            throw new DatosUsuarioException("Porfavor ingrese correctamente los datos requeridos");
        }
        if (!usuario.esRegistroValido()) {
            throw new DatosUsuarioException("Porfavor introduzca los datos correctamente");
        }
        if (!usuario.contraseñasValidas()) {
            throw new DatosUsuarioException("Ambas contraseñas debebn ser iguales");
        }
    }

}
