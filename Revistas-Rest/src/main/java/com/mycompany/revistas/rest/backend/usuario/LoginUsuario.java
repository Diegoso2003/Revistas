/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.revistas.rest.backend.usuario;

import com.mycompany.revistas.rest.backend.excepciones.DatosInvalidosException;
import com.mycompany.revistas.rest.backend.excepciones.DatosUsuarioException;
import com.mycompany.revistas.rest.backend.jwt_token.TokenJWT;
import com.mycompany.revistas.rest.backend.jwt_token.TokenDTO;
import com.mycompany.revistas.rest.backend.usuario.querys.UsuarioDB;
import java.util.Optional;

/**
 *
 * @author rafael-cayax
 */
public class LoginUsuario {
    private Usuario usuario;
    private TokenDTO tokenjwt;

    public LoginUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public TokenDTO iniciarSesion() throws DatosUsuarioException{
        this.tokenjwt = new TokenDTO();
        validarUsuario();
        generarTokens();
        return tokenjwt;
    }

    private void validarUsuario() throws DatosUsuarioException {
        try {
            if (this.usuario == null || !this.usuario.esValido()) {
                throw new DatosUsuarioException("ingrese correctamente sus datos");
            }
            UsuarioDB b = new UsuarioDB();
            Optional<Usuario> validacion = b.obtenerDatosUsuario(this.usuario.getNombre());
            Usuario usuario = validacion.orElseThrow(() -> new DatosUsuarioException("Nombre o Contraseña Incorrectos"));
            compararContraseña(usuario);
        } catch (DatosInvalidosException ex) {
            throw new DatosUsuarioException("Usuario o contraseña incorrectos");
        }
    }

    private void compararContraseña(Usuario usuario1) throws DatosUsuarioException {
        Encriptador encriptador = new Encriptador();
        boolean valida = encriptador.esContraseñaValida(this.usuario.getContraseña(), usuario1.getContraseña());
        if (!valida) {
            throw new DatosUsuarioException("Nombre o Contraseña Incorrectos");
        }
        this.usuario.setRol(usuario1.getRol());
    }
    
    
    private void generarTokens() {
        TokenJWT generador = new TokenJWT();
        this.tokenjwt.setToken(generador.generateAccessToken(usuario.getNombre(), usuario.getRol().name()));
        this.tokenjwt.setTokenRefresh(generador.generateRefreshToken(usuario.getNombre(), usuario.getRol().name()));
    }

}
