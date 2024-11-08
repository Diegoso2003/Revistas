/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.revistas.rest.backend.reporte;

import com.mycompany.revistas.rest.backend.dtos.ReportePago;
import com.mycompany.revistas.rest.backend.dtos.ResultadoPago;
import com.mycompany.revistas.rest.backend.excepciones.DatosUsuarioException;
import com.mycompany.revistas.rest.backend.jwt_token.TokenJWT;
import com.mycompany.revistas.rest.backend.revista.crud.RevistasRead;
import com.mycompany.revistas.rest.backend.usuario.Usuario;
import java.util.List;

/**
 *
 * @author rafael-cayax
 */
public class FiltrarReportePago {

    private ReportePago reporte;
    private List<ResultadoPago> resultado;
    private Usuario usuario;
    private double total;

    public FiltrarReportePago(ReportePago reporte) {
        this.reporte = reporte;
    }

    public List<ResultadoPago> obtenerLista(String token) throws DatosUsuarioException {
        TokenJWT t = new TokenJWT();
        usuario = t.obtenerUsuario(token);
        obtenerConsulta();
        return resultado;
    }

    private void obtenerConsulta() {
        RevistasRead r = new RevistasRead();
        String statement = "SELECT rba.* "
                + "FROM registro_bloqueo_anuncio rba "
                + "JOIN revista r ON rba.ID_revista_bloqueo = r.ID "
                + "WHERE r.nombre_editor = ? ";
        if (reporte.rangoFecha()) {
            statement += " AND rba.fecha_pago BETWEEN ? AND ? ";
        }
        if (reporte.tieneID()) {
            statement += " AND rba.ID_revista_bloqueo = ? ";
        }
        statement += " ORDER BY rba.ID_revista_bloqueo";
        resultado = r.conseguirResultado(statement, reporte, usuario);
        this.total = r.getPrecio();
    }

    public double getTotal() {
        return total;
    }

    
}
