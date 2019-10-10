/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lagashproyect;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

/**
 *
 * @author matia
 */
public class ServicioExterno implements Runnable {

    static int precio = 0;
    public Parquimetro parquimetro = new Parquimetro();
    public static boolean hayAuto = false;
    Random rnd = new Random();
    private boolean running;

    static List<String> registro = new ArrayList<>();

    public void generarReg() {
        String reg = "Patente: " + parquimetro.getPatente() + " - Email: " + ObtenerEmailPorPatente(parquimetro.getPatente()) + " - Tiempo estacionado: " + parquimetro.getMinutosEstacionado() + " minutos - Precio a pagar: $" + precio / 100;
        registro.add(reg);
    }

    public ServicioExterno() {//constructor por defecto
    }

    //metodo para generar patentes
    public void IngresoPatente() {
        hayAuto = true;
        String Matricula = "";

        for (int i = 0; i < 7; i++) {
            if (i < 2) {
                Matricula += (rnd.nextInt(10));
            } else if (i >= 2 && i <= 4) {
                Matricula += ((char) (rnd.nextInt(26) + 'a'));
            } else if (i >= 5 && i <= 7) {
                Matricula += (rnd.nextInt(10));
            }
        }
        parquimetro.setPatente(Matricula.toUpperCase());
    }

    public static String ObtenerEmailPorPatente(String patente) {

        return "Prueba@Prueba.com";
    }

    public static void EnviarEmail(String asunto, String cuerpo, String destinatario) {
        JOptionPane.showMessageDialog(null, "Para: " + destinatario + "\nAsunto: " + asunto + "\n------------------------------------------------------\n" + cuerpo);

    }

    @Override
    public void run() {
        int seg = 0;
        int min = 0;
        running = false;
        while (hayAuto = true && !running) {

            try {
                seg++; //contador de segundos
                Thread.sleep(1); //codigo para hacer bucle mas lento (tarda 1 seg en completar bucle) se reduce a 1 el parametro por cuestión de testing

                if (seg == 60) {
                    parquimetro.AvanzarMinuto(); //suma 1 minuto (notificación

                    seg = 0; //reiniciamos los segundos
                    if (min % 60 == 0 || min == 0) {
                        precio += parquimetro.CentavosPorHora;
                    }
                    min++;
                    parquimetro.setMinutosEstacionado(min);
                }
            } catch (InterruptedException ex) {
                Logger.getLogger(ServicioExterno.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

    }

    public void terminate(boolean terminar) {
        synchronized (this) {
            this.running = terminar;
            generarReg();
            parquimetro.EstacionamientoFinalizado();
            precio = 0;

        }
    }
}
