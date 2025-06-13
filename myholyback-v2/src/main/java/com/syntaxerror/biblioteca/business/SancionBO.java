package com.syntaxerror.biblioteca.business;

import com.syntaxerror.biblioteca.business.util.BusinessException;
import com.syntaxerror.biblioteca.business.util.BusinessValidator;
import com.syntaxerror.biblioteca.model.PrestamosDTO;
import com.syntaxerror.biblioteca.model.enums.TipoSancion;
import com.syntaxerror.biblioteca.model.SancionesDTO;
import com.syntaxerror.biblioteca.persistance.dao.impl.PrestamoDAOImpl;
import com.syntaxerror.biblioteca.persistance.dao.impl.SancionDAOImpl;
import java.util.ArrayList;
import java.util.Date;
import com.syntaxerror.biblioteca.persistance.dao.PrestamoDAO;
import com.syntaxerror.biblioteca.persistance.dao.SancionDAO;

public class SancionBO {

    private final SancionDAO sancionDAO;
    private final PrestamoDAO prestamoDAO;

    public SancionBO() {
        this.sancionDAO = new SancionDAOImpl();
        this.prestamoDAO = new PrestamoDAOImpl();
    }

    public int insertar(TipoSancion tipo, Date fecha, Double monto, Date duracion, String descripcion, Integer idPrestamo) throws BusinessException {
        validarDatos(tipo, fecha, monto, duracion, descripcion, idPrestamo);

        PrestamosDTO prestamo = prestamoDAO.obtenerPorId(idPrestamo);
        if (prestamo == null) {
            throw new BusinessException("El préstamo con ID " + idPrestamo + " no existe.");
        }
        SancionesDTO sancion = new SancionesDTO();
        sancion.setTipo(tipo);
        sancion.setFecha(fecha);
        sancion.setMonto(monto);
        sancion.setDuracion(duracion);
        sancion.setDescripcion(descripcion);

        sancion.setPrestamo(prestamo);

        return this.sancionDAO.insertar(sancion);
    }

    public int modificar(Integer idSancion, TipoSancion tipo, Date fecha, Double monto, Date duracion, String descripcion, Integer idPrestamo) throws BusinessException {
        BusinessValidator.validarId(idSancion, "sanción");
        validarDatos(tipo, fecha, monto, duracion, descripcion, idPrestamo);

        PrestamosDTO prestamo = prestamoDAO.obtenerPorId(idPrestamo);
        if (prestamo == null) {
            throw new BusinessException("El préstamo con ID " + idPrestamo + " no existe.");
        }
        SancionesDTO sancion = new SancionesDTO();
        sancion.setIdSancion(idSancion);
        sancion.setTipo(tipo);
        sancion.setFecha(fecha);
        sancion.setMonto(monto);
        sancion.setDuracion(duracion);
        sancion.setDescripcion(descripcion);

        sancion.setPrestamo(prestamo);

        return this.sancionDAO.modificar(sancion);
    }

    public int eliminar(Integer idSancion) throws BusinessException {
        BusinessValidator.validarId(idSancion, "sanción");
        SancionesDTO sancion = new SancionesDTO();
        sancion.setIdSancion(idSancion);
        return this.sancionDAO.eliminar(sancion);
    }

    public SancionesDTO obtenerPorId(Integer idSancion) throws BusinessException {
        BusinessValidator.validarId(idSancion, "sanción");
        return this.sancionDAO.obtenerPorId(idSancion);
    }

    public ArrayList<SancionesDTO> listarTodos() {
        return this.sancionDAO.listarTodos();
    }

    private void validarDatos(TipoSancion tipo, Date fecha, Double monto, Date duracion, String descripcion, Integer idPrestamo) throws BusinessException {
        if (tipo == null) {
            throw new BusinessException("Debe especificarse un tipo de sanción.");
        }
        if (fecha == null) {
            throw new BusinessException("La fecha de sanción no puede ser nula.");
        }
        if (monto != null && monto < 0) {
            throw new BusinessException("El monto de la sanción no puede ser negativo.");
        }
        if (descripcion == null || descripcion.trim().isEmpty()) {
            throw new BusinessException("Debe incluirse una descripción válida.");
        }
        BusinessValidator.validarId(idPrestamo, "préstamo asociado");
    }

    public ArrayList<SancionesDTO> listarSancionesPorPersona(int idPersona) throws BusinessException {
        BusinessValidator.validarId(idPersona, "persona");
        return this.sancionDAO.listarSancionesPorPersona(idPersona);
    }

    public ArrayList<SancionesDTO> listarSancionesActivasPorPersona(int idPersona) throws BusinessException {
        BusinessValidator.validarId(idPersona, "persona");
        return this.sancionDAO.listarSancionesActivasPorPersona(idPersona);
    }

    //Bloquea procesos si hay sanciones
    public void verificarSancionesActivas(int idPersona) throws BusinessException {
        BusinessValidator.validarId(idPersona, "persona");

        ArrayList<SancionesDTO> sancionesActivas = this.sancionDAO.listarSancionesActivasPorPersona(idPersona);

        if (!sancionesActivas.isEmpty()) {
            throw new BusinessException("No puedes solicitar préstamos mientras tengas sanciones activas.");
        }
    }

    //Avisa si hay sanciones
    public boolean tieneSancionesActivas(int idPersona) throws BusinessException {
        BusinessValidator.validarId(idPersona, "persona");
        ArrayList<SancionesDTO> sancionesActivas = sancionDAO.listarSancionesActivasPorPersona(idPersona);
        return !sancionesActivas.isEmpty();
    }

}
