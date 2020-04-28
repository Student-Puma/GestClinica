package es.uvigo.esei.pro2.core;

/**
 *
 * @author Suraei
 */

public class Fecha {

    private int año;
    private int mes;
    private int dia;

    public Fecha(int dia, int mes, int anho) {
        this.año = anho;
        this.mes = mes;
        this.dia = dia;
    }

    public int getAño() {
        return this.año;
    }

    public void setAño(int año) {
        this.año = año;
    }

    public int getDia() {
        return this.dia;
    }

    public void setDia(int dia) {
        this.dia = dia;
    }

    public int getMes() {
        return this.mes;
    }

    public void setMes(int mes) {
        this.mes = mes;
    }
    
    @Override
    public String toString(){
        StringBuilder toret = new StringBuilder();
        toret.append(this.dia).append("/").append(this.mes).append("/").append(this.año);
        return toret.toString();
    }
}

