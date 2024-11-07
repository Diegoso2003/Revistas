/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.revistas.rest.backend.revista;

import com.mycompany.revistas.rest.backend.excepciones.DatosUsuarioException;
import com.mycompany.revistas.rest.backend.jwt_token.TokenJWT;
import com.mycompany.revistas.rest.backend.revista.crud.RevistasRead;
import com.mycompany.revistas.rest.backend.usuario.Usuario;
import java.util.List;

/**
 *
 * @author rafael-cayax
 */
public class RevistaLista {
    
    private List<Revista> revistas;
    private Usuario usuario;
    private boolean admin;
    private String statement;
    
    public List<Revista> conseguirRevistas(String token) throws DatosUsuarioException{
        admin = false;
        validarUsuario(token);
        listarAnuncios();
        return revistas;
    }

    private void validarUsuario(String token) throws DatosUsuarioException {
        TokenJWT t = new TokenJWT();
        usuario = t.obtenerUsuario(token);
        switch(usuario.getRol()){
            case ADMINISTRADOR:
                statement = "select * from revista";
                admin = true;
                break;
            case ANUNCIADOR:
                statement = "select * from revista nombre_editor = ?";
                break;
            default:
                throw new DatosUsuarioException("no tiene permisos");
        }
    }

    private void listarAnuncios() throws DatosUsuarioException {
        RevistasRead datos = new RevistasRead();
        this.revistas = datos.obtenerRevistas(statement, admin, usuario.getNombre());
    }
}
