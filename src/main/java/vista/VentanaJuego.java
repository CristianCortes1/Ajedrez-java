/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package vista;

import com.mycompany.ajedrez.Ajedrez;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.stream.Stream;

import javax.imageio.ImageIO;
import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.Timer;

/**
 *
 * @author bejar
 */
@SuppressWarnings("serial")
public class VentanaJuego extends JFrame {


    private JPanel panelJuego;
    private JButton cuadricula[][];
    private VentanaJugadaPeon ventanaJugadaPeon;
    private JPanel panelIzquierdo;
    private JPanel panelDerecho;
    private JFrame reglas;
    private JButton botonReglas;
    private JFrame elegirTurno;
    private Ajedrez ajedrez;

    public VentanaJuego() {
        initComponents();
        setIcono();

    }
    private JButton turno1;
    private JButton turno2;
    private JPanel turnosPannel;

    public void elegirTurnoVentana() {
        setElegirTurno(new JFrame());
        getElegirTurno().setUndecorated(true);
        getElegirTurno().setSize(new Dimension(600, 100));
        getElegirTurno().setLocationRelativeTo(null);
        setTurnosPannel(new JPanel(new GridLayout(1, 2)));
        JPanel panelTitulo = new JPanel();
        panelTitulo.setBackground(Color.BLACK);
        JLabel titulo = new JLabel("¿Quien comienza?");
        titulo.setFont(new Font("Serif", Font.PLAIN, 18));
        titulo.setForeground(Color.WHITE);
        titulo.setAlignmentX(Component.CENTER_ALIGNMENT);
        panelTitulo.add(titulo);
        setTurno1(createButton("Blancas", Color.BLACK, Color.WHITE, Color.WHITE));
        setTurno2(createButton("Negras", Color.BLACK, Color.WHITE, Color.WHITE));
        getTurnosPannel().setBackground(Color.BLACK);
        getTurnosPannel().add(getTurno1());
        getTurnosPannel().add(getTurno2());
        getElegirTurno().setLayout(new BorderLayout());
        getElegirTurno().getContentPane().add(panelTitulo, BorderLayout.NORTH);
        getElegirTurno().getContentPane().add(getTurnosPannel(), BorderLayout.SOUTH);
        getElegirTurno().setVisible(true);
    }

    private void initComponents() {
        setVentanaJugadaPeon(new VentanaJugadaPeon());

        // Contenedor principal para el tablero y las etiquetas
        JPanel contenedorJuego = new JPanel(new BorderLayout());
        contenedorJuego.setBackground(new Color(139, 69, 19)); // Aplicar el color de fondo para eliminar borde blanco

        // Crear el panel del tablero de juego de 8x8
        setPanelJuego(new JPanel(new GridLayout(8, 8)));
        getPanelJuego().setBackground(new Color(139, 69, 19)); // Asegurarse de que el tablero tenga el mismo color de fondo
        setCuadricula(new JButton[8][8]);
        iniciarCuadricula();

        // Panel para las letras (parte inferior)
        JPanel panelLetras = new JPanel(new GridLayout(1, 8));
        panelLetras.setBackground(new Color(139, 69, 19)); // Aplicar el color de fondo

        for (int j = 0; j < 8; j++) {
            JLabel label = new JLabel(Character.toString((char) ('A' + j)), SwingConstants.CENTER);
            label.setForeground(Color.WHITE); // Color de letra blanco para visibilidad
            panelLetras.add(label);
        }

        // Panel para los números (lado derecho)
        JPanel panelNumeros = new JPanel(new GridLayout(8, 1));
        panelNumeros.setBackground(new Color(139, 69, 19)); // Aplicar el color de fondo

        for (int i = 0; i < 8; i++) {
            JLabel label = new JLabel(Integer.toString(8 - i), SwingConstants.CENTER);
            label.setForeground(Color.WHITE); // Color de letra blanco para visibilidad
            panelNumeros.add(label);
        }

        // Añadir el tablero de juego al centro del contenedor
        contenedorJuego.add(getPanelJuego(), BorderLayout.CENTER);
        contenedorJuego.add(panelLetras, BorderLayout.SOUTH);
        contenedorJuego.add(panelNumeros, BorderLayout.EAST);

        // Crear los paneles laterales
        setPanelIzquierdo(new JPanel(new GridLayout(8, 2)));
        setPanelDerecho(new JPanel(new GridLayout(8, 2)));
        // Configurar el color de los paneles laterales para que coincida con el tablero
        getPanelIzquierdo().setBackground(new Color(139, 69, 19)); // mismo color que el fondo del tablero
        getPanelDerecho().setBackground(new Color(139, 69, 19)); // mismo color que el fondo del tablero
        botonesPanelIzquierdo();
        // Configurar el tamaño de los paneles laterales
        getPanelIzquierdo().setPreferredSize(new Dimension(200, 600)); // Ajusta el tamaño según sea necesario
        getPanelDerecho().setPreferredSize(new Dimension(200, 600)); // Ajusta el tamaño según sea necesario

        // Configurar el diseño del JFrame principal
        this.setLayout(new BorderLayout());
        this.getContentPane().add(getPanelIzquierdo(), BorderLayout.WEST);
        this.getContentPane().add(getPanelDerecho(), BorderLayout.EAST);
        this.getContentPane().add(contenedorJuego, BorderLayout.CENTER);

        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setSize(1300, 700); // Ajustar el tamaño total para incluir los paneles laterales y mayor anchura
        this.setLocationRelativeTo(null);
    }
    private JButton btnReiniciar;

    @SuppressWarnings("unused")
    public void botonesPanelIzquierdo() {
        // Inicializa los botones y el panel de botones
        JButton salir;
        // Crear los botones con el método createButton
        setBtnReiniciar(createButton("Reiniciar", new Color(139, 69, 19), Color.WHITE, Color.WHITE));
        salir = createButton("Salir", new Color(139, 69, 19), Color.WHITE, Color.WHITE);
        setBotonReglas(createButton("Reglas", new Color(139, 69, 19), Color.WHITE, Color.WHITE));
        // Establecer dimensiones y acciones para los botones
        getBotonReglas().setPreferredSize(new Dimension(100, 50));
        getBotonReglas().setMinimumSize(new Dimension(100, 50));
        getBotonReglas().setMaximumSize(new Dimension(100, 50));
        getBotonReglas().addActionListener((e) -> reglas());
        salir.addActionListener((e) -> {
            System.exit(0);
        });
        getBtnReiniciar().addActionListener((e) -> {
            setAjedrez(new Ajedrez());
            getAjedrez().reRun();
        });

        // Añadir el panel de botones al panel izquierdo
        getPanelIzquierdo().add(getBotonReglas());
        getPanelIzquierdo().add(getBtnReiniciar());
        getPanelIzquierdo().add(salir);
    }

    @SuppressWarnings("unused")
    public void reglas() {
        setReglas(new JFrame("Reglas"));
        getReglas().setSize(1285, 661);
        getReglas().setLocationRelativeTo(this);
        getReglas().setLocation(41, 41);
        getReglas().setUndecorated(true);
        JPanel rulesPanel = new JPanel();
        rulesPanel.setLayout(new BoxLayout(rulesPanel, BoxLayout.Y_AXIS));
        rulesPanel.setBackground(new Color(50, 50, 50)); // Color de fondo gris oscuro
        rulesPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20)); // Añadir espacio alrededor

        String text = "<html>"
                + "<h2>Reglas del Juego de Ajedrez</h2>"
                + "<ul>"
                + "<li><b>Objetivo:</b> Dar jaque mate al rey del oponente.</li>"
                + "<li><b>Movimientos de las piezas:</b>"
                + "<ul>"
                + "<li><b>Peón:</b> Se mueve hacia adelante una casilla, pero captura en diagonal. En su primer movimiento puede avanzar dos casillas.</li>"
                + "<li><b>Torre:</b> Se mueve en línea recta horizontal o verticalmente, sin límite de casillas.</li>"
                + "<li><b>Alfil:</b> Se mueve en diagonal, sin límite de casillas.</li>"
                + "<li><b>Reina:</b> Combina los movimientos de la torre y el alfil, moviéndose en línea recta en cualquier dirección (horizontal, vertical, diagonal).</li>"
                + "<li><b>Rey:</b> Se mueve una casilla en cualquier dirección (horizontal, vertical, diagonal). La captura del rey significa jaque mate.</li>"
                + "<li><b>Movimientos Especiales:</b>"
                + "<ul>"
                + "<li><b>Enroque:</b> Movimiento especial del rey y una torre. El rey se mueve dos casillas hacia la torre, y la torre se mueve a la casilla inmediatamente después del rey.</li>"
                + "<li><b>Captura al paso:</b> Movimiento especial del peón para capturar un peón enemigo que se haya movido dos casillas desde su posición inicial.</li>"
                + "<li><b>Promoción:</b> Cuando un peón alcanza la última fila del tablero, puede ser promovido a cualquier otra pieza (excepto el rey).</li>"
                + "</ul></li>"
                + "<li><b>Empate:</b> El juego puede terminar en empate por varias razones, como la falta de material para dar jaque mate, repetición de movimientos, o el acuerdo mutuo entre los jugadores.</li>"
                + "</ul>"
                + "</html>";

        JLabel reglasTxt = new JLabel("<html>" + text.replaceAll("\n", "<br/>") + "</html>");
        reglasTxt.setFont(new Font("Serif", Font.PLAIN, 18));
        reglasTxt.setForeground(Color.WHITE);
        reglasTxt.setAlignmentX(Component.CENTER_ALIGNMENT);

        // Crear botón para volver atrás
        JButton btnBack = createButton("Atrás", Color.BLACK, Color.RED, Color.RED);
        btnBack.setForeground(Color.RED);
        btnBack.setBorder(BorderFactory.createLineBorder(Color.RED, 2));

        // Añadir acción al botón de volver atrás
        btnBack.addActionListener(e -> {
            getReglas().setVisible(false);
        });
        // Añadir componentes al panel de reglas
        rulesPanel.add(Box.createRigidArea(new Dimension(0, 20))); // Espacio arriba
        rulesPanel.add(reglasTxt);
        rulesPanel.add(Box.createRigidArea(new Dimension(0, 20))); // Espacio entre el texto y el botón
        rulesPanel.add(btnBack);
        getReglas().add(rulesPanel);
        getReglas().setVisible(true);
    }

    private JButton createButton(String text, Color background, Color foreground, Color border) {
        JButton button = new JButton(text);
        int widht = 200, height = 50;
        button.setPreferredSize(new Dimension(widht, height)); // Ancho fijo y altura mayor para el botón
        button.setMinimumSize(new Dimension(widht, height)); // Tamaño mínimo
        button.setMaximumSize(new Dimension(widht, height)); // Tamaño máximo
        button.setFont(new Font("Serif", Font.PLAIN, 18)); // Fuente con estilo de ajedrez
        button.setAlignmentX(Component.CENTER_ALIGNMENT); // Centrar el botón
        button.setBackground(background); // Fondo negro para el botón
        button.setForeground(foreground); // Color del texto
        button.setFocusPainted(false); // Quitar borde azul cuando el botón está enfocado
        button.setBorder(BorderFactory.createLineBorder(border, 2)); // Borde rojo
        return button;
    }

    private void iniciarCuadricula() {
        getPanelJuego().removeAll();  // Limpiar el panel del tablero antes de agregar componentes

        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                // Añadir botones de la cuadricula del tablero
                getCuadricula()[i][j] = new JButton();
                ImageIcon icono = null;

                if (i == 6) {
                    // Peón blanco 0 (cambiado de la fila 1 a la fila 6)
                    icono = obtenerIcono(0);
                } else if (i == 1) {
                    // Peón negro 1 (cambiado de la fila 6 a la fila 1)
                    icono = obtenerIcono(1);
                } else if (i == 7 && (j == 0 || j == 7)) {
                    // Torre Blanca 2 (cambiado de la fila 0 a la fila 7)
                    icono = obtenerIcono(2);
                } else if (i == 0 && (j == 0 || j == 7)) {
                    // Torre Negra 3 (cambiado de la fila 7 a la fila 0)
                    icono = obtenerIcono(3);
                } else if (i == 7 && (j == 1 || j == 6)) {
                    // Caballo Blanco 4 (cambiado de la fila 0 a la fila 7)
                    icono = obtenerIcono(4);
                } else if (i == 0 && (j == 1 || j == 6)) {
                    // Caballo Negro 5 (cambiado de la fila 7 a la fila 0)
                    icono = obtenerIcono(5);
                } else if (i == 7 && (j == 2 || j == 5)) {
                    // Alfil Blanco 6 (cambiado de la fila 0 a la fila 7)
                    icono = obtenerIcono(6);
                } else if (i == 0 && (j == 2 || j == 5)) {
                    // Alfil Negro 7 (cambiado de la fila 7 a la fila 0)
                    icono = obtenerIcono(7);
                } else if (i == 7 && j == 4) {
                    // Rey Blanco 8 (cambiado de la fila 0 a la fila 7)
                    icono = obtenerIcono(8);
                } else if (i == 0 && j == 4) {
                    // Rey Negro 9 (cambiado de la fila 7 a la fila 0)
                    icono = obtenerIcono(9);
                } else if (i == 7 && j == 3) {
                    // Reina Blanca 10 (cambiado de la fila 0 a la fila 7)
                    icono = obtenerIcono(10);
                } else if (i == 0 && j == 3) {
                    // Reina Negra 11 (cambiado de la fila 7 a la fila 0)
                    icono = obtenerIcono(11);
                }

                getCuadricula()[i][j].setFocusPainted(false);
                getCuadricula()[i][j].setBorderPainted(false);

                if ((i + j) % 2 == 0) {
                    getCuadricula()[i][j].setBackground(new Color(222, 184, 135));
                } else {
                    getCuadricula()[i][j].setBackground(new Color(139, 69, 19));
                }

                getCuadricula()[i][j].setIcon(icono);
                getCuadricula()[i][j].setPreferredSize(new Dimension(100, 75));
                getPanelJuego().add(getCuadricula()[i][j]);
            }
        }

        getPanelJuego().revalidate(); // Actualizar el panel para mostrar los cambios
        getPanelJuego().repaint();
    }

    public void mostrarTextoTemporal(String texto, int duracion) {
        // Crear un nuevo JFrame
        JFrame tempFrame = new JFrame();

        tempFrame.setSize(200, 100); // Tamaño del JFrame
        tempFrame.setLocationRelativeTo(null); // Centrar en la pantalla
        tempFrame.setUndecorated(true); // Sin bordes ni barra de título
        tempFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        // Crear un JLabel para mostrar el texto
        JLabel textoLabel = new JLabel(texto, SwingConstants.CENTER);
        textoLabel.setPreferredSize(new Dimension(200, 100));

        // Añadir el JLabel al JFrame
        tempFrame.getContentPane().add(textoLabel);

        // Mostrar el JFrame
        tempFrame.setBackground(Color.red);
        tempFrame.setVisible(true);

        // Crear un temporizador para cerrar el JFrame después de la duración especificada
        Timer timer = new Timer(duracion, (@SuppressWarnings("unused") ActionEvent e) -> {
            tempFrame.dispose(); // Cerrar el JFrame
        });
        timer.setRepeats(false); // El temporizador no se repite
        timer.start(); // Iniciar el temporizador
    }

    public void reiniciarTablero() {
        iniciarCuadricula();
        initComponents();
    }

    public ImageIcon obtenerIcono(int indicePieza) {
        String[] piezas = {
            "PeonBlanco.png", "PeonNegro.png",
            "TorreBlanco.png", "TorreNegro.png",
            "CaballoBlanco.png", "CaballoNegro.png",
            "AlfilBlanco.png", "AlfilNegro.png",
            "ReyBlanco.png", "ReyNegro.png",
            "ReinaBlanco.png", "ReinaNegro.png"
        };

        // Ruta de la imagen
        String rutaImagen = "images/" + piezas[indicePieza];
        System.out.println(rutaImagen);

        // Cargar y redimensionar la imagen
        BufferedImage imagen = null;
        try {
            imagen = ImageIO.read(new File(rutaImagen));
        } catch (IOException e) {
        }

        if (imagen != null) {
            Image imagenRedimensionada = imagen.getScaledInstance(100, 75, Image.SCALE_SMOOTH);
            return new ImageIcon(imagenRedimensionada);
        } else {
            return new ImageIcon(); // Devuelve un icono vacío si no se puede cargar la imagen
        }

    }

    private void setIcono() {
        try {
            // Ruta del archivo del ícono
            String rutaIcono = "images/Icono.jpg";
            // Cargar la imagen
            BufferedImage imagenIcono = ImageIO.read(new File(rutaIcono));
            // Redimensionar la imagen si es necesario
            Image imagenRedimensionada = imagenIcono.getScaledInstance(32, 32, Image.SCALE_SMOOTH);
            // Establecer el ícono del JFrame
            this.setIconImage(imagenRedimensionada);
        } catch (IOException e) {
        }
    }

    public JButton getBotonReglas() {
        return botonReglas;
    }

    public JFrame getReglas() {
        return reglas;
    }

    public JButton[][] getCuadricula() {
        return cuadricula;
    }

    public JPanel getPanelIzquierdo() {
        return panelIzquierdo;
    }

    public JPanel getPanelDerecho() {
        return panelDerecho;
    }

    /**
     * @return the ventanaJugadaPeon
     */
    public VentanaJugadaPeon getVentanaJugadaPeon() {
        return ventanaJugadaPeon;
    }

    /**
     * @param ventanaJugadaPeon the ventanaJugadaPeon to set
     */
    public void setVentanaJugadaPeon(VentanaJugadaPeon ventanaJugadaPeon) {
        this.ventanaJugadaPeon = ventanaJugadaPeon;
    }

    /**
     * @return the elegirTurno
     */
    public JFrame getElegirTurno() {
        return elegirTurno;
    }

    /**
     * @param elegirTurno the elegirTurno to set
     */
    public void setElegirTurno(JFrame elegirTurno) {
        this.elegirTurno = elegirTurno;
    }

    /**
     * @return the panelJuego
     */
    public JPanel getPanelJuego() {
        return panelJuego;
    }

    /**
     * @param panelJuego the panelJuego to set
     */
    public void setPanelJuego(JPanel panelJuego) {
        this.panelJuego = panelJuego;
    }

    /**
     * @param cuadricula the cuadricula to set
     */
    public void setCuadricula(JButton[][] cuadricula) {
        this.cuadricula = cuadricula;
    }

    /**
     * @param panelIzquierdo the panelIzquierdo to set
     */
    public void setPanelIzquierdo(JPanel panelIzquierdo) {
        this.panelIzquierdo = panelIzquierdo;
    }

    /**
     * @param panelDerecho the panelDerecho to set
     */
    public void setPanelDerecho(JPanel panelDerecho) {
        this.panelDerecho = panelDerecho;
    }

    /**
     * @param reglas the reglas to set
     */
    public void setReglas(JFrame reglas) {
        this.reglas = reglas;
    }

    /**
     * @param botonReglas the botonReglas to set
     */
    public void setBotonReglas(JButton botonReglas) {
        this.botonReglas = botonReglas;
    }

    /**
     * @return the ajedrez
     */
    public Ajedrez getAjedrez() {
        return ajedrez;
    }

    /**
     * @param ajedrez the ajedrez to set
     */
    public void setAjedrez(Ajedrez ajedrez) {
        this.ajedrez = ajedrez;
    }

    /**
     * @return the turno1
     */
    public JButton getTurno1() {
        return turno1;
    }

    /**
     * @param turno1 the turno1 to set
     */
    public void setTurno1(JButton turno1) {
        this.turno1 = turno1;
    }

    /**
     * @return the turno2
     */
    public JButton getTurno2() {
        return turno2;
    }

    /**
     * @param turno2 the turno2 to set
     */
    public void setTurno2(JButton turno2) {
        this.turno2 = turno2;
    }

    /**
     * @return the turnosPannel
     */
    public JPanel getTurnosPannel() {
        return turnosPannel;
    }

    /**
     * @param turnosPannel the turnosPannel to set
     */
    public void setTurnosPannel(JPanel turnosPannel) {
        this.turnosPannel = turnosPannel;
    }

    /**
     * @return the btnReiniciar
     */
    public JButton getBtnReiniciar() {
        return btnReiniciar;
    }

    /**
     * @param btnReiniciar the btnReiniciar to set
     */
    public void setBtnReiniciar(JButton btnReiniciar) {
        this.btnReiniciar = btnReiniciar;
    }

}
