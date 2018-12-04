/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mainahorcado;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;

/**
 *
 * @author Omar
 */
public class Archivo {

    static String n;

    /**
     *
     * @param path
     * @return
     */
    public String abrirArchivo(String path) {
        String aux = "";
        String texto = "";
        Boolean pass = true;
        while (pass) {
            try {
                File abre = new File(path);
                FileReader archivos = new FileReader(abre);
                BufferedReader lee = new BufferedReader(archivos);

                while ((aux = lee.readLine()) != null) {
                    Biblioteca.agregarPalabras(aux.toUpperCase());
                }
                lee.close();
                pass = false;
            } catch (IOException ex) {
                pass = true;
                JOptionPane.showMessageDialog(null, ex + ""
                        + "\nNo se ha encontrado el archivo principal. Por favor"
                        + " cree un nuevo archivo llamado palabrasBD.tct",
                        "Advertencia", JOptionPane.WARNING_MESSAGE);
            }
        }
        return texto;
    }

    public static void guardarLog() {
        try {
            String nombre = "";
            String aux = "";
            String texto = "";
            Boolean pass = true;
            File logPartidas = new File("logPartidas.txt");

            if (logPartidas != null) {
//                FileReader archivos = new FileReader(logPartidas);
//                BufferedReader lee = new BufferedReader(archivos);
//                while ((aux = lee.readLine()) != null) {
//                    texto += aux + "\n";
//                }

//                FileReader archivos = new FileReader(logPartidas.getAbsolutePath());
//                BufferedReader lee = new BufferedReader(archivos);
//                while ((aux = lee.readLine()) != null) {
//                    texto += aux + "\n";
//                }
//                lee.close();
//                System.out.println(texto);
                FileWriter save = new FileWriter(logPartidas.getAbsolutePath() + ".txt");
                save.write(texto);
                save.append("\r\n");
                save.write("hora:" + Display.horaActual.getText() + "\n");
                save.append("\r\n");
                save.write("palabra:" + Biblioteca.palabras.get(Juego.contador));
                save.append("\r\n");
                save.write("Tiempo:" + Display.tiempoPartida.getText());
                save.append("\r\n");
                save.close();
            }
        } catch (IOException ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(null,
                    "Su archivo no se ha guardado",
                    "Advertencia", JOptionPane.WARNING_MESSAGE);
        }
    }
}
