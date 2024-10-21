/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.revistas.rest.resources.anuncio;

import com.mycompany.revistas.rest.backend.anuncio.AnuncioDTO;
import com.mycompany.revistas.rest.backend.anuncio.PreciosDTO;
import com.mycompany.revistas.rest.backend.anuncio.ValidadorAnuncio;
import com.mycompany.revistas.rest.backend.anuncio.crud.AnuncioDB;
import com.mycompany.revistas.rest.backend.excepciones.DatosUsuarioException;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.PUT;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.QueryParam;
import jakarta.ws.rs.core.Context;
import jakarta.ws.rs.core.HttpHeaders;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import java.io.InputStream;
import org.glassfish.jersey.media.multipart.FormDataBodyPart;
import org.glassfish.jersey.media.multipart.FormDataContentDisposition;
import org.glassfish.jersey.media.multipart.FormDataParam;

/**
 *
 * @author rafael-cayax
 */
@Path("anuncios")
public class AnunciosResource {
    
    @Path("/subirAnuncio")
    @POST
    @Consumes(MediaType.MULTIPART_FORM_DATA)
    public Response subirAnuncio(
            @Context HttpHeaders header,
            @FormDataParam("fecha") String fecha,
            @FormDataParam("texto") String texto,
            @FormDataParam("video") String videoURl,
            @FormDataParam("tipo") String tipo,
            @FormDataParam("vigencia") String vigencia,
            @FormDataParam("fileObject") FormDataBodyPart bodyPart,
            @FormDataParam("fileObject") InputStream uploadedInputStream,
            @FormDataParam("fileObject") FormDataContentDisposition fileDetails,
            @QueryParam(value = "data") String data){
        try {
            AnuncioDTO a = new AnuncioDTO();
            a.setToken(header.getHeaderString(HttpHeaders.AUTHORIZATION));
            System.out.println(a.getToken());
            a.setFecha(fecha);
            a.setTexto(texto);
            a.setVideo(videoURl);
            a.setTipo(tipo);
            a.setVigencia(vigencia);
            a.setInfo(bodyPart);
            a.setImagen(uploadedInputStream);
            a.setFile2(fileDetails);
            ValidadorAnuncio anuncio = new ValidadorAnuncio(a);
            anuncio.subirAnuncio();
            return Response.ok().build();
        } catch (DatosUsuarioException ex) {
            return Response.status(Response.Status.NOT_ACCEPTABLE)
                           .entity("{\"mensaje\":\""+ex.getMessage()+"\"}")
                           .build();
        }
    }
    
    @Path("/precios")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response conseguirPrecios(){
        try {
            AnuncioDB anuncio = new AnuncioDB();
            PreciosDTO precios = anuncio.obtenerPrecios();
            return Response.ok(precios).build();
        } catch (DatosUsuarioException ex) {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
    }
    
    @Path("/videosytexto")
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public Response subirAnuncioTextoVideo(){
        
        return Response.ok().build();
    }
    
    @Path("/imagen")
    @PUT
    @Consumes(MediaType.MULTIPART_FORM_DATA)
    public Response actualizarArchivo(){
        
        return Response.ok().build();
    }
}
