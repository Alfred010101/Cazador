package hilos;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

/**
 *
 * @author Alfred
 */
public class VentanaControlador implements Runnable
{

    JFrame ventana;

    public VentanaControlador(JFrame ventana)
    {
        this.ventana = ventana;
    }

    @Override
    public void run()
    {
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