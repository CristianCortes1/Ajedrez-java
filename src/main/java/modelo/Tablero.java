/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package modelo;

import java.util.ArrayList;

/**
 *
 * @author bejar
 */
public final class Tablero {

    private Casilla[][] casillas;
//    Scanner sc;

    public Tablero() {
//        sc=new Scanner(System.in);
        iniciarCuadricula();
//        iniciarFichas();
    }

    //iniciamentos
    public void iniciarCuadricula() {
        setCasillas(new Casilla[8][8]);
        for (Casilla[] casilla : getCasillas()) {
            for (int j = 0; j < casilla.length; j++) {
                casilla[j] = new Casilla();
            }
        }

    }

    public void iniciarFichas() {
        for (int i = 0; i < 8; i++) {
            getCasillas()[1][i].setFicha(new Ficha(0,  1));
            getCasillas()[6][i].setFicha(new Ficha(0,  2));
        }
        //Fichas blancas
        getCasillas()[0][0].setFicha(new Ficha(1,  1));
        getCasillas()[0][1].setFicha(new Ficha(2,  1));
        getCasillas()[0][2].setFicha(new Ficha(3,  1));
        getCasillas()[0][3].setFicha(new Ficha(5,  1));
        getCasillas()[0][4].setFicha(new Ficha(4,  1));
        getCasillas()[0][5].setFicha(new Ficha(3,  1));
        getCasillas()[0][6].setFicha(new Ficha(2,  1));
        getCasillas()[0][7].setFicha(new Ficha(1,  1));

        //Fichas negras
        getCasillas()[7][0].setFicha(new Ficha(1,  2));
        getCasillas()[7][1].setFicha(new Ficha(2, 2));
        getCasillas()[7][2].setFicha(new Ficha(3,  2));
        getCasillas()[7][3].setFicha(new Ficha(5,  2));
        getCasillas()[7][4].setFicha(new Ficha(4,  2));
        getCasillas()[7][5].setFicha(new Ficha(3,  2));
        getCasillas()[7][6].setFicha(new Ficha(2,  2));
        getCasillas()[7][7].setFicha(new Ficha(1,  2));
    }

    //movimientos normales
    public ArrayList<int[]> movimientosPeon(int x, int y) {
    ArrayList<int[]> movimientos = new ArrayList<>();
    
    if (!comprobarFicha("Peon", x, y)) {
        return null;
    }
    
    Ficha peon = getCasillas()[y][x].getFicha();
    int color = peon.getCOLOR();
    
    if (color == 1) {
        try {
            if (y + 1 < 8 && !casillas[y + 1][x].isOcupado()) {
                movimientos.add(new int[]{x, y + 1});
                
                if (y == 1 && !casillas[y + 2][x].isOcupado()) {
                    movimientos.add(new int[]{x, y + 2});
                }
            }
        } catch (ArrayIndexOutOfBoundsException e) {
        }
    }
    else if (color == 2) {
        try {
            if (y - 1 >= 0 && !casillas[y - 1][x].isOcupado()) {
                movimientos.add(new int[]{x, y - 1});
                
                if (y == 6 && !casillas[y - 2][x].isOcupado()) {
                    movimientos.add(new int[]{x, y - 2});
                }
            }
        } catch (ArrayIndexOutOfBoundsException e) {
        }
    }

    return movimientos;
}


    public ArrayList<int[]> movimientosTorre(int x, int y) {
        ArrayList<int[]> movimientos = new ArrayList<>();
        if (!comprobarFicha("Torre", x, y)) {
            return null;
        }
        for (int i = y + 1; i < 8; i++) {
            if (!casillas[i][x].isOcupado()) {
                int movimiento[] = new int[2];
                movimiento[0] = x;
                movimiento[1] = i;
                movimientos.add(movimiento);
            } else if (getCasillas()[i][x].isOcupado()) {
                break;
            }
        }
        //derecha
        for (int i = x + 1; i < 8; i++) {
            if (!casillas[y][i].isOcupado()) {
                int movimiento[] = new int[2];
                movimiento[0] = i;
                movimiento[1] = y;
                movimientos.add(movimiento);
            } else if (getCasillas()[y][i].isOcupado()) {
                break;
            }
        }
        //arriba
        for (int i = y - 1; i >= 0; i--) {
            if (!casillas[i][x].isOcupado()) {
                int movimiento[] = new int[2];
                movimiento[0] = x;
                movimiento[1] = i;
                movimientos.add(movimiento);
            } else if (getCasillas()[i][x].isOcupado()) {
                break;
            }
        }
        //izquierda
        for (int i = x - 1; i >= 0; i--) {
            if (!casillas[y][i].isOcupado()) {
                int movimiento[] = new int[2];
                movimiento[0] = i;
                movimiento[1] = y;
                movimientos.add(movimiento);
            } else if (getCasillas()[y][i].isOcupado()) {
                break;
            }
        }
        return movimientos;
    }

    public ArrayList<int[]> movimientosCaballo(int x, int y) {
        ArrayList<int[]> movimientos = new ArrayList<>();
        if (!comprobarFicha("Caballo", x, y)) {
            return null;
        }
        try {
            if (!casillas[y + 2][x + 1].isOcupado()) {
                int movimiento[] = new int[2];
                movimiento[0] = x + 1;
                movimiento[1] = y + 2;
                movimientos.add(movimiento);
            }
        } catch (Exception e) {
        }
        try {
            if (!casillas[y + 2][x - 1].isOcupado()) {
                int movimiento[] = new int[2];
                movimiento[0] = x - 1;
                movimiento[1] = y + 2;
                movimientos.add(movimiento);
            }
        } catch (Exception e) {
        }
        try {
            if (!casillas[y - 2][x + 1].isOcupado()) {
                int movimiento[] = new int[2];
                movimiento[0] = x + 1;
                movimiento[1] = y - 2;
                movimientos.add(movimiento);
            }
        } catch (Exception e) {
        }
        try {
            if (!casillas[y - 2][x - 1].isOcupado()) {
                int movimiento[] = new int[2];
                movimiento[0] = x - 1;
                movimiento[1] = y - 2;
                movimientos.add(movimiento);
            }

        } catch (Exception e) {
        }
        try {
            if (!casillas[y + 1][x + 2].isOcupado()) {
                int movimiento[] = new int[2];
                movimiento[0] = x + 2;
                movimiento[1] = y + 1;
                movimientos.add(movimiento);
            }

        } catch (Exception e) {
        }
        try {
            if (!casillas[y + 1][x - 2].isOcupado()) {
                int movimiento[] = new int[2];
                movimiento[0] = x - 2;
                movimiento[1] = y + 1;
                movimientos.add(movimiento);

            }
        } catch (Exception e) {
        }
        try {
            if (!casillas[y - 1][x + 2].isOcupado()) {
                int movimiento[] = new int[2];
                movimiento[0] = x + 2;
                movimiento[1] = y - 1;
                movimientos.add(movimiento);
            }
        } catch (Exception e) {
        }
        try {
            if (!casillas[y - 1][x - 2].isOcupado()) {
                int movimiento[] = new int[2];
                movimiento[0] = x - 2;
                movimiento[1] = y - 1;
                movimientos.add(movimiento);
            }
        } catch (Exception e) {
        }
        return movimientos;
    }

    public ArrayList<int[]> movimientosAlfil(int x, int y) {
        ArrayList<int[]> movimientos = new ArrayList<>();
        if (!comprobarFicha("Alfil", x, y)) {
            return null;
        }
        // Diagonal abajo derecha
        for (int i = x + 1, j = y + 1; i < 8 && j < 8; i++, j++) {
            if (!casillas[j][i].isOcupado()) {
                int movimiento[] = new int[2];
                movimiento[0] = i;
                movimiento[1] = j;
                movimientos.add(movimiento);
            } else if (getCasillas()[j][i].isOcupado()) {
                break;
            }
        }

        // Diagonal abajo izquierda
        for (int i = x - 1, j = y + 1; i >= 0 && j < 8; i--, j++) {
            if (!casillas[j][i].isOcupado()) {
                int movimiento[] = new int[2];
                movimiento[0] = i;
                movimiento[1] = j;
                movimientos.add(movimiento);
            } else if (getCasillas()[j][i].isOcupado()) {
                break;
            }
        }

        // Diagonal arriba derecha
        for (int i = x + 1, j = y - 1; i < 8 && j >= 0; i++, j--) {
            if (!casillas[j][i].isOcupado()) {
                int movimiento[] = new int[2];
                movimiento[0] = i;
                movimiento[1] = j;
                movimientos.add(movimiento);
            } else if (getCasillas()[j][i].isOcupado()) {
                break;
            }
        }

        // Diagonal arriba izquierda
        for (int i = x - 1, j = y - 1; i >= 0 && j >= 0; i--, j--) {
            if (!casillas[j][i].isOcupado()) {
                int movimiento[] = new int[2];
                movimiento[0] = i;
                movimiento[1] = j;
                movimientos.add(movimiento);
            } else if (getCasillas()[j][i].isOcupado()) {
                break;
            }
        }
        return movimientos;
    }

    public ArrayList<int[]> movimientosRey(int x, int y) {
        ArrayList<int[]> movimientos = new ArrayList<>();

        if (!comprobarFicha("Rey", x, y)) {
            return null;
        }
        //abajo
        try {
            if (!casillas[y + 1][x].isOcupado()) {
                int[] movimiento = {x, y + 1};
                movimientos.add(movimiento);
            }
        } catch (Exception e) {
        }
//izquierda
        try {

            if (!casillas[y][x - 1].isOcupado()) {
                int[] movimiento = {x - 1, y};
                movimientos.add(movimiento);

            }
        } catch (Exception e) {
        }
//arriba
        try {
            if (!casillas[y - 1][x].isOcupado()) {
                int[] movimiento = {x, y - 1};
                movimientos.add(movimiento);
            }
        } catch (Exception e) {
        }
//derecha
        try {

            if (!casillas[y][x + 1].isOcupado()) {
                int[] movimiento = {x + 1, y};
                movimientos.add(movimiento);

            }
        } catch (Exception e) {
        }
//diag arr der
        try {

            if (!casillas[y - 1][x + 1].isOcupado()) {
                int[] movimiento = {x + 1, y - 1};
                movimientos.add(movimiento);

            }
        } catch (Exception e) {
        }
//diag abj der
        try {

            if (!casillas[y + 1][x + 1].isOcupado()) {
                int[] movimiento = {x + 1, y + 1};
                movimientos.add(movimiento);

            }
        } catch (Exception e) {
        }
//arr izq
        try {

            if (!casillas[y - 1][x - 1].isOcupado()) {
                int[] movimiento = {x - 1, y - 1};
                movimientos.add(movimiento);

            }
        } catch (Exception e) {
        }
//abj izq
        try {

            if (!casillas[y + 1][x - 1].isOcupado()) {
                int[] movimiento = {x - 1, y + 1};
                movimientos.add(movimiento);

            }
        } catch (Exception e) {
        }

        return movimientos;
    }

    public ArrayList<int[]> movimientosReina(int x, int y) {
        ArrayList<int[]> movimientos = new ArrayList<>();
        if (!comprobarFicha("Reina", x, y)) {
            return null;
        }
        //abajo
        for (int i = y + 1; i < 8; i++) {
            if (!casillas[i][x].isOcupado()) {
                int movimiento[] = new int[2];
                movimiento[0] = x;
                movimiento[1] = i;
                movimientos.add(movimiento);
            } else if (getCasillas()[i][x].isOcupado()) {
                break;
            }
        }
        //derecha
        for (int i = x + 1; i < 8; i++) {
            if (!casillas[y][i].isOcupado()) {
                int movimiento[] = new int[2];
                movimiento[0] = i;
                movimiento[1] = y;
                movimientos.add(movimiento);
            } else if (getCasillas()[y][i].isOcupado()) {
                break;
            }
        }
        //arriba
        for (int i = y - 1; i >= 0; i--) {
            if (!casillas[i][x].isOcupado()) {
                int movimiento[] = new int[2];
                movimiento[0] = x;
                movimiento[1] = i;
                movimientos.add(movimiento);
            } else if (getCasillas()[i][x].isOcupado()) {
                break;
            }
        }
        //izquierda
        for (int i = x - 1; i >= 0; i--) {
            if (!casillas[y][i].isOcupado()) {
                int movimiento[] = new int[2];
                movimiento[0] = i;
                movimiento[1] = y;
                movimientos.add(movimiento);
            } else if (getCasillas()[y][i].isOcupado()) {
                break;
            }
        }
        // Diagonal abajo derecha
        for (int i = x + 1, j = y + 1; i < 8 && j < 8; i++, j++) {
            if (!casillas[j][i].isOcupado()) {
                int movimiento[] = new int[2];
                movimiento[0] = i;
                movimiento[1] = j;
                movimientos.add(movimiento);
            } else if (getCasillas()[j][i].isOcupado()) {
                break;
            }
        }

        // Diagonal abajo izquierda
        for (int i = x - 1, j = y + 1; i >= 0 && j < 8; i--, j++) {
            if (!casillas[j][i].isOcupado()) {
                int movimiento[] = new int[2];
                movimiento[0] = i;
                movimiento[1] = j;
                movimientos.add(movimiento);
            } else if (getCasillas()[j][i].isOcupado()) {
                break;
            }
        }

        // Diagonal arriba derecha
        for (int i = x + 1, j = y - 1; i < 8 && j >= 0; i++, j--) {
            if (!casillas[j][i].isOcupado()) {
                int movimiento[] = new int[2];
                movimiento[0] = i;
                movimiento[1] = j;
                movimientos.add(movimiento);
            } else if (getCasillas()[j][i].isOcupado()) {
                break;
            }
        }

        // Diagonal arriba izquierda
        for (int i = x - 1, j = y - 1; i >= 0 && j >= 0; i--, j--) {
            if (!casillas[j][i].isOcupado()) {
                int movimiento[] = new int[2];
                movimiento[0] = i;
                movimiento[1] = j;
                movimientos.add(movimiento);
            } else if (getCasillas()[j][i].isOcupado()) {
                break;
            }
        }
        return movimientos;
    }

    //movimientos ataque
    public ArrayList<int[]> movimientosPeonAtaque(int x, int y) {
        ArrayList<int[]> movimientos = new ArrayList<>();
        if (!comprobarFicha("Peon", x, y)) {
            return null;
        }
        Ficha peon = getCasillas()[y][x].getFicha();
        if (peon.getCOLOR() == 1) {
            try {

                if (getCasillas()[y + 1][x + 1].isOcupado()
                        && getCasillas()[y + 1][x + 1].getFicha().getCOLOR() == 2) {
                    int movimiento[] = new int[2];
                    movimiento[0] = x + 1;
                    movimiento[1] = y + 1;
                    movimientos.add(movimiento);
                }
            } catch (Exception e) {
            }
            try {
                if (getCasillas()[y + 1][x - 1].isOcupado()
                        && getCasillas()[y + 1][x - 1].getFicha().getCOLOR() == 2) {
                    int movimiento[] = new int[2];
                    movimiento[0] = x - 1;
                    movimiento[1] = y + 1;
                    movimientos.add(movimiento);
                }
            } catch (Exception e) {
            }
        } else if (peon.getCOLOR() == 2) {
            try {
                if (getCasillas()[y - 1][x + 1].isOcupado()
                        && getCasillas()[y - 1][x + 1].getFicha().getCOLOR() == 1) {
                    int movimiento[] = new int[2];
                    movimiento[0] = x + 1;
                    movimiento[1] = y - 1;
                    movimientos.add(movimiento);
                }
            } catch (Exception e) {
            }
            try {
                if (getCasillas()[y - 1][x - 1].isOcupado()
                        && getCasillas()[y - 1][x - 1].getFicha().getCOLOR() == 1) {
                    int movimiento[] = new int[2];
                    movimiento[0] = x - 1;
                    movimiento[1] = y - 1;
                    movimientos.add(movimiento);
                }
            } catch (Exception e) {
            }
        }
        return movimientos;
    }

    public ArrayList<int[]> movimientosTorreAtaque(int x, int y) {
        ArrayList<int[]> movimientos = new ArrayList<>();
        if (!comprobarFicha("Torre", x, y)) {
            return null;
        }
        Ficha torre = getCasillas()[y][x].getFicha();
        // Verificar color de la torre
        int color = torre.getCOLOR();
        // Movimientos hacia abajo
        for (int i = y + 1; i < 8; i++) {
            if (getCasillas()[i][x].isOcupado()) {
                if (getCasillas()[i][x].getFicha().getCOLOR() != color) {
                    // Puede atacar si la pieza en la casilla es del color opuesto
                    int movimiento[] = new int[2];
                    movimiento[0] = x;
                    movimiento[1] = i;
                    movimientos.add(movimiento);
                }
                break; // Detenerse si se encuentra una pieza
            }
        }

        // Movimientos hacia arriba
        for (int i = y - 1; i >= 0; i--) {
            if (getCasillas()[i][x].isOcupado()) {
                if (getCasillas()[i][x].getFicha().getCOLOR() != color) {
                    // Puede atacar si la pieza en la casilla es del color opuesto
                    int movimiento[] = new int[2];
                    movimiento[0] = x;
                    movimiento[1] = i;
                    movimientos.add(movimiento);
                }
                break; // Detenerse si se encuentra una pieza
            }
        }

        // Movimientos hacia la derecha
        for (int i = x + 1; i < 8; i++) {
            if (getCasillas()[y][i].isOcupado()) {
                if (getCasillas()[y][i].getFicha().getCOLOR() != color) {
                    // Puede atacar si la pieza en la casilla es del color opuesto
                    int movimiento[] = new int[2];
                    movimiento[0] = i;
                    movimiento[1] = y;
                    movimientos.add(movimiento);
                }
                break; // Detenerse si se encuentra una pieza
            }
        }

        // Movimientos hacia la izquierda
        for (int i = x - 1; i >= 0; i--) {
            if (getCasillas()[y][i].isOcupado()) {
                if (getCasillas()[y][i].getFicha().getCOLOR() != color) {
                    // Puede atacar si la pieza en la casilla es del color opuesto
                    int movimiento[] = new int[2];
                    movimiento[0] = i;
                    movimiento[1] = y;
                    movimientos.add(movimiento);
                }
                break; // Detenerse si se encuentra una pieza
            }
        }

        return movimientos;
    }

    public ArrayList<int[]> movimientosCaballoAtaque(int x, int y) {
        ArrayList<int[]> movimientos = new ArrayList<>();
        if (!comprobarFicha("Caballo", x, y)) {
            return null;
        }
        Ficha caballo = getCasillas()[y][x].getFicha();
        int color = caballo.getCOLOR();
        try {
            if (getCasillas()[y + 2][x + 1].isOcupado()
                    && getCasillas()[y + 2][x + 1].getFicha().getCOLOR() != color) {
                int movimiento[] = new int[2];
                movimiento[0] = x + 1;
                movimiento[1] = y + 2;
                movimientos.add(movimiento);
            }
        } catch (Exception e) {
        }
        try {
            if (getCasillas()[y + 2][x - 1].isOcupado()
                    && getCasillas()[y + 2][x - 1].getFicha().getCOLOR() != color) {
                int movimiento[] = new int[2];
                movimiento[0] = x - 1;
                movimiento[1] = y + 2;
                movimientos.add(movimiento);
            }
        } catch (Exception e) {
        }
        try {
            if (getCasillas()[y - 2][x + 1].isOcupado()
                    && getCasillas()[y - 2][x + 1].getFicha().getCOLOR() != color) {
                int movimiento[] = new int[2];
                movimiento[0] = x + 1;
                movimiento[1] = y - 2;
                movimientos.add(movimiento);
            }
        } catch (Exception e) {
        }
        try {
            if (getCasillas()[y - 2][x - 1].isOcupado()
                    && getCasillas()[y - 2][x - 1].getFicha().getCOLOR() != color) {
                int movimiento[] = new int[2];
                movimiento[0] = x - 1;
                movimiento[1] = y - 2;
                movimientos.add(movimiento);
            }
        } catch (Exception e) {
        }
        try {
            if (getCasillas()[y + 1][x + 2].isOcupado()
                    && getCasillas()[y + 1][x + 2].getFicha().getCOLOR() != color) {
                int movimiento[] = new int[2];
                movimiento[0] = x + 2;
                movimiento[1] = y + 1;
                movimientos.add(movimiento);
            }
        } catch (Exception e) {
        }
        try {
            if (getCasillas()[y + 1][x - 2].isOcupado()
                    && getCasillas()[y + 1][x - 2].getFicha().getCOLOR() != color) {
                int movimiento[] = new int[2];
                movimiento[0] = x - 2;
                movimiento[1] = y + 1;
                movimientos.add(movimiento);

            }
        } catch (Exception e) {
        }
        try {

            if (getCasillas()[y - 1][x + 2].isOcupado()
                    && getCasillas()[y - 1][x + 2].getFicha().getCOLOR() != color) {
                int movimiento[] = new int[2];
                movimiento[0] = x + 2;
                movimiento[1] = y - 1;
                movimientos.add(movimiento);
            }
        } catch (Exception e) {
        }
        try {

            if (getCasillas()[y - 1][x - 2].isOcupado()
                    && getCasillas()[y - 1][x - 2].getFicha().getCOLOR() != color) {
                int movimiento[] = new int[2];
                movimiento[0] = x - 2;
                movimiento[1] = y - 1;
                movimientos.add(movimiento);
            }
        } catch (Exception e) {
        }

        return movimientos;
    }

    public ArrayList<int[]> movimientosAlfilAtaque(int x, int y) {
        ArrayList<int[]> movimientos = new ArrayList<>();
        if (!comprobarFicha("Alfil", x, y)) {
            return null;
        }
        Ficha alfil = getCasillas()[y][x].getFicha();
        int color = alfil.getCOLOR();

        // Diagonal abajo derecha
        for (int i = x + 1, j = y + 1; i < 8 && j < 8; i++, j++) {
            if (getCasillas()[j][i].isOcupado()) {
                if (getCasillas()[j][i].getFicha().getCOLOR() != color) {
                    int[] movimiento = {i, j};
                    movimientos.add(movimiento);
                }
                break; // Detenerse si se encuentra una pieza
            }
        }

        // Diagonal abajo izquierda
        for (int i = x - 1, j = y + 1; i >= 0 && j < 8; i--, j++) {
            if (getCasillas()[j][i].isOcupado()) {
                if (getCasillas()[j][i].getFicha().getCOLOR() != color) {
                    int[] movimiento = {i, j};
                    movimientos.add(movimiento);
                }
                break; // Detenerse si se encuentra una pieza
            }
        }

        // Diagonal arriba derecha
        for (int i = x + 1, j = y - 1; i < 8 && j >= 0; i++, j--) {
            if (getCasillas()[j][i].isOcupado()) {
                if (getCasillas()[j][i].getFicha().getCOLOR() != color) {
                    int[] movimiento = {i, j};
                    movimientos.add(movimiento);
                }
                break; // Detenerse si se encuentra una pieza
            }
        }

        // Diagonal arriba izquierda
        for (int i = x - 1, j = y - 1; i >= 0 && j >= 0; i--, j--) {
            if (getCasillas()[j][i].isOcupado()) {
                if (getCasillas()[j][i].getFicha().getCOLOR() != color) {
                    int[] movimiento = {i, j};
                    movimientos.add(movimiento);
                }
                break; // Detenerse si se encuentra una pieza
            }
        }

        return movimientos;
    }

    public ArrayList<int[]> movimientosReyAtaque(int x, int y) {
        ArrayList<int[]> movimientos = new ArrayList<>();
        if (!comprobarFicha("Rey", x, y)) {
            return null;
        }
        Ficha rey = getCasillas()[y][x].getFicha();
        int color = rey.getCOLOR();
//abajo
        try {
            if (getCasillas()[y + 1][x].isOcupado()
                    && getCasillas()[y + 1][x].getFicha().getCOLOR() != color) {
                int[] movimiento = {x, y + 1};
                movimientos.add(movimiento);
            }
        } catch (Exception e) {
        }
//izquierda
        try {

            if (getCasillas()[y][x - 1].isOcupado()
                    && getCasillas()[y][x - 1].getFicha().getCOLOR() != color) {
                int[] movimiento = {x - 1, y};
                movimientos.add(movimiento);

            }
        } catch (Exception e) {
        }
//arriba
        try {

            if (getCasillas()[y - 1][x].isOcupado()
                    && getCasillas()[y - 1][x].getFicha().getCOLOR() != color) {
                int[] movimiento = {x, y - 1};
                movimientos.add(movimiento);

            }
        } catch (Exception e) {
        }
//derecha
        try {

            if (getCasillas()[y][x + 1].isOcupado()
                    && getCasillas()[y][x + 1].getFicha().getCOLOR() != color) {
                int[] movimiento = {x + 1, y};
                movimientos.add(movimiento);

            }
        } catch (Exception e) {
        }
//diag arr der
        try {

            if (getCasillas()[y - 1][x + 1].isOcupado()
                    && getCasillas()[y - 1][x + 1].getFicha().getCOLOR() != color) {
                int[] movimiento = {x + 1, y - 1};
                movimientos.add(movimiento);

            }
        } catch (Exception e) {
        }
//diag abj der
        try {

            if (getCasillas()[y + 1][x + 1].isOcupado()
                    && getCasillas()[y + 1][x + 1].getFicha().getCOLOR() != color) {
                int[] movimiento = {x + 1, y + 1};
                movimientos.add(movimiento);

            }
        } catch (Exception e) {
        }
//arr izq
        try {

            if (getCasillas()[y - 1][x - 1].isOcupado()
                    && getCasillas()[y - 1][x - 1].getFicha().getCOLOR() != color) {
                int[] movimiento = {x - 1, y - 1};
                movimientos.add(movimiento);

            }
        } catch (Exception e) {
        }
//abj izq
        try {

            if (getCasillas()[y + 1][x - 1].isOcupado()
                    && getCasillas()[y + 1][x - 1].getFicha().getCOLOR() != color) {
                int[] movimiento = {x - 1, y + 1};
                movimientos.add(movimiento);

            }
        } catch (Exception e) {
        }

        return movimientos;
    }

    public ArrayList<int[]> movimientosReinaAtaque(int x, int y) {
        ArrayList<int[]> movimientos = new ArrayList<>();
        if (!comprobarFicha("Reina", x, y)) {
            return null;
        }
        Ficha reina = getCasillas()[y][x].getFicha();
        int color = reina.getCOLOR();

        // Movimientos en línea recta (vertical y horizontal)
        // Abajo
        for (int i = y + 1; i < 8; i++) {
            if (getCasillas()[i][x].isOcupado()) {
                if (getCasillas()[i][x].getFicha().getCOLOR() != color) {
                    int[] movimiento = {x, i};
                    movimientos.add(movimiento);
                }
                break; // Detenerse si se encuentra una pieza
            }
        }

        // Arriba
        for (int i = y - 1; i >= 0; i--) {
            if (getCasillas()[i][x].isOcupado()) {
                if (getCasillas()[i][x].getFicha().getCOLOR() != color) {
                    int[] movimiento = {x, i};
                    movimientos.add(movimiento);
                }
                break; // Detenerse si se encuentra una pieza
            }
        }

        // Derecha
        for (int i = x + 1; i < 8; i++) {
            if (getCasillas()[y][i].isOcupado()) {
                if (getCasillas()[y][i].getFicha().getCOLOR() != color) {
                    int[] movimiento = {i, y};
                    movimientos.add(movimiento);
                }
                break; // Detenerse si se encuentra una pieza
            }
        }

        // Izquierda
        for (int i = x - 1; i >= 0; i--) {
            if (getCasillas()[y][i].isOcupado()) {
                if (getCasillas()[y][i].getFicha().getCOLOR() != color) {
                    int[] movimiento = {i, y};
                    movimientos.add(movimiento);
                }
                break; // Detenerse si se encuentra una pieza
            }
        }

        // Movimientos diagonales
        // Diagonal abajo derecha
        for (int i = x + 1, j = y + 1; i < 8 && j < 8; i++, j++) {
            if (getCasillas()[j][i].isOcupado()) {
                if (getCasillas()[j][i].getFicha().getCOLOR() != color) {
                    int[] movimiento = {i, j};
                    movimientos.add(movimiento);
                }
                break; // Detenerse si se encuentra una pieza
            }
        }

        // Diagonal abajo izquierda
        for (int i = x - 1, j = y + 1; i >= 0 && j < 8; i--, j++) {
            if (getCasillas()[j][i].isOcupado()) {
                if (getCasillas()[j][i].getFicha().getCOLOR() != color) {
                    int[] movimiento = {i, j};
                    movimientos.add(movimiento);
                }
                break; // Detenerse si se encuentra una pieza
            }
        }

        // Diagonal arriba derecha
        for (int i = x + 1, j = y - 1; i < 8 && j >= 0; i++, j--) {
            if (getCasillas()[j][i].isOcupado()) {
                if (getCasillas()[j][i].getFicha().getCOLOR() != color) {
                    int[] movimiento = {i, j};
                    movimientos.add(movimiento);
                }
                break; // Detenerse si se encuentra una pieza
            }
        }

        // Diagonal arriba izquierda
        for (int i = x - 1, j = y - 1; i >= 0 && j >= 0; i--, j--) {
            if (getCasillas()[j][i].isOcupado()) {
                if (getCasillas()[j][i].getFicha().getCOLOR() != color) {
                    int[] movimiento = {i, j};
                    movimientos.add(movimiento);
                }
                break; // Detenerse si se encuentra una pieza
            }
        }

        return movimientos;
    }

    //movimientos generales
    public ArrayList<int[]> movimientosAtaque(int tipoFicha, int x, int y) {
        return switch (tipoFicha) {
            case 0 -> movimientosPeonAtaque(x, y);
            case 1 -> movimientosTorreAtaque(x, y);
            case 2 -> movimientosCaballoAtaque(x, y);
            case 3 -> movimientosAlfilAtaque(x, y);
            case 5 -> movimientosReinaAtaque(x, y);
            case 4 -> movimientosReyAtaque(x, y);
            default -> new ArrayList<>();
        }; // Peón
        // Torre
        // Caballo
        // Alfil
        // Reina
        // Rey
    }

    public ArrayList<int[]> movimientosPosibles(int x, int y) {
        ArrayList<int[]> movimientos = new ArrayList<>();
        if (!casillas[y][x].isOcupado()) {
            return movimientos; // No hay pieza en la casilla
        }
        Ficha ficha = getCasillas()[y][x].getFicha();
        String tipoFicha = ficha.getTIPOFICHA();
        switch (tipoFicha) {
            case "Peon" -> {
                movimientos.addAll(movimientosPeon(x, y));
                movimientos.addAll(movimientosPeonAtaque(x, y));
            }
            case "Torre" -> {
                movimientos.addAll(movimientosTorre(x, y));
                movimientos.addAll(movimientosTorreAtaque(x, y));
            }
            case "Caballo" -> {
                movimientos.addAll(movimientosCaballo(x, y));
                movimientos.addAll(movimientosCaballoAtaque(x, y));
            }
            case "Alfil" -> {
                movimientos.addAll(movimientosAlfil(x, y));
                movimientos.addAll(movimientosAlfilAtaque(x, y));
            }
            case "Reina" -> {
                movimientos.addAll(movimientosReina(x, y));
                movimientos.addAll(movimientosReinaAtaque(x, y));
            }
            case "Rey" -> {
                movimientos.addAll(movimientosRey(x, y));
                movimientos.addAll(movimientosReyAtaque(x, y));
            }
        }
        return movimientos;
    }

    //comprobaciones
    public boolean esJaque(int colorRey) {
        int[] posicionRey = encontrarPosicionRey(colorRey);
        if (posicionRey == null) {
            return false; // El rey no se encuentra en el tablero
        }

        int reyX = posicionRey[0];
        int reyY = posicionRey[1];

        // Revisar todas las posibles piezas del adversario
        for (int y = 0; y < 8; y++) {
            for (int x = 0; x < 8; x++) {
                if (getCasillas()[y][x].isOcupado() && getCasillas()[y][x].getFicha().getCOLOR() != colorRey) {
                    Ficha ficha = getCasillas()[y][x].getFicha();
                    ArrayList<int[]> movimientos = movimientosAtaque(ficha.getTIPO(), x, y);
                    if (movimientos != null) {
                        for (int[] movimiento : movimientos) {
                            if (movimiento[0] == reyX && movimiento[1] == reyY) {
                                return true; // El rey está en jaque
                            }
                        }
                    }
                }
            }
        }
        return false; // El rey no está en jaque
    }

    public int[] encontrarPosicionRey(int color) {
        for (int y = 0; y < 8; y++) {
            for (int x = 0; x < 8; x++) {
                if (getCasillas()[y][x].isOcupado() && getCasillas()[y][x].getFicha().getTIPO() == 4 && getCasillas()[y][x].getFicha().getCOLOR() == color) {
                    return new int[]{x, y}; // Devuelve la posición del rey
                }
            }
        }
        return null; // Rey no encontrado
    }

    public boolean esJaqueMate(int color) {
        // Encontrar la posición del rey del color especificado
        int[] posicionRey = encontrarPosicionRey(color);
        if (posicionRey == null) {
            return false; // El rey no está en el tablero
        }


        // Si el rey está en jaque
        if (!esJaque(color)) {
            return false;
        }

        // Verificar si hay algún movimiento válido que pueda liberar al rey del jaque
        for (int y = 0; y < 8; y++) {
            for (int x = 0; x < 8; x++) {
                if (getCasillas()[y][x].isOcupado() && getCasillas()[y][x].getFicha().getCOLOR() == color) {
                    ArrayList<int[]> movimientosPosibles = movimientosPosibles(x, y);
                    for (int[] movimiento : movimientosPosibles) {
                        // Realizar el movimiento temporalmente
                        Ficha ficha = getCasillas()[y][x].getFicha();

                        Casilla destino = getCasillas()[movimiento[1]][movimiento[0]];
                        Casilla origen = getCasillas()[y][x];
                        Ficha fichaDestino = destino.getFicha();

                        // Mover pieza
                        destino.setFicha(ficha);
                        origen.setFicha(null);

                        // Verificar si el rey sigue en jaque
                        boolean sigueEnJaque = esJaque(color);

                        // Deshacer el movimiento
                        origen.setFicha(ficha);
                        destino.setFicha(fichaDestino);

                        if (!sigueEnJaque) {
                            System.out.println(movimiento[0] + " " + movimiento[1] + "\n" + ficha.toString());
                            return false; // Hay un movimiento válido que saca al rey del jaque
                        }
                    }
                }
            }
        }

        // Si no encontramos ningún movimiento que saque al rey del jaque, es jaque mate
        return true;
    }

    public boolean comprobarFicha(String ficha, int x, int y) {
        return getCasillas()[y][x].getFicha().getTIPOFICHA().equalsIgnoreCase(ficha);
    }

    //toString
    @Override
    public String toString() {
        String mostrarTablero = "";
        for (Casilla[] casilla : getCasillas()) {
            mostrarTablero += "[";
            for (int j = 0; j < casilla.length; j++) {
                if (j == getCasillas().length - 1) {
                    mostrarTablero += casilla[j] + "";
                } else {
                    mostrarTablero += casilla[j] + ",";
                }
            }
            mostrarTablero += "]\n";
        }

        return mostrarTablero;
    }

    public boolean contieneFicha(int color, int x, int y) {
        return getCasillas()[y][x].isOcupado() && getCasillas()[y][x].getFicha().getCOLOR() == color;
    }

    /**
     * @return the casillas
     */
    public Casilla[][] getCasillas() {
        return casillas;
    }

    /**
     * @param casillas the casillas to set
     */
    public void setCasillas(Casilla[][] casillas) {
        this.casillas = casillas;
    }

}
