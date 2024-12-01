/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package vista;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

/**
 *
 * @author bejar
 */
public final class VentanaJugadaPeon extends JFrame {

    private static final long serialVersionUID = 1L;

    private JPanel panel;
    private JButton fichas[][];

    public VentanaJugadaPeon() {
        initComonents();
        setIcono();
    }

    public void initComonents() {
        setPanel(new JPanel(new GridLayout(1, 5)));
        setFichas(new JButton[2][5]);
        for (JButton[] ficha : getFichas()) {
            for (int j = 0; j < ficha.length; j++) {
                ficha[j] = new JButton();
                ficha[j].setBackground(Color.white);
            }
        }
        this.setSize(500, 125);
        this.getContentPane().add(getPanel());
        this.setLocationRelativeTo(null);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

    }

    public void ponerImagenes(int color) {
        for (int i = 0; i < 5; i++) {
            ImageIcon icono;
            if (color == 0) {
                icono = obtenerIcono(i);
            } else {
                icono = obtenerIcono(i + 5);
            }

            getFichas()[color][i].setIcon(icono);
            getFichas()[color][i].setPreferredSize(new Dimension(100, 75));
            getPanel().add(getFichas()[color][i]);
        }
    }

    public ImageIcon obtenerIcono(int indicePieza) {
        String[] piezas = {
            "PeonBlanco.png", "TorreBlanco.png",
            "CaballoBlanco.png", "AlfilBlanco.png",
            "ReinaBlanco.png", "PeonNegro.png",
            "TorreNegro.png", "CaballoNegro.png",
            "AlfilNegro.png", "ReinaNegro.png"};

        // Ruta de la imagen
        String rutaImagen = "images/" + piezas[indicePieza];

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

    /**
     * @return the serialVersionUID
     */
    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    /**
     * @return the panel
     */
    public JPanel getPanel() {
        return panel;
    }

    /**
     * @param panel the panel to set
     */
    public void setPanel(JPanel panel) {
        this.panel = panel;
    }

    /**
     * @return the fichas
     */
    public JButton[][] getFichas() {
        return fichas;
    }

    /**
     * @param fichas the fichas to set
     */
    public void setFichas(JButton[][] fichas) {
        this.fichas = fichas;
    }
    
}
