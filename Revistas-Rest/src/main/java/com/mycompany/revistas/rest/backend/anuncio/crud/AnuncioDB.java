/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.revistas.rest.backend.anuncio.crud;

import com.mycompany.revistas.rest.backend.anuncio.PreciosDTO;
import com.mycompany.revistas.rest.backend.base_de_datos.PoolConnections;
import com.mycompany.revistas.rest.backend.excepciones.DatosUsuarioException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;


/**
 *
 * @author rafael-cayax
 */
public class AnuncioDB {
 
    public PreciosDTO obtenerPrecios() throws DatosUsuarioException{
        PreciosDTO precios= new PreciosDTO();
        String statement = "select texto, texto_imagen, video, dia_1, dia_3, semana_1, semana_2 from precio_de_anuncios";
        try {
            Connection coneccion = PoolConnections.getInstance().getConnection();
            Statement st = coneccion.createStatement();
            ResultSet result = st.executeQuery(statement);
            if (result.next()) {
                precios.setImagen(result.getDouble("texto_imagen"));
                precios.setTexto(result.getDouble("texto"));
                precios.setVideo(result.getDouble("video"));
                precios.setDia1(result.getDouble("dia_1"));
                precios.setDia3(result.getDouble("dia_3"));
                precios.setSemana1(result.getDouble("semana_1"));
                precios.setSemana2(result.getDouble("semana_2"));
            }
        } catch (SQLException e) {
            throw new DatosUsuarioException("No se pudo guardar el anuncio pruebe mas tarde");
        }
        return precios;
    }
}
