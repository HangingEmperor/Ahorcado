package mainahorcado;

/**
 *
 * @author Omar Flores Salazar
 */
public class Palabra {

    /**
     * Metodo que se encarga de dar una palabra aleatoria.
     *
     * @see Palabra#palabraAleatoria()
     * @return Un numero entero generado aleatoriamente.
     */
    public static int palabraAleatoria() {
        int numero = (int) (Math.random() * Biblioteca.palabras.size());
        return numero;
    }

    /**
     *
     * @return
     */
    public static String nuevaPalabra() {
        if (Juego.contador >= Biblioteca.palabras.size()) {
            Juego.contador = 0;
        }
        Juego.contador = Palabra.palabraAleatoria();

        System.out.println(Juego.contador);
        System.out.println(Biblioteca.palabras.get(Juego.contador));

        int tamaño = Biblioteca.palabras.get(Juego.contador).length();
        Juego.guiones = "";
        Juego.sustPalabras = Biblioteca.palabras.get(Juego.contador);

        for (int i = 0; i < tamaño; i++) {
            Juego.guiones += "-";
        }
        return Juego.guiones;
    }
}
