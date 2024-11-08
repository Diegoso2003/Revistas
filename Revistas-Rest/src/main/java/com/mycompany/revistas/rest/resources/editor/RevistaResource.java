/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.revistas.rest.resources.editor;

import com.mycompany.revistas.rest.backend.dtos.CompraBloqueo;
import com.mycompany.revistas.rest.backend.excepciones.DatosUsuarioException;
import com.mycompany.revistas.rest.backend.revista.ActualizarBloqueo;
import com.mycompany.revistas.rest.backend.revista.BloqueoRevista;
import com.mycompany.revistas.rest.backend.revista.BloqueosRevista;
import com.mycompany.revistas.rest.backend.revista.Revista;
import com.mycompany.revistas.rest.backend.revista.RevistaLista;
import com.mycompany.revistas.rest.backend.revista.SubirRevista;
import com.mycompany.revistas.rest.backend.revista.crud.RevistasRead;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.PUT;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.Context;
import jakarta.ws.rs.core.HttpHeaders;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
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
@Path("revistas")
public class RevistaResource {
    
    @Path("/datosRevista")
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response subirRevista(@Context HttpHeaders header, Revista revista){
        try {
            String token = header.getHeaderString(HttpHeaders.AUTHORIZATION);
            SubirRevista r = new SubirRevista(revista);
            revista = r.subirDatosRevista(token);
            return Response.ok(revista).build();
        } catch (DatosUsuarioException ex) {
            return Response.status(Response.Status.BAD_REQUEST)
                           .entity("{\"mensaje\":\""+ex.getMessage()+"\"}")
                           .build();
        }
    }
    
    @Path("/categorias")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response conseguirCategorias(){
        try {
            RevistasRead revistas = new RevistasRead();
            List<String> categorias = revistas.conseguirCategoria();
            return Response.ok(categorias).build();
        } catch (DatosUsuarioException ex) {
            return Response.status(Response.Status.BAD_REQUEST)
                           .entity("{\"mensaje\":\""+ex.getMessage()+"\"}")
                           .build();
        }
    }
    
    @Path("/pdfrevista")
    @POST
    @Consumes(MediaType.MULTIPART_FORM_DATA)
    public Response subirPdfRevista(
            @Context HttpHeaders header,
            @FormDataParam("fileObject") FormDataBodyPart bodyPart,
            @FormDataParam("fileObject") InputStream uploadedInputStream,
            @FormDataParam("fileObject") FormDataContentDisposition fileDetails,
            @FormDataParam("idRevista") String id
    ){
        try {
            String token = header.getHeaderString(HttpHeaders.AUTHORIZATION);
            SubirRevista s = new SubirRevista(token, id);
            s.subirPDF(uploadedInputStream, fileDetails);
            return Response.ok().build();
        } catch (DatosUsuarioException ex) {
            return Response.status(Response.Status.NOT_ACCEPTABLE)
                           .entity("{\"mensaje\":\""+ex.getMessage()+"\"}")
                           .type(MediaType.APPLICATION_JSON)
                           .build();
        }
    }
    
    @Path("/nuevoNumero")
    @POST
    @Consumes(MediaType.MULTIPART_FORM_DATA)
    public Response subirNuevoNumero(
            @Context HttpHeaders header,
            @FormDataParam("fileObject") FormDataBodyPart bodyPart,
            @FormDataParam("fileObject") InputStream uploadedInputStream,
            @FormDataParam("fileObject") FormDataContentDisposition fileDetails,
            @FormDataParam("idRevista") String id
    ){
        try {
            String token = header.getHeaderString(HttpHeaders.AUTHORIZATION);
            SubirRevista s = new SubirRevista(token, id);
            s.nuevoNumero(uploadedInputStream, fileDetails);
            return Response.ok().build();
        } catch (DatosUsuarioException ex) {
            return Response.status(Response.Status.NOT_ACCEPTABLE)
                           .entity("{\"mensaje\":\""+ex.getMessage()+"\"}")
                           .type(MediaType.APPLICATION_JSON)
                           .build();
        }
    }
    
    @Path("/conseguirRevistas")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response conseguirRevistas(@Context HttpHeaders header){
        RevistaLista lista = new RevistaLista();
        String token = header.getHeaderString(HttpHeaders.AUTHORIZATION);
        List<Revista> revistas = lista.conseguirRevistas(token);
        return Response.ok(revistas).build();
    }
    
    @Path("/conseguir/{id}")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response conseguirRevistaPorID(@PathParam("id") int id){
        RevistasRead r = new RevistasRead();
        Revista revista = r.conseguirRevistaPorID(id);
        return Response.ok(revista).build();
    }
    
    @Path("/bloqueos")
    @PUT
    @Consumes(MediaType.APPLICATION_JSON)
    public Response actualizarBloqueos(@Context HttpHeaders header, BloqueosRevista bloqueos){
        try {
            String token = header.getHeaderString(HttpHeaders.AUTHORIZATION);
            ActualizarBloqueo a = new ActualizarBloqueo(bloqueos);
            a.actualizarBloqueo(token);
            return Response.ok().build();
        } catch (DatosUsuarioException ex) {
            return Response.status(Response.Status.NOT_ACCEPTABLE)
                           .entity("{\"mensaje\":\""+ex.getMessage()+"\"}")
                           .type(MediaType.APPLICATION_JSON)
                           .build();
        }
    }
    
    @Path("/compraBloqueo")
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public Response comprarBloqueo(@Context HttpHeaders header, CompraBloqueo bloqueo){
        try {
            BloqueoRevista r = new BloqueoRevista(bloqueo);
            String token = header.getHeaderString(HttpHeaders.AUTHORIZATION);
            r.comprarBloqueo(token);
            return Response.ok().build();
        } catch (DatosUsuarioException ex) {
            return Response.status(Response.Status.NOT_ACCEPTABLE)
                           .entity("{\"mensaje\":\""+ex.getMessage()+"\"}")
                           .type(MediaType.APPLICATION_JSON)
                           .build();
        }
    }
}
