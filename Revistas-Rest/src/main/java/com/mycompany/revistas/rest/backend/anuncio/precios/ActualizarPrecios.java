/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.revistas.rest.backend.anuncio.precios;

import com.mycompany.revistas.rest.backend.anuncio.PreciosDTO;
import com.mycompany.revistas.rest.backend.anuncio.crud.AnuncioRead;
import com.mycompany.revistas.rest.backend.anuncio.crud.AnuncioUpdate;
import com.mycompany.revistas.rest.backend.excepciones.DatosUsuarioException;

/**
 *
 * @author rafael-cayax
 */
public class ActualizarPrecios {

    private PreciosDTO preciosViejos;
    private PreciosDTO nuevos;

    public ActualizarPrecios(PreciosDTO nuevos) {
        this.nuevos = nuevos;
    }

    public PreciosDTO actualizar() throws DatosUsuarioException {
        consultarPrecios();
        if (nuevos.actualizar()) {
            reemplazarPrecios();
            actualizarPrecios();
        }
        return preciosViejos;
    }

    private void consultarPrecios() throws DatosUsuarioException {
        AnuncioRead a = new AnuncioRead();
        preciosViejos = a.obtenerPrecios();
    }

    private void reemplazarPrecios() throws DatosUsuarioException {
        preciosViejos.actualizarCambios(nuevos);
        if (!preciosViejos.sonPreciosValidos()) {
            throw new DatosUsuarioException("Ingrese valores mayores a cero");
        }
    }

    private void actualizarPrecios() {
        AnuncioUpdate a = new AnuncioUpdate();
    }

}
