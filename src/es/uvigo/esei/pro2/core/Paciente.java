/*
 * Definicion de la clase Paciente
 */
package es.uvigo.esei.pro2.core;

/**
 *
 * @author Suraei
 */
public abstract class Paciente extends Persona {

    protected Fecha fechaNac;
    protected String numHistorial;

    public Paciente(String nombre, String domicilio, String numHistorial, Fecha fechaNac) {
        super(nombre, domicilio);
        this.fechaNac = fechaNac;
        this.numHistorial = numHistorial;

    }

    public String getNumHistorial() {
        return numHistorial;
    }

    public void setNumHistorial(String numHistorial) {
        this.numHistorial = numHistorial;
    }

    public Fecha getFechaNac() {
        return fechaNac;
    }

    public void setFechaNac(Fecha fechaNac) {
        this.fechaNac = fechaNac;
    }

    @Override
    public String toString() {
        StringBuilder toret = new StringBuilder();
        toret.append(this.numHistorial).append(" ; ").append(super.toString()).append(" ; ").append(this.fechaNac.toString()).append(" ; ");
        return toret.toString();
    }
}
