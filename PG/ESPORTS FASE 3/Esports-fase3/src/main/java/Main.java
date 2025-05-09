/**
 * Clase principal `Main` que sirve como punto de entrada para la aplicación.
 * Se encarga de inicializar los controladores y de iniciar la vista principal.
 */
public class Main {

    private static Controller.ModeloController modeloController; // Controlador del modelo
    private static Controller.VistaController vistaController; // Controlador de la vista

    /**
     * Método principal de la aplicación.
     * Llama a la creación de objetos y luego inicia la vista principal.
     *
     * @param args Argumentos de línea de comandos (no utilizados en esta aplicación).
     */
    public static void main(String[] args) {
        crearObjetos();
        vistaController.iniciarPrincipal();
    }

    /**
     * Método que crea e inicializa los controladores de la aplicación.
     * Establece la relación entre el controlador del modelo y el controlador de la vista.
     */
    public static void crearObjetos() {
        modeloController = new Controller.ModeloController();
        vistaController = new Controller.VistaController();
        modeloController.setVistaController(vistaController);
    }
}