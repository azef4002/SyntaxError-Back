package com.syntaxerror.biblioteca.bibliows;

import com.syntaxerror.biblioteca.business.SancionBO;
import com.syntaxerror.biblioteca.business.util.BusinessException;
import com.syntaxerror.biblioteca.model.SancionesDTO;
import com.syntaxerror.biblioteca.model.enums.TipoSancion;

import jakarta.jws.WebService;
import jakarta.jws.WebMethod;
import jakarta.jws.WebParam;
import jakarta.xml.ws.WebServiceException;

import java.util.ArrayList;
import java.util.Date;

@WebService(serviceName = "SancionWS")
public class SancionWS {

    private final SancionBO sancionBO;

    public SancionWS() {
        this.sancionBO = new SancionBO();
    }

    @WebMethod(operationName = "insertarSancion")
    public int insertarSancion(
            @WebParam(name = "tipo") TipoSancion tipo,
            @WebParam(name = "fecha") Date fecha,
            @WebParam(name = "monto") Double monto,
            @WebParam(name = "duracion") Date duracion,
            @WebParam(name = "descripcion") String descripcion,
            @WebParam(name = "idPrestamo") Integer idPrestamo
    ) {
        try {
            return sancionBO.insertar(tipo, fecha, monto, duracion, descripcion, idPrestamo);
        } catch (BusinessException e) {
            throw new WebServiceException("Error al insertar sanción: " + e.getMessage());
        }
    }

    @WebMethod(operationName = "modificarSancion")
    public int modificarSancion(
            @WebParam(name = "idSancion") Integer idSancion,
            @WebParam(name = "tipo") TipoSancion tipo,
            @WebParam(name = "fecha") Date fecha,
            @WebParam(name = "monto") Double monto,
            @WebParam(name = "duracion") Date duracion,
            @WebParam(name = "descripcion") String descripcion,
            @WebParam(name = "idPrestamo") Integer idPrestamo
    ) {
        try {
            return sancionBO.modificar(idSancion, tipo, fecha, monto, duracion, descripcion, idPrestamo);
        } catch (BusinessException e) {
            throw new WebServiceException("Error al modificar sanción: " + e.getMessage());
        }
    }

    @WebMethod(operationName = "eliminarSancion")
    public int eliminarSancion(@WebParam(name = "idSancion") Integer idSancion) {
        try {
            return sancionBO.eliminar(idSancion);
        } catch (BusinessException e) {
            throw new WebServiceException("Error al eliminar sanción: " + e.getMessage());
        }
    }

    @WebMethod(operationName = "obtenerSancionPorId")
    public SancionesDTO obtenerSancionPorId(@WebParam(name = "idSancion") Integer idSancion) {
        try {
            return sancionBO.obtenerPorId(idSancion);
        } catch (BusinessException e) {
            throw new WebServiceException("Error al obtener sanción: " + e.getMessage());
        }
    }

    @WebMethod(operationName = "listarSanciones")
    public ArrayList<SancionesDTO> listarSanciones() {
        try {
            return sancionBO.listarTodos();
        } catch (Exception e) {
            throw new WebServiceException("Error al listar sanciones: " + e.getMessage());
        }
    }

    @WebMethod(operationName = "listarSancionesPorPersona")
    public ArrayList<SancionesDTO> listarSancionesPorPersona(@WebParam(name = "idPersona") int idPersona) {
        try {
            return sancionBO.listarSancionesPorPersona(idPersona);
        } catch (BusinessException e) {
            throw new WebServiceException("Error al listar sanciones por persona: " + e.getMessage());
        }
    }
    //Bloquea prestamos
    @WebMethod(operationName = "verificarSancionesActivas")
    public void verificarSancionesActivas(@WebParam(name = "idPersona") int idPersona) {
        try {
            sancionBO.verificarSancionesActivas(idPersona);
        } catch (BusinessException e) {
            throw new WebServiceException("Sanciones activas encontradas: " + e.getMessage());
        }
    }
   
    @WebMethod(operationName = "tieneSancionesActivas")
    public boolean tieneSancionesActivas(@WebParam(name = "idPersona") int idPersona) {
        try {
            return sancionBO.tieneSancionesActivas(idPersona);
        } catch (BusinessException e) {
            throw new WebServiceException("Error al verificar sanciones: " + e.getMessage());
        }
    }

}
