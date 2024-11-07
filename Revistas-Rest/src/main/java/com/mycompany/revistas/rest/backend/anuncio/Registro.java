/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.revistas.rest.backend.anuncio;

import com.mycompany.revistas.rest.backend.anuncio.crud.AnuncioUpdate;

/**
 *
 * @author rafael-cayax
 */
public class Registro {
    private final RegistroAnuncio registro;

    public Registro(RegistroAnuncio registro) {
        this.registro = registro;
    }
    
    public void actualizarRegistro(){
        AnuncioUpdate a = new AnuncioUpdate();
        a.actualizarContador(registro);
    }
}
