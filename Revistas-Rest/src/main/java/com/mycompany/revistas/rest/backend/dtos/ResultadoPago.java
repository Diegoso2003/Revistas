/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.revistas.rest.backend.dtos;

import java.time.LocalDate;

/**
 *
 * @author rafael-cayax
 */
public class ResultadoPago {
    private LocalDate fechaPago;
    private Integer idRevista;
    private Integer dias;
    private Double pago;

    public LocalDate getFechaPago2() {
        return fechaPago;
    }
    
    public String getFechaPago(){
        return fechaPago.toString();
    }

    public void setFechaPago(LocalDate fechaPago) {
        this.fechaPago = fechaPago;
    }

    public Integer getIdRevista() {
        return idRevista;
    }

    public void setIdRevista(Integer idRevista) {
        this.idRevista = idRevista;
    }

    public Integer getDias() {
        return dias;
    }

    public void setDias(Integer dias) {
        this.dias = dias;
    }

    public Double getPago() {
        return pago;
    }

    public void setPago(Double pago) {
        this.pago = pago;
    }
    
    
}
