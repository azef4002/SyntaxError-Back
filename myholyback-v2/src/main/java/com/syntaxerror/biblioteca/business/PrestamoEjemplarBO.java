package com.syntaxerror.biblioteca.business;

import com.syntaxerror.biblioteca.business.util.BusinessException;
import com.syntaxerror.biblioteca.business.util.BusinessValidator;
import com.syntaxerror.biblioteca.model.PrestamosDeEjemplaresDTO;
import com.syntaxerror.biblioteca.model.enums.EstadoPrestamoEjemplar;
import com.syntaxerror.biblioteca.persistance.dao.impl.PrestamoEjemplarDAOImpl;

import java.util.ArrayList;
import java.util.Date;
import com.syntaxerror.biblioteca.persistance.dao.PrestamoEjemplarDAO;

public class PrestamoEjemplarBO {

    private final PrestamoEjemplarDAO prestamosDAO;

    public PrestamoEjemplarBO() {
        this.prestamosDAO = new PrestamoEjemplarDAOImpl();
    }

    public int insertar(Integer idPrestamo, Integer idEjemplar, EstadoPrestamoEjemplar estado, Date fechaRealDevolucion) throws BusinessException {
        BusinessValidator.validarId(idPrestamo, "préstamo");
        BusinessValidator.validarId(idEjemplar, "ejemplar");

        PrestamosDeEjemplaresDTO dto = new PrestamosDeEjemplaresDTO();
        dto.setIdPrestamo(idPrestamo);
        dto.setIdEjemplar(idEjemplar);
        dto.setEstado(estado);
        dto.setFechaRealDevolucion(fechaRealDevolucion);

        return prestamosDAO.insertar(dto);
    }

    public int modificar(Integer idPrestamo, Integer idEjemplar, EstadoPrestamoEjemplar estado, Date fechaRealDevolucion) throws BusinessException {
        BusinessValidator.validarId(idPrestamo, "préstamo");
        BusinessValidator.validarId(idEjemplar, "ejemplar");

        PrestamosDeEjemplaresDTO dto = new PrestamosDeEjemplaresDTO();
        dto.setIdPrestamo(idPrestamo);
        dto.setIdEjemplar(idEjemplar);
        dto.setEstado(estado);
        dto.setFechaRealDevolucion(fechaRealDevolucion);

        return prestamosDAO.modificar(dto);
    }

    public int eliminar(Integer idPrestamo, Integer idEjemplar) throws BusinessException {
        BusinessValidator.validarId(idPrestamo, "préstamo");
        BusinessValidator.validarId(idEjemplar, "ejemplar");

        PrestamosDeEjemplaresDTO dto = new PrestamosDeEjemplaresDTO();
        dto.setIdPrestamo(idPrestamo);
        dto.setIdEjemplar(idEjemplar);

        return prestamosDAO.eliminar(dto);
    }

    public PrestamosDeEjemplaresDTO obtenerPorId(Integer idPrestamo, Integer idEjemplar) throws BusinessException {
        BusinessValidator.validarId(idPrestamo, "préstamo");
        BusinessValidator.validarId(idEjemplar, "ejemplar");

        return prestamosDAO.obtenerPorId(idPrestamo, idEjemplar);
    }

    public ArrayList<PrestamosDeEjemplaresDTO> listarTodos() {
        return prestamosDAO.listarTodos();
    }

    public ArrayList<Integer> obtenerIdEjemplaresPrestadosPorPersona(int idPersona) {
        PrestamoEjemplarDAO dao = new PrestamoEjemplarDAOImpl();
        return dao.obtenerIdEjemplaresPrestadosPorIdPersona(idPersona);
    }

    public ArrayList<Integer> obtenerIdEjemplaresSolicitadosPorPersona(int idPersona) {
        PrestamoEjemplarDAO dao = new PrestamoEjemplarDAOImpl();
        return dao.obtenerIdEjemplaresSolicitadosPorPersona(idPersona);
    }

    public ArrayList<PrestamosDeEjemplaresDTO> listarPrestamosDevueltos() {
        return prestamosDAO.listarPrestamosDevueltos();
    }

    public ArrayList<PrestamosDeEjemplaresDTO> listarPrestamosAtrasados() {
        return prestamosDAO.listarPrestamosAtrasados();
    }

    public ArrayList<PrestamosDeEjemplaresDTO> listarPrestamosSolicitados() {
        return prestamosDAO.listarPrestamosSolicitados();
    }

    public ArrayList<PrestamosDeEjemplaresDTO> listarPrestamosNoCulminados() {
        return prestamosDAO.listarPrestamosNoCulminados();
    }
}
