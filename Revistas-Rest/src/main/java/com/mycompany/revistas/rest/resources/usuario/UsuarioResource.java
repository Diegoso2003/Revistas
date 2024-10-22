/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.revistas.rest.resources.usuario;

import com.mycompany.revistas.rest.backend.dtos.Cartera;
import com.mycompany.revistas.rest.backend.excepciones.DatosUsuarioException;
import com.mycompany.revistas.rest.backend.jwt_token.TokenDTO;
import com.mycompany.revistas.rest.backend.usuario.LoginUsuario;
import com.mycompany.revistas.rest.backend.usuario.RegistroUsuario;
import com.mycompany.revistas.rest.backend.usuario.SaldoCartera;
import com.mycompany.revistas.rest.backend.usuario.Usuario;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.PUT;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.Context;
import jakarta.ws.rs.core.HttpHeaders;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

/**
 *
 * @author rafael-cayax
 */
@Path("usuarios")
public class UsuarioResource {
    
    @Path("/registro")
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public Response crearUsuario(Usuario usuario){
        try {
            RegistroUsuario registro = new RegistroUsuario(usuario);
            registro.registrarUsuario();
            return Response.status(Response.Status.CREATED)
                    .build();
        } catch (DatosUsuarioException ex) {
            return Response.status(Response.Status.NOT_ACCEPTABLE)
                           .entity("{\"mensaje\":\""+ex.getMessage()+"\"}")
                           .build();
        }
    }
   
    @Path("/login")
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response iniciarSesion(Usuario usuario){
        try {
            LoginUsuario user = new LoginUsuario(usuario);
            TokenDTO token = user.iniciarSesion();
            return Response.ok(token).build();
        } catch (DatosUsuarioException ex) {
            return Response.status(Response.Status.NOT_ACCEPTABLE)
                           .entity("{\"mensaje\":\""+ex.getMessage()+"\"}")
                           .build();
        }
    }
    
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response obtenerSaldo(@Context HttpHeaders header){
        try {
            String token = header.getHeaderString(HttpHeaders.AUTHORIZATION);
            SaldoCartera cartera = new SaldoCartera(token);
            Usuario usuario = cartera.obtenerDatos();
            return Response.ok(usuario.getCartera()).build();
        } catch (DatosUsuarioException ex) {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
    }
    
    @PUT
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response recargarSaldo(@Context HttpHeaders header, Cartera cartera){
        try {
            String token = header.getHeaderString(HttpHeaders.AUTHORIZATION);
            SaldoCartera c = new SaldoCartera(token);
            double total = c.recargarCartera(cartera);
            return Response.ok(total).build();
        } catch (DatosUsuarioException ex) {
            return Response.status(Response.Status.NOT_ACCEPTABLE)
                           .entity("{\"mensaje\":\""+ex.getMessage()+"\"}")
                           .build();
        }
    }
    
}
