package Utils;

import Exceptions.CampoVacioException;

import javax.swing.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Clase `Utilidades` que proporciona métodos de utilidad para validaciones y otras operaciones comunes.
 */
public class Utilidades {

    /**
     * Valida un dato utilizando una expresión regular.
     *
     * @param dato El dato a validar.
     * @param expresionRegular La expresión regular que define el formato válido.
     * @return `true` si el dato cumple con la expresión regular, `false` en caso contrario.
     * @throws CampoVacioException Si el dato es nulo o está vacío.
     */
    public static boolean validarDato(String dato, String expresionRegular) throws CampoVacioException {
        if (dato == null || dato.trim().isEmpty()) {
            throw new CampoVacioException("");
        }
        if (dato.isEmpty())
            throw new CampoVacioException("");
        Pattern pat = Pattern.compile(expresionRegular);
        Matcher mat = pat.matcher(dato);
        return mat.matches();
    }
}