/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lagashproyect;

import javax.swing.JOptionPane;

/**
 *
 * @author matia
 */
public class LagashProyect {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {

        //declaracion de variables

        ServicioExterno s1 = new ServicioExterno();  //Objeto tipo ServicioExterno
        Thread t;
        int respuesta;
        String pregunta;

        //interfaz inicial, se ejecuta todo el codigo
        do {
            pregunta = JOptionPane.showInputDialog(null, "Elija una opción válida:\n1) Verificar espacio.\n2) Ingresar vehiculo.\n3) Retirar vehiculo.\n4) Mostrar registro.\n5) Finalizar programa.", 1);
            respuesta = Integer.parseInt(pregunta);
            switch (respuesta) {
                case 1:
                    if (s1.parquimetro.getPatente() == null && s1.parquimetro.getMinutosEstacionado() == 0) {
                        JOptionPane.showMessageDialog(null, "No hay vehículo estacionado.\n Patente: " + s1.parquimetro.getPatente() + "\n Tiempo: " + s1.parquimetro.getMinutosEstacionado());
                    } else {
                        s1.parquimetro.AutoDetectado(s1.parquimetro.getPatente());
                    }
                    break;
                case 2:
                    if (s1.hayAuto == false) {
                        t = new Thread(s1);
                        s1.IngresoPatente();  //generamos patente
                        t.start();  //se inicia el hilo
                    } else if (s1.hayAuto == true) {
                        s1.parquimetro.AutoDetectado(s1.parquimetro.getPatente());
                    }
                    break;
                case 3:
                    if (s1.hayAuto) {
                        s1.terminate(true);

                    } else {
                        JOptionPane.showMessageDialog(null, "No hay vehículo estacionado.\n Patente: " + s1.parquimetro.getPatente() + "\n Tiempo: " + s1.parquimetro.getMinutosEstacionado());
                    }
                    break;
                case 4:
                    if (ServicioExterno.registro.isEmpty()) {
                        JOptionPane.showMessageDialog(null, "No hay registro previo");
                    } else {
                        System.out.println("------------------------Registro de estacionamiento-----------------------------");
                        for (String registro : ServicioExterno.registro) {
                            System.out.println(registro);
                        }
                        System.out.println("--------------------------------------------------------------------------------");
                    }
                    break;
                case 5:
                    System.exit(0);
                    break;
                default:
                    JOptionPane.showMessageDialog(null, "Ingrese una opción válida");
            }
        } while (respuesta != 5);
    }

}
