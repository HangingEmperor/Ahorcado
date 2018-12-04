package mainahorcado;

import javax.swing.Timer;

/**
 *
 * @author Omar Flores Salazar
 */
public class MainAhorcado {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        Archivo a = new Archivo();
        a.abrirArchivo("palabrasBD.txt");

        Ventana ventana = new Ventana();
        ventana.setVisible(true);

        Display window = new Display();
        Timer miTemporizador = new Timer(1000, window);
        window.setTimer(miTemporizador);
        miTemporizador.start();

        if (!miTemporizador.isRunning()) {
            System.exit(0);
        }
    }
}
