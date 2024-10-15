/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.revistas.rest.backend.usuario;

import org.mindrot.jbcrypt.BCrypt;

/**
 *
 * @author rafael-cayax
 */
public class Encriptador {

    /**
     * encripta la contraseña
     * @param contraseña la contraseñ a encriptar
     * @return retorna la contraseña encriptada
    */
    public String encriptar(String contraseña) {
        return BCrypt.hashpw(contraseña, BCrypt.gensalt());
    }
    
    /**
     * evalua si la contraseña sin encriptar es la misma a la contraseña
     * ya encriptada
     * @param contraseña la contraseña sin encriptar
     * @param contraseñaHashed la contraseña encriptada
     * @return retorna true si es la misma contraseña y false si no lo es
    */
    public boolean esContraseñaValida(String contraseña, String contraseñaHashed){
        return BCrypt.checkpw(contraseña, contraseñaHashed);
    }
}
