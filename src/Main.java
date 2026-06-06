import controlador.JuegoControlador;

public class Main {
    public static void main(String[] args) {
        System.out.println("Iniciando Buscaminas POO");
        
        JuegoControlador controlador = new JuegoControlador();
        controlador.iniciarJuego();
    }
}