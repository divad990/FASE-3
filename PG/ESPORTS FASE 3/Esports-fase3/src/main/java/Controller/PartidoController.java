package Controller;

import Model.DAO.EnfrentamientoDAO;
import Model.DAO.PartidoDAO;
import Model.Enfrentamiento;
import Model.Equipo;
import Utils.BaseDatos;

public class PartidoController {

    private final PartidoDAO partidoDAO;
    private final ModeloController modeloController;
    public PartidoController(ModeloController modeloController) {
        this.modeloController = modeloController;
        this.partidoDAO = new PartidoDAO(BaseDatos.getEm(), BaseDatos.getT());
    }

    public void crearPartido(Equipo e1, Equipo e2, Enfrentamiento e) {

        partidoDAO.crearPartido(e1,e);
        partidoDAO.crearPartido(e2,e);

    }
}
