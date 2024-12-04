
package hilos;

import java.awt.Point;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.ImageIcon;
import javax.swing.JLabel;

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
     
    public Pato(JLabel imgPato, String color, int trayectoria)
    {
        this.imgPato = imgPato;
        this.delay = 70;
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
                }
            }
        });
    }
    
    @Override
    public void run()
    {
         try
        {
            for (int i = 0; i < TRAYECTORIA.getCoordenadas().size(); i++)
            {
                System.out.println("A");
                imagen = new ImageIcon(PATH + TRAYECTORIA.getSprites().get(i));
                imgPato.setIcon(imagen);
                Point punto = TRAYECTORIA.getCoordenadas().get(i);
                imgPato.setBounds(punto.x, punto.y, imagen.getIconWidth(), imagen.getIconHeight());

                Thread.sleep(delay);

                if (morido)
                {
//                    Contador.setPuntaje(switch(color)
//                            {
//                                case "negro" -> 200;
//                                case "rojo" -> 150;
//                                case "azul" -> 100;
//                                default -> 100;
//                            });
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
//                    Perro p = new Perro(layeredPane);
//                    layeredPane.add(p, Integer.valueOf(2));
//                    if (!Contador.perroRecogiendo)
//                    {
//                        Contador.perroRecogiendo = true;
//                        p.atrapar(1);
//                        Contador.perroRecogiendo = false;
//                    }                    
                    break;
                }
            }
        } catch (InterruptedException ex)
        {
            System.out.println(ex);
        }
    }    
}
