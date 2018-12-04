/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mainahorcado;
    
/**
 *
 * @author Omar
 */
public class BancoDePalabrasEnMemoria implements BancoDePalabras {

    int posicionArregloPalabras = 0;

    /**
     *
     * @return
     */
    @Override
    public String getPalabraAAdivinar() {
        boolean validation = false;
        int numeroAleatorio = 0;
        int[] arrayNumeroAleatorio = new int[Biblioteca.palabras.size()];

        while (!validation) {
            int coincidencias = 0;
            numeroAleatorio = (int) (Math.random() * Biblioteca.palabras.size());
            arrayNumeroAleatorio[posicionArregloPalabras] = numeroAleatorio;

            for (int i = 0; i < arrayNumeroAleatorio.length; i++) {
                if (arrayNumeroAleatorio[i] == numeroAleatorio) {
                    coincidencias++;
                }
            }
            if (coincidencias > 1) {
                validation = false;
            } else {
                validation = true;
            }
        }

        String palabra = Biblioteca.palabras.get(numeroAleatorio);

        if (posicionArregloPalabras == Biblioteca.palabras.size()) {
            posicionArregloPalabras = 0;
        }
        posicionArregloPalabras++;
        return palabra;
    }

}
