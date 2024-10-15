/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.revistas.rest.backend.usuario;

/**
 * Esta clase representa el concepto de un usuario.
 */
public class Usuario {

    private String nombre;
    private String contraseña;
    private String confirmacionContraseña;
    private EnumRol rol;

    /**
     * evalua si el usuario ha ingresado correctamente su nombre y contraseña
     *
     * @return true si ingreso sus datos, false para cualquier otra cosa
     */
    public boolean esValido() {
        return (nombre != null && contraseña != null) && (nombre.length() != 0 && contraseña.length() != 0);
    }

    /**
     * evalua si el usuario ha ingresado correctamente su nombre contraseña y 
     * su confirmacion de la contraseña
     *
     * @return true si ingreso todo, false para cualquier otra cosa
     */
    public boolean esRegistroValido() {
        return (nombre != null && contraseña != null && confirmacionContraseña != null)
                && (nombre.length() != 0 && contraseña.length() >= 8 && contraseña.length() >= 8)
                && rol != null;
    }

    /**
     * evalua si la contraseña ingresada coincide con la contraseña de confirmacion
     * @return si ambas contraseña son iguales false para cualqier otra cosa
     */
    public boolean contraseñasValidas() {
        return contraseña.equals(confirmacionContraseña);
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre.trim();
    }

    public String getContraseña() {
        return contraseña;
    }

    public void setContraseña(String contraseña) {
        this.contraseña = contraseña;
    }

    public EnumRol getRol() {
        return rol;
    }

    public void setRol(EnumRol rol) {
        this.rol = rol;
    }

    public String getConfirmacionContraseña() {
        return confirmacionContraseña;
    }

    public void setConfirmacionContraseña(String confirmacionContraseña) {
        this.confirmacionContraseña = confirmacionContraseña;
    }

    public void encriptarContraseña() {
        Encriptador encriptador = new Encriptador();
        this.contraseña = encriptador.encriptar(contraseña);
    }

}
