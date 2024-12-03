package gui;

import hilos.VentanaControlador;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JLayeredPane;
import javax.swing.JPanel;

/**
 *
 * @author Alfred
 */
public class Ventana extends JFrame
{

    private JLayeredPane layeredPane;
    private JLabel perro;
    private BufferedImage cazador;
    private JPanel cazadorLayer; // Capa donde se dibuja el arma
    private int mouseX = 0;

    public Ventana()
    {
        super("Duck Hunt 2.0");
        setSize(525, 480);
        setResizable(false);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        initComponets();
    }

    /**
     * @return the layeredPane
     */
    public JLayeredPane getPane()
    {
        return layeredPane;
    }

    /**
     * @return the perro
     */
    public JLabel getPerro()
    {
        return perro;
    }

//    /**
//     * @param perro the perro to set
//     */
//    public void setPerro(JLabel perro)
//    {
//        this.perro = perro;
//    }
    /**
     * @return the cazador
     */
    public BufferedImage getCazador()
    {
        return cazador;
    }

//    /**
//     * @param cazador the cazador to set
//     */
//    public void setCazador(JLabel cazador)
//    {
//        this.cazador = cazador;
//    }
    private void initComponets()
    {
        layeredPane = new JLayeredPane();
        layeredPane.setPreferredSize(new Dimension(525, 480));
        setContentPane(layeredPane);
        layeredPane.setLayout(null);

        ImageIcon n1 = new ImageIcon("src/gui/img/fondo_fondo.png");
        JLabel lblFondo1 = new JLabel(n1);
        lblFondo1.setBounds(0, 0, n1.getIconWidth(), n1.getIconHeight());
        layeredPane.add(lblFondo1, Integer.valueOf(0));

        ImageIcon n2 = new ImageIcon("src/gui/img/world.png");
        JLabel lblFondo2 = new JLabel(n2);
        lblFondo2.setBounds(0, 0, n2.getIconWidth(), n2.getIconHeight());
        layeredPane.add(lblFondo2, Integer.valueOf(2));

        perro = new JLabel();
        layeredPane.add(perro, Integer.valueOf(3));

        try
        {
            cazador = ImageIO.read(new File("src/gui/img/arma_cazador2.png"));
        } catch (IOException e)
        {
            e.printStackTrace();
        }

        cazadorLayer = new JPanel()
        {
            @Override
            protected void paintComponent(Graphics g)
            {
                super.paintComponent(g);

                int weaponBaseX = getWidth() / 2;
                int weaponBaseY = getHeight() - 40;
                double angle = 45 + (double) mouseX / getWidth() * 90 - 90;

                // Configurar el dibujo
                Graphics2D g2d = (Graphics2D) g;
                g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
                g2d.translate(weaponBaseX, weaponBaseY);
                g2d.rotate(Math.toRadians(angle));
                g2d.drawImage(cazador, -cazador.getWidth() / 2 + 30, -cazador.getHeight() + 120, null);
                g2d.rotate(-Math.toRadians(angle));
                g2d.translate(-weaponBaseX, -weaponBaseY);
            }
        };

        cazadorLayer.setOpaque(false);
        cazadorLayer.setBounds(0, 0, 525, 480);
        layeredPane.add(cazadorLayer, JLayeredPane.PALETTE_LAYER);

        layeredPane.addMouseMotionListener(new MouseMotionAdapter()
        {
            @Override
            public void mouseMoved(MouseEvent e)
            {
                mouseX = e.getX();
                cazadorLayer.repaint();
            }
        });

        new Thread(new VentanaControlador(this)).start();
    }
}
