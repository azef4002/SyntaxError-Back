package com.syntaxerror.biblioteca.bibliows;

import com.syntaxerror.biblioteca.business.PersonaBO;
import com.syntaxerror.biblioteca.business.util.BusinessException;
import com.syntaxerror.biblioteca.model.PersonasDTO;
import com.syntaxerror.biblioteca.model.enums.TipoPersona;
import com.syntaxerror.biblioteca.model.enums.Turnos;

import jakarta.jws.WebMethod;
import jakarta.jws.WebParam;
import jakarta.jws.WebService;
import jakarta.xml.ws.WebServiceException;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@WebService(serviceName = "PersonaWS")
public class PersonaWS {

    private final PersonaBO personaBO;

    public PersonaWS() {
        personaBO = new PersonaBO();
    }

    @WebMethod(operationName = "listarPersonas")
    public ArrayList<PersonasDTO> listarPersonas() {
        try {
            return personaBO.listarTodos();
        } catch (Exception e) {
            throw new WebServiceException("Error al listar personas: " + e.getMessage());
        }
    }

    @WebMethod(operationName = "obtenerPersona")
    public PersonasDTO obtenerPersona(@WebParam(name = "idPersona") Integer idPersona) {
        try {
            return personaBO.obtenerPorId(idPersona);
        } catch (BusinessException e) {
            throw new WebServiceException("Error al obtener persona: " + e.getMessage());
        }
    }

    @WebMethod(operationName = "insertarPersona")
    public int insertarPersona(
            @WebParam(name = "codigo") String codigo,
            @WebParam(name = "nombre") String nombre,
            @WebParam(name = "paterno") String paterno,
            @WebParam(name = "materno") String materno,
            @WebParam(name = "direccion") String direccion,
            @WebParam(name = "telefono") String telefono,
            @WebParam(name = "correo") String correo,
            @WebParam(name = "contrasenha") String contrasenha,
            @WebParam(name = "tipo") TipoPersona tipo,
            @WebParam(name = "turno") Turnos turno,
            @WebParam(name = "fechaContratoInicio") Date fechaContratoInicio,
            @WebParam(name = "fechaContratoFinal") Date fechaContratoFinal,
            @WebParam(name = "deuda") Double deuda,
            @WebParam(name = "fechaSancionFinal") Date fechaSancionFinal,
            @WebParam(name = "vigente") Boolean vigente,
            @WebParam(name = "idNivel") Integer idNivel,
            @WebParam(name = "idSede") Integer idSede
    ) {
        try {
            return personaBO.insertar(codigo, nombre, paterno, materno, direccion, telefono, correo, contrasenha,
                    tipo, turno, fechaContratoInicio, fechaContratoFinal, deuda, fechaSancionFinal, vigente, idNivel, idSede);
        } catch (BusinessException e) {
            throw new WebServiceException("Error al insertar persona: " + e.getMessage());
        }
    }

    @WebMethod(operationName = "modificarPersona")
    public int modificarPersona(
            @WebParam(name = "idPersona") Integer idPersona,
            @WebParam(name = "codigo") String codigo,
            @WebParam(name = "nombre") String nombre,
            @WebParam(name = "paterno") String paterno,
            @WebParam(name = "materno") String materno,
            @WebParam(name = "direccion") String direccion,
            @WebParam(name = "telefono") String telefono,
            @WebParam(name = "correo") String correo,
            @WebParam(name = "contrasenha") String contrasenha,
            @WebParam(name = "tipo") TipoPersona tipo,
            @WebParam(name = "turno") Turnos turno,
            @WebParam(name = "fechaContratoInicio") Date fechaContratoInicio,
            @WebParam(name = "fechaContratoFinal") Date fechaContratoFinal,
            @WebParam(name = "deuda") Double deuda,
            @WebParam(name = "fechaSancionFinal") Date fechaSancionFinal,
            @WebParam(name = "vigente") Boolean vigente,
            @WebParam(name = "idNivel") Integer idNivel,
            @WebParam(name = "idSede") Integer idSede
    ) {
        try {
            return personaBO.modificar(idPersona, codigo, nombre, paterno, materno, direccion, telefono, correo, contrasenha,
                    tipo, turno, fechaContratoInicio, fechaContratoFinal, deuda, fechaSancionFinal, vigente, idNivel, idSede);
        } catch (BusinessException e) {
            throw new WebServiceException("Error al modificar persona: " + e.getMessage());
        }
    }

    @WebMethod(operationName = "eliminarPersona")
    public int eliminarPersona(@WebParam(name = "idPersona") Integer idPersona) {
        try {
            return personaBO.eliminar(idPersona);
        } catch (BusinessException e) {
            throw new WebServiceException("Error al eliminar persona: " + e.getMessage());
        }
    }

    @WebMethod(operationName = "obtenerPersonaPorCredenciales")
    public PersonasDTO obtenerPersonaPorCredenciales(@WebParam(name = "correo_codigo") String correo_codigo, @WebParam(name = "contrasenha") String contrasenha) {
        try {
            return personaBO.obtenerPorCredenciales(correo_codigo, contrasenha);
        } catch (BusinessException e) {
            throw new WebServiceException("Error al obtener por credenciales: " + e.getMessage());
        }
    }

    @WebMethod(operationName = "calcularLimitePrestamos")
    public int calcularLimitePrestamos(@WebParam(name = "codigo") String codigo) {
        try {
            return personaBO.calcularLimitePrestamos(codigo);
        } catch (BusinessException e) {
            throw new WebServiceException("Error al calcular límite de préstamos: " + e.getMessage());
        }
    }

    @WebMethod(operationName = "modificarContrasenha")
    public int modificarContrasenha(
            @WebParam(name = "idPersona") Integer idPersona,
            @WebParam(name = "nuevaContrasenha") String nuevaContrasenha) {
        try {
            return personaBO.modificarContrasenha(idPersona, nuevaContrasenha);
        } catch (BusinessException e) {
            throw new WebServiceException("Error al modificar contraseña: " + e.getMessage());
        }
    }

    @WebMethod(operationName = "listarPersonasPaginado")
    public List<PersonasDTO> listarPersonasPaginado(
            @WebParam(name = "limite") int limite,
            @WebParam(name = "pagina") int pagina
    ) {
        try {
            return personaBO.listarTodosPaginado(limite, pagina);
        } catch (BusinessException e) {
            throw new WebServiceException("Error al listar personas paginadas: " + e.getMessage());
        }
    }

}
