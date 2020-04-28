package es.uvigo.esei.pro2.core;

/**
 *
 * @author Suraei
 */
public class Medico extends Persona {
    
    private String numColegiado;
    
    public Medico(String nombre, String domicilio, String numColegiado){
        super(nombre,domicilio);
        this.numColegiado = numColegiado;
    }

    public String getNumColegiado() {
        return numColegiado;
    }

    public void setNumColegiado(String numColegiado) {
        this.numColegiado = numColegiado;
    }
    
    @Override
    public String toString(){
        StringBuilder toret = new StringBuilder();
        toret.append(this.nombre).append(" ; ").append(this.domicilio).append(" ; ").append(this.numColegiado);
        return toret.toString();
    }
}
