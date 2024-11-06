/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.revistas.rest.resources.editor;

import com.mycompany.revistas.rest.backend.excepciones.DatosUsuarioException;
import com.mycompany.revistas.rest.backend.revista.Revista;
import com.mycompany.revistas.rest.backend.revista.SubirRevista;
import com.mycompany.revistas.rest.backend.revista.crud.RevistasRead;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.Context;
import jakarta.ws.rs.core.HttpHeaders;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import java.io.InputStream;
import java.util.List;
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
}
