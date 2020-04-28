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
public abstract class Persona {
    
    protected String nombre;  
    protected String domicilio;  
    
    public Persona(String nombre, String domicilio){
        this.nombre = nombre;
        this.domicilio = domicilio;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDomicilio() {
        return domicilio;
    }

    public void setDomicilio(String domicilio) {
        this.domicilio = domicilio;
    }
    
    @Override
    public String toString() {
        StringBuilder toret = new StringBuilder();
        toret.append(nombre).append(" ; ").append(domicilio);
        return toret.toString();
    }
}
