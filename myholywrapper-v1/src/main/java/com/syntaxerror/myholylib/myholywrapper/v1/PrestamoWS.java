package com.syntaxerror.myholylib.myholywrapper.v1;

import com.syntaxerror.biblioteca.business.PrestamoBO;
import com.syntaxerror.biblioteca.business.util.BusinessException;
import com.syntaxerror.biblioteca.model.EjemplarDTO;
import com.syntaxerror.biblioteca.model.PrestamoDTO;
import com.syntaxerror.biblioteca.model.enums.EstadoPrestamoEjemplar;

import jakarta.jws.WebService;
import jakarta.jws.WebMethod;
import jakarta.jws.WebParam;
import jakarta.xml.ws.WebServiceException;

import java.util.ArrayList;
import java.util.Date;

@WebService(serviceName = "PrestamoWS")
public class PrestamoWS {

    private final PrestamoBO prestamoBO;

    public PrestamoWS() {
        this.prestamoBO = new PrestamoBO();
    }

    @WebMethod(operationName = "insertarPrestamo")
    public int insertarPrestamo(
            @WebParam(name = "fechaSolicitud") Date fechaSolicitud,
            @WebParam(name = "fechaPrestamo") Date fechaPrestamo,
            @WebParam(name = "fechaDevolucion") Date fechaDevolucion,
            @WebParam(name = "idPersona") Integer idPersona
    ) {
        try {
            return prestamoBO.insertar(fechaSolicitud, fechaPrestamo, fechaDevolucion, idPersona);
        } catch (BusinessException e) {
            throw new WebServiceException("Error al insertar préstamo: " + e.getMessage());
        }
    }

    @WebMethod(operationName = "modificarPrestamo")
    public int modificarPrestamo(
            @WebParam(name = "idPrestamo") Integer idPrestamo,
            @WebParam(name = "fechaSolicitud") Date fechaSolicitud,
            @WebParam(name = "fechaPrestamo") Date fechaPrestamo,
            @WebParam(name = "fechaDevolucion") Date fechaDevolucion,
            @WebParam(name = "idPersona") Integer idPersona
    ) {
        try {
            return prestamoBO.modificar(idPrestamo, fechaSolicitud, fechaPrestamo, fechaDevolucion, idPersona);
        } catch (BusinessException e) {
            throw new WebServiceException("Error al modificar préstamo: " + e.getMessage());
        }
    }

    @WebMethod(operationName = "eliminarPrestamo")
    public int eliminarPrestamo(@WebParam(name = "idPrestamo") Integer idPrestamo) {
        try {
            return prestamoBO.eliminar(idPrestamo);
        } catch (BusinessException e) {
            throw new WebServiceException("Error al eliminar préstamo: " + e.getMessage());
        }
    }

    @WebMethod(operationName = "obtenerPrestamo")
    public PrestamoDTO obtenerPrestamo(@WebParam(name = "idPrestamo") Integer idPrestamo) {
        try {
            return prestamoBO.obtenerPorId(idPrestamo);
        } catch (BusinessException e) {
            throw new WebServiceException("Error al obtener préstamo: " + e.getMessage());
        }
    }

    @WebMethod(operationName = "listarPrestamos")
    public ArrayList<PrestamoDTO> listarPrestamos() {
        try {
            return prestamoBO.listarTodos();
        } catch (Exception e) {
            throw new WebServiceException("Error al listar préstamos: " + e.getMessage());
        }
    }

    @WebMethod(operationName = "contarPrestamosActivos")
    public int contarPrestamosActivos(@WebParam(name = "idUsuario") int idUsuario) {
        try {
            return prestamoBO.contarPrestamosActivosPorUsuario(idUsuario);
        } catch (BusinessException e) {
            throw new WebServiceException("Error al contar préstamos activos: " + e.getMessage());
        }
    }

    @WebMethod(operationName = "contarPrestamosAtrasados")
    public int contarPrestamosAtrasados(@WebParam(name = "idUsuario") int idUsuario) {
        try {
            return prestamoBO.contarPrestamosAtrasadosPorUsuario(idUsuario);
        } catch (BusinessException e) {
            throw new WebServiceException("Error al contar préstamos atrasados: " + e.getMessage());
        }
    }

    @WebMethod(operationName = "contarPrestamosPorMaterial")
    public int contarPrestamosPorMaterial(@WebParam(name = "idMaterial") int idMaterial) {
        try {
            return prestamoBO.contarPrestamosPorMaterial(idMaterial);
        } catch (BusinessException e) {
            throw new WebServiceException("Error al contar préstamos por material: " + e.getMessage());
        }
    }

    @WebMethod(operationName = "solicitarPrestamo")
    public void solicitarPrestamo(
            @WebParam(name = "idPersona") Integer idPersona,
            @WebParam(name = "idMaterial") Integer idMaterial,
            @WebParam(name = "idSede") Integer idSede
    ) {
        try {
            prestamoBO.solicitarPrestamo(idPersona, idMaterial, idSede);
        } catch (BusinessException | java.text.ParseException e) {
            throw new WebServiceException("Error al solicitar préstamo: " + e.getMessage());
        }
    }

    @WebMethod(operationName = "listarEjemplaresPrestadosPorPersona")
    public ArrayList<EjemplarDTO> listarEjemplaresPrestadosPorPersona(@WebParam(name = "idPersona") int idPersona) {
        try {
            return prestamoBO.listarEjemplaresPrestadosPorPersona(idPersona);
        } catch (BusinessException e) {
            throw new WebServiceException("Error al listar ejemplares prestados: " + e.getMessage());
        }
    }

    @WebMethod(operationName = "listarEjemplaresSolicitadosPorPersona")
    public ArrayList<EjemplarDTO> listarEjemplaresSolicitadosPorPersona(@WebParam(name = "idPersona") int idPersona) {
        try {
            return prestamoBO.listarEjemplaresSolicitadosPorPersona(idPersona);
        } catch (BusinessException e) {
            throw new WebServiceException("Error al listar ejemplares solicitados: " + e.getMessage());
        }
    }

    @WebMethod(operationName = "listarPrestamosPorPersona")
    public ArrayList<PrestamoDTO> listarPrestamosPorPersona(@WebParam(name = "idPersona") int idPersona) {
        try {
            return prestamoBO.listarPrestamosPorPersona(idPersona);
        } catch (BusinessException e) {
            throw new WebServiceException("Error al listar préstamos por persona: " + e.getMessage());
        }
    }

    @WebMethod(operationName = "listarPrestamosActivosPorPersona")
    public ArrayList<PrestamoDTO> listarPrestamosActivosPorPersona(@WebParam(name = "idPersona") int idPersona) {
        try {
            return prestamoBO.listarPrestamoPorEstadoPersona(idPersona, EstadoPrestamoEjemplar.PRESTADO);
        } catch (BusinessException e) {
            throw new WebServiceException("Error al listar préstamos activos por persona: " + e.getMessage());
        }
    }

    @WebMethod(operationName = "listarPrestamosSolicitadosPorPersona")
    public ArrayList<PrestamoDTO> listarPrestamosSolicitadosPorPersona(@WebParam(name = "idPersona") int idPersona) {
        try {
            return prestamoBO.listarPrestamoPorEstadoPersona(idPersona, EstadoPrestamoEjemplar.SOLICITADO);
        } catch (BusinessException e) {
            throw new WebServiceException("Error al listar préstamos activos por persona: " + e.getMessage());
        }
    }

    @WebMethod(operationName = "listarPrestamosDevueltosPorPersona")
    public ArrayList<PrestamoDTO> listarPrestamosDevueltosPorPersona(@WebParam(name = "idPersona") int idPersona) {
        try {
            return prestamoBO.listarPrestamoPorEstadoPersona(idPersona, EstadoPrestamoEjemplar.DEVUELTO);
        } catch (BusinessException e) {
            throw new WebServiceException("Error al listar préstamos activos por persona: " + e.getMessage());
        }
    }

    @WebMethod(operationName = "listarPrestamosAtrasadosPorPersona")
    public ArrayList<PrestamoDTO> listarPrestamosAtrasadosPorPersona(@WebParam(name = "idPersona") int idPersona) {
        try {

            return prestamoBO.listarPrestamoPorEstadoPersona(idPersona, EstadoPrestamoEjemplar.ATRASADO);
        } catch (BusinessException e) {
            throw new WebServiceException("Error al listar préstamos activos por persona: " + e.getMessage());
        }
    }

    @WebMethod(operationName = "listarPrestamosSolicitados")
    public ArrayList<PrestamoDTO> listarPrestamosSolicitados() {
        return prestamoBO.listarPrestamosSolicitados();
    }

    @WebMethod(operationName = "listarPrestamosAtrasados")
    public ArrayList<PrestamoDTO> listarPrestamosAtrasados() {
        return prestamoBO.listarPrestamosAtrasados();
    }

    @WebMethod(operationName = "listarPrestamosDevueltos")
    public ArrayList<PrestamoDTO> listarPrestamosDevueltos() {
        return prestamoBO.listarPrestamosDevueltos();
    }

    @WebMethod(operationName = "listarPrestamosNoCulminados")
    public ArrayList<PrestamoDTO> listarPrestamosNoCulminados() {
        return prestamoBO.listarPrestamosNoCulminados();
    }

    @WebMethod(operationName = "devolverPrestamo")
    public void devolverPrestamo(@WebParam(name = "idPrestamo") Integer idPrestamo) {
        try {
            prestamoBO.devolverPrestamo(idPrestamo);
        } catch (BusinessException e) {
            throw new WebServiceException("Error al devolver préstamo: " + e.getMessage());
        }
    }

}
