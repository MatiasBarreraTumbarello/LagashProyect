/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lagashproyect;

/**
 *
 * @author matia
 */
public interface IControladorParquimetro {
    
    int CentavosPorHora = 1000; //10 pesos la hora
    
    public void AutoDetectado (String patente);
    
    public void AvanzarMinuto ();
    
    public void EstacionamientoFinalizado();
}

