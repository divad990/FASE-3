package Exceptions;

/**
 * Excepción personalizada `CampoVacioException`.
 * Se utiliza para indicar que un campo obligatorio está vacío.
 */
public class CampoVacioException extends Exception {

    /**
     * Constructor de la clase `CampoVacioException`.
     *
     * @param mensaje El mensaje de error que describe la excepción.
     */
    public CampoVacioException(String mensaje) {
        super(mensaje);
    }
}