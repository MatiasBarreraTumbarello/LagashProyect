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
public class Parquimetro implements IControladorParquimetro {

    private String Patente = null;

    private int MinutosEstacionado = 0;

    public Parquimetro() {
    } //constructor por defecto

    public String getPatente() {  //Getter de Patente
        return Patente;
    }

    public void setPatente(String Patente) { //Setter de Patente
        this.Patente = Patente;
    }

    public void setMinutosEstacionado(int MinutosEstacionado) {
        this.MinutosEstacionado = MinutosEstacionado;
    }

    public int getMinutosEstacionado() {
        return MinutosEstacionado;
    }

    //determina si hay autos o no
    @Override
    public void AutoDetectado(String patente) { //metodo sobrescrito de interfaz
        JOptionPane.showMessageDialog(null, "Hay un vehículo estacionado. \n Patente: " + patente + "\n Tiempo: " + this.MinutosEstacionado);
    }

    //Determina la diferencia de minutos desde que llega auto
    @Override
    public void AvanzarMinuto() { //metodo sobreescrito de interfaz
        MinutosEstacionado++;
        System.out.println("Ha pasado un minuto estacionado. Tiempo transcurrido: " + MinutosEstacionado + " minutos.");  //se muestra en consola el mje
    }

    @Override
    public void EstacionamientoFinalizado() {

        String email = ServicioExterno.ObtenerEmailPorPatente(this.Patente);
        ServicioExterno.EnviarEmail("Pago servicio de estacionamiento", "Estimado por el medio del mismo le avisamos que tiene que pagar una suma total a: $" + ServicioExterno.precio / 100, email);
        System.out.println("--------------------------------------------------------------------------------");
        System.out.println("El auto estacionado se retiró. Tiempo total: " + MinutosEstacionado);
        this.MinutosEstacionado = 0; //En caso de no haber auto estacionado los minutos devuelven 0
        this.Patente = null; //en caso de no haber auto estacionado la patente devuelve null
        ServicioExterno.hayAuto = false;
    }

}
