/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.revistas.rest.backend.anuncio;

import java.io.InputStream;
import java.time.LocalDate;

/**
 *
 * @author rafael-cayax
 */
public class Anuncio {

    private int ID;
    private double precio;
    private String nombreUsuario;
    private String urlVideo;
    private String textoAnuncio;
    private LocalDate fecha;
    private TipoAnuncio tipo;
    private Vigencia vigencia;
    private String extension;
    private InputStream imagen;

    /**
     * evalua si el texto del anuncio es diferente a null o si tiene caracteres
     * o si le falta algun dato adicional
     *
     * @return
     */
    private boolean esAnuncioTextoValido() {
        return textoAnuncio != null && !textoAnuncio.isBlank();
    }

    private boolean esAnuncioTextoImagenValido() {
        return esAnuncioTextoValido() && imagen != null && extension != null;
    }

    private boolean esAnuncioVideoValido() {
        return urlVideo != null && !urlVideo.isBlank();
    }

    public boolean esAnuncioValido() {
        if (tipo == null) {
            return false;
        }
        switch (tipo) {
            case TEXTO:
                return esAnuncioTextoValido();

            case TEXTO_E_IMAGEN:
                return esAnuncioTextoImagenValido();

            default:
                return esAnuncioVideoValido();
        }
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

    public String getExtension() {
        return extension;
    }

    public void setExtension(String estension) {
        this.extension = estension;
    }

    public InputStream getImagen() {
        return imagen;
    }

    public void setImagen(InputStream imagen) {
        this.imagen = imagen;
    }

    public double getPrecio() {
        return precio;
    }

    public void setPrecio(double precio) {
        this.precio = precio;
    }
    
    boolean esComprable(double cartera, PreciosDTO precios) {
        double total = 0;
        switch (tipo) {
            case TEXTO:
                total += precios.getTexto();
                break;
            case TEXTO_E_IMAGEN:
                total += precios.getImagen();
                break;
            case VIDEO:
                total += precios.getVideo();
        }
        switch (vigencia) {
            case DIA_1:
                total += precios.getDia1();
                break;
            case DIA_3:
                total += precios.getDia3();
                break;
            case SEMANA_1:
                total += precios.getSemana1();
                break;
            case SEMANA_2:
                total += precios.getSemana2();
        }
        this.precio = total;
        return cartera >= total;
    }

    public String getInfoAnuncio() {
        if(tipo == TipoAnuncio.TEXTO){
            return this.textoAnuncio;
        } else {
            return this.urlVideo;
        }
    }
}
