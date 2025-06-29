package com.syntaxerror.biblioteca.persistance.dao;

import com.syntaxerror.biblioteca.model.NivelesInglesDTO;
import java.util.ArrayList;
import java.util.List;


public interface NivelInglesDAO {
    
    public Integer insertar(NivelesInglesDTO nivelDeIngles);

    public NivelesInglesDTO obtenerPorId(Integer idNivel);

    public ArrayList<NivelesInglesDTO> listarTodos();

    public Integer modificar(NivelesInglesDTO nivelDeIngles);

    public Integer eliminar(NivelesInglesDTO nivelDeIngles);
    
    public List<NivelesInglesDTO> listarNombresNiveles();
}
