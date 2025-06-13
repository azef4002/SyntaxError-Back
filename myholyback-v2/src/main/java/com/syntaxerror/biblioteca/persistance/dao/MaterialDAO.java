package com.syntaxerror.biblioteca.persistance.dao;

import com.syntaxerror.biblioteca.model.MaterialesDTO;
import java.util.ArrayList;
import java.util.List;

public interface MaterialDAO { //falta tabla intermedia

    public Integer insertar(MaterialesDTO material);

    public MaterialesDTO obtenerPorId(Integer idMaterial);

    public ArrayList<MaterialesDTO> listarTodos();

    public Integer modificar(MaterialesDTO material);

    public Integer eliminar(MaterialesDTO material);

    public ArrayList<MaterialesDTO> listarPorTituloConteniendo(String texto);

    ArrayList<MaterialesDTO> listarVigentesPorTituloConteniendo(String texto);

    ArrayList<MaterialesDTO> listarPorSede(Integer idSede);

    List<MaterialesDTO> listarVigentesPorSede(Integer idSede);

}
