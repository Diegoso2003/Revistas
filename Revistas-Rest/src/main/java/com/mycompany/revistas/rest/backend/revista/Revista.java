/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.revistas.rest.backend.revista;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateDeserializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateSerializer;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 *
 * @author rafael-cayax
 */
public class Revista {
    @JsonFormat(pattern = "yyyy-MM-dd")
    @JsonSerialize(using = LocalDateSerializer.class)
    @JsonDeserialize(using = LocalDateDeserializer.class)
    private LocalDate fecha;
    private int ID;
    private String nombre;
    private String nombreEditor;
    private String nombreCategoria;
    private String descripcion;
    private String etiquetasUnidas;
    private boolean bloqueoComentario;
    private boolean bloqueoAnuncios;
    private boolean bloqueoSuscripcion;
    private double precio;
    private double precioBloqueo;
    private int numeroLikes;
    private List<Pdf> pdfs;
    private Set<String> etiquetas;

    public LocalDate getFecha() {
        return fecha;
    }

    public void setFecha(LocalDate fecha) {
        this.fecha = fecha;
    }

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getNombreEditor() {
        return nombreEditor;
    }

    public void setNombreEditor(String nombreEditor) {
        this.nombreEditor = nombreEditor;
    }

    public String getNombreCategoria() {
        return nombreCategoria;
    }

    public void setNombreCategoria(String nombreCategoria) {
        this.nombreCategoria = nombreCategoria;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public boolean isBloqueoComentario() {
        return bloqueoComentario;
    }

    public void setBloqueoComentario(boolean bloqueoComentario) {
        this.bloqueoComentario = bloqueoComentario;
    }

    public boolean isBloqueoAnuncios() {
        return bloqueoAnuncios;
    }

    public void setBloqueoAnuncios(boolean bloqueoAnuncios) {
        this.bloqueoAnuncios = bloqueoAnuncios;
    }

    public boolean isBloqueoSuscripcion() {
        return bloqueoSuscripcion;
    }

    public void setBloqueoSuscripcion(boolean bloqueoSuscripcion) {
        this.bloqueoSuscripcion = bloqueoSuscripcion;
    }

    public double getPrecio() {
        return precio;
    }

    public void setPrecio(double precio) {
        this.precio = precio;
    }

    public double getPrecioBloqueo() {
        return precioBloqueo;
    }

    public void setPrecioBloqueo(double precioBloqueo) {
        this.precioBloqueo = precioBloqueo;
    }

    public int getNumeroLikes() {
        return numeroLikes;
    }

    public void setNumeroLikes(int numeroLikes) {
        this.numeroLikes = numeroLikes;
    }

    public List<Pdf> getPdfs() {
        return pdfs;
    }

    public void setPdfs(List<Pdf> pdfs) {
        this.pdfs = pdfs;
    }

    public Set<String> getEtiquetas() {
        return etiquetas;
    }

    public void setEtiquetas(Set<String> etiquetas) {
        this.etiquetas = etiquetas;
    }

    public String getEtiquetasUnidas() {
        return etiquetasUnidas;
    }

    public void setEtiquetasUnidas(String etiquetasUnidas) {
        this.etiquetasUnidas = etiquetasUnidas;
    }
    
    public boolean esRevistaValida(){
        return fecha != null && nombre != null && !nombre.isBlank() && nombreCategoria != null
                && !nombreCategoria.isBlank() && descripcion != null && !descripcion.isBlank()
                && etiquetasUnidas != null && !etiquetasUnidas.isBlank();
    }
    
    public void extraerEtiquetas(){
        String[] e = etiquetasUnidas.split("\\s*,\\s*");
        Set<String> etiquetas = new HashSet<>(); 
        for(String et: e){
            et = et.replaceAll("\\s+", " ");
            et = et.toLowerCase();
            etiquetas.add(et);
        }
        this.etiquetas = etiquetas;
    }
}
