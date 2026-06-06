import static org.junit.Assert.*;
import org.junit.Test;
import modelo.Tablero;

public class TableroTest {

    @Test
    public void testTableroTiene100Casillas() {
        Tablero t = new Tablero();
        int totalCasillas = t.getFilas() * t.getColumnas();
        assertEquals(100, totalCasillas);
    }

    @Test
    public void testTableroTiene10Minas() {
        Tablero t = new Tablero();
        int minasContadas = 0;
        for (int i = 0; i < t.getFilas(); i++) {
            for (int j = 0; j < t.getColumnas(); j++) {
                if (t.getCasillas()[i][j].isEsMina()) {
                    minasContadas++;
                }
            }
        }
        assertEquals(10, minasContadas);
    }
}