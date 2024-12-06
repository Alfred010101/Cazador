package hilos;

import gui.Ventana;
import java.awt.Point;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JLayeredPane;
import javax.swing.SwingUtilities;

/**
 *
 * @author Alfred
 */
public class Pato implements Runnable
{

    private JLabel imgPato;
    private ImageIcon imagen;
    private final int delay;
    private final String PATH;
    private boolean morido = false;
    private final TrayectoriaVuelo TRAYECTORIA;
    private final Perro perro;
    private final JLayeredPane layeredPane;
    public Pato(JLabel imgPato, String color, int trayectoria, Perro perro, JLayeredPane layeredPane)
    {
        this.imgPato = imgPato;
        this.delay = 70;
        this.perro = perro;
        this.layeredPane = layeredPane;
        PATH = "src/gui/img/patos/" + color + "/";
        TRAYECTORIA = new TrayectoriaVuelo(trayectoria);
        initComponents();
    }

    /**
     * @return the imgPato
     */
    public JLabel getImgPato()
    {
        return imgPato;
    }

    private void initComponents()
    {
        imgPato.addMouseListener(new MouseAdapter()
        {
            @Override
            public void mouseClicked(MouseEvent e)
            {
                if (!morido)
                {
                    morido = !morido;
                    Ventana.contadorPatos--;
                }
                e.getComponent().getParent().dispatchEvent(SwingUtilities.convertMouseEvent(e.getComponent(), e, layeredPane));
            }
        });
    }

    @Override
    public void run()
    {
        try
        {
            Point punto = new Point();
            do
            {
                for (int i = 0; i < TRAYECTORIA.getCoordenadas().size() && !morido; i++)
                {
                    imagen = new ImageIcon(PATH + TRAYECTORIA.getSprites().get(i));
                    imgPato.setIcon(imagen);
                    punto = TRAYECTORIA.getCoordenadas().get(i);
                    imgPato.setBounds(punto.x, punto.y, imagen.getIconWidth(), imagen.getIconHeight());

                    Thread.sleep(delay);
                    
                    if(Ventana.contadorBalas == 0)
                    {
                        imgPato.setIcon(null);
                        break;
                    }
                }
            } while (!morido && Ventana.contadorBalas > 0);
            
            if (morido)
            {
              
            if (TRAYECTORIA.getCoordenadas().get(0).x < TRAYECTORIA.getCoordenadas().get(TRAYECTORIA.getCoordenadas().size() - 1).x)
            {
                imagen = new ImageIcon(PATH + "scaredRight.png");
            } else
            {
                imagen = new ImageIcon(PATH + "scaredLeft.png");
            }
            imgPato.setIcon(imagen);
            imgPato.setBounds(punto.x, punto.y, imagen.getIconWidth(), imagen.getIconHeight());

//                    Contador.lblContPatos.setText("Patos Cazdos : " + Contador.getTotalMoridos());
//                    Contador.lblPuntaje.setText("Puntaje : " + Contador.getPuntaje());
            Thread.sleep(250);

            int a = 1;
            int posY = punto.y;
            while (posY < 300)
            {
                imagen = new ImageIcon(PATH + "duckfall" + a + ".png");
                imgPato.setIcon(imagen);
                imgPato.setBounds(punto.x, posY, imagen.getIconWidth(), imagen.getIconHeight());
                posY += 10;
                a = (a == 1) ? 2 : 1;

                Thread.sleep(40);
            }
            imgPato.setIcon(null);
            
            if (!perro.isOcupado())
            {
                perro.setOcupado(true);
                perro.atrapar(punto.x - 40);
                perro.setOcupado(false);
            }
            
            }
        } catch (InterruptedException ex)
        {
            System.out.println(ex);
        }
    }
}
