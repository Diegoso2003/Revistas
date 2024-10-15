/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.revistas.rest.resources;

import com.mycompany.revistas.rest.backend.excepciones.DatosUsuarioException;
import com.mycompany.revistas.rest.backend.jwt_token.TokenDTO;
import com.mycompany.revistas.rest.backend.usuario.LoginUsuario;
import com.mycompany.revistas.rest.backend.usuario.RegistroUsuario;
import com.mycompany.revistas.rest.backend.usuario.Usuario;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

/**
 *
 * @author rafael-cayax
 */
@Path("usuarios")
public class UsuarioResource {
    
    @Path("registro")
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
    
    @Path("login")
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response login(Usuario usuario){
        try {
            LoginUsuario log = new LoginUsuario(usuario);
            TokenDTO tokens = log.iniciarSesion();
            return Response.ok(tokens).build();
        } catch (DatosUsuarioException ex) {
            return Response.status(Response.Status.NOT_ACCEPTABLE)
                           .entity("{\"mensaje\":\""+ex.getMessage()+"\"}")
                           .build();
        }
    }
}
