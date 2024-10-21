/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Exception.java to edit this template
 */
package com.mycompany.revistas.rest.backend.excepciones;

/**
 *
 * @author rafael-cayax
 */
public class DatosInvalidosException extends Exception {

    /**
     * Creates a new instance of <code>DatosNoEncontradosException</code>
     * without detail message.
     */
    public DatosInvalidosException() {
    }

    /**
     * Constructs an instance of <code>DatosNoEncontradosException</code> with
     * the specified detail message.
     *
     * @param msg the detail message.
     */
    public DatosInvalidosException(String msg) {
        super(msg);
    }
}
