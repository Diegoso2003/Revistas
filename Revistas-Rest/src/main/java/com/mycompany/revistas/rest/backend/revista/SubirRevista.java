/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.revistas.rest.backend.revista;

import com.mycompany.revistas.rest.backend.excepciones.DatosUsuarioException;
import com.mycompany.revistas.rest.backend.jwt_token.TokenJWT;
import com.mycompany.revistas.rest.backend.revista.crud.RevistaCreate;
import com.mycompany.revistas.rest.backend.revista.crud.RevistaDelete;
import com.mycompany.revistas.rest.backend.usuario.Usuario;
import java.io.InputStream;
import org.glassfish.jersey.media.multipart.FormDataContentDisposition;

/**
 *
 * @author rafael-cayax
 */
public class SubirRevista {
    private Revista revista;
    private Usuario usuario;
    private String token;

    public SubirRevista(Revista revista) {
        this.revista = revista;
    }
    
    public SubirRevista(String token, String id){
        this.token = token;
        revista = new Revista();
        revista.setID(Integer.parseInt(id));
    }
    
    public Revista subirDatosRevista(String token) throws DatosUsuarioException{
        TokenJWT j = new TokenJWT();
        usuario = j.obtenerUsuario(token);
        validarRevista();
        return revista;
    }

    private void validarRevista() throws DatosUsuarioException {
        if (!revista.esRevistaValida()) {
            throw new DatosUsuarioException("Ingrese correctamente los datos de la revista");
        }
        revista.extraerEtiquetas();
        if (revista.getEtiquetas().isEmpty()) {
            throw new DatosUsuarioException("debe ingresar etiquetas validas");
        }
        RevistaCreate r = new RevistaCreate();
        r.subirRevista(revista, usuario);
    }
    
    public void subirPDF(InputStream archivo, FormDataContentDisposition fileDetails) throws DatosUsuarioException{
        TokenJWT j = new TokenJWT();
        usuario = j.obtenerUsuario(token);
        try {
            validarPDF(archivo, fileDetails);
            String nombre = fileDetails.getFileName();
            revista.setNombre(nombre);
            cargarPDF(archivo);
        } catch (Exception e) {
            RevistaDelete r = new RevistaDelete();
            r.borrarRevista(revista);
            throw new DatosUsuarioException(e.getMessage());
        }
    }

    private void validarPDF(InputStream archivo, FormDataContentDisposition fileDetails) throws DatosUsuarioException {
        if (archivo == null || fileDetails == null) {
            throw new DatosUsuarioException("ingrese correctamente los datos de la revista");
        }
        
    }

    private void cargarPDF(InputStream archivo) throws DatosUsuarioException {
        RevistaCreate c = new RevistaCreate();
        c.subirPDF(revista, usuario, archivo);
    }
}
