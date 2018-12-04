package mainahorcado;

import java.awt.Color;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;

/**
 *
 * @author Omar
 */
public class Juego {

    static String sustPalabras = "";
    static String guiones = "";
    static Boolean adivino;
    static int intentos = 0;
    static int cambioImagen = 1;
    static int contador = 0;

    /**
     *
     * @param letraPresionada
     */
    public void inGame(String letraPresionada) {
        adivino = false;
        for (int i = 0; i < Biblioteca.palabras.get(contador).length(); i++) {
            if ((letraPresionada).equals(Character.toString(Biblioteca.palabras.get(contador).charAt(i)))) {
                adivino = true;
                int posicion = sustPalabras.indexOf(letraPresionada);
                String textoPanel = Ventana.palabra.getText();
                char textCharPanel[] = textoPanel.toCharArray();
                char sust_palabrasChar[] = sustPalabras.toCharArray();

                String new_textoPanel = "";
                String new_sustPalabras = "";
                for (int j = 0; j < textCharPanel.length; j++) {
                    if (j == posicion) {
                        new_textoPanel += letraPresionada;
                        new_sustPalabras += "-";
                    } else {
                        new_sustPalabras += sust_palabrasChar[j];
                        new_textoPanel += textCharPanel[j];
                    }
                }
                sustPalabras = new_sustPalabras;
                Ventana.palabra.setText(new_textoPanel);

                for (int k = 0; k < 26; k++) {
                    if (letraPresionada.equals(Ventana.letrasBtn[k].getText())) {
                        Ventana.letrasBtn[k].setBackground(Color.GREEN);
                    }
                }
            }
        }

        if (!adivino) {
            intentos++;
            cambioImagen++;
            if (cambioImagen <= 7) {
                Ventana.img.setIcon(new ImageIcon("hangman" + Integer.toString(cambioImagen) + ".png"));
            }
            for (int k = 0; k < 26; k++) {
                if (letraPresionada.equals(Ventana.letrasBtn[k].getText())) {
                    Ventana.letrasBtn[k].setBackground(Color.RED);
                }
            }
        }
        if (intentos >= 6) {
            gameOver();
        } else if (Biblioteca.palabras.get(contador).equals(Ventana.palabra.getText())) {
            winGame();
        }

    }

    /**
     *
     * Método que muestra un mensaje al usuario que ha ganado en el juego.
     *
     * @see Ventana#winGame()
     */
    public void winGame() {
        int resp = JOptionPane.showConfirmDialog(null, "Felicidades, has ganado. \n"
                + "¿Deseas reiniciar?", "¡Has Ganado!", JOptionPane.YES_NO_OPTION);
        if (resp == 0) {
            restartGame();
        } else {
            System.exit(0);
        }
        restartGame();
    }

    /**
     *
     * Método que muestra un mensaje al usuario que ha perdido en el juego.
     *
     * @see Ventana#gameOver()
     */
    public void gameOver() {
        Ventana.palabra.setText(Biblioteca.palabras.get(contador));
        int resp = JOptionPane.showConfirmDialog(null, "Has perdido. \n "
                + "¿Deseas reiniciar?", "¡Has perdido!", JOptionPane.YES_NO_OPTION);
        if (resp == 0) {
            restartGame();
        } else {
            System.exit(0);
        }
    }

    /**
     *
     * Método que reinicia el juego.
     *
     * @see Ventana#restartGame()
     */
    public static void restartGame() {
        Archivo.guardarLog();
        Display.miTemporizador.restart();
        Display.second = 0;
        Display.minute = 0;
        Display.hour = 0;
        Display.isTimerRunning = false;
        Display.secondTotal = 0;

        Ventana.img.setIcon(new ImageIcon("hangman1.png"));
        intentos = 0;
        cambioImagen = 1;
        for (int k = 0; k < 26; k++) {
            Ventana.letrasBtn[k].setBackground(Color.WHITE);
        }
        Ventana.insertarPalabra();
    }
}
