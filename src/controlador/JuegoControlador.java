package controlador;

import modelo.Tablero;
import vista.VistaConsola;
import excepciones.CasillaYaDescubiertaException;
import java.util.Scanner;

public class JuegoControlador {
    private Tablero tablero;
    private VistaConsola vista;
    private Scanner scanner;

    public JuegoControlador() {
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

        // Lanzar nuestra excepción personalizada si la casilla ya fue descubierta
        if (tablero.getCasillas()[fila][columna].isEstaDescubierta()) {
            throw new CasillaYaDescubiertaException("¡La casilla " + entrada + " ya fue descubierta previamente!");
        }

        // Si pasa todas las validaciones, descubrimos la casilla temporalmente
        tablero.getCasillas()[fila][columna].setEstaDescubierta(true);
        System.out.println("\n¡Casilla " + entrada + " descubierta con éxito!");
    }
}