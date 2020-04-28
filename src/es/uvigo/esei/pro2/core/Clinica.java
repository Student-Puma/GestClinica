/*  Definición de la clase Clinica
 *  En una clínica tendremos una serie de pacientes
*/

package es.uvigo.esei.pro2.core;

/**
 *
 * @author Suraei
 */
public class Clinica {
    private Paciente[] pacientes;
    private int numPacientes;

    /** Nueva Clinica con un num. max. de pacientes.
     * @param maxPacientes el num. max. de pacientes, como entero.
     */
    public Clinica(int maxPacientes)
    {
        numPacientes = 0;
        pacientes = new Paciente[ maxPacientes  ];
    }

    /**
     * Devuelve el paciente situado en pos
     * @param pos el lugar del paciente en el vector de pacientes
     * @return el objeto Paciente correspondiente.
     */
    public Paciente get(int pos) throws Exception
    {
        if ( pos >= getNumPacientes() ) {
             throw new Exception ("get(): sobrepasa la pos: " + ( pos + 1 ) + " / " + getMaxPacientes() );
        
            //System.err.println( "get(): sobrepasa la pos: " + ( pos + 1 ) + " / " + getMaxPacientes() );
            //System.exit( -1 );
        }

        return pacientes[ pos ];
    }

    /** Devuelve el num. de pacientes creados.
     * @return el num. de pacientes existentes, como entero.
     */
    public int getNumPacientes()
    {
        return numPacientes;
    }

    /** Devuelve el max. de numPacientes
     * @return el num. de pacientes max,, como entero
     */
    public int getMaxPacientes()
    {
        return pacientes.length;
    }

    /** Inserta un nuevo paciente
     * @param p el nuevo objeto Paciente
     */
    public void inserta(Paciente p) throws Exception
    {
        final int maxPacientes = getMaxPacientes();

        if ( getNumPacientes() >= maxPacientes ) {
             throw new Exception("inserta(): sobrepasa max.: " + getMaxPacientes() );
//            System.err.println( "inserta(): sobrepasa max.: " + maxPacientes );
//            System.exit( -1 );
        }

        pacientes[ numPacientes ] = p;
        ++numPacientes;
    }
   
    public void elimina(int pos)
    {        
        pacientes [ pos ] = pacientes [ --numPacientes ];
    }
    
    public String toString(){
        StringBuilder toret;
        final int numPacientes = getNumPacientes();

        toret = new StringBuilder();
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
                            if ( pacientes[i].getTipo() == Paciente.TipoSeguro.PRIVADO ){
                                System.out.println(pacientes[i].toString());
                            }
                        }
                        break;
            case 'A':   for (int i = 0; i < numPac; i++) {
                            if ( pacientes[i].getTipo() == Paciente.TipoSeguro.ASEGURADO ){
                                System.out.println(pacientes[i].toString());
                            }
                        }
                        break;                                      
            }         
    
    }      
    
}
