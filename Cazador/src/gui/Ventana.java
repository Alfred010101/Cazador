package gui;

import hilos.VentanaControlador;
import java.awt.Dimension;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JLayeredPane;

/**
 *
 * @author Alfred
 */
public class Ventana extends JFrame
{

    private JLayeredPane layeredPane;
    private JLabel perro;
    
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

    /**
     * @param perro the perro to set
     */
    public void setPerro(JLabel perro)
    {
        this.perro = perro;
    }

    private void initComponets()
    {
        layeredPane = new JLayeredPane();
        layeredPane.setPreferredSize(new Dimension(525, 480));
        setContentPane(layeredPane);
        layeredPane.setLayout(null);

        ImageIcon n1 = new ImageIcon("src/gui/img/fondo_fondo.png");
        ImageIcon n2 = new ImageIcon("src/gui/img/world.png");

        JLabel lblFondo1 = new JLabel(n1);
        lblFondo1.setBounds(0, 0, n1.getIconWidth(), n1.getIconHeight());

        JLabel lblFondo2 = new JLabel(n2);
        lblFondo2.setBounds(0, 0, n2.getIconWidth(), n2.getIconHeight());
        layeredPane.add(lblFondo1, Integer.valueOf(0));
        layeredPane.add(lblFondo2, Integer.valueOf(2));

        perro = new JLabel();
        layeredPane.add(perro, Integer.valueOf(3));
        new Thread(new VentanaControlador(this)).start();
    }

}
