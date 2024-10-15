/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.revistas.rest.backend.anuncio;

import java.time.LocalDate;

/**
 *
 * @author rafael-cayax
 */
public class Anuncio {
    private int ID;
    private String nombreUsuario;
    private String urlVideo;
    private String textoAnuncio;
    private LocalDate fecha;
    private TipoAnuncio tipo;
    private Vigencia vigencia;
    private String estension;
    private String imagen;

    /**
     * evalua si el texto del anuncio es diferente a null o si tiene caracteres 
     * o si le falta algun dato adicional
     * @return 
     */
    private boolean esAnuncioTextoValido(){
        return textoAnuncio != null && textoAnuncio.length() != 0;
    }

    private boolean esAnuncioTextoImagenValido(){
        return esAnuncioTextoValido() && imagen != null;
    }
    
    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public String getNombreUsuario() {
        return nombreUsuario;
    }

    public void setNombreUsuario(String nombreUsuario) {
        this.nombreUsuario = nombreUsuario;
    }

    public String getUrlVideo() {
        return urlVideo;
    }

    public void setUrlVideo(String urlVideo) {
        this.urlVideo = urlVideo;
    }

    public String getTextoAnuncio() {
        return textoAnuncio;
    }

    public void setTextoAnuncio(String textoAnuncio) {
        this.textoAnuncio = textoAnuncio;
    }

    public LocalDate getFecha() {
        return fecha;
    }

    public void setFecha(LocalDate fecha) {
        this.fecha = fecha;
    }

    public TipoAnuncio getTipo() {
        return tipo;
    }

    public void setTipo(TipoAnuncio tipo) {
        this.tipo = tipo;
    }

    public Vigencia getVigencia() {
        return vigencia;
    }

    public void setVigencia(Vigencia vigencia) {
        this.vigencia = vigencia;
    }

    public String getEstension() {
        return estension;
    }

    public void setEstension(String estension) {
        this.estension = estension;
    }
    
}
