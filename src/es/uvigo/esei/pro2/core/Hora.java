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
        toret.append(String.format("%02d", this.hora))
                .append(":")
                .append(String.format("%02d", this.minutos));
        return toret.toString();
    }
}

