package mainahorcado;

import java.util.ArrayList;

/**
 *
 * @author Omar
 */
public class Biblioteca {

    /**
     * Diccionario de palabras que se van a generar en el juego.
 */
    static ArrayList<String> palabras = new ArrayList<String>();

    /**
     *
     * @param palabra
     */
    public static void agregarPalabras(String palabra) {
        palabras.add(palabra);
    }

}
