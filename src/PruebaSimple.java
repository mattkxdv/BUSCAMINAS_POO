import modelo.Tablero;

public class PruebaSimple {
    public static void main(String[] args) {
        Tablero t = new Tablero();
        
        // Probamos si tiene 100 casillas
        int total = t.getFilas() * t.getColumnas();
        System.out.println("¿Tiene 100 casillas? " + (total == 100));
        
        // Probamos las minas
        int minas = 0;
        for (int i=0; i<10; i++) 
            for (int j=0; j<10; j++) 
                if (t.getCasillas()[i][j].isEsMina()) minas++;
        
        System.out.println("¿Tiene 10 minas? " + (minas == 10));
    }
}