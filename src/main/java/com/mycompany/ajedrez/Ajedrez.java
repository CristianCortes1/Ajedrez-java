/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */
package com.mycompany.ajedrez;

import controlador.*;
import modelo.Logic;
import vista.*;

/**
 *
 * @author bejar
 */
public class Ajedrez {

    private static VentanaInicial ventanaPrincipal;
    private static VentanaJuego ventanaJuego;
    private static VentanaGanador ventanaGanador;
    private static Logic logica;
    private static Controler controlador;

    public static void main(String[] args) {
        ventanaPrincipal = new VentanaInicial();
        ventanaJuego = new VentanaJuego();
        ventanaGanador = new VentanaGanador();
        logica = new Logic();
        controlador = new Controler(ventanaPrincipal, ventanaJuego, logica, ventanaGanador);

//        ControlerVentanaInicial controladorVentana = new ControlerVentanaInicial(ventanaPrincipal);
    }

    public void reRun() {
        ventanaJuego.dispose();
        ventanaPrincipal = new VentanaInicial();
        ventanaJuego = new VentanaJuego();
        ventanaGanador = new VentanaGanador();
        logica = new Logic();
        controlador = new Controler(ventanaPrincipal, ventanaJuego, logica, ventanaGanador);
    }

}
