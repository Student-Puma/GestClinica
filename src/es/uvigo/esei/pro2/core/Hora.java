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
public class Hora {
    private int hora;
    private int minutos;
    
    public Hora(int hora, int minutos){
        this.hora = hora;
        this.minutos = minutos;
    }

    public int getHora() {
        return hora;
    }

    public void setHora(int hora) {
        this.hora = hora;
    }

    public int getMinutos() {
        return minutos;
    }

    public void setMinutos(int minutos) {
        this.minutos = minutos;
    }

    @Override
    public String toString(){
        StringBuilder toret = new StringBuilder();
        toret.append(this.hora).append(":").append(this.minutos);
        return toret.toString();
    }
}

