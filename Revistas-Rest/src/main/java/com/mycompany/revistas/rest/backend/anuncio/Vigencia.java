/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Enum.java to edit this template
 */
package com.mycompany.revistas.rest.backend.anuncio;

/**
 *
 * @author rafael-cayax
 */
public enum Vigencia {
    DIA_1(1),
    DIA_3(3),
    SEMANA_1(7),
    SEMANA_2(14);
    
    private final int dias;

    private Vigencia(int dias) {
        this.dias = dias;
    }
    
    public int dias(){
        return dias;
    }
}
