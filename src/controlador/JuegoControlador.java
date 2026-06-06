package controlador;

import modelo.Tablero;
import modelo.Casilla;
import vista.VistaConsola;
import excepciones.CasillaYaDescubiertaException;
import java.util.Scanner;

public class JuegoControlador {
    private Tablero tablero;
    private VistaConsola vista;
    private Scanner scanner;

    public JuegoControlador() {
        // Inicializamos el modelo, la vista y el lector de teclado
        this.tablero = new Tablero();
        this.vista = new VistaConsola();
        this.scanner = new Scanner(System.in);
    }

    public void iniciarJuego() {
        boolean juegoTerminado = false;

        while (!juegoTerminado) {
            vista.imprimirTablero(tablero);
            
            System.out.print("\nIngresa una coordenada (ejemplo: A5) o 'S' para salir: ");
            String entrada = scanner.nextLine().toUpperCase();

            if (entrada.equals("S")) {
                System.out.println("Saliendo del juego...");
                break;
            }

            // Aquí manejamos las excepciones como pide la rúbrica
            try {
                procesarEntrada(entrada);
            } catch (CasillaYaDescubiertaException e) {
                System.out.println("\n[!] " + e.getMessage());
            } catch (ArrayIndexOutOfBoundsException e) {
                System.out.println("\n[!] Error: Coordenadas fuera del tablero. Usa letras A-J y números 1-10.");
            } catch (Exception e) {
                System.out.println("\n[!] Error: Formato incorrecto. Por favor sigue el formato, por ejemplo: A5");
            }
        }
        
        scanner.close();
    }

    private void procesarEntrada(String entrada) throws CasillaYaDescubiertaException {
        // Validar que la entrada tenga al menos 2 caracteres (letra y número)
        if (entrada.length() < 2) {
            throw new IllegalArgumentException();
        }

        // Convertir la letra a un índice de fila (A=0, B=1, C=2...)
        char letra = entrada.charAt(0);
        int fila = letra - 'A';
        
        // Convertir el resto del texto a un número de columna (restando 1 porque los índices empiezan en 0)
        int columna = Integer.parseInt(entrada.substring(1)) - 1;

        // Validar que las coordenadas estén dentro del tamaño del tablero
        if (fila < 0 || fila >= tablero.getFilas() || columna < 0 || columna >= tablero.getColumnas()) {
            throw new ArrayIndexOutOfBoundsException();
        }

        Casilla casillaActual = tablero.getCasillas()[fila][columna];

        // Lanzar nuestra excepción personalizada si la casilla ya fue descubierta
        if (casillaActual.isEstaDescubierta()) {
            throw new CasillaYaDescubiertaException("¡La casilla " + entrada + " ya fue descubierta previamente!");
        }

        // Si pasa todas las validaciones, descubrimos la casilla
        casillaActual.setEstaDescubierta(true);

        // LÓGICA DE DERROTA (Si pisó una mina)
        if (casillaActual.isEsMina()) {
            vista.imprimirTablero(tablero); // Imprime el tablero una última vez para mostrar la mina
            System.out.println("\n💥 ¡Booooom! Has pisado una mina en " + entrada + ". FIN DEL JUEGO 💥");
            System.exit(0); // Esto cierra el programa automáticamente
        }

        // LÓGICA DE VICTORIA (Si descubrió todo lo seguro)
        if (verificarVictoria()) {
            vista.imprimirTablero(tablero);
            System.out.println("\n🏆 ¡FELICIDADES! Has descubierto todas las casillas seguras. ¡GANASTE! 🏆");
            System.exit(0); // Cierra el programa
        }

        System.out.println("\n¡Casilla " + entrada + " descubierta con éxito!");
    }

    // Método que cuenta si ya abrimos todas las casillas que no son minas
    private boolean verificarVictoria() {
        int casillasSegurasDescubiertas = 0;
        int totalCasillasSeguras = (tablero.getFilas() * tablero.getColumnas()) - tablero.getNumMinas();

        for (int i = 0; i < tablero.getFilas(); i++) {
            for (int j = 0; j < tablero.getColumnas(); j++) {
                Casilla c = tablero.getCasillas()[i][j];
                // Si la casilla está descubierta y NO es una mina, sumamos 1
                if (c.isEstaDescubierta() && !c.isEsMina()) {
                    casillasSegurasDescubiertas++;
                }
            }
        }
        
        // Si las que descubrimos son iguales a las seguras totales, ganamos
        return casillasSegurasDescubiertas == totalCasillasSeguras;
    }
}