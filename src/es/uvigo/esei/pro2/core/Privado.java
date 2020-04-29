package es.uvigo.esei.pro2.core;

/**
 *
 * @author Suraei
 */
public class Privado extends Paciente {

    private String dni;

    public Privado(String nombre, String domicilio, String numHistorial, Fecha fechaNac, String dni) {
        super(nombre, domicilio, numHistorial, fechaNac);
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
        toret.append(super.toString()).append("Privado ; ").append(dni);
        return toret.toString();
    }
}
