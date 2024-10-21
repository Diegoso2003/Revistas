/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.revistas.rest.backend.anuncio;

import com.mycompany.revistas.rest.backend.anuncio.crud.AnuncioDB;
import com.mycompany.revistas.rest.backend.excepciones.DatosInvalidosException;
import com.mycompany.revistas.rest.backend.excepciones.DatosUsuarioException;
import com.mycompany.revistas.rest.backend.usuario.TipoRol;
import com.mycompany.revistas.rest.backend.usuario.Usuario;
import com.mycompany.revistas.rest.backend.usuario.querys.UsuarioDB;
import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.Optional;

/**
 *
 * @author rafael-cayax
 */
public class ValidadorAnuncio {
    private Usuario usuario;
    private AnuncioDTO datosAnuncio;
    private Anuncio anuncio;

    public ValidadorAnuncio(AnuncioDTO anuncio) {
        this.datosAnuncio = anuncio;
    }

    public void subirAnuncio() throws DatosUsuarioException {
        extraerDatosUsuario();
        validarPermisosDelUsuario();
        validarAnuncio();
        calcularPrecioAnuncio();
        cargarAnuncio();
    }

    private void extraerDatosUsuario() throws DatosUsuarioException {
        try {
            String nombre = datosAnuncio.getNombreUsuario();
            UsuarioDB info = new UsuarioDB();
            Optional<Usuario> datos = info.obtenerDatosUsuario(nombre);
            this.usuario = datos.orElseThrow(() -> new DatosUsuarioException("Usuario no reconocido intente mas tarde"));
        } catch (DatosInvalidosException ex) {
            throw new DatosUsuarioException("Usuario no reconocido intente mas tarde");
        }
    }

    private void validarPermisosDelUsuario() throws DatosUsuarioException {
        TipoRol rol = usuario.getRol();
        if (rol != TipoRol.ANUNCIADOR) {
            throw new DatosUsuarioException("EL usuario no tiene los permisos necesarios");
        }
    }

    private void validarAnuncio() throws DatosUsuarioException {
        anuncio = new Anuncio();
        try {
            anuncio.setNombreUsuario(usuario.getNombre());
            anuncio.setFecha(LocalDate.parse(datosAnuncio.getFecha()));
            anuncio.setTipo(TipoAnuncio.valueOf(datosAnuncio.getTipo()));
            anuncio.setVigencia(Vigencia.valueOf(datosAnuncio.getVigencia()));
            anuncio.setUrlVideo(datosAnuncio.getVideo());
            anuncio.setTextoAnuncio(datosAnuncio.getTexto());
            anuncio.setExtension(datosAnuncio.getInfo().getMediaType().toString());
            anuncio.setImagen(datosAnuncio.getImagen());
            if (!anuncio.esAnuncioValido()) {
                throw new DatosUsuarioException("porfavor ingrese los datos correctamente");
            }
        } catch (DateTimeParseException  | NullPointerException | IllegalArgumentException e) {
            throw new DatosUsuarioException("Por favor ingrese los datos correctamente");
        }
    }

    private void calcularPrecioAnuncio() throws DatosUsuarioException {
        double cartera = usuario.getCartera();
        AnuncioDB precio = new AnuncioDB();
        if (!anuncio.esComprable(cartera, precio.obtenerPrecios())) {
            throw new DatosUsuarioException("insuficiente saldo en la cartera, ssldo actual: " + cartera);
        }
    }

    private void cargarAnuncio() {
        
    }
    
    
}
