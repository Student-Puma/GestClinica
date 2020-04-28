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
public class CitaMedica {
    private Hora hora;
    private Fecha fecha;
    
    public CitaMedica(int dia, int mes, int año, int hora, int minutos){
        this.hora = new Hora(hora, minutos);
        this.fecha = new Fecha(dia, mes, año);
    }

    public Hora getHora() {
        return hora;
    }

    public void setHora(Hora hora) {
        this.hora = hora;
    }

    public Fecha getFecha() {
        return fecha;
    }

    public void setFecha(Fecha fecha) {
        this.fecha = fecha;
    }
    
    @Override
    public String toString() {
        StringBuilder toret = new StringBuilder();
        toret.append(this.hora.toString()).append(" ").append(this.fecha.toString());
        return toret.toString();
    }
}
