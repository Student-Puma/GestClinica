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
public class Medico extends Persona {
    
    private String numColegiado;
    
    public Medico(String nombre, String domicilio, String numColegiado){
        super(nombre,domicilio);
        this.numColegiado = numColegiado;
    }

    public String getNumColegiado() {
        return numColegiado;
    }

    public void setNumColegiado(String numColegiado) {
        this.numColegiado = numColegiado;
    }
    
    @Override
    public String toString(){
        StringBuilder toret = new StringBuilder();
        
        toret.append(getNombre()).append(" ; ");
        toret.append(getDomicilio()).append(" ; ");
        toret.append(getNumColegiado());
        
        return toret.toString();
    }
}
