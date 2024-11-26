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
public class Logic {

    private Tablero tablero;
    private int turno;
    private boolean primerMovimiento1_1;
    private boolean primerMovimiento1_2;
    private boolean primerMovimiento2_1;
    private boolean primerMovimiento2_2;
    private final Ficha[] fichasNegrasF;
    private final Ficha[] fichasBlancasF;
    //F=fallecidas
    //0 blancas;1=negras
    private int jugadaPeonPosicionX;
    private ArrayList<int[]> siguientesMovimientos;

    public Logic() {
        tablero = new Tablero();
        tablero.iniciarFichas();
        turno = 2;
        primerMovimiento1_1 = true;
        primerMovimiento2_1 = true;
        primerMovimiento1_2 = true;
        primerMovimiento2_2 = true;
        fichasNegrasF = new Ficha[16];
        fichasBlancasF = new Ficha[16];
        siguientesMovimientos = new ArrayList<>();
    }

    public void ejecutarEnroqueCorto(int y) {
        tablero.getCasillas()[y][6].setFicha(getTablero().getCasillas()[y][4].getFicha());
        tablero.getCasillas()[y][4].setFicha(null);
        tablero.getCasillas()[y][5].setFicha(getTablero().getCasillas()[y][7].getFicha());
        tablero.getCasillas()[y][7].setFicha(null);
        if (getTurno() == 1) {
            setPrimerMovimiento1_1(false);
            setPrimerMovimiento1_2(false);
        } else {
            setPrimerMovimiento2_1(false);
            setPrimerMovimiento2_2(false);
        }
    }

    public void ejecutarEnroqueLargo(int y) {
        tablero.getCasillas()[y][1].setFicha(getTablero().getCasillas()[y][4].getFicha());
        tablero.getCasillas()[y][4].setFicha(null);
        tablero.getCasillas()[y][2].setFicha(getTablero().getCasillas()[y][0].getFicha());
        tablero.getCasillas()[y][0].setFicha(null);
        if (getTurno() == 1) {
            setPrimerMovimiento1_1(false);
            setPrimerMovimiento1_2(false);
        } else {
            setPrimerMovimiento2_1(false);
            setPrimerMovimiento2_2(false);
        }
    }

    public boolean comprobarEnroqueCorto(int x, int y, int newX) {
        if (x == 4 && newX == 6 || x == 7 && newX == 4) {
            if (getTablero().getCasillas()[y][5].getFicha() == null && getTablero().getCasillas()[y][6].getFicha() == null) {
                return true;
            }
        }
        return false;
    }

    public boolean comprobarEnroqueLargo(int x, int y, int newX) {
        if (x == 4 && newX == 1 || x == 0 && newX == 4) {
            if (getTablero().getCasillas()[y][1].getFicha() == null && getTablero().getCasillas()[y][2].getFicha() == null && getTablero().getCasillas()[y][3].getFicha() == null) {
                return true;
            }
        }
        return false;

    }

    public boolean comprobarJugadaPeonBlanco() {
        for (int i = 0; i < 8; i++) {
            if (getTablero().getCasillas()[7][i].getFicha() != null && getTablero().getCasillas()[7][i].getFicha().getTIPO() == 0) {
                setJugadaPeonPosicionX(i);
                return true;
            }
        }

        return false;
    }

    public boolean comprobarJugadaPeonNegro() {
        for (int i = 0; i < 8; i++) {
            if (getTablero().getCasillas()[0][i].getFicha() != null && getTablero().getCasillas()[0][i].getFicha().getTIPO() == 0) {
                setJugadaPeonPosicionX(i);
                return true;
            }

        }

        return false;
    }

    public boolean mover(int x, int y, int newX, int newY) {
        Ficha ficha = getTablero().getCasillas()[y][x].getFicha();
        if (ficha.getCOLOR() == getTurno() && esPosibleMover(x, y, newX, newY)) {
            Ficha antigua = getTablero().getCasillas()[y][x].getFicha();
            Ficha nueva = getTablero().getCasillas()[newY][newX].getFicha();
            tablero.getCasillas()[y][x].setFicha(null);
            tablero.getCasillas()[newY][newX].setFicha(ficha);
            if (getTablero().esJaque(getTurno())) {
                System.out.println("no se movio");
                tablero.getCasillas()[y][x].setFicha(antigua);
                tablero.getCasillas()[newY][newX].setFicha(nueva);
                return false;
            }
            if (ficha.getTIPO() == 4 || ficha.getTIPO() == 1) {
                if (getTurno() == 1) {
                    switch (x) {
                        case 0 ->
                            setPrimerMovimiento1_1(false);
                        case 7 ->
                            setPrimerMovimiento1_2(false);
                        case 4 -> {
                            setPrimerMovimiento1_1(false);
                            setPrimerMovimiento1_2(false);
                        }
                        default -> {
                        }
                    }

                } else {
                    switch (x) {
                        case 0 ->
                            setPrimerMovimiento2_1(false);
                        case 7 ->
                            setPrimerMovimiento2_2(false);
                        case 4 -> {
                            setPrimerMovimiento2_1(false);
                            setPrimerMovimiento2_2(false);
                        }
                        default -> {
                        }
                    }
                }
            }
            System.out.println(getTablero().toString());
            return true;
        } else {
            return false;
        }

    }
    public void siguientesMovimientos(int x,int y){
        setSiguientesMovimientos(getTablero().movimientosPosibles(x, y));
    }
    public boolean esPosibleMover(int x, int y, int newX, int newY) {
        ArrayList<int[]> movimientos = getTablero().movimientosPosibles(x, y);
        boolean esPosible = false;

        for (int[] movimiento : movimientos) {
            if (movimiento[0] == newX && movimiento[1] == newY) {
                esPosible = true;
            }
        }
        if (esPosible && getTablero().getCasillas()[newY][newX].isOcupado()) {
            if (getTurno() == 1) {
                for (int i = 0; i < getFichasBlancasF().length; i++) {
                    if (getFichasBlancasF()[i] == null) {
                        getFichasBlancasF()[i] = getTablero().getCasillas()[newY][newX].getFicha();
                        break;
                    }
                }
            } else {
                for (int i = 0; i < getFichasNegrasF().length; i++) {
                    if (getFichasNegrasF()[i] == null) {
                        getFichasNegrasF()[i] = getTablero().getCasillas()[newY][newX].getFicha();
                        break;
                    }
                }
            }
        }
        
        return esPosible;
    }

    public boolean isJaque() {
        return getTablero().esJaque(getTurno());
    }

    public void reiniciarTablero() {
        getTablero().iniciarCuadricula();
        getTablero().iniciarFichas();
        setTurno(1);
    }

    /**
     * @return the tablero
     */
    public Tablero getTablero() {
        return tablero;
    }

    /**
     * @param tablero the tablero to set
     */
    public void setTablero(Tablero tablero) {
        this.tablero = tablero;
    }

    /**
     * @return the turno
     */
    public int getTurno() {
        return turno;
    }

    /**
     * @param turno the turno to set
     */
    public void setTurno(int turno) {
        this.turno = turno;
    }
    /**
     * @return the primerMovimiento1_1
     */
    public boolean isPrimerMovimiento1_1() {
        return primerMovimiento1_1;
    }

    /**
     * @param primerMovimiento1_1 the primerMovimiento1_1 to set
     */
    public void setPrimerMovimiento1_1(boolean primerMovimiento1_1) {
        this.primerMovimiento1_1 = primerMovimiento1_1;
    }

    /**
     * @return the primerMovimiento1_2
     */
    public boolean isPrimerMovimiento1_2() {
        return primerMovimiento1_2;
    }

    /**
     * @param primerMovimiento1_2 the primerMovimiento1_2 to set
     */
    public void setPrimerMovimiento1_2(boolean primerMovimiento1_2) {
        this.primerMovimiento1_2 = primerMovimiento1_2;
    }

    /**
     * @return the primerMovimiento2_1
     */
    public boolean isPrimerMovimiento2_1() {
        return primerMovimiento2_1;
    }

    /**
     * @param primerMovimiento2_1 the primerMovimiento2_1 to set
     */
    public void setPrimerMovimiento2_1(boolean primerMovimiento2_1) {
        this.primerMovimiento2_1 = primerMovimiento2_1;
    }

    /**
     * @return the primerMovimiento2_2
     */
    public boolean isPrimerMovimiento2_2() {
        return primerMovimiento2_2;
    }

    /**
     * @param primerMovimiento2_2 the primerMovimiento2_2 to set
     */
    public void setPrimerMovimiento2_2(boolean primerMovimiento2_2) {
        this.primerMovimiento2_2 = primerMovimiento2_2;
    }

    /**
     * @return the fichasNegrasF
     */
    public Ficha[] getFichasNegrasF() {
        return fichasNegrasF;
    }

    /**
     * @return the fichasBlancasF
     */
    public Ficha[] getFichasBlancasF() {
        return fichasBlancasF;
    }

    /**
     * @return the jugadaPeonPosicionX
     */
    public int getJugadaPeonPosicionX() {
        return jugadaPeonPosicionX;
    }

    /**
     * @param jugadaPeonPosicionX the jugadaPeonPosicionX to set
     */
    public void setJugadaPeonPosicionX(int jugadaPeonPosicionX) {
        this.jugadaPeonPosicionX = jugadaPeonPosicionX;
    }

    /**
     * @return the siguientesMovimientos
     */
    public ArrayList<int[]> getSiguientesMovimientos() {
        return siguientesMovimientos;
    }

    /**
     * @param siguientesMovimientos the siguientesMovimientos to set
     */
    public void setSiguientesMovimientos(ArrayList<int[]> siguientesMovimientos) {
        this.siguientesMovimientos = siguientesMovimientos;
    }

}
