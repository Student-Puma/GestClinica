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
public class Privado extends Paciente {
    
    private String dni;

    public Privado(String dni, String numHistorial, String nombre, String domicilio, Fecha fechaNac) {
        super(numHistorial, nombre, domicilio, fechaNac);
        this.dni = dni;
    }

    public String getDni() {
        return dni;
    }

    public void setDni(String dni) {
        this.dni = dni;
    }

    
    
    @Override
    public String toString() {
        StringBuilder toret = new StringBuilder();
        toret.append(super.toString()).append("Privado:").append(dni);
        return toret.toString();
    }
}
