/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package vista;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Image;
import java.awt.Insets;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

/**
 *
 * @author bejar
 */
public class VentanaGanador extends JFrame {

    private static final long serialVersionUID = 1L;


    private final JButton buttonRestart;
    private JPanel panelPrincipal;
    private JLabel titulo;

    public VentanaGanador() {
        buttonRestart = new JButton();
        panelPrincipal = new JPanel();
        titulo = new JLabel();
        setIcono();
    }

    public void initComponents(int turno) {
        principalPanel();
        titulo = new JLabel("HAS GANADO JUGADOR" + " " + turno);
        titulo.setForeground(Color.gray);
//        titulo.setPreferredSize(new Dimension(300,200));
        titulo.setSize(400, 250);
        // Create a Font object with the desired size
        Font largeFont = new Font("Arial", Font.BOLD, 58); // Font name, style, size

        // Apply the Font to the JLabel
        titulo.setFont(largeFont);
        GridBagConstraints gbcTitulo = new GridBagConstraints();
        gbcTitulo.gridx = 0;          // Column position
        gbcTitulo.gridy = 0;          // Row position
        gbcTitulo.anchor = GridBagConstraints.CENTER; // Center the label horizontally
        gbcTitulo.insets = new Insets(0, 0, 400, 0); // Add padding (top, left, bottom, right)

        // Add the label to the panel
        panelPrincipal.add(titulo, gbcTitulo);

        buttonRestart.setText("REINICIAR");
//        resizedImage(getButtonPlay(), "buttonRestart.png");
        buttonRestart.setBackground(Color.gray);
        Font fontButton = new Font("Arial", Font.BOLD, 20); // Font name, style, size
        buttonRestart.setFont(fontButton);
        getButtonRestart().setPreferredSize(new Dimension(150, 50));

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = 0;          // Column position
        gbc.gridy = 0;          // Row position
        gbc.anchor = GridBagConstraints.CENTER; // Center the button
        gbc.insets = new Insets(0, 0, -400, 0); // Add padding to push the button down

        // Add the button to the panel
        panelPrincipal.add(getButtonRestart(), gbc);

        // Optionally, set up the JFrame and add the panel
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.getContentPane().add(panelPrincipal);
        this.setSize(800, 600); // Set frame size
        this.setLocationRelativeTo(null);
        this.setVisible(true);
    }

    public void principalPanel() {
        panelPrincipal = new JPanel(new GridBagLayout()) {
            private BufferedImage backgroundImage;

            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                if (backgroundImage != null) {
                    // Draw the background image scaled to the size of the panel
                    g.drawImage(backgroundImage, 0, 0, getWidth(), getHeight(), this);
                }
            }

            {
                // Load the background image
                try {
                    backgroundImage = ImageIO.read(new File("C:\\Users\\bejar\\Documents\\NetBeansProjects\\Ajedrez\\src\\main\\java\\vista\\images\\ganador.jpg"));
                } catch (IOException e) {
                }
            }
        };
    }

    public void resizedImage(JButton button, String imagePath) {
        // Create the JButton

        // Load and resize the image
        BufferedImage originalImage = null;
        try {
            originalImage = ImageIO.read(new File("C:\\Users\\bejar\\Documents\\NetBeansProjects\\Ajedrez\\src\\main\\java\\vista\\images\\" + imagePath));
        } catch (IOException e) {
        }

        if (originalImage != null) {
            // Resize the image (for example, to 100x50 pixels)
            Image scaledImage = originalImage.getScaledInstance(350, 400, Image.SCALE_SMOOTH);

            // Set the resized image as the button's icon
            button.setIcon(new ImageIcon(scaledImage));
        } else {
            System.err.println("Error: Could not load the image.");
        }
    }

    /**
     * @return the buttonPlay
     */
    public JButton getButtonRestart() {
        return buttonRestart;
    }

    private void setIcono() {
        try {
            // Ruta del archivo del ícono
            String rutaIcono = "C:\\Users\\bejar\\Documents\\NetBeansProjects\\Ajedrez\\src\\main\\java\\vista\\images\\Icono.jpg";
            // Cargar la imagen
            BufferedImage imagenIcono = ImageIO.read(new File(rutaIcono));
            // Redimensionar la imagen si es necesario
            Image imagenRedimensionada = imagenIcono.getScaledInstance(32, 32, Image.SCALE_SMOOTH);
            // Establecer el ícono del JFrame
            this.setIconImage(imagenRedimensionada);
        } catch (IOException e) {
        }
    }
}
