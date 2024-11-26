/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit5TestClass.java to edit this template
 */
package modelo;

import java.util.ArrayList;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/**
 *
 * @author bejar
 */
public class TableroTest {

    private Tablero tablero; // La clase que contiene el método movimientosPeon

    @BeforeEach
    void setUp() {
        // Configura el tablero y las fichas para los tests
        tablero = new Tablero();
        tablero.iniciarCuadricula();
    }

    @Test
    void testMovimientosPeonBlanco() {
        tablero.iniciarFichas();
        ArrayList<int[]> movimientos = tablero.movimientosPeon(1, 1);
        for (int[] movimiento : movimientos) {
            System.out.println("[" + movimiento[0] + "," + movimiento[1] + "]");
        }
        assertNotNull(movimientos);
        assertEquals(2, movimientos.size()); // Ajusta según el caso esperado
        System.out.println("");
        // Verifica que los movimientos sean correctos
        assertTrue(containsMovement(movimientos, 1, 2));
        assertTrue(containsMovement(movimientos, 1, 3));
    }

    @Test
    void testMovimientosPeonNegro() {

        tablero.iniciarFichas();
        ArrayList<int[]> movimientos = tablero.movimientosPeon(3, 6);
        for (int[] movimiento : movimientos) {
            System.out.println("[" + movimiento[0] + "," + movimiento[1] + "]");
        }
        assertNotNull(movimientos);
        assertEquals(2, movimientos.size()); // Ajusta según el caso esperado

        // Verifica que los movimientos sean correctos
        assertTrue(containsMovement(movimientos, 3, 4));
        assertTrue(containsMovement(movimientos, 3, 5));
    }

    @Test
    void testMovimientosTorrre() {

        tablero.getCasillas()[4][4].setFicha(new Ficha(1, 1));
        ArrayList<int[]> movimientos = tablero.movimientosTorre(4, 4);
        for (int[] movimiento : movimientos) {
            System.out.println("[" + movimiento[0] + "," + movimiento[1] + "]");
        }
        assertNotNull(movimientos);
        assertEquals(14, movimientos.size()); // Ajusta según el caso esperado

        // Verifica que los movimientos sean correctos
        assertTrue(containsMovement(movimientos, 4, 5));
        assertTrue(containsMovement(movimientos, 5, 4));
        assertTrue(containsMovement(movimientos, 4, 3));
        assertTrue(containsMovement(movimientos, 3, 4));
    }

    @Test
    void testMovimientosCaballo() {

        tablero.getCasillas()[4][4].setFicha(new Ficha(2,  1));
        ArrayList<int[]> movimientos = tablero.movimientosCaballo(4, 4);
        for (int[] movimiento : movimientos) {
            System.out.println("[" + movimiento[0] + "," + movimiento[1] + "]");
        }
        assertNotNull(movimientos);
        assertEquals(8, movimientos.size()); // Ajusta según el caso esperado

        // Verifica que los movimientos sean correctos
        assertTrue(containsMovement(movimientos, 5, 6));
        assertTrue(containsMovement(movimientos, 3, 6));
        assertTrue(containsMovement(movimientos, 6, 3));
        assertTrue(containsMovement(movimientos, 2, 3));
    }

    @Test
    void testMovimientosAlfil() {
        tablero.getCasillas()[4][4].setFicha(new Ficha(3,  1));
        ArrayList<int[]> movimientos = tablero.movimientosAlfil(4, 4);
        for (int[] movimiento : movimientos) {
            System.out.println("[" + movimiento[0] + "," + movimiento[1] + "]");
        }
        System.out.println(movimientos.size());
        assertNotNull(movimientos); // Ajusta según el caso esperado

        // Verifica que los movimientos sean correctos
        assertTrue(containsMovement(movimientos, 5, 5));
        assertTrue(containsMovement(movimientos, 3, 3));
        assertTrue(containsMovement(movimientos, 2, 2));
        assertTrue(containsMovement(movimientos, 1, 1));
    }

    @Test
    void testMovimientosReina() {

        tablero.getCasillas()[4][4].setFicha(new Ficha(5, 1));
        ArrayList<int[]> movimientos = tablero.movimientosReina(4, 4);
        for (int[] movimiento : movimientos) {
            System.out.println("[" + movimiento[0] + "," + movimiento[1] + "]");
        }
        assertNotNull(movimientos);
        assertEquals(27, movimientos.size()); // Ajusta según el caso esperado

        // Verifica que los movimientos sean correctos
        assertTrue(containsMovement(movimientos, 4, 5));
        assertTrue(containsMovement(movimientos, 5, 4));
        assertTrue(containsMovement(movimientos, 4, 3));
        assertTrue(containsMovement(movimientos, 3, 4));
        assertTrue(containsMovement(movimientos, 5, 5));
        assertTrue(containsMovement(movimientos, 3, 3));
        assertTrue(containsMovement(movimientos, 2, 2));
        assertTrue(containsMovement(movimientos, 1, 1));
    }

    @Test
    void testMovimientosRey() {

        tablero.getCasillas()[4][4].setFicha(new Ficha(4, 1));
        ArrayList<int[]> movimientos = tablero.movimientosRey(4, 4);
        for (int[] movimiento : movimientos) {
            System.out.println("[" + movimiento[0] + "," + movimiento[1] + "]");
        }
        assertNotNull(movimientos);
        assertEquals(8, movimientos.size()); // Ajusta según el caso esperado

        // Verifica que los movimientos sean correctos
        assertTrue(containsMovement(movimientos, 4, 5));
        assertTrue(containsMovement(movimientos, 5, 4));
        assertTrue(containsMovement(movimientos, 4, 3));
        assertTrue(containsMovement(movimientos, 3, 4));
    }

    @Test
    void testMovimientosPeonBlancoAtaque() {
        tablero.getCasillas()[4][4].setFicha(new Ficha(0, 1));
        tablero.getCasillas()[5][5].setFicha(new Ficha(0,  2));
        tablero.getCasillas()[5][3].setFicha(new Ficha(0,  2));
        ArrayList<int[]> movimientos = tablero.movimientosPeonAtaque(4, 4);
        for (int[] movimiento : movimientos) {
            System.out.println("[" + movimiento[0] + "," + movimiento[1] + "]");
        }
        assertNotNull(movimientos);
        assertEquals(2, movimientos.size()); // Ajusta según el caso esperado
        System.out.println("");
        // Verifica que los movimientos sean correctos
        assertTrue(containsMovement(movimientos, 3, 5));
        assertTrue(containsMovement(movimientos, 5, 5));
    }

    @Test
    void testMovimientosPeonNegroAtaque() {
        tablero.getCasillas()[4][4].setFicha(new Ficha(0,  2));
        tablero.getCasillas()[3][5].setFicha(new Ficha(0,  1));
        tablero.getCasillas()[3][3].setFicha(new Ficha(0, 1));
        ArrayList<int[]> movimientos = tablero.movimientosPeonAtaque(4, 4);
        for (int[] movimiento : movimientos) {
            System.out.println("[" + movimiento[0] + "," + movimiento[1] + "]");
        }
        assertNotNull(movimientos);
        assertEquals(2, movimientos.size()); // Ajusta según el caso esperado
        System.out.println("");
        // Verifica que los movimientos sean correctos
        assertTrue(containsMovement(movimientos, 3, 3));
        assertTrue(containsMovement(movimientos, 5, 3));
    }

    @Test
    void testMovimientosTorrreAtaque() {

        tablero.getCasillas()[4][4].setFicha(new Ficha(1,  1));
        tablero.getCasillas()[4][5].setFicha(new Ficha(0,  2));
        tablero.getCasillas()[5][4].setFicha(new Ficha(0, 2));
        ArrayList<int[]> movimientos = tablero.movimientosTorreAtaque(4, 4);
        for (int[] movimiento : movimientos) {
            System.out.println("[" + movimiento[0] + "," + movimiento[1] + "]");
        }
        System.out.println(tablero.toString());
        assertNotNull(movimientos);
        assertEquals(2, movimientos.size()); // Ajusta según el caso esperado

        // Verifica que los movimientos sean correctos
        assertTrue(containsMovement(movimientos, 5, 4));
        assertTrue(containsMovement(movimientos, 4, 5));
    }

    @Test
    void testMovimientosCaballoAtaque() {

        tablero.getCasillas()[4][4].setFicha(new Ficha(2,  1));
        tablero.getCasillas()[5][2].setFicha(new Ficha(0,  2));
        tablero.getCasillas()[5][6].setFicha(new Ficha(0, 2));
        ArrayList<int[]> movimientos = tablero.movimientosCaballoAtaque(4, 4);
        for (int[] movimiento : movimientos) {
            System.out.println("[" + movimiento[0] + "," + movimiento[1] + "]");
        }
        System.out.println(tablero.toString());
        assertNotNull(movimientos);
        assertEquals(2, movimientos.size()); // Ajusta según el caso esperado

        // Verifica que los movimientos sean correctos
        assertTrue(containsMovement(movimientos, 2, 5));
        assertTrue(containsMovement(movimientos, 6, 5));
    }

    @Test
    void testMovimientosAlfilAtaque() {

        tablero.getCasillas()[4][4].setFicha(new Ficha(3,  1));
        tablero.getCasillas()[3][3].setFicha(new Ficha(0,  2));
        tablero.getCasillas()[5][5].setFicha(new Ficha(0,  2));
        ArrayList<int[]> movimientos = tablero.movimientosAlfilAtaque(4, 4);
        for (int[] movimiento : movimientos) {
            System.out.println("[" + movimiento[0] + "," + movimiento[1] + "]");
        }
        System.out.println(tablero.toString());
        assertNotNull(movimientos);
        assertEquals(2, movimientos.size()); // Ajusta según el caso esperado

        // Verifica que los movimientos sean correctos
        assertTrue(containsMovement(movimientos, 5, 5));
        assertTrue(containsMovement(movimientos, 3, 3));
    }

    @Test
    void testMovimientosReyAtaque() {

        tablero.getCasillas()[4][4].setFicha(new Ficha(4,  1));
        tablero.getCasillas()[4][5].setFicha(new Ficha(0,  2));
        tablero.getCasillas()[5][4].setFicha(new Ficha(0, 2));
        ArrayList<int[]> movimientos = tablero.movimientosReyAtaque(4, 4);
        for (int[] movimiento : movimientos) {
            System.out.println("[" + movimiento[0] + "," + movimiento[1] + "]");
        }
        System.out.println(tablero.toString());
        assertNotNull(movimientos);
        assertEquals(2, movimientos.size()); // Ajusta según el caso esperado

        // Verifica que los movimientos sean correctos
        assertTrue(containsMovement(movimientos, 5, 4));
        assertTrue(containsMovement(movimientos, 4, 5));
    }

    @Test
    void testMovimientosReinaAtaque() {

        tablero.getCasillas()[0][3].setFicha(new Ficha(5,  1));
        tablero.getCasillas()[0][4].setFicha(new Ficha(4,  1));
//        tablero.casillas[5][5].ficha = new Ficha(0, 5, 5, 2);
        ArrayList<int[]> movimientos = tablero.movimientosReina(3, 0);
        for (int[] movimiento : movimientos) {
            System.out.println("[" + movimiento[0] + "," + movimiento[1] + "]");
        }
        System.out.println(tablero.toString());
        assertNotNull(movimientos);
//        assertEquals(0, movimientos.size()); // Ajusta según el caso esperado

        // Verifica que los movimientos sean correctos
        assertTrue(!containsMovement(movimientos, 4, 0));
//        assertTrue(!containsMovement(movimientos, 5, 5));
    }

    // Método auxiliar para verificar si una lista de movimientos contiene un movimiento específico
    private boolean containsMovement(ArrayList<int[]> movimientos, int x, int y) {
        for (int[] movimiento : movimientos) {
            if (movimiento[0] == x && movimiento[1] == y) {
                return true;
            }
        }
        return false;
    }
}

//    /**
//     * Test of toString method, of class Tablero.
//     */
//    @Test
//    public void testToString() {
//        System.out.println("toString");
//        Tablero instance = new Tablero();
//        String expResult = "";
//        String result = instance.toString();
//        assertEquals(expResult, result);
//        // TODO review the generated test code and remove the default call to fail.
//        fail("The test case is a prototype.");
//    }

