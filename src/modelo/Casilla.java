package modelo;

public class Casilla {
    // Atributos encapsulados (privados) 
    private boolean esMina;
    private boolean estaDescubierta;
    private boolean estaMarcada;
    private int minasAlrededor;

    // Constructor: Inicializa la casilla por defecto
    public Casilla() {
        this.esMina = false;
        this.estaDescubierta = false;
        this.estaMarcada = false;
        this.minasAlrededor = 0;
    }

    // Métodos Getters y Setters para acceder a los atributos 
    
    public boolean isEsMina() {
        return esMina;
    }

    public void setEsMina(boolean esMina) {
        this.esMina = esMina;
    }

    public boolean isEstaDescubierta() {
        return estaDescubierta;
    }

    public void setEstaDescubierta(boolean estaDescubierta) {
        this.estaDescubierta = estaDescubierta;
    }

    public boolean isEstaMarcada() {
        return estaMarcada;
    }

    public void setEstaMarcada(boolean estaMarcada) {
        this.estaMarcada = estaMarcada;
    }

    public int getMinasAlrededor() {
        return minasAlrededor;
    }

    public void setMinasAlrededor(int minasAlrededor) {
        this.minasAlrededor = minasAlrededor;
    }
}