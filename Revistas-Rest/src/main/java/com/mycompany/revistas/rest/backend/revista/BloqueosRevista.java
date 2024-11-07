/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.revistas.rest.backend.revista;

/**
 *
 * @author rafael-cayax
 */
public class BloqueosRevista {
    private int idRevista;
    private boolean bloqueoSuscripcion;
    private boolean bloqueoComentarios;

    public int getIdRevista() {
        return idRevista;
    }

    public void setIdRevista(int idRevista) {
        this.idRevista = idRevista;
    }

    public boolean isBloqueoSuscripcion() {
        return bloqueoSuscripcion;
    }

    public void setBloqueoSuscripcion(boolean bloqueoSuscripcion) {
        this.bloqueoSuscripcion = bloqueoSuscripcion;
    }

    public boolean isBloqueoComentarios() {
        return bloqueoComentarios;
    }

    public void setBloqueoComentarios(boolean bloqueoComentarios) {
        this.bloqueoComentarios = bloqueoComentarios;
    }
    
}
