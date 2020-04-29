package es.uvigo.esei.pro2.core;

/**
 *
 * @author Suraei
 */
public class Asegurado extends Paciente {

    private String poliza;
    private String compañia;

    public Asegurado(String nombre, String domicilio, String numHistorial, Fecha fechaNac, String poliza, String compañia) {
        super(nombre, domicilio, numHistorial, fechaNac);
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
    public String toString() {
        StringBuilder toret = new StringBuilder();
        toret.append(super.toString());
        toret.append("Asegurado ; ");
        toret.append(poliza).append(" ; ").append(compañia);
        return toret.toString();
    }

}
