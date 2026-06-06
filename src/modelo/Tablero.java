package modelo;

import java.util.Random;

public class Tablero {
    private Casilla[][] casillas;
    private final int FILAS = 10;
    private final int COLUMNAS = 10;
    private final int NUM_MINAS = 10;

    // Constructor
    public Tablero() {
        casillas = new Casilla[FILAS][COLUMNAS];
        inicializarTablero();
        colocarMinas();
    }

    // 1. Inicializa cada cuadrito del tablero creando un objeto Casilla
    private void inicializarTablero() {
        for (int i = 0; i < FILAS; i++) {
            for (int j = 0; j < COLUMNAS; j++) {
                casillas[i][j] = new Casilla();
            }
        }
    }

    // 2. Coloca 10 minas aleatoriamente
    private void colocarMinas() {
        Random rand = new Random();
        int minasColocadas = 0;

        while (minasColocadas < NUM_MINAS) {
            int fila = rand.nextInt(FILAS);
            int columna = rand.nextInt(COLUMNAS);

            // Si la casilla NO es mina, le ponemos una
            if (!casillas[fila][columna].isEsMina()) {
                casillas[fila][columna].setEsMina(true);
                minasColocadas++;
            }
        }
    }

    // Getter para obtener la matriz de casillas
    public Casilla[][] getCasillas() {
        return casillas;
    }
    
    public int getFilas() {
        return FILAS;
    }
    
    public int getColumnas() {
        return COLUMNAS;
    }
}