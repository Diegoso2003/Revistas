/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.revistas.rest.backend.dtos;

/**
 *
 * @author rafael-cayax
 */
public class Cartera {
    private Double cartera;

    public Double getCartera() {
        return cartera;
    }

    public void setCartera(Double cartera) {
        this.cartera = cartera;
    }
    
    public boolean esValida(){
        return cartera!= null && cartera > 0;
    }
    
}
