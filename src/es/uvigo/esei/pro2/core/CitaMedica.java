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
    private Medico medico;
    private Paciente paciente;
    
    public CitaMedica(Hora hora, Fecha fecha, Medico medico, Paciente paciente){
       this.hora = hora;
       this.fecha = fecha;
       this.medico = medico;
       this.paciente = paciente;
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

    public Medico getMedico() {
        return medico;
    }

    public void setMedico(Medico medico) {
        this.medico = medico;
    }

    public Paciente getPaciente() {
        return paciente;
    }

    public void setPaciente(Paciente paciente) {
        this.paciente = paciente;
    }

  
    
    @Override
    public String toString() {
        StringBuilder toret = new StringBuilder();
        toret.append("Cita:\n");
        toret.append(fecha.toString()).append(" - ").append(hora.toString()).append("\n");
        toret.append("Médico: ").append(medico.toString());
        toret.append("\n");
        toret.append("Pacienteaciente: ").append(paciente.toString());
        
        return toret.toString();
    }
}
