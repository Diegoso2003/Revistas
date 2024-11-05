/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.revistas.rest.resources.administrador;

import com.mycompany.revistas.rest.backend.anuncio.PreciosDTO;
import com.mycompany.revistas.rest.backend.anuncio.precios.ActualizarPrecios;
import com.mycompany.revistas.rest.backend.excepciones.DatosUsuarioException;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.PUT;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

/**
 *
 * @author rafael-cayax
 */
@Path("admin")
public class AdministradorResource {
   
    @Path("/anuncios")
    @PUT
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response actuzlizarAnuncio(PreciosDTO precios){
        try {
            ActualizarPrecios actu = new ActualizarPrecios(precios);
            PreciosDTO preciosNuevos = actu.actualizar() ;
            return Response.ok(preciosNuevos).build();
        } catch (DatosUsuarioException ex) {
            return Response.status(Response.Status.NOT_ACCEPTABLE)
                           .entity("{\"mensaje\":\""+ex.getMessage()+"\"}")
                           .type(MediaType.APPLICATION_JSON)
                           .build();
        }
    }
}
