package gui;

import hilos.Pato;
import hilos.VentanaControlador;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Point;
import java.awt.RenderingHints;
import java.awt.Toolkit;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.LinkedList;
import java.util.Queue;
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
    private Queue<Pato> patos;

    private JLabel lblInputPatos;
    private JLabel lblOutputPatos;
    private JLabel lblInputBalas;
    private JLabel lblOutputBalas;

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
     * @return the cazadorLayer
     */
    public JPanel getCazadorLayer()
    {
        return cazadorLayer;
    }

    /**
     * @return the perro
     */
    public JLabel getPerro()
    {
        return perro;
    }

    /**
     * @return the cazador
     */
    public BufferedImage getCazador()
    {
        return cazador;
    }

    /**
     * @return the patos
     */
    public Queue<Pato> getPatos()
    {
        return patos;
    }
    
     /**
     * @return the lblInputPatos
     */
    public JLabel getLblInputPatos()
    {
        return lblInputPatos;
    }
    
    /**
     * @return the lblOutputPatos
     */
    public JLabel getLblOutputPatos()
    {
        return lblOutputPatos;
    }

    /**
     * @return the lblInputBalas
     */
    public JLabel getLblInputBalas()
    {
        return lblInputBalas;
    }

    /**
     * @return the lblOutputBalas
     */
    public JLabel getLblOutputBalas()
    {
        return lblOutputBalas;
    }

    private void initComponets()
    {
        Toolkit toolkit = Toolkit.getDefaultToolkit();
        Image cursorImage = toolkit.getImage("src/gui/img/apuntador.png");

        Point hotspot = new Point(cursorImage.getWidth(null) / 2, cursorImage.getHeight(null) / 2); // Punto de referencia
        Cursor customCursor = toolkit.createCustomCursor(cursorImage, hotspot, "Cursor Personalizado");
        // Aplicar el cursor al JFrame
        setCursor(customCursor);

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

        lblInputPatos = initJLabel(lblInputPatos, 0, 350);
        lblOutputPatos = initJLabel(lblOutputPatos, 15, 370);
        lblInputBalas = initJLabel(lblInputBalas, 2, 390);
        lblOutputBalas = initJLabel(lblOutputBalas, 16, 410);
        
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
        cazadorLayer.setVisible(false);

        patos = new LinkedList<>();

        new Thread(new VentanaControlador(this)).start();
    }
    
    private JLabel initJLabel(JLabel lbl, int x, int y)
    {
        lbl = new JLabel();
        lbl.setFont(new Font("Arial", Font.BOLD, 18));
        lbl.setPreferredSize(new Dimension(300, 30));
        lbl.setBounds(x, y, lbl.getPreferredSize().width, lbl.getPreferredSize().height);
        lbl.setForeground(Color.WHITE);
        layeredPane.add(lbl, Integer.valueOf(4));
        return lbl;
    }
}
