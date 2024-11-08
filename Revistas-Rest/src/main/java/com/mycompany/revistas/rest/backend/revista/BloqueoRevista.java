/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.revistas.rest.backend.revista;

import com.mycompany.revistas.rest.backend.dtos.CompraBloqueo;
import com.mycompany.revistas.rest.backend.excepciones.DatosInvalidosException;
import com.mycompany.revistas.rest.backend.excepciones.DatosUsuarioException;
import com.mycompany.revistas.rest.backend.jwt_token.TokenJWT;
import com.mycompany.revistas.rest.backend.revista.crud.RevistaCreate;
import com.mycompany.revistas.rest.backend.revista.crud.RevistasRead;
import com.mycompany.revistas.rest.backend.usuario.Usuario;
import com.mycompany.revistas.rest.backend.usuario.querys.UsuarioDB;
import java.util.Optional;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author rafael-cayax
 */
public class BloqueoRevista {
    private CompraBloqueo bloqueo;
    private Usuario usuario;

    public BloqueoRevista(CompraBloqueo bloqueo) {
        this.bloqueo = bloqueo;
    }
    
    public void comprarBloqueo(String token) throws DatosUsuarioException{
        try {
            TokenJWT t = new TokenJWT();
            usuario = t.obtenerUsuario(token);
            UsuarioDB r = new UsuarioDB();
            Optional<Usuario> user = r.obtenerDatosUsuario(usuario.getNombre());
            usuario = user.orElseThrow(() -> new DatosUsuarioException("usuario no encontado"));
            validarCompra();
        } catch (DatosInvalidosException ex) {
            throw new DatosUsuarioException("datos invalidos");
        }
    }

    private void validarCompra() throws DatosUsuarioException {
        if (!bloqueo.esValido()) {
            throw new DatosUsuarioException("Ingrese correctamente los datos");
        }
        RevistasRead r = new RevistasRead();
        double precio = r.conseguirPrecioBloqueo(bloqueo.getIdRevista());
        double precioTotal = bloqueo.Calcular(precio);
        if (precioTotal > usuario.getCartera()) {
            throw new DatosUsuarioException("Insuficiente saldo, Saldo actual: Q" + usuario.getCartera() );
        }
        if (precioTotal <= 0) {
            throw new DatosUsuarioException("no se encontro la revista");
        }
        activarBloqueo();
    }

    private void activarBloqueo() throws DatosUsuarioException {
        RevistaCreate r = new RevistaCreate();
        r.activarBloqueo(bloqueo, usuario);
    }
}
