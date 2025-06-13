package com.syntaxerror.biblioteca.business;

import com.syntaxerror.biblioteca.business.util.BusinessException;
import com.syntaxerror.biblioteca.business.util.BusinessValidator;
import com.syntaxerror.biblioteca.model.SedesDTO;
import com.syntaxerror.biblioteca.persistance.dao.impl.SedeDAOImpl;
import java.util.ArrayList;
import com.syntaxerror.biblioteca.persistance.dao.SedeDAO;

public class SedeBO {

    private final SedeDAO sedeDAO;

    public SedeBO() {
        this.sedeDAO = new SedeDAOImpl();
    }

    public int insertar(String nombre, String direccion, String distrito, String telefonoContacto,
            String correoContacto, Boolean activa) throws BusinessException {

        validarDatos(nombre, direccion, distrito, telefonoContacto, correoContacto);

        SedesDTO sede = new SedesDTO();
        sede.setNombre(nombre);
        sede.setDireccion(direccion);
        sede.setDistrito(distrito);
        sede.setTelefonoContacto(telefonoContacto);
        sede.setCorreoContacto(correoContacto);
        sede.setActiva(activa != null ? activa : true);

        return this.sedeDAO.insertar(sede);
    }

    public int modificar(Integer idSede, String nombre, String direccion, String distrito,
            String telefonoContacto, String correoContacto, Boolean activa) throws BusinessException {

        if (idSede == null || idSede <= 0) {
            throw new BusinessException("Debe proporcionar un ID de sede válido.");
        }

        validarDatos(nombre, direccion, distrito, telefonoContacto, correoContacto);

        SedesDTO sede = new SedesDTO();
        sede.setIdSede(idSede);
        sede.setNombre(nombre);
        sede.setDireccion(direccion);
        sede.setDistrito(distrito);
        sede.setTelefonoContacto(telefonoContacto);
        sede.setCorreoContacto(correoContacto);
        sede.setActiva(activa != null ? activa : true);

        return this.sedeDAO.modificar(sede);
    }

    public int eliminar(Integer idSede) throws BusinessException {
        if (idSede == null || idSede <= 0) {
            throw new BusinessException("Debe proporcionar un ID de sede válido.");
        }
        SedesDTO sede = new SedesDTO();
        sede.setIdSede(idSede);
        return this.sedeDAO.eliminar(sede);
    }

    public SedesDTO obtenerPorId(Integer idSede) {
        return this.sedeDAO.obtenerPorId(idSede);
    }

    public ArrayList<SedesDTO> listarTodos() {
        return this.sedeDAO.listarTodos();
    }

    private void validarDatos(String nombre, String direccion, String distrito,
            String telefono, String correo) throws BusinessException {

        BusinessValidator.validarTexto(nombre, "nombre");
        BusinessValidator.validarTexto(direccion, "direccion");
        BusinessValidator.validarTexto(distrito, "distrito");

        if (telefono == null || telefono.length() < 9) {
            throw new BusinessException("El teléfono de contacto debe tener al menos 9 dígitos.");
        }
        if (correo == null || !correo.contains("@")) {
            throw new BusinessException("El correo debe tener un formato válido.");
        }
    }

    //SE CONSIDERA QUE HAY POCAS SEDES EN LA TABLA
    public ArrayList<SedesDTO> listarSedesActivas() throws BusinessException {
        ArrayList<SedesDTO> sedesActivas = new ArrayList<>();
        ArrayList<SedesDTO> todas = sedeDAO.listarTodos();

        for (SedesDTO sede : todas) {
            if (Boolean.TRUE.equals(sede.getActiva())) {
                sedesActivas.add(sede);
            }
        }

        return sedesActivas;
    }

    public ArrayList<SedesDTO> listarSedesActivasPorMaterial(int idMaterial) throws BusinessException {
        BusinessValidator.validarId(idMaterial, "material");
        return sedeDAO.listarSedesActivasPorMaterial(idMaterial);
    }

}
