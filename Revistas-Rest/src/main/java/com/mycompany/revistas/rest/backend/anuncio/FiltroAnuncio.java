/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.revistas.rest.backend.anuncio;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

/**
 *
 * @author rafael-cayax
 */
public class FiltroAnuncio {
    private final Anuncio anuncio;

    public FiltroAnuncio(Anuncio anuncio) {
        this.anuncio = anuncio;
    }
    
    public boolean esVigente(){
        LocalDate fecha = anuncio.getFecha();
        LocalDate actual = LocalDate.now();
        if (fecha.isEqual(actual) || fecha.isAfter(actual)) {
            return true;
        }
        return validarDiasVigentes();
    }

    private boolean validarDiasVigentes() {
        LocalDate hoy = LocalDate.now();
        long dias = ChronoUnit.DAYS.between(anuncio.getFecha(), hoy);
        return dias <= anuncio.getVigencia().dias();
    }
}
