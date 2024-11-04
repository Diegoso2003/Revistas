/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.revistas.rest.backend.anuncio;

import com.mycompany.revistas.rest.backend.anuncio.crud.AnuncioRead;
import com.mycompany.revistas.rest.backend.excepciones.DatosUsuarioException;
import com.mycompany.revistas.rest.backend.jwt_token.TokenJWT;
import com.mycompany.revistas.rest.backend.usuario.Usuario;
import java.util.List;

/**
 *
 * @author rafael-cayax
 */
public class AnunciosList {
    private List<Anuncio> anuncios;
    private String statement;
    private boolean admin;
    private Usuario usuario;
    
    public List<Anuncio> conseguirAnuncios(String token) throws DatosUsuarioException{
        admin = false;
        validarUsuario(token);
        listarAnuncios();
        return anuncios;
    }

    private void validarUsuario(String token) throws DatosUsuarioException {
        TokenJWT t = new TokenJWT();
        usuario = t.obtenerUsuario(token);
        switch(usuario.getRol()){
            case ADMINISTRADOR:
                statement = "select * from anuncio where estado = ?";
                admin = true;
                break;
            case ANUNCIADOR:
                statement = "select * from anuncio where estado = ? and nombre_usuario = ?";
                break;
            default:
                throw new DatosUsuarioException("no tiene permisos");
        }
    }

    private void listarAnuncios() throws DatosUsuarioException {
        AnuncioRead datos = new AnuncioRead();
        this.anuncios = datos.obtenerAnuncios(statement, admin, usuario.getNombre());
    }
    
    public Anuncio conseguirAnuncioPorID(String token, int anuncio) throws DatosUsuarioException{
        TokenJWT t = new TokenJWT();
        Usuario usuario = t.obtenerUsuario(token);
        AnuncioRead a = new AnuncioRead();
        return a.conseguirAnuncioPorID(anuncio, usuario);
    }
}
