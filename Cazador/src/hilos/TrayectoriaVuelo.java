package hilos;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Alfred
 */
public class TrayectoriaVuelo
{

    private List<Point> coordenadas;
    private List<String> sprites;
    private final int trayectoria;

    public TrayectoriaVuelo(int trayectoria)
    {
        this.coordenadas = new ArrayList<>();
        this.sprites = new ArrayList<>();
        this.trayectoria = trayectoria;
        setCordenadas();
        setSprites();
    }

    private void setCordenadas()
    {
        coordenadas = switch (trayectoria)
        {
            case 0 ->
                List.of(
                new Point(0, 200),
                new Point(15, 190),
                new Point(30, 200),
                new Point(45, 190),
                new Point(60, 200),
                new Point(75, 190),
                new Point(90, 200),
                new Point(105, 190),
                new Point(120, 200),
                new Point(135, 190),
                new Point(150, 200),
                new Point(165, 190),
                new Point(180, 200),
                new Point(195, 190),
                new Point(210, 180),
                new Point(225, 170),
                new Point(240, 160),
                new Point(255, 150),
                new Point(270, 140),
                new Point(285, 130),
                new Point(300, 120),
                new Point(315, 110),
                new Point(330, 100),
                new Point(345, 90),
                new Point(360, 80),
                new Point(375, 70),
                new Point(390, 60),
                new Point(405, 50),
                new Point(420, 40),
                new Point(435, 30),
                new Point(450, 20),
                new Point(435, 10),
                new Point(420, 0),
                new Point(405, -10),
                new Point(390, 0),
                new Point(375, 5),
                new Point(360, 5),
                new Point(345, 10),
                new Point(330, 10),
                new Point(315, 10),
                new Point(300, 10),
                new Point(285, 10),
                new Point(270, 10),
                new Point(255, 15),
                new Point(240, 15),
                new Point(225, 15),
                new Point(210, 20),
                new Point(195, 20),
                new Point(180, 20),
                new Point(165, 30),
                new Point(150, 30),
                new Point(135, 35),
                new Point(120, 35),
                new Point(105, 35),
                new Point(90, 40),
                new Point(75, 50),
                new Point(60, 55),
                new Point(45, 60),
                new Point(60, 65),
                new Point(75, 70),
                new Point(90, 75),
                new Point(105, 80),
                new Point(120, 85),
                new Point(135, 90),
                new Point(150, 95),
                new Point(165, 100),
                new Point(180, 105),
                new Point(195, 110),
                new Point(210, 115),
                new Point(225, 120),
                new Point(240, 125),
                new Point(255, 130),
                new Point(260, 135),
                new Point(270, 140),
                new Point(280, 145),
                new Point(290, 150),
                new Point(300, 155),
                new Point(310, 160),
                new Point(320, 165),
                new Point(335, 170),
                new Point(340, 175),
                new Point(350, 180),
                new Point(360, 185),
                new Point(370, 190),
                new Point(380, 195),
                new Point(370, 195),
                new Point(355, 195),
                new Point(340, 195),
                new Point(325, 195),
                new Point(310, 190),
                new Point(295, 190),
                new Point(280, 190),
                new Point(265, 185),
                new Point(250, 185),
                new Point(235, 185),
                new Point(220, 185),
                new Point(205, 180),
                new Point(190, 180),
                new Point(175, 175),
                new Point(160, 175),
                new Point(145, 170),
                new Point(130, 170),
                new Point(125, 165),
                new Point(100, 165),
                new Point(90, 170),
                new Point(80, 175),
                new Point(70, 180),
                new Point(60, 185),
                new Point(50, 195),
                new Point(40, 200),
                new Point(30, 205),
                new Point(20, 210),
                new Point(10, 215),
                new Point(0, 220),
                new Point(-10, 220),
                new Point(-20, 220)
                );

            case 1 ->
                List.of(
                new Point(0, 260),
                new Point(12, 250),
                new Point(24, 240),
                new Point(36, 230),
                new Point(48, 220),
                new Point(60, 210),
                new Point(60, 200),
                new Point(96, 190),
                new Point(108, 180),
                new Point(120, 170),
                new Point(132, 160),
                new Point(144, 150),
                new Point(156, 140),
                new Point(168, 130),
                new Point(180, 120),
                new Point(192, 110),
                new Point(204, 100),
                new Point(216, 90),
                new Point(228, 80),
                new Point(240, 70),
                new Point(252, 60),
                new Point(264, 50),
                new Point(276, 40),
                new Point(288, 30),
                new Point(300, 20),
                new Point(312, 10),
                new Point(324, 0),
                new Point(336, -10),
                new Point(348, -20),
                new Point(360, -25),
                new Point(372, -30),
                new Point(384, -35),
                new Point(396, -40),
                new Point(405, -60)
                );

            case 2 ->
            {
                coordenadas.add(new Point(0, 200));

                // Ascenso ondulado
                for (int i = 1; i <= 20; i++)
                {
                    int x = i * 10;
                    int y = 200 + (i % 2 == 0 ? 10 : -10); // Alterna entre 200 y 210
                    coordenadas.add(new Point(x, y));
                }

                // Descenso en curva
                for (int i = 1; i <= 15; i++)
                {
                    int x = 200 + i * 10;
                    int y = 200 - i * 10; // Baja gradualmente
                    coordenadas.add(new Point(x, y));
                }

                // Oscilación lateral
                for (int i = 1; i <= 15; i++)
                {
                    int x = 350 + (i % 2 == 0 ? 10 : -10) * i; // Oscila en x
                    int y = 50 + i * 5; // Sube ligeramente en y
                    coordenadas.add(new Point(x, y));
                }

                // Reascenso curvado
                for (int i = 1; i <= 20; i++)
                {
                    int x = 500 - i * 10;
                    int y = 100 + i * 5; // Vuelve a subir
                    coordenadas.add(new Point(x, y));
                }

                // Cierre del ciclo
                for (int i = 1; i <= 20; i++)
                {
                    int x = 300 - i * 10;
                    int y = 200 - (i % 2 == 0 ? 10 : -10); // Ondula de vuelta al inicio
                    coordenadas.add(new Point(x, y));
                }

                // Asegurar el cierre
                coordenadas.add(new Point(0, 200));

                yield coordenadas;
            }
            default ->
                List.of();
        };
    }

    public List<Point> getCoordenadas()
    {
        return coordenadas;
    }

    private void setSprites()
    {
        sprites = switch (trayectoria)
        {
            case 0, 2, 3, 4, 6, 7, 8, 9, 10, 11, 13 ->
                List.of(
                "duckright1.png",
                "duckright2.png",
                "duckright3.png",
                "duckright4.png",
                "duckright1.png",
                "duckright2.png",
                "duckright3.png",
                "duckright4.png",
                "duckright1.png",
                "duckright2.png",
                "duckright3.png",
                "duckright4.png",
                "duckright1.png",
                "duckright2.png",
                "duckright3.png",
                "duckright4.png",
                "duckright1.png",
                "duckright2.png",
                "duckright3.png",
                "duckright4.png",
                "duckright1.png",
                "duckright2.png",
                "duckright3.png",
                "duckright4.png",
                "duckright1.png",
                "duckright2.png",
                "duckright3.png",
                "duckright4.png",
                "duckright1.png",
                "duckright2.png",
                "duckright3.png",
                "duckleft1.png",
                "duckleft2.png",
                "duckleft3.png",
                "duckleft4.png",
                "duckleft1.png",
                "duckleft2.png",
                "duckleft3.png",
                "duckleft4.png",
                "duckleft1.png",
                "duckleft2.png",
                "duckleft3.png",
                "duckleft4.png",
                "duckleft1.png",
                "duckleft2.png",
                "duckleft3.png",
                "duckleft4.png",
                "duckleft1.png",
                "duckleft2.png",
                "duckleft3.png",
                "duckleft4.png",
                "duckleft1.png",
                "duckleft2.png",
                "duckleft3.png",
                "duckleft4.png",
                "duckleft1.png",
                "duckleft2.png",
                "duckright1.png",
                "duckright2.png",
                "duckright3.png",
                "duckright4.png",
                "duckright1.png",
                "duckright2.png",
                "duckright3.png",
                "duckright4.png",
                "duckright1.png",
                "duckright2.png",
                "duckright3.png",
                "duckright4.png",
                "duckright1.png",
                "duckright2.png",
                "duckright3.png",
                "duckright4.png",
                "duckright1.png",
                "duckright2.png",
                "duckright3.png",
                "duckright4.png",
                "duckright1.png",
                "duckright2.png",
                "duckright3.png",
                "duckright4.png",
                "duckright1.png",
                "duckright2.png",
                "duckright3.png",
                "duckright4.png",
                "duckleft1.png",
                "duckleft2.png",
                "duckleft3.png",
                "duckleft4.png",
                "duckleft1.png",
                "duckleft2.png",
                "duckleft3.png",
                "duckleft4.png",
                "duckleft1.png",
                "duckleft2.png",
                "duckleft3.png",
                "duckleft4.png",
                "duckleft1.png",
                "duckleft2.png",
                "duckleft3.png",
                "duckleft4.png",
                "duckleft1.png",
                "duckleft2.png",
                "duckleft3.png",
                "duckleft4.png",
                "duckleft1.png",
                "duckleft2.png",
                "duckleft3.png",
                "duckleft4.png",
                "duckleft1.png",
                "duckleft2.png",
                "duckleft3.png",
                "duckleft4.png",
                "duckleft1.png",
                "duckleft2.png",
                "duckleft3.png"
                );
            case 1 ->
                List.of(
                "duckright1.png",
                "duckright1.png",
                "duckright1.png",
                "duckright1.png",
                "duckright1.png",
                "duckright1.png",
                "duckright1.png",
                "duckright1.png",
                "duckright1.png",
                "duckright1.png",
                "duckright1.png",
                "duckright1.png",
                "duckright1.png",
                "duckright1.png",
                "duckright1.png",
                "duckright1.png",
                "duckright1.png",
                "duckright1.png",
                "duckright1.png",
                "duckright1.png",
                "duckright1.png",
                 "duckright1.png",
                "duckright1.png",
                "duckright1.png",
                "duckright1.png",
                "duckright1.png",
                "duckright1.png",
                "duckright1.png",
                "duckright1.png",
                "duckright1.png",
                "duckright1.png",
                "duckright1.png",
                "duckright1.png",
                "duckright1.png",
                "duckright1.png",
                "duckright1.png",
                "duckright1.png",
                "duckright1.png",
                "duckright1.png",
                "duckright1.png",
                "duckright1.png",
                "duckright1.png",
                 "duckright1.png",
                "duckright1.png",
                "duckright1.png",
                "duckright1.png",
                "duckright1.png",
                "duckright1.png",
                "duckright1.png",
                "duckright1.png",
                "duckright1.png",
                "duckright1.png",
                "duckright1.png",
                "duckright1.png",
                "duckright1.png",
                "duckright1.png",
                "duckright1.png",
                "duckright1.png",
                "duckright1.png",
                "duckright1.png",
                "duckright1.png",
                "duckright1.png",
                "duckright1.png",
                 "duckright1.png",
                "duckright1.png",
                "duckright1.png",
                "duckright1.png",
                "duckright1.png",
                "duckright1.png",
                "duckright1.png",
                "duckright1.png",
                "duckright1.png",
                "duckright1.png",
                "duckright1.png",
                "duckright1.png",
                "duckright1.png",
                "duckright1.png",
                "duckright1.png",
                "duckright1.png",
                "duckright1.png",
                "duckright1.png",
                "duckright1.png",
                "duckright1.png",
                "duckright1.png"
                );
           
            default ->
                List.of(
                "duckleft1.png",
                "duckleft2.png",
                "duckleft3.png",
                "duckleft4.png",
                "duckleft1.png",
                "duckleft2.png",
                "duckleft3.png",
                "duckleft4.png",
                "duckleft1.png",
                "duckleft2.png",
                "duckleft3.png",
                "duckleft4.png",
                "duckleft1.png",
                "duckleft2.png",
                "duckleft3.png",
                "duckleft4.png",
                "duckleft1.png",
                "duckleft2.png",
                "duckleft3.png",
                "duckleft4.png",
                "duckleft1.png",
                "duckleft2.png",
                "duckleft3.png",
                "duckleft4.png",
                "duckleft1.png",
                "duckleft2.png",
                "duckleft3.png",
                "duckleft4.png",
                "duckleft1.png",
                "duckleft2.png",
                "duckleft1.png",
                "duckleft2.png",
                "duckleft3.png",
                "duckleft4.png",
                "duckleft1.png",
                "duckleft2.png",
                "duckleft3.png",
                "duckleft4.png",
                "duckleft1.png",
                "duckleft2.png",
                "duckleft3.png",
                "duckleft4.png",
                "duckleft1.png",
                "duckleft2.png",
                "duckleft3.png",
                "duckleft4.png",
                "duckleft1.png",
                "duckleft2.png",
                "duckleft3.png",
                "duckleft4.png",
                "duckleft1.png",
                "duckleft2.png",
                "duckleft3.png",
                "duckleft4.png",
                "duckleft1.png",
                "duckleft2.png",
                "duckleft3.png",
                "duckleft4.png",
                "duckleft1.png",
                "duckleft2.png"
                );
        };
    }

    public List<String> getSprites()
    {
        return sprites;
    }
}
