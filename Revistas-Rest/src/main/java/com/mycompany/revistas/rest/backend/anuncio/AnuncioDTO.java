/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.revistas.rest.backend.anuncio;

import com.mycompany.revistas.rest.backend.excepciones.DatosUsuarioException;
import com.mycompany.revistas.rest.backend.jwt_token.TokenJWT;
import io.jsonwebtoken.Claims;
import java.io.InputStream;
import org.glassfish.jersey.media.multipart.FormDataBodyPart;
import org.glassfish.jersey.media.multipart.FormDataContentDisposition;

/**
 *
 * @author rafael-cayax
 */
public class AnuncioDTO {
   private String fecha;
   private String token;
   private String texto;
   private String tipo;
   private String vigencia;
   private String video;
   private FormDataBodyPart info;
   private InputStream imagen;
   private FormDataContentDisposition file2;

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getTexto() {
        return texto;
    }

    public void setTexto(String texto) {
        this.texto = texto;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public String getVigencia() {
        return vigencia;
    }

    public void setVigencia(String vigencia) {
        this.vigencia = vigencia;
    }

    public FormDataBodyPart getInfo() {
        return info;
    }

    public void setInfo(FormDataBodyPart info) {
        this.info = info;
    }

    public FormDataContentDisposition getFile2() {
        return file2;
    }

    public void setFile2(FormDataContentDisposition file2) {
        this.file2 = file2;
    }

    public InputStream getImagen() {
        return imagen;
    }

    public void setImagen(InputStream inputStream) {
        this.imagen = inputStream;
    }

    /**
     * metodo que sirve para extraer el nombre del anuncio del token jwt
     * @return el nombre del usuario en String
     * @throws DatosUsuarioException si el token es nulo
     */
    public String getNombreUsuario() throws DatosUsuarioException {
        TokenJWT t = new TokenJWT();
        return t.obtenerNombreUsuario(this.token);
    }

    public String getVideo() {
        return video;
    }

    public void setVideo(String video) {
        this.video = video;
    }
   
}
