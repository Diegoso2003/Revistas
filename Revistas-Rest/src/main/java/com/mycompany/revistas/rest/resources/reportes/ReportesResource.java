/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.revistas.rest.resources.reportes;

import com.mycompany.revistas.rest.backend.dtos.ReportePago;
import com.mycompany.revistas.rest.backend.dtos.ResultadoPago;
import com.mycompany.revistas.rest.backend.excepciones.DatosUsuarioException;
import com.mycompany.revistas.rest.backend.reporte.FiltrarReportePago;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.WebApplicationException;
import jakarta.ws.rs.core.Context;
import jakarta.ws.rs.core.HttpHeaders;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.core.StreamingOutput;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import net.sf.jasperreports.engine.util.JRLoader;

/**
 *
 * @author rafael-cayax
 */
@Path("reportes")
public class ReportesResource {

    @Path("/reportePago")
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces("application/pdf")
    public Response crearReporte(ReportePago reporte, @Context HttpHeaders header) {
        try {
            String token = header.getHeaderString(HttpHeaders.AUTHORIZATION);
            FiltrarReportePago f = new FiltrarReportePago(reporte);
            List<ResultadoPago> list = f.obtenerLista(token);
            System.out.println(list.size());
            InputStream reportTemplate = getClass().getClassLoader().getResourceAsStream("reportePago.jasper");

            if (reportTemplate == null) {
                throw new IOException("La plantilla de reporte no se encontró.");
            }
            JRBeanCollectionDataSource ds = new JRBeanCollectionDataSource(list);

            Map<String, Object> params = new HashMap<>();
            params.put("total", "Total pagado: Q" + f.getTotal());

            JasperReport report = (JasperReport) JRLoader.loadObject(reportTemplate);
            JasperPrint jasperPrint = JasperFillManager.fillReport(report, params, ds);
            

            StreamingOutput fileStream = (java.io.OutputStream output) -> {
            try {
                JasperExportManager.exportReportToPdfStream(jasperPrint, output);
                output.flush();
            } catch (Exception e) {
                throw new WebApplicationException("File Not Found !!");
            }
        };
            return Response.ok(fileStream, MediaType.APPLICATION_OCTET_STREAM)
                    .header("Content-Disposition", "attachment; filename=ReporteAnuncios.pdf")
                    .build();
        } catch (DatosUsuarioException ex) {
            return Response.status(Response.Status.NOT_ACCEPTABLE)
                    .entity("{\"mensaje\":\"" + ex.getMessage() + "\"}")
                    .type(MediaType.APPLICATION_JSON)
                    .build();
        } catch (IOException | JRException ex) {
            ex.printStackTrace();
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                    .entity("Error generating report: " + ex.getMessage())
                    .build();
        }

    }
}
