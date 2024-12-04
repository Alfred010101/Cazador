package hilos;

import gui.Ventana;
import java.util.Random;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

/**
 *
 * @author Alfred
 */
public class VentanaControlador implements Runnable
{

    Ventana ventana;

    public VentanaControlador(Ventana ventana)
    {
        this.ventana = ventana;
    }

    @Override
    public void run()
    {
        Perro perro = new Perro(ventana.getPerro(), ventana.getPane());
        perro.intro();
            
        while (true)
        {
            int numeroPatos = validarEntrada("Ingrese el número de patos");
            if (numeroPatos == -1)
            {
                System.exit(0);
            }

            int numeroBalas = validarEntrada("Ingrese el número de balas");
            if (numeroBalas == -1)
            {
                System.exit(0);
            }
            
            Random rand = new Random();
            int trayectoria = -1;
            int tmp;

            for (int i = 0; i < numeroPatos; i++)
            {
                String color = switch (rand.nextInt(3))
                {
                    case 0 ->
                        "azul";
                    case 1 ->
                        "negro";
                    case 2 ->
                        "rojo";
                    default ->
                        "negro";
                };

                do
                {
                    tmp = rand.nextInt(15);
                } while (trayectoria == tmp);
                trayectoria = tmp;

                ventana.getPatos().add(new Pato(new JLabel(), color, trayectoria));
            }
            System.out.println(ventana.getPatos().size());
            Thread[] hilosEjucutar = new Thread[numeroPatos];

            for (int i = 0; i < numeroPatos; i++)
            {
                Pato patoEjucutar = ventana.getPatos().poll();
                hilosEjucutar[i] = new Thread(patoEjucutar);
                ventana.getPane().add(patoEjucutar.getImgPato(), Integer.valueOf(1));
                hilosEjucutar[i].start();
                try
                {
                    Thread.sleep(200);
                } catch (InterruptedException ex)
                {
                    System.out.println(ex);
                }
            }

            for (int i = 0; i < numeroPatos; i++)
            {
                try
                {
                    hilosEjucutar[i].join();
                } catch (InterruptedException e)
                {
                    System.out.println(e);
                }
            }
        }
    }

    private int validarEntrada(String mensaje)
    {
        while (true)
        {
            String input = JOptionPane.showInputDialog(ventana, mensaje, "INPUT", JOptionPane.QUESTION_MESSAGE);
            if (input == null)
            {
                return -1; // El usuario cancelo.
            }
            try
            {
                int numero = Integer.parseInt(input);
                if (numero > 0)
                {
                    return numero;
                } else
                {
                    throw new NumberFormatException();
                }
            } catch (NumberFormatException e)
            {
                JOptionPane.showMessageDialog(ventana, "Por favor, introduzca un número válido mayor que 0.", "Error de entrada", JOptionPane.ERROR_MESSAGE);
            }
        }
    }
}
