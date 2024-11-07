/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.revistas.rest.backend.revista;

/**
 *
 * @author rafael-cayax
 */
public class Pdf {
    private int idPdf;
    private int idRevistaPrincipal;
    private String nombre;

    public int getIdPdf() {
        return idPdf;
    }

    public void setIdPdf(int idPdf) {
        this.idPdf = idPdf;
    }

    public int getIdRevistaPrincipal() {
        return idRevistaPrincipal;
    }

    public void setIdRevistaPrincipal(int idRevistaPrincipal) {
        this.idRevistaPrincipal = idRevistaPrincipal;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
    
    
}
