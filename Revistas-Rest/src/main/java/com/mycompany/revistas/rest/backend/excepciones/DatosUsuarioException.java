/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Exception.java to edit this template
 */
package com.mycompany.revistas.rest.backend.excepciones;

/**
 *
 * @author rafael-cayax
 */
public class DatosUsuarioException extends Exception {

    /**
     * Creates a new instance of <code>DatosUsuarioException</code> without
     * detail message.
     */
    public DatosUsuarioException() {
    }

    /**
     * Constructs an instance of <code>DatosUsuarioException</code> with the
     * specified detail message.
     *
     * @param msg the detail message.
     */
    public DatosUsuarioException(String msg) {
        super(msg);
    }
}
