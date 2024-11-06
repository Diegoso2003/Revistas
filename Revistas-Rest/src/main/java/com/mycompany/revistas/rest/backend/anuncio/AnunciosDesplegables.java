/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.revistas.rest.backend.anuncio;

import com.mycompany.revistas.rest.backend.anuncio.crud.AnuncioRead;
import com.mycompany.revistas.rest.backend.excepciones.DatosUsuarioException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 *
 * @author rafael-cayax
 */
public class AnunciosDesplegables {

    List<Anuncio> anuncios;

    public List<Anuncio> obtenerAnunciosActivos() {
        try {
            String statement = "select * from anuncio where estado = ?";
            AnuncioRead datos = new AnuncioRead();
            this.anuncios = datos.obtenerAnuncios(statement, true, null);
            seleccionarAlAzar();
            return anuncios;
        } catch (DatosUsuarioException ex) {
            return new ArrayList<>();
        }
    }

    private void seleccionarAlAzar() {
        if (anuncios.size() > 2) {
            List<Anuncio> anuncios2 = new ArrayList<>();
            Random random = new Random();
            int r = random.nextInt(0, anuncios.size());
            anuncios2.add(anuncios.get(r));
            Anuncio anuncio;
            do {
                r = random.nextInt(0, anuncios.size());
                anuncio = anuncios.get(r);
            } while (anuncios2.getFirst().equals(anuncio));
            anuncios2.add(anuncio);
            this.anuncios = anuncios2;
        }
    }
}
