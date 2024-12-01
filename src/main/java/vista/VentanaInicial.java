/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package vista;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

/**
 *
 * @author bejar
 */
public final class VentanaInicial extends JFrame {

    private static final long serialVersionUID = 1L;

    private JPanel titlePanel;
    private JLabel titleLabel;
    private JButton buttonPlay;
    private JButton btnRules;
    private JButton btnExit;
    private JPanel buttonPanel;
    private JPanel backgroundPanel;
    private JPanel rulesPanel;

    @SuppressWarnings("unused")
    public VentanaInicial() {
        initComponents();
        btnExit.addActionListener((e) -> {
            System.exit(0);
        });
        setIcono();
    }

    @SuppressWarnings("unused")
    public void rulesPanel() {
        // Crear o reutilizar el panel de reglas
        if (rulesPanel == null) {
            rulesPanel = new JPanel();
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

            JLabel reglas = new JLabel("<html>" + text.replaceAll("\n", "<br/>") + "</html>");
            reglas.setFont(new Font("Serif", Font.PLAIN, 16));
            reglas.setForeground(Color.WHITE);
            reglas.setAlignmentX(Component.CENTER_ALIGNMENT);

            // Crear botón para volver atrás
            JButton btnBack = createButton("Atrás");

            // Añadir acción al botón de volver atrás
            btnBack.addActionListener(e -> showMainMenu());

            // Añadir componentes al panel de reglas
            rulesPanel.add(Box.createRigidArea(new Dimension(0, 20))); // Espacio arriba
            rulesPanel.add(reglas);
            rulesPanel.add(Box.createRigidArea(new Dimension(0, 20))); // Espacio entre el texto y el botón
            rulesPanel.add(btnBack);
        }

        // Reemplazar el contenido actual del backgroundPanel con el rulesPanel
        backgroundPanel.removeAll();
        backgroundPanel.add(rulesPanel, BorderLayout.CENTER);
        backgroundPanel.revalidate();
        backgroundPanel.repaint();
    }

    private void showMainMenu() {
        backgroundPanel.removeAll();
        backgroundPanel.add(buttonPanel, BorderLayout.SOUTH);
        backgroundPanel.revalidate();
        backgroundPanel.repaint();
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

    private void initComponents() {
        // Crear la ventana principal
        this.setTitle("Chess Game");
        this.setSize(1024, 700);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setLayout(new BorderLayout());
        this.setLocationRelativeTo(null);

        // Crear un panel para el título
        titlePanel = new JPanel();
        titleLabel = new JLabel("Chess Game", JLabel.CENTER);
        titleLabel.setFont(new Font("Serif", Font.BOLD, 32)); // Fuente con estilo de ajedrez
        titleLabel.setForeground(Color.WHITE); // Color del texto
        titlePanel.setBackground(new Color(0, 0, 0)); // Fondo negro para el panel del título
        titlePanel.add(titleLabel);

        // Añadir el panel del título al centro de la ventana
        this.add(titlePanel, BorderLayout.NORTH);

        // Crear un panel para los botones
        buttonPanel = new JPanel();
        buttonPanel.setLayout(new BoxLayout(buttonPanel, BoxLayout.Y_AXIS)); // Alinea los botones verticalmente
        buttonPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10)); // Añadir espacio alrededor de los botones
        buttonPanel.setOpaque(false); // Hacer el panel transparente para ver la imagen de fondo

        // Crear los botones
        buttonPlay = createButton("Jugar");
        btnRules = createButton("Ver Reglas");
        btnExit = createButton("Salir");

        // Añadir los botones al panel de botones
        buttonPanel.add(buttonPlay);
        buttonPanel.add(Box.createRigidArea(new Dimension(0, 10))); // Espacio entre los botones
        buttonPanel.add(btnRules);
        buttonPanel.add(Box.createRigidArea(new Dimension(0, 10))); // Espacio entre los botones
        buttonPanel.add(btnExit);

        // Crear un panel con la imagen de fondo
        backgroundPanel = new JPanel() {
            private Image backgroundImage;

            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                if (backgroundImage != null) {
                    g.drawImage(backgroundImage, 0, 0, getWidth(), getHeight(), this);
                }
            }

            {
                try {
                    // Cargar la imagen de fondo
                    backgroundImage = ImageIO.read(new File("images/imageBackground.jpg"));
                } catch (IOException e) {
                }
            }
        };
        backgroundPanel.setLayout(new BorderLayout());
        backgroundPanel.add(buttonPanel, BorderLayout.SOUTH);

        // Añadir el panel de fondo a la ventana
        this.add(backgroundPanel, BorderLayout.CENTER);

        // Hacer visible la ventana
        this.setVisible(true);

    }

    // Crear un método para configurar los botones
    private static JButton createButton(String text) {
        JButton button = new JButton(text);
        int widht = 400, height = 50;
        button.setPreferredSize(new Dimension(widht, height)); // Ancho fijo y altura mayor para el botón
        button.setMinimumSize(new Dimension(widht, height)); // Tamaño mínimo
        button.setMaximumSize(new Dimension(widht, height)); // Tamaño máximo
        button.setFont(new Font("Serif", Font.PLAIN, 18)); // Fuente con estilo de ajedrez
        button.setAlignmentX(Component.CENTER_ALIGNMENT); // Centrar el botón
        button.setBackground(Color.BLACK); // Fondo negro para el botón
        button.setForeground(Color.RED); // Color del texto
        button.setFocusPainted(false); // Quitar borde azul cuando el botón está enfocado
        button.setBorder(BorderFactory.createLineBorder(Color.RED, 2)); // Borde rojo
        return button;
    }

    /**
     * @return the buttonPlay
     */
    public JButton getButtonPlay() {
        return buttonPlay;
    }

    public JPanel getTitlePanel() {
        return titlePanel;
    }

    public JLabel getTitleLabel() {
        return titleLabel;
    }

    public JButton getBtnRules() {
        return btnRules;
    }

    public JButton getBtnExit() {
        return btnExit;
    }

    public JPanel getButtonPanel() {
        return buttonPanel;
    }

    public JPanel getBackgroundPanel() {
        return backgroundPanel;
    }

}
