/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.revistas.rest.backend.revista;

import com.mycompany.revistas.rest.backend.excepciones.DatosUsuarioException;
import com.mycompany.revistas.rest.backend.jwt_token.TokenJWT;
import com.mycompany.revistas.rest.backend.revista.crud.RevistasRead;
import com.mycompany.revistas.rest.backend.usuario.Usuario;
import java.util.LinkedList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author rafael-cayax
 */
public class RevistaLista {
    
    private List<Revista> revistas;
    private Usuario usuario;
    private boolean admin;
    private String statement;
    
    public List<Revista> conseguirRevistas(String token){
        try {
            admin = false;
            validarUsuario(token);
            listarRevistas();
            return revistas;
        } catch (DatosUsuarioException ex) {
            return new LinkedList<>();
        }
    }

    private void validarUsuario(String token) throws DatosUsuarioException {
        TokenJWT t = new TokenJWT();
        usuario = t.obtenerUsuario(token);
        switch(usuario.getRol()){
            case ADMINISTRADOR:
                statement = "select * from revista";
                admin = true;
                break;
            case EDITOR:
                statement = "select * from revista where nombre_editor = ?";
                break;
            default:
                throw new DatosUsuarioException("no tiene permisos");
        }
    }

    private void listarRevistas() throws DatosUsuarioException {
        RevistasRead datos = new RevistasRead();
        this.revistas = datos.obtenerRevistas(statement, admin, usuario.getNombre());
    }
}
