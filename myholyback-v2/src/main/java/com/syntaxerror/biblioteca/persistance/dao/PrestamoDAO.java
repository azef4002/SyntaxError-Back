package com.syntaxerror.biblioteca.persistance.dao;

import com.syntaxerror.biblioteca.model.PrestamosDTO;
import java.util.ArrayList;

public interface PrestamoDAO {

    public Integer insertar(PrestamosDTO prestamo);

    public PrestamosDTO obtenerPorId(Integer idPrestamo);

    public ArrayList<PrestamosDTO> listarTodos();

    public Integer modificar(PrestamosDTO prestamo);

    public Integer eliminar(PrestamosDTO prestamo);

    ArrayList<PrestamosDTO> listarPorIdPersona(int idPersona);

}
