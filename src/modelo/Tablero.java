package modelo;

import java.util.Random;

public class Tablero {
    private Casilla[][] casillas;
    private final int FILAS = 10;
    private final int COLUMNAS = 10;
    private final int NUM_MINAS = 10;

    public Tablero() {
        casillas = new Casilla[FILAS][COLUMNAS];
        inicializarTablero();
        colocarMinas();
        calcularMinasAdyacentes(); // ¡Nueva línea agregada!
    }

    private void inicializarTablero() {
        for (int i = 0; i < FILAS; i++) {
            for (int j = 0; j < COLUMNAS; j++) {
                casillas[i][j] = new Casilla();
            }
        }
    }

    private void colocarMinas() {
        Random rand = new Random();
        int minasColocadas = 0;
        while (minasColocadas < NUM_MINAS) {
            int fila = rand.nextInt(FILAS);
            int columna = rand.nextInt(COLUMNAS);
            if (!casillas[fila][columna].isEsMina()) {
                casillas[fila][columna].setEsMina(true);
                minasColocadas++;
            }
        }
    }

    // --- NUEVOS MÉTODOS ---
    
    private void calcularMinasAdyacentes() {
        for (int f = 0; f < FILAS; f++) {
            for (int c = 0; c < COLUMNAS; c++) {
                if (!casillas[f][c].isEsMina()) {
                    int minas = contarMinas(f, c);
                    casillas[f][c].setMinasAlrededor(minas);
                }
            }
        }
    }

    private int contarMinas(int fila, int columna) {
        int contador = 0;
        // Revisamos la cuadrícula de 3x3 alrededor de la casilla
        for (int i = -1; i <= 1; i++) {
            for (int j = -1; j <= 1; j++) {
                int f = fila + i;
                int c = columna + j;
                // Verificamos que no se salga de los bordes del tablero
                if (f >= 0 && f < FILAS && c >= 0 && c < COLUMNAS) {
                    if (casillas[f][c].isEsMina()) {
                        contador++;
                    }
                }
            }
        }
        return contador;
    }

    public Casilla[][] getCasillas() { return casillas; }
    public int getFilas() { return FILAS; }
    public int getColumnas() { return COLUMNAS; }
    public int getNumMinas() { return NUM_MINAS; }
}