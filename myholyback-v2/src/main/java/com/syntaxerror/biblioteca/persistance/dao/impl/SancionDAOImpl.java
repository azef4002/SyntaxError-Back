package com.syntaxerror.biblioteca.persistance.dao.impl;

import com.syntaxerror.biblioteca.persistance.dao.impl.base.DAOImplBase;
import com.syntaxerror.biblioteca.model.PrestamosDTO;
import com.syntaxerror.biblioteca.model.SancionesDTO;
import com.syntaxerror.biblioteca.model.enums.TipoSancion;
import com.syntaxerror.biblioteca.persistance.dao.impl.util.Columna;
import java.sql.Date;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import com.syntaxerror.biblioteca.persistance.dao.SancionDAO;

public class SancionDAOImpl extends DAOImplBase implements SancionDAO {

    private SancionesDTO sancion;

    public SancionDAOImpl() {
        super("BIB_SANCIONES");
        this.retornarLlavePrimaria = true;
        this.sancion = null;
    }

    @Override
    protected void configurarListaDeColumnas() {
        this.listaColumnas.add(new Columna("ID_SANCION", true, true));
        this.listaColumnas.add(new Columna("TIPO", false, false));
        this.listaColumnas.add(new Columna("FECHA", false, false));
        this.listaColumnas.add(new Columna("MONTO", false, false));
        this.listaColumnas.add(new Columna("DURACION", false, false));
        this.listaColumnas.add(new Columna("DESCRIPCION", false, false));
        this.listaColumnas.add(new Columna("PRESTAMO_IDPRESTAMO", false, false));
    }

    @Override
    protected void incluirValorDeParametrosParaInsercion() throws SQLException {

        //si es autoincremental, se salta el (1,ID)
        this.statement.setString(1, this.sancion.getTipo().name());
        this.statement.setDate(2, new Date(this.sancion.getFecha().getTime()));
        this.statement.setDouble(3, this.sancion.getMonto());
        this.statement.setDate(4, new Date(this.sancion.getDuracion().getTime()));
        this.statement.setString(5, this.sancion.getDescripcion());
        this.statement.setInt(6, this.sancion.getPrestamo().getIdPrestamo());
    }

    @Override
    protected void incluirValorDeParametrosParaModificacion() throws SQLException {

        this.statement.setString(1, this.sancion.getTipo().name());
        this.statement.setDate(2, new Date(this.sancion.getFecha().getTime()));
        this.statement.setDouble(3, this.sancion.getMonto());
        this.statement.setDate(4, new Date(this.sancion.getDuracion().getTime()));
        this.statement.setString(5, this.sancion.getDescripcion());
        this.statement.setInt(6, this.sancion.getPrestamo().getIdPrestamo());
        this.statement.setInt(7, this.sancion.getIdSancion());
        //En modificar el ID va al ultimo
    }

    @Override
    protected void incluirValorDeParametrosParaEliminacion() throws SQLException {
        this.statement.setInt(1, this.sancion.getIdSancion());
        //Para eliminar solo va el id
    }

    @Override
    protected void incluirValorDeParametrosParaObtenerPorId() throws SQLException {
        this.statement.setInt(1, this.sancion.getIdSancion());
        //Para obtener por Id igual solo el id
    }

    @Override
    protected void instanciarObjetoDelResultSet() throws SQLException {
        this.sancion = new SancionesDTO();
        this.sancion.setIdSancion(this.resultSet.getInt("ID_SANCION"));
        this.sancion.setTipo(TipoSancion.valueOf(this.resultSet.getString("TIPO")));
        this.sancion.setFecha(this.resultSet.getDate("FECHA"));
        this.sancion.setMonto(this.resultSet.getDouble("MONTO"));
        this.sancion.setDuracion(this.resultSet.getDate("DURACION"));
        this.sancion.setDescripcion(this.resultSet.getString("DESCRIPCION"));

        // Crear objetos DTO básicos para las relaciones
        PrestamosDTO prestamo = new PrestamosDTO();
        prestamo.setIdPrestamo(this.resultSet.getInt("PRESTAMO_IDPRESTAMO"));
        this.sancion.setPrestamo(prestamo);
    }

    @Override
    protected void limpiarObjetoDelResultSet() {
        this.sancion = null;
    }

    @Override
    protected void agregarObjetoALaLista(List lista) throws SQLException {
        this.instanciarObjetoDelResultSet();
        lista.add(this.sancion);
    }

    @Override
    public Integer insertar(SancionesDTO sancion) {
        this.sancion = sancion;
        return super.insertar();
    }

    @Override
    public SancionesDTO obtenerPorId(Integer idSancion) {
        this.sancion = new SancionesDTO();
        this.sancion.setIdSancion(idSancion);
        super.obtenerPorId();
        return this.sancion;
    }

    @Override
    public ArrayList<SancionesDTO> listarTodos() {
        return (ArrayList<SancionesDTO>) super.listarTodos();
    }

    @Override
    public Integer modificar(SancionesDTO sancion) {
        this.sancion = sancion;
        return super.modificar();
    }

    @Override
    public Integer eliminar(SancionesDTO sancion) {
        this.sancion = sancion;
        return super.eliminar();
    }

    @Override
    public ArrayList<SancionesDTO> listarSancionesPorPersona(int idPersona) {
        String sql = """
        SELECT %s
        FROM BIB_SANCIONES s
        JOIN BIB_PRESTAMOS p ON s.PRESTAMO_IDPRESTAMO = p.ID_PRESTAMO
        WHERE p.PERSONA_IDPERSONA = ?
    """.formatted(this.generarListaDeCamposConAlias("s"));

        return (ArrayList<SancionesDTO>) super.listarTodos(
                sql,
                (params) -> {
                    try {
                        this.statement.setInt(1, idPersona);
                    } catch (SQLException e) {
                        System.err.println("Error en listarSancionesPorPersona: " + e.getMessage());
                    }
                },
                null
        );
    }

    @Override
    public ArrayList<SancionesDTO> listarSancionesActivasPorPersona(int idPersona) {
        String sql = """
        SELECT %s
        FROM BIB_SANCIONES s
        JOIN BIB_PRESTAMOS p ON s.PRESTAMO_IDPRESTAMO = p.ID_PRESTAMO
        WHERE p.PERSONA_IDPERSONA = ?
          AND s.DURACION > CURRENT_DATE
    """.formatted(this.generarListaDeCamposConAlias("s"));

        return (ArrayList<SancionesDTO>) super.listarTodos(
                sql,
                (params) -> {
                    try {
                        this.statement.setInt(1, idPersona);
                    } catch (SQLException e) {
                        System.err.println("Error en listarSancionesActivasPorPersona: " + e.getMessage());
                    }
                },
                null
        );
    }

}
