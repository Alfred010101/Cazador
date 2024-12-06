package hilos;

import java.awt.Point;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JLayeredPane;

/**
 *
 * @author Alfred
 */
public class Perro
{

    private final JLabel imgPerro;
    private final JLayeredPane layeredPane;
    private final String PATH = "src/gui/img/perro/";
    private boolean ocupado = false;
    private final List<Point> coordenadas;
    private final List<Point> pcCoordendas;

    public Perro(JLabel imgPerro, JLayeredPane layeredPane)
    {
        this.imgPerro = imgPerro;
        this.layeredPane = layeredPane;

        coordenadas = List.of(
                new Point(220, 280),
                new Point(220, 275),
                new Point(220, 270),
                new Point(220, 265),
                new Point(220, 260),
                new Point(220, 255),
                new Point(220, 250),
                new Point(220, 245),
                new Point(220, 240),
                new Point(220, 235),
                new Point(220, 230),
                new Point(220, 225),
                new Point(220, 220)
        );

        pcCoordendas = new ArrayList<>(coordenadas);
        Collections.reverse(pcCoordendas);
    }

    /**
     * @return the ocupado
     */
    public boolean isOcupado()
    {
        return ocupado;
    }

    /**
     * @param ocupado the ocupado to set
     */
    public void setOcupado(boolean ocupado)
    {
        this.ocupado = ocupado;
    }

    public void intro()
    {
        List<Point> caminar1Coordenadas = List.of(
                new Point(-30, 280),
                new Point(5, 280),
                new Point(30, 280),
                new Point(55, 280)
        );

        List<Point> caminar2Coordenadas = List.of(
                new Point(55, 280),
                new Point(80, 280),
                new Point(105, 280),
                new Point(130, 280)
        );

        List<Point> saltarCoordenadas = List.of(
                new Point(165, 220),
                new Point(175, 200),
                new Point(185, 180),
                new Point(195, 160)
        );

        List<Point> caerCoordenadas = List.of(
                new Point(225, 160),
                new Point(235, 180),
                new Point(245, 200),
                new Point(255, 220),
                new Point(265, 240)
        );

        accionCaminar(caminar1Coordenadas, "dogright", 180);
        accionOlfatear(caminar1Coordenadas.get(caminar1Coordenadas.size() - 1), "dognuzzle", 4, 70);
        accionCaminar(caminar2Coordenadas, "dogright", 180);
        accionOlfatear(caminar2Coordenadas.get(caminar1Coordenadas.size() - 1), "dognuzzle", 4, 70);
        accionPreparado(caminar2Coordenadas.get(caminar1Coordenadas.size() - 1), "clue.png", 300);
        accionSaltar(saltarCoordenadas, "dogjump1.png", 90);
        layeredPane.setLayer(imgPerro, 1);
        accionSaltar(caerCoordenadas, "dogjump2.png", 90);
        imgPerro.setIcon(null);
    }

    public void atrapar(int x)
    {
        subir(x, coordenadas, "oneDuck.png");
        pausar(300);
        subir(x, pcCoordendas, "oneDuck.png");
        imgPerro.setIcon(null);
    }

    private void accionCaminar(List<Point> coordenadas, String sources, int delay)
    {
        ImageIcon imagen;
        for (int x = 0; x < coordenadas.size(); x++)
        {
            imagen = new ImageIcon(PATH + sources + (x + 1) + ".png");
            imgPerro.setIcon(imagen);
            imgPerro.setBounds(coordenadas.get(x).x, coordenadas.get(x).y, imagen.getIconWidth(), imagen.getIconHeight());
            pausar(delay);
        }
    }

    private void accionOlfatear(Point coordenadas, String sources, int repetir, int delay)
    {
        ImageIcon imagen;
        for (int x = 0; x < repetir; x++)
        {
            imagen = new ImageIcon(PATH + sources + ((x + 1) % 2) + ".png");
            imgPerro.setIcon(imagen);
            imgPerro.setBounds(coordenadas.x, coordenadas.y, imagen.getIconWidth(), imagen.getIconHeight());
            pausar(delay);
        }
    }

    private void accionSaltar(List<Point> coordenadas, String sources, int delay)
    {
        ImageIcon imagen = new ImageIcon(PATH + sources);
        imgPerro.setIcon(imagen);
        for (int x = 0; x < coordenadas.size(); x++)
        {
            imgPerro.setBounds(coordenadas.get(x).x, coordenadas.get(x).y, imagen.getIconWidth(), imagen.getIconHeight());
            pausar(delay);
        }
    }

    private void accionPreparado(Point coordenadas, String sources, int delay)
    {
        ImageIcon imagen;
        imagen = new ImageIcon(PATH + sources);
        imgPerro.setIcon(imagen);
        imgPerro.setBounds(coordenadas.x, coordenadas.y, imagen.getIconWidth(), imagen.getIconHeight());
        pausar(delay);
    }

    private void subir(int xPos, List<Point> coordenadas, String sources)
    {
        ImageIcon imagen;
        imagen = new ImageIcon(PATH + sources);
        imgPerro.setIcon(imagen);
        for (int x = 0; x < coordenadas.size(); x++)
        {
            imgPerro.setBounds(xPos, coordenadas.get(x).y, imagen.getIconWidth(), imagen.getIconHeight());
            pausar(60);
        }
    }

    private void pausar(int delay)
    {
        try
        {
            Thread.sleep(delay);
        } catch (InterruptedException ex)
        {
            Thread.currentThread().interrupt();
            System.err.println("Interrupción en la animación: " + ex.getMessage());
        }
    }
}
