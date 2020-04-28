package es.uvigo.esei.pro2.core;

/**
 *
 * @author Suraei
 */
public class CitaMedica {

    private Hora hora;
    private Fecha fecha;

    public CitaMedica(int dia, int mes, int anho, int hora, int minutos) {
        this.hora = new Hora(hora, minutos);
        this.fecha = new Fecha(dia, mes, anho);
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
        toret.append(fecha.toString()).append(" - ").append(hora.toString());
        return toret.toString();
    }
}
