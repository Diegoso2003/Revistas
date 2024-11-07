/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.revistas.rest.backend.revista;

import com.mycompany.revistas.rest.backend.excepciones.DatosUsuarioException;
import com.mycompany.revistas.rest.backend.jwt_token.TokenJWT;
import com.mycompany.revistas.rest.backend.revista.crud.RevistaUpdate;
import com.mycompany.revistas.rest.backend.usuario.Usuario;

/**
 *
 * @author rafael-cayax
 */
public class ActualizarBloqueo {
    private Usuario usuario;
    private final BloqueosRevista bloqueos;

    public ActualizarBloqueo(BloqueosRevista bloqueos) {
        this.bloqueos = bloqueos;
    }
    
    public void actualizarBloqueo(String token) throws DatosUsuarioException{
        TokenJWT t = new TokenJWT();
        usuario = t.obtenerUsuario(token);
        actualizar();
    }

    private void actualizar() {
        RevistaUpdate rev = new RevistaUpdate();
        rev.actualizarBloqueos(bloqueos, usuario);
    }
}
