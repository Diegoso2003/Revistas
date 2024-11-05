/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.revistas.rest.backend.anuncio;

/**
 *
 * @author rafael-cayax
 */
public class PreciosDTO {
    private Double texto;
    private Double imagen;
    private Double video;
    private Double dia1;
    private Double dia3;
    private Double semana1;
    private Double semana2;

    public Double getTexto() {
        return texto;
    }

    public void setTexto(Double texto) {
        this.texto = texto;
    }

    public Double getImagen() {
        return imagen;
    }

    public void setImagen(Double imagen) {
        this.imagen = imagen;
    }

    public Double getVideo() {
        return video;
    }

    public void setVideo(Double video) {
        this.video = video;
    }

    public Double getDia1() {
        return dia1;
    }

    public void setDia1(Double dia1) {
        this.dia1 = dia1;
    }

    public Double getDia3() {
        return dia3;
    }

    public void setDia3(Double dia3) {
        this.dia3 = dia3;
    }

    public Double getSemana1() {
        return semana1;
    }

    public void setSemana1(Double semana1) {
        this.semana1 = semana1;
    }

    public Double getSemana2() {
        return semana2;
    }

    public void setSemana2(Double semana2) {
        this.semana2 = semana2;
    }
    
    public boolean actualizar(){
        return texto != null || this.imagen != null || this.video != null || this.semana1 != null || this.semana2 != null ||
                this.dia1 != null || this.dia3 != null;
    }
    
    public boolean sonPreciosValidos(){
        return texto > 0  && imagen > 0 && video > 0 && semana1 > 0 && semana2 > 0 
                && dia1 > 0 && dia3 > 0;
    }

    public void actualizarCambios(PreciosDTO nuevos) {
        if (nuevos.getDia1() != null) {
            this.dia1 = nuevos.dia1;
        }
        if (nuevos.getDia3() != null) {
            this.dia3 = nuevos.dia3;
        }
        if (nuevos.semana1 != null) {
            this.semana1 = nuevos.semana1;
        }
        if (nuevos.semana2 != null) {
            this.semana2 = nuevos.semana2;
        }
        if (nuevos.imagen != null) {
            this.imagen = nuevos.imagen;
        }
        if (nuevos.texto != null) {
            this.texto = nuevos.texto;
        }
        if (nuevos.video != null) {
            this.video = nuevos.video;
        }
    }

}
