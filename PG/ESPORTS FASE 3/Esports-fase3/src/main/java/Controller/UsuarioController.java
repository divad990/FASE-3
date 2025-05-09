package Controller;

import Model.Usuario;
import Model.DAO.UsuarioDAO;
import Utils.BaseDatos;

/**
 * Clase `UsuarioController` que actúa como controlador para gestionar las operaciones relacionadas con los usuarios.
 * Se comunica con el DAO para realizar operaciones CRUD sobre los usuarios.
 */
public class UsuarioController {

    private final UsuarioDAO usuarioDAO; // DAO para interactuar con la base de datos de usuarios

    /**
     * Constructor de la clase `UsuarioController`.
     * Inicializa el DAO de usuarios con la conexión a la base de datos.
     */
    public UsuarioController() {
        this.usuarioDAO = new UsuarioDAO(BaseDatos.getEm(), BaseDatos.getT());
    }

    /**
     * Busca un usuario en la base de datos por su nombre de usuario.
     *
     * @param username El nombre de usuario a buscar.
     * @return Un objeto `Usuario` que representa al usuario encontrado.
     * @throws Exception Sí ocurre un error durante la búsqueda.
     */
    public Usuario buscarUsuario(String username) throws Exception {
        Usuario usuario = new Usuario();
        usuario.setUsername(username);
        usuario = usuarioDAO.buscar(usuario);
        return usuario;
    }
}