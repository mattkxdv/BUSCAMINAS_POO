package controlador;

import modelo.Tablero;
import vista.VistaConsola;

public class JuegoControlador {
    private Tablero tablero;
    private VistaConsola vista;

    public JuegoControlador() {
        // Inicializamos el modelo y la vista
        this.tablero = new Tablero();
        this.vista = new VistaConsola();
    }

    public void iniciarJuego() {
        // Por ahora, solo mandamos a imprimir el tablero inicial
        vista.imprimirTablero(tablero);
    }
}