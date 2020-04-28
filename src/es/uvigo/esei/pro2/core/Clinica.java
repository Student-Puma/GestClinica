/*  Definición de la clase Clinica
 *  En una clínica tendremos una serie de pacientes
*/

package es.uvigo.esei.pro2.core;

/**
 *
 * @author Suraei
 */
public class Clinica {
    private String nombreClinica;
    private Paciente[] pacientes;
    private int numPacientes;
    private Medico[] medicos;
    private int numMedicos;
    private CitaMedica[] citamedica;
    private int numCitas;

    /** Nueva Clinica con un num. max. de pacientes.
     * @param maxPacientes el num. max. de pacientes, como entero.
     * @param maxMedicos
     * @param maxCitas
     */
    public Clinica(String nombre, int maxPacientes, int maxMedicos, int maxCitas)
    {
        this.nombreClinica = nombre;
        numPacientes = 0;
        pacientes = new Paciente[ maxPacientes  ];
        
        numMedicos = 0;
        medicos = new Medico[maxMedicos];
        
        numCitas = 0;
        citamedica = new CitaMedica[maxCitas];
        
    }

    public String getNombreClinica() {
        return nombreClinica;
    }

    public void setNombreClinica(String nombreClinica) {
        this.nombreClinica = nombreClinica;
    }
    
   

  //PACIENTES
    public Paciente get(int pos) throws Exception
    {
        if ( pos >= getNumPacientes() ) {
             throw new Exception ("get(): sobrepasa la pos: " + ( pos + 1 ) + " / " + getMaxPacientes() );
        
        }

        return pacientes[ pos ];
    }


    
    public int getNumPacientes()
    {
        return numPacientes;
    }


    public int getMaxPacientes()
    {
        return pacientes.length;
    }

    public void inserta(Paciente p) throws Exception
    {
        final int maxPacientes = getMaxPacientes();

        if ( getNumPacientes() >= maxPacientes ) {
             throw new Exception("inserta(): sobrepasa max.: " + getMaxPacientes() );
        }

        pacientes[ numPacientes ] = p;
        ++numPacientes;
    }
   
    public void elimina(int pos) throws Exception
    {       
        if (pos >= getNumPacientes()){
            throw new Exception("get() : sobrepasa la pos: " + (pos + 1) + " / " + getMaxPacientes() );
        }
        pacientes [ pos ] = pacientes [ --numPacientes ];
    }
    
    public String toString(){
        StringBuilder toret;
        final int numPacientes = getNumPacientes();

        toret = new StringBuilder();
        toret.append("Clinica: ").append(nombreClinica).append("\n");
        toret.append("Pacientes: \n");
        if ( numPacientes > 0 ) {
            for (int i = 0; i < numPacientes; i++) {
                toret.append (( i + 1 ) + ". " );
                toret.append(pacientes [i].toString() + "\n");
            }
        } else {
            toret.append("No hay pacientes." );
        }
        
        return toret.toString();
    }
    
    public void listarPacientes(char c)             
    {
        int numPac = getNumPacientes();                       
       
        switch (c) {
            case 'P':   for (int i = 0; i < numPac; i++) {
                            if ( pacientes[i] instanceof Privado){
                                System.out.println(pacientes[i].toString());
                            }
                        }
                        break;
            case 'A':   for (int i = 0; i < numPac; i++) {
                            if ( pacientes[i] instanceof Asegurado){
                                System.out.println(pacientes[i].toString());
                            }
                        }
                        break;                                      
            }         
    
    }      
    
}

//MEDICOS



//CITAS