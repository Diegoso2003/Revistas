/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.revistas.rest.resources.anuncio;

import com.mycompany.revistas.rest.backend.anuncio.ActualizarAnuncio;
import com.mycompany.revistas.rest.backend.anuncio.Anuncio;
import com.mycompany.revistas.rest.backend.anuncio.AnuncioDTO;
import com.mycompany.revistas.rest.backend.anuncio.AnunciosList;
import com.mycompany.revistas.rest.backend.anuncio.PreciosDTO;
import com.mycompany.revistas.rest.backend.anuncio.ValidadorAnuncio;
import com.mycompany.revistas.rest.backend.anuncio.crud.AnuncioRead;
import com.mycompany.revistas.rest.backend.anuncio.crud.AnuncioUpdate;
import com.mycompany.revistas.rest.backend.excepciones.DatosUsuarioException;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.DELETE;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.PATCH;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.PUT;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.QueryParam;
import jakarta.ws.rs.WebApplicationException;
import jakarta.ws.rs.core.Context;
import jakarta.ws.rs.core.HttpHeaders;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.core.StreamingOutput;
import java.io.InputStream;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
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
            System.out.println(ex);
            return Response.status(Response.Status.BAD_REQUEST)
                           .entity("{\"mensaje\":\""+ex.getMessage()+"\"}")
                           .build();
        }
    }
    
    @Path("/precios")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response conseguirPrecios(){
        try {
            AnuncioRead anuncio = new AnuncioRead();
            PreciosDTO precios = anuncio.obtenerPrecios();
            return Response.ok(precios).build();
        } catch (DatosUsuarioException ex) {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
    }
    
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response conseguirAnuncios(@Context HttpHeaders header){
        try {
            String token = header.getHeaderString(HttpHeaders.AUTHORIZATION);
            AnunciosList anuncios = new AnunciosList();
            List<Anuncio> listado = anuncios.conseguirAnuncios(token);
            return Response.ok(listado).build();
        } catch (DatosUsuarioException ex) {
            return Response.status(Response.Status.NOT_ACCEPTABLE)
                           .entity("{\"mensaje\":\""+ex.getMessage()+"\"}")
                           .build();
        }
    }
    
    @Path("{id}")
    @DELETE
    public Response desactivarAnuncio(@PathParam("id") int id){
        try {
            AnuncioUpdate a = new AnuncioUpdate();
            a.desactivarAnuncio(id);
            return Response.ok().build();
        } catch (DatosUsuarioException ex) {
            return Response.status(Response.Status.NOT_ACCEPTABLE)
                           .entity("{\"mensaje\":\""+ex.getMessage()+"\"}")
                           .build();
        }
    }
    
    @Path("/imagen/{id}")
    @GET
    public Response conseguirImagen(@PathParam("id") int id){
        AnuncioRead anuncio = new AnuncioRead();
        InputStream stream = anuncio.conseguirImagen(id);
        StreamingOutput fileStream = (java.io.OutputStream output) -> {
            try {
                byte[] data = stream.readAllBytes();
                output.write(data);
                output.flush();
            } catch (Exception e) {
                throw new WebApplicationException("File Not Found !!");
            }
        };
        return Response.ok(fileStream, MediaType.APPLICATION_OCTET_STREAM).build();
    }
    
    @Path("/video")
    @PUT
    @Consumes(MediaType.APPLICATION_JSON)
    public Response actualizarLinkVideo(@Context HttpHeaders header,Anuncio anuncio){
        try {
            String token = header.getHeaderString(HttpHeaders.AUTHORIZATION);
            ActualizarAnuncio a = new ActualizarAnuncio(token, anuncio);
            a.actualizarVideo();
            return Response.ok().build();
        } catch (DatosUsuarioException ex) {
            return Response.status(Response.Status.NOT_ACCEPTABLE)
                           .entity("{\"mensaje\":\""+ex.getMessage()+"\"}")
                           .build();
        }
    }
    
    @Path("/imagen")
    @PUT
    @Consumes(MediaType.MULTIPART_FORM_DATA)
    public Response actualizarImagen(@Context HttpHeaders header,
            @FormDataParam("fileObject") FormDataBodyPart bodyPart,
            @FormDataParam("fileObject") InputStream uploadedInputStream,
            @FormDataParam("fileObject") FormDataContentDisposition fileDetails,
            @FormDataParam("id") int id){
        try {
            String token = header.getHeaderString(HttpHeaders.AUTHORIZATION);
            ActualizarAnuncio a = new ActualizarAnuncio(token, uploadedInputStream, id);
            a.actualizarImagen();
            return Response.ok().build();
        } catch (DatosUsuarioException ex) {
            return Response.status(Response.Status.NOT_ACCEPTABLE)
                           .entity("{\"mensaje\":\""+ex.getMessage()+"\"}")
                           .build();
        }
    }
    
    @Path("/texto")
    @PUT
    @Consumes(MediaType.APPLICATION_JSON)
    public Response actualizarTexto(@Context HttpHeaders header, Anuncio anuncio){
        try {
            String token = header.getHeaderString(HttpHeaders.AUTHORIZATION);
            ActualizarAnuncio a = new ActualizarAnuncio(token, anuncio);
            a.actualizarTexto();
            return Response.ok().build();
        } catch (DatosUsuarioException ex) {
            return Response.status(Response.Status.NOT_ACCEPTABLE)
                           .entity("{\"mensaje\":\""+ex.getMessage()+"\"}")
                           .build();
        }
    }
    
}
