/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.revistas.rest.backend.anuncio;

import com.mycompany.revistas.rest.backend.anuncio.crud.AnuncioUpdate;
import com.mycompany.revistas.rest.backend.excepciones.DatosUsuarioException;
import com.mycompany.revistas.rest.backend.jwt_token.TokenJWT;
import com.mycompany.revistas.rest.backend.usuario.Usuario;
import java.io.InputStream;

/**
 *
 * @author rafael-cayax
 */
public class ActualizarAnuncio {
    private Anuncio anuncio;
    private String token;
    private Usuario usuario;

    public ActualizarAnuncio(String token, Anuncio anuncio) {
        this.anuncio = anuncio;
        this.token = token;
    }

    public ActualizarAnuncio(String token, InputStream imagen, int id) {
        this.token = token;
        this.anuncio = new Anuncio();
        this.anuncio.setID(id);
        this.anuncio.setImagen(imagen);
    }
    
    public void actualizarTexto() throws DatosUsuarioException{
        if (anuncio.getTextoAnuncio() == null || anuncio.getTextoAnuncio().isBlank()) {
            throw new DatosUsuarioException("Ingresa un texto valido");
        }
        conseguirDatosUsuario();
        AnuncioUpdate a = new AnuncioUpdate();
        a.actualizarTexto(anuncio, usuario);
    }
    
    public void actualizarImagen() throws DatosUsuarioException{
        if (anuncio.getImagen() == null) {
            throw new DatosUsuarioException("ingresa una imagen valida");
        }
        conseguirDatosUsuario();
        AnuncioUpdate a = new AnuncioUpdate();
        a.actualizarImagen(anuncio, usuario);
    }
    
    public void actualizarVideo() throws DatosUsuarioException{
        if (anuncio.getUrlVideo() == null || anuncio.getUrlVideo().isBlank()) {
            throw new DatosUsuarioException("ingresa una url valida");
        }
        conseguirDatosUsuario();
        AnuncioUpdate a = new AnuncioUpdate();
        a.actualizarVideo(anuncio, usuario);
    }

    private void conseguirDatosUsuario() throws DatosUsuarioException {
        TokenJWT t = new TokenJWT();
        usuario = t.obtenerUsuario(token);
    }
}
