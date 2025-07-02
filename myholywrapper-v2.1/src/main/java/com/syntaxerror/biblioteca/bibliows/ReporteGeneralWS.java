package com.syntaxerror.biblioteca.bibliows;

import com.syntaxerror.biblioteca.bibliows.reports.ReporteUtil;
import com.syntaxerror.biblioteca.business.ReporteGeneralBO;
import com.syntaxerror.biblioteca.business.util.BusinessException;
import com.syntaxerror.biblioteca.model.ReportesGeneralesDTO;

import jakarta.jws.WebService;
import jakarta.jws.WebMethod;
import jakarta.jws.WebParam;
import jakarta.xml.ws.WebServiceException;

import java.util.ArrayList;

@WebService(serviceName = "ReporteGeneralWS")
public class ReporteGeneralWS {

    private final ReporteGeneralBO reporteBO;

    public ReporteGeneralWS() {
        this.reporteBO = new ReporteGeneralBO();
    }

    @WebMethod(operationName = "generarReporte")
    public void generarReporte(
        @WebParam(name = "anio") Integer anio,
        @WebParam(name = "mes") Integer mes
    ) {
        try {
            reporteBO.generarReporte(anio, mes);
        } catch (BusinessException e) {
            throw new WebServiceException("Error al generar el reporte: " + e.getMessage());
        }
    }

    @WebMethod(operationName = "listarPorPeriodo")
    public ArrayList<ReportesGeneralesDTO> listarPorPeriodo(
        @WebParam(name = "anio") Integer anio,
        @WebParam(name = "mes") Integer mes,
        @WebParam(name = "idPrestamo") Integer idPrestamo,
        @WebParam(name = "idPersona") Integer idPersona
    ) {
        try {
            return reporteBO.listarPorPeriodo(anio, mes, idPrestamo, idPersona);
        } catch (BusinessException e) {
            throw new WebServiceException("Error al listar el reporte: " + e.getMessage());
        }
    }
    
    @WebMethod(operationName = "reporteGeneral")
    public byte[] reporteProductos(
        @WebParam(name = "sedeId") Integer sedeId,
        @WebParam(name = "anho") Integer anho,
        @WebParam(name = "mes") Integer mes
    ){
        return ReporteUtil.reportePrestamosPorSede( sedeId,  anho,  mes);
    }
    
    @WebMethod(operationName = "reporteMaterialesSolicitados")
    public byte[] reporteMaterialesSolicitados(
        @WebParam(name = "sedeId") Integer sedeId,
        @WebParam(name = "anho") Integer anho,
        @WebParam(name = "mes") Integer mes
    ){
        return ReporteUtil.reporteMaterialesSolicitados( sedeId,  anho,  mes);
    }
}