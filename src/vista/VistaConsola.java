package vista;

import modelo.Casilla;
import modelo.Tablero;

public class VistaConsola {

    public void imprimirTablero(Tablero tablero) {
        Casilla[][] casillas = tablero.getCasillas();
        int filas = tablero.getFilas();
        int columnas = tablero.getColumnas();

        System.out.println("\n  1 2 3 4 5 6 7 8 9 10");
        
        for (int i = 0; i < filas; i++) {
            // Convertimos el número de fila a letras (A, B, C...)
            char letraFila = (char) ('A' + i);
            System.out.print(letraFila + " ");

            for (int j = 0; j < columnas; j++) {
                Casilla c = casillas[i][j];
                
                if (c.isEstaDescubierta()) {
                    if (c.isEsMina()) {
                        System.out.print("X ");
                    } else if (c.getMinasAlrededor() > 0) {
                        System.out.print(c.getMinasAlrededor() + " ");
                    } else {
                        System.out.print("V ");
                    }
                } else {
                    if (c.isEstaMarcada()) {
                        System.out.print("M "); // M de Marcada (Bandera)
                    } else {
                        System.out.print("- "); // Casilla cubierta
                    }
                }
            }
            System.out.println();
        }
    }
}