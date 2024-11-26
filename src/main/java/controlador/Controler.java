/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controlador;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import modelo.*;
import vista.*;

/**
 *
 * @author bejar
 */
public final class Controler implements ActionListener {
    private final VentanaGanador ventanaGanador;
    private final VentanaInicial ventanaInicial;
    private final VentanaJuego ventanaJuego;
    private final Logic logica;
    private int[] posicionAntigua;
    private int[] posicionActual;

    public Controler(VentanaInicial ventanaInicial, VentanaJuego ventanaJuego, Logic logica, VentanaGanador ventanaGanador) {
        this.logica = logica;
        this.ventanaInicial = ventanaInicial;
        this.ventanaJuego = ventanaJuego;
        this.ventanaGanador = ventanaGanador;
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                this.ventanaJuego.getCuadricula()[i][j].addActionListener(this);
            }
        }
        this.ventanaGanador.getButtonRestart().addActionListener(this);
        this.ventanaInicial.getButtonPlay().addActionListener(this);
        this.ventanaInicial.getBtnRules().addActionListener(this);
    }

    /**
     *
     * @param e
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        System.out.println(e.getSource().equals(ventanaJuego.getBtnReiniciar()));
        if (ventanaInicial.isVisible()) {
            pasarVentana(e);
            if (e.getSource().equals(ventanaInicial.getBtnRules())) {
                ventanaInicial.rulesPanel();
                return;
            }
            return;
        }
        if (ventanaJuego.getVentanaJugadaPeon().isVisible()) {
            ejecutarJugadaPeon(e);
            return;
        }
        jugar(e);

        reiniciar(e);
    }

    public void agregarFichasMuertas() {
        // Limpia los paneles laterales antes de agregar nuevas fichas
        ventanaJuego.getPanelIzquierdo().removeAll();
        ventanaJuego.getPanelDerecho().removeAll();
        ventanaJuego.botonesPanelIzquierdo();
        // izquierdo
        for (int i = 0; i < logica.getFichasBlancasF().length; i++) {
            if (logica.getFichasBlancasF()[i] != null) {
                // 0=peon,1=torre,2=caballo,3=alfil,4=rey,5=reina
                ImageIcon icono = null;
                switch (logica.getFichasBlancasF()[i].getTIPO()) {
                    case 0 ->
                        icono = ventanaJuego.obtenerIcono(0); // Peón blanco
                    case 1 ->
                        icono = ventanaJuego.obtenerIcono(2); // Torre blanca
                    case 2 ->
                        icono = ventanaJuego.obtenerIcono(4); // Caballo blanco
                    case 3 ->
                        icono = ventanaJuego.obtenerIcono(6); // Alfil blanco
                    case 4 ->
                        icono = ventanaJuego.obtenerIcono(8); // Rey blanco
                    case 5 ->
                        icono = ventanaJuego.obtenerIcono(10); // Reina blanca
                    default -> {
                    }
                }
                if (icono != null) {
                    JLabel labelFicha = new JLabel(icono);
                    labelFicha.setPreferredSize(new Dimension(100, 75));
                    ventanaJuego.getPanelIzquierdo().add(labelFicha);
                }
            }
        }

        // derecho (similar para fichas negras)
        for (int i = 0; i < logica.getFichasNegrasF().length; i++) {
            if (logica.getFichasNegrasF()[i] != null) {
                ImageIcon icono = null;
                switch (logica.getFichasNegrasF()[i].getTIPO()) {
                    case 0 ->
                        icono = ventanaJuego.obtenerIcono(1); // Peón negro
                    case 1 ->
                        icono = ventanaJuego.obtenerIcono(3); // Torre negra
                    case 2 ->
                        icono = ventanaJuego.obtenerIcono(5); // Caballo negro
                    case 3 ->
                        icono = ventanaJuego.obtenerIcono(7); // Alfil negro
                    case 4 ->
                        icono = ventanaJuego.obtenerIcono(9); // Rey negro
                    case 5 ->
                        icono = ventanaJuego.obtenerIcono(11); // Reina negra
                    default -> {
                    }
                }
                if (icono != null) {
                    JLabel labelFicha = new JLabel(icono);
                    labelFicha.setPreferredSize(new Dimension(100, 75));
                    ventanaJuego.getPanelDerecho().add(labelFicha);
                }
            }
        }

        // Actualiza la interfaz para mostrar los cambios
        ventanaJuego.getPanelIzquierdo().revalidate();
        ventanaJuego.getPanelIzquierdo().repaint();
        ventanaJuego.getPanelDerecho().revalidate();
        ventanaJuego.getPanelDerecho().repaint();

    }

    //eventos
    public void pasarVentana(ActionEvent e) {
        if (e.getSource().equals(ventanaInicial.getButtonPlay())) {
            ventanaJuego.setVisible(true);
            ventanaJuego.elegirTurnoVentana();
            ventanaJuego.getTurno1().addActionListener((action) -> {
                logica.setTurno(2);
                ventanaJuego.getElegirTurno().dispose();
            });
            ventanaJuego.getTurno2().addActionListener((action) -> {
                logica.setTurno(1);
                ventanaJuego.getElegirTurno().dispose();
            });
            ventanaInicial.dispose();
        }
    }

    public void moverEnPantalla(int x, int y, int newX, int newY) {
        if (logica.mover(x, y, newX, newY)) {
            Icon iconoAntiguo = ventanaJuego.getCuadricula()[y][x].getIcon();
            ventanaJuego.getCuadricula()[y][x].setIcon(null);
            ventanaJuego.getCuadricula()[newY][newX].setIcon(iconoAntiguo);
            if (logica.comprobarJugadaPeonBlanco()) {
                abrirJugadaPeon(x, y, newX, newY, logica.getTurno());

            } else if (logica.comprobarJugadaPeonNegro()) {
                abrirJugadaPeon(x, y, newX, newY, logica.getTurno());
            }
            if (logica.getTurno() == 1) {
                logica.setTurno(logica.getTurno() + 1);
            } else {
                logica.setTurno(logica.getTurno() - 1);
            }
            agregarFichasMuertas();
        } else if (logica.comprobarEnroqueCorto(x, y, newX)) {
            if (logica.getTurno() == 1) {
                if (logica.isPrimerMovimiento1_2()) {
                    ejecutarEnroqueCorto(y);
                    logica.ejecutarEnroqueCorto(y);
                    logica.setTurno(logica.getTurno() + 1);
                }
            } else {
                if (logica.isPrimerMovimiento2_2()) {
                    logica.ejecutarEnroqueLargo(y);
                    ejecutarEnroqueCorto(y);
                    logica.setTurno(logica.getTurno() - 1);
                }

            }

        } else if (logica.comprobarEnroqueLargo(x, y, newX)) {
            if (logica.getTurno() == 1) {
                if (logica.isPrimerMovimiento1_1()) {
                    ejecutarEnroqueLargo(y);
                    logica.setTurno(logica.getTurno() + 1);
                }
            } else {
                if (logica.isPrimerMovimiento2_1()) {
                    ejecutarEnroqueLargo(y);
                    logica.setTurno(logica.getTurno() - 1);
                }

            }
        }

    }

    public void mostrarSiguientesMovimientos(int x, int y) {
        logica.siguientesMovimientos(x, y);
        if (logica.getSiguientesMovimientos() == null) {
            return;
        }
        for (int[] siguienteMovimiento : logica.getSiguientesMovimientos()) {
            int Y = siguienteMovimiento[1];
            int X = siguienteMovimiento[0];
            if ((Y + X) % 2 == 0) {
                ventanaJuego.getCuadricula()[Y][X].setBackground(new Color(254, 221, 178));
            } else {
                ventanaJuego.getCuadricula()[Y][X].setBackground(new Color(171, 90, 32));
            }
        }
    }

    public void ocultarSiguientesMovimientos() {
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                if ((i + j) % 2 == 0) {
                    ventanaJuego.getCuadricula()[i][j].setBackground(new Color(222, 184, 135));
                } else {
                    ventanaJuego.getCuadricula()[i][j].setBackground(new Color(139, 69, 19));
                }
            }
        }
    }

    public void ejecutarEnroqueCorto(int y) {

        Icon iconoAntiguoRey = ventanaJuego.getCuadricula()[y][4].getIcon();
        ventanaJuego.getCuadricula()[y][4].setIcon(null);
        ventanaJuego.getCuadricula()[y][6].setIcon(iconoAntiguoRey);

        Icon iconoAntiguoTorre = ventanaJuego.getCuadricula()[y][7].getIcon();
        ventanaJuego.getCuadricula()[y][7].setIcon(null);
        ventanaJuego.getCuadricula()[y][5].setIcon(iconoAntiguoTorre);
    }

    public void ejecutarEnroqueLargo(int y) {

        Icon iconoAntiguoRey = ventanaJuego.getCuadricula()[y][4].getIcon();
        ventanaJuego.getCuadricula()[y][4].setIcon(null);
        ventanaJuego.getCuadricula()[y][1].setIcon(iconoAntiguoRey);

        Icon iconoAntiguoTorre = ventanaJuego.getCuadricula()[y][0].getIcon();
        ventanaJuego.getCuadricula()[y][0].setIcon(null);
        ventanaJuego.getCuadricula()[y][2].setIcon(iconoAntiguoTorre);
    }

//    /**
//     * encapsular todo
//     * crear modo 1 jugador
//     */
    public void jugar(ActionEvent e) {

        posicionActual = obtenerBoton(e);

        if (posicionActual == null) {
            return; // Si la posición no es válida, no hacemos nada
        }

        // Verificar si la casilla actual contiene una ficha del jugador actual
        boolean casillaValida = logica.getTablero().contieneFicha(logica.getTurno(), posicionActual[0], posicionActual[1]);

        if (logica.getTurno() == 1) {
            if (posicionAntigua == null) {
                // Primera vez que se presiona un botón
                if (casillaValida) {
                    posicionAntigua = posicionActual;
                    mostrarSiguientesMovimientos(posicionActual[0], posicionActual[1]);
                } else {
                    System.out.println("Casilla vacía. Selección reiniciada.");
                    posicionAntigua = null; // Restablece la selección si la casilla está vacía
                }
            } else {
                moverEnPantalla(posicionAntigua[0], posicionAntigua[1], posicionActual[0], posicionActual[1]);
                posicionAntigua = null;
                if (!logica.getTablero().esJaqueMate(logica.getTurno()) && logica.isJaque()) {
                    ventanaJuego.mostrarTextoTemporal("JAQUE!", 1000);
                }
                if (logica.getTablero().esJaqueMate(logica.getTurno())) {
                    ventanaGanador.initComponents(logica.getTurno());
                }
                ocultarSiguientesMovimientos();
            }

        } else {
            if (posicionAntigua == null) {
                // Primera vez que se presiona un botón
                if (casillaValida) {
                    posicionAntigua = posicionActual;
                    mostrarSiguientesMovimientos(posicionActual[0], posicionActual[1]);
                } else {
                    System.out.println("Casilla vacía. Selección reiniciada.");
                    posicionAntigua = null; // Restablece la selección si la casilla está vacía
                }
            } else {
                moverEnPantalla(posicionAntigua[0], posicionAntigua[1], posicionActual[0], posicionActual[1]);
                posicionAntigua = null;
                if (!logica.getTablero().esJaqueMate(logica.getTurno()) && logica.isJaque()) {
                    ventanaJuego.mostrarTextoTemporal("JAQUE!", 1000);
                }
                if (logica.getTablero().esJaqueMate(logica.getTurno())) {
                    ventanaGanador.initComponents(logica.getTurno());
                }
                ocultarSiguientesMovimientos();
            }
        }
        if (logica.getTablero().esJaqueMate(1) || logica.getTablero().esJaqueMate(2)) {
            ventanaGanador.initComponents(logica.getTurno());
        }

    }

    public void abrirJugadaPeon(int x, int y, int newX, int newY, int turno) {
        ventanaJuego.setVentanaJugadaPeon(new VentanaJugadaPeon());
        for (int i = 0; i < 2; i++) {
            for (int j = 0; j < 5; j++) {
                ventanaJuego.getVentanaJugadaPeon().getFichas()[i][j].addActionListener(this);
                ventanaJuego.getVentanaJugadaPeon().getFichas()[i][j].setFocusPainted(false);
                ventanaJuego.getVentanaJugadaPeon().getFichas()[i][j].setBorderPainted(false);
            }
        }
        if (turno == 2) {
            ventanaJuego.getVentanaJugadaPeon().ponerImagenes(0);
            ventanaJuego.getVentanaJugadaPeon().setUndecorated(true);
            ventanaJuego.getVentanaJugadaPeon().setVisible(true);
        } else if (turno == 1) {
            ventanaJuego.getVentanaJugadaPeon().ponerImagenes(1);
            ventanaJuego.getVentanaJugadaPeon().setUndecorated(true);
            ventanaJuego.getVentanaJugadaPeon().setVisible(true);

        }
    }

    public void ejecutarJugadaPeon(ActionEvent e) {
        if (logica.getTurno() == 2) {
            if (e.getSource().equals(ventanaJuego.getVentanaJugadaPeon().getFichas()[1][1])) {
                logica.getTablero().getCasillas()[7][logica.getJugadaPeonPosicionX()].setFicha(new Ficha(1, 1));
                ventanaJuego.getCuadricula()[7][logica.getJugadaPeonPosicionX()].setIcon(ventanaJuego.getVentanaJugadaPeon().obtenerIcono(6));
            }
            if (e.getSource().equals(ventanaJuego.getVentanaJugadaPeon().getFichas()[1][2])) {
                logica.getTablero().getCasillas()[7][logica.getJugadaPeonPosicionX()].setFicha(new Ficha(2, 1));
                ventanaJuego.getCuadricula()[7][logica.getJugadaPeonPosicionX()].setIcon(ventanaJuego.getVentanaJugadaPeon().obtenerIcono(7));
            }
            if (e.getSource().equals(ventanaJuego.getVentanaJugadaPeon().getFichas()[1][3])) {
                logica.getTablero().getCasillas()[7][logica.getJugadaPeonPosicionX()].setFicha(new Ficha(3, 1));
                ventanaJuego.getCuadricula()[7][logica.getJugadaPeonPosicionX()].setIcon(ventanaJuego.getVentanaJugadaPeon().obtenerIcono(8));
            }
            if (e.getSource().equals(ventanaJuego.getVentanaJugadaPeon().getFichas()[1][4])) {
                logica.getTablero().getCasillas()[7][logica.getJugadaPeonPosicionX()].setFicha(new Ficha(5, 1));
                ventanaJuego.getCuadricula()[7][logica.getJugadaPeonPosicionX()].setIcon(ventanaJuego.getVentanaJugadaPeon().obtenerIcono(9));
            }
        } else {
            if (e.getSource().equals(ventanaJuego.getVentanaJugadaPeon().getFichas()[0][1])) {
                logica.getTablero().getCasillas()[0][logica.getJugadaPeonPosicionX()].setFicha(new Ficha(1, 2));
                ventanaJuego.getCuadricula()[0][logica.getJugadaPeonPosicionX()].setIcon(ventanaJuego.getVentanaJugadaPeon().obtenerIcono(1));
            }
            if (e.getSource().equals(ventanaJuego.getVentanaJugadaPeon().getFichas()[0][2])) {
                logica.getTablero().getCasillas()[0][logica.getJugadaPeonPosicionX()].setFicha(new Ficha(2, 2));
                ventanaJuego.getCuadricula()[0][logica.getJugadaPeonPosicionX()].setIcon(ventanaJuego.getVentanaJugadaPeon().obtenerIcono(2));
            }
            if (e.getSource().equals(ventanaJuego.getVentanaJugadaPeon().getFichas()[0][3])) {
                logica.getTablero().getCasillas()[0][logica.getJugadaPeonPosicionX()].setFicha(new Ficha(3, 2));
                ventanaJuego.getCuadricula()[0][logica.getJugadaPeonPosicionX()].setIcon(ventanaJuego.getVentanaJugadaPeon().obtenerIcono(3));
            }
            if (e.getSource().equals(ventanaJuego.getVentanaJugadaPeon().getFichas()[0][4])) {
                logica.getTablero().getCasillas()[0][logica.getJugadaPeonPosicionX()].setFicha(new Ficha(5, 2));
                ventanaJuego.getCuadricula()[0][logica.getJugadaPeonPosicionX()].setIcon(ventanaJuego.getVentanaJugadaPeon().obtenerIcono(4));
            }
        }

//        ventanaJuego.ventanaJugadaPeon.removeAll();
        ventanaJuego.getVentanaJugadaPeon().dispose();
    }

    public void reiniciar(ActionEvent e) {
        if (e.getSource().equals(ventanaGanador.getButtonRestart())) {
            logica.reiniciarTablero(); // Reiniciar la lógica del juego

            // Cerrar la ventanaJuego actual
            ventanaJuego.dispose();

            // Crear una nueva instancia de VentanaJuego
//            ventanaJuego = new VentanaJuego();
            ventanaJuego.reiniciarTablero();

            // Volver a añadir los ActionListeners a los botones de la nueva ventanaJuego
            for (int i = 0; i < 8; i++) {
                for (int j = 0; j < 8; j++) {
                    ventanaJuego.getCuadricula()[i][j].addActionListener(this);
                }
            }

            // Actualizar el estado del controlador
            posicionAntigua = null;
            ventanaGanador.dispose();
            ventanaInicial.setVisible(true);
        }
    }

    public int[] obtenerBoton(ActionEvent e) {
        int[] posicion = new int[2];
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                if (e.getSource().equals(ventanaJuego.getCuadricula()[i][j])) {
                    posicion[0] = j;
                    posicion[1] = i;
                }
            }
        }
        return posicion;
    }

}
