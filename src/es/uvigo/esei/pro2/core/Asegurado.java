/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package es.uvigo.esei.pro2.core;

/**
 *
 * @author Suraei
 */
public class Asegurado extends Paciente {
    private String poliza;
    private String compañia;

    public Asegurado(String poliza, String compañia,String numHistorial, String nombre, String domicilio,  Fecha fechaNac) {
        super( numHistorial,nombre, domicilio, fechaNac);
        this.poliza = poliza;
        this.compañia = compañia;
    }

    public String getPoliza() {
        return poliza;
    }

    public void setPoliza(String poliza) {
        this.poliza = poliza;
    }

    public String getCompañia() {
        return compañia;
    }

    public void setCompañia(String compañia) {
        this.compañia = compañia;
    }
    
@Override
public String toString(){
        StringBuilder toret = new StringBuilder();
        toret.append(super.toString());
        toret.append(" Asegurado: ");
        toret.append(poliza).append(", ").append(compañia);
        return toret.toString();
}
    
    
}
