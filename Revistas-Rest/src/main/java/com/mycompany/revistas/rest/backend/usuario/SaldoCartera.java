/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.revistas.rest.backend.usuario;

import com.mycompany.revistas.rest.backend.dtos.Cartera;
import com.mycompany.revistas.rest.backend.excepciones.DatosInvalidosException;
import com.mycompany.revistas.rest.backend.excepciones.DatosUsuarioException;
import com.mycompany.revistas.rest.backend.jwt_token.TokenJWT;
import com.mycompany.revistas.rest.backend.usuario.querys.UsuarioDB;
import java.util.Optional;

/**
 *
 * @author rafael-cayax
 */
public class SaldoCartera {
    private final String token;
    private double total;
    private Cartera cartera;
    private Usuario usuario;

    public SaldoCartera(String token) {
        this.token = token;
    }
    
    public Usuario obtenerDatos() throws DatosUsuarioException{
        try {
            TokenJWT t = new TokenJWT();
            String nombre = t.obtenerNombreUsuario(token);
            UsuarioDB datos = new UsuarioDB();
            Optional<Usuario> user = datos.obtenerDatosUsuario(nombre);
            Usuario usuario = user.orElseThrow(() -> new DatosUsuarioException());
            return usuario;
        } catch (DatosInvalidosException ex) {
            throw new DatosUsuarioException();
        }
    }
    
    public double recargarCartera(Cartera cartera) throws DatosUsuarioException{
        this.cartera = cartera;
        validarCantidad();
        validarUsuario();
        actualizarSaldo();
        return total;
    }

    private void validarCantidad() throws DatosUsuarioException {
        if (!cartera.esValida()) {
            throw new DatosUsuarioException("Porfavor ingrese un valor valido");
        }
    }

    private void validarUsuario() throws DatosUsuarioException {
        usuario = obtenerDatos();
        TipoRol rol = usuario.getRol();
        if (rol != TipoRol.ANUNCIADOR && rol != TipoRol.EDITOR) {
            throw new DatosUsuarioException("No tiene permitido recargar la cartera");
        }
    }

    private void actualizarSaldo() throws DatosUsuarioException {
        try {
            total = cartera.getCartera() + usuario.getCartera();
            UsuarioDB user = new UsuarioDB();
            user.actualizarSaldo(total, usuario.getNombre());
        } catch (DatosInvalidosException ex) {
            throw new DatosUsuarioException("La cartera no se actualizo intente mas tarde");
        }
    }
}
