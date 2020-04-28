package es.uvigo.esei.pro2.ui;

import es.uvigo.esei.pro2.core.Clinica;
import es.uvigo.esei.pro2.core.Paciente;

import java.util.Scanner;

/**
 * Interfaz de lin. de comando
 */
public class Ilc {
    /**
     * Realiza el reparto de la funcionalidad
     * ler = lee, evalua, repite
     */
    public void ler()
    {
        int op;

        // Lee el num. max. de pacientes
        int maxPacientes = leeNum( "Num. max. pacientes: " );

        // Prepara
        Clinica coleccion = new Clinica( maxPacientes );

        // Bucle ppal
        do {
            System.out.println( "\nGestión de una clínica hospitalaria" );

            op = menu( coleccion );
            
            try {
                switch( op ) {
                    case 0:
                        System.out.println( "Fin." );
                        break;
                    case 1:
                        insertaPaciente( coleccion );
                        break;
                    case 2:
                        modificaPaciente( coleccion );
                        break;
                    case 3:
                        eliminaPaciente( coleccion );
                        break;
                    case 4:
                        //visualiza( coleccion );
                        System.out.println(coleccion.toString());
                        break;
                    case 5:
                         listarPorTipo(coleccion);
                        break;
                    default:
                        System.out.println( "No es correcta esa opción ( " 
                                            + op + " )" );
                }
            }
             catch(Exception e){
                System.err.println("\nERROR: " + e.getMessage());
            }
        } while( op != 0 );

    }
    
     /**
     * Lee un num. de teclado
     * @param msg El mensaje a visualizap.
     * @return El num., como entero
     */
    private int leeNum(String msg)
    {
        boolean repite;
        int toret = 0;
        Scanner teclado = new Scanner( System.in );

        do {
            repite = false;
            System.out.print( msg );

            try {
                toret = Integer.parseInt( teclado.nextLine() );
            } catch (NumberFormatException exc) {
                repite = true;
            }
        } while( repite );

        return toret;
    }

    /**
     * Presenta un menu con las opciones, y permite seleccionar una.
     * @return la opcion seleccionada, como entero
     */
    private int menu(Clinica coleccion)
    {
        int toret;

        do {
            System.out.println("Número de pacientes: "
                                + coleccion.getNumPacientes() 
                                + " / " + coleccion.getMaxPacientes());
            System.out.println(
                              "\n1. Inserta un nuevo paciente\n"
                            + "2. Modifica un paciente\n"
                            + "3. Elimina un paciente\n"
                            + "4. Listar pacientes\n"
                            + "5. Listar pacientes por tipo\n"          
                            + "0. Salir\n" );
            toret = leeNum( "Selecciona: " );
        } while( toret < 0
              && toret > 5 );

        System.out.println();
        return toret;
    }

    /**
     *  Crea un nuevo paciente y lo inserta en la coleccion
     *  @param coleccion La coleccion en la que se inserta el paciente.
     */
    private void insertaPaciente(Clinica coleccion) throws Exception
    {
        Paciente p = new Paciente( "", "", "", 0, Paciente.TipoSeguro.PRIVADO );

        modificaPaciente( p );
        coleccion.inserta( p );
    }

    /**
     * Borra un paciente por su posicion en la colección.
     * @param coleccion La coleccion en la que se elimina el paciente
     */
    private void eliminaPaciente(Clinica coleccion)
    {
        if ( coleccion.getNumPacientes() > 0 ) {
            coleccion.elimina( leePosPaciente( coleccion ) );
        } else {
            System.out.println( "La coleccion no contiene pacientes." );
        }
    }

    /**
     * Modifica un paciente existente.
     * @param coleccion La coleccion de la cual modificar un paciente.
     */
    private void modificaPaciente(Clinica coleccion) throws Exception
    {
        if ( coleccion.getNumPacientes() > 0 ) {
            this.modificaPaciente( coleccion.get( leePosPaciente( coleccion ) ) );
        } else {
            System.out.println( "La coleccion no contiene pacientes." );
        }
    }

    private void modificaPaciente(Paciente p)
    {
        String info;
        char tipoPaciente;
        Scanner teclado = new Scanner( System.in );

        // Numero de historial
        System.out.print( "Numero de historial del paciente " );
        if ( p.getNumHistorial().length() > 0 ) {
            System.out.print( "[" + p.getNumHistorial() + "]" );
        }
        System.out.print( ": " );
        info = teclado.nextLine().trim();

        if ( info.length() > 0 ) {
            p.setNumHistorial(info );
        }

        // Nombre
        System.out.print( "Nombre del paciente " );
        if ( p.getNombre().length() > 0 ) {
            System.out.print( "[" + p.getNombre() + "]" );
        }
        System.out.print( ": " );
        info = teclado.nextLine().trim();

        if ( info.length() > 0 ) {
            p.setNombre( info );
        }
        
        // Domicilio
        System.out.print( "Domicilio del paciente " );
        if ( p.getDomicilio().length() > 0 ) {
            System.out.print( "[" + p.getDomicilio() + "]" );
        }
        System.out.print( ": " );
        info = teclado.nextLine().trim();

        if ( info.length() > 0 ) {
            p.setDomicilio( info );
        }
        
        // Año
        System.out.print( "Año de nacimiento del paciente " );
        if ( p.getAno() > 0 ) {
            System.out.print( "[" + p.getAno() + "]" );
        }
        System.out.print( ": " );
        info = teclado.nextLine().trim();

        if ( info.length() > 0 ) {
            p.setAno( Integer.parseInt(info) );
        }

        // Tipo de atención      
        do {
            tipoPaciente = leeCaracter("Introduce el tipo de paciente (P: privado, A: asegurado): ");            
        } while((tipoPaciente != 'P') && (tipoPaciente != 'A') );
        
        switch (tipoPaciente){
            case 'P': p.setTipo(Paciente.TipoSeguro.PRIVADO);
                      break;
            case 'A': p.setTipo(Paciente.TipoSeguro.ASEGURADO);
                      break;          
        }

    }    
    
    /**
     * Lee del teclado la posición de un paciente en la colección
     * @param coleccion La colección de la que se obtiene el max.
     * @return la posición del paciente, como entero.
     */
    private int leePosPaciente(Clinica coleccion)
    {
        final int numPacientes = coleccion.getNumPacientes();
        int toret;

        do {
            toret = leeNum( "Introduzca posición del paciente (1..." + numPacientes + "): " );
        } while( toret < 1
              || toret > numPacientes );

        return toret - 1;
    }
    
    /** 
     * Lee un caracter del teclado
     * @param men Mensaje a visualizar
     * @return el caracter introducido por el usuario
     */
    private char leeCaracter(String men)
    {
        Scanner teclado = new Scanner (System.in);

        System.out.print(men);
        return ( teclado.nextLine().trim().charAt(0) );             
    }
    
    /**
     * Visualiza los pacentes almacenados en la coleccion por la salida std.
     * @param coleccion El objeto Clinica del que visualizar sus pacientes.
     */
    // Este método no se invoca al hacer el segundo ejercicio del proyecto
    /*private void visualiza(Clinica coleccion)
    {
        final int numPacientes = coleccion.getNumPacientes();

        if ( numPacientes > 0 ) {
            for (int i = 0; i < numPacientes; i++) {
                System.out.print( ( i + 1 ) + ". " );
                System.out.println( coleccion.get( i ).toString() );
            }
        } else {
            System.out.println( "No hay pacientes." );
        }

    }*/
    
     /**
     *  Lista las pacientes de la coleccion por el tipo de seguro
     *  @param coleccion La Clinica de la que se listan los pacientes.
     */
    public void listarPorTipo( Clinica coleccion )          
    {     
        char info;
       
        Scanner entrada = new Scanner( System.in );
        
        do {
            System.out.print("Indica el tipo de pacientes a listar: (Privado (P) o Asegurados (A)): ");
            info = entrada.nextLine().trim().toUpperCase().charAt(0);
        } while ((info != 'P') && (info != 'A') );
        
        coleccion.listarPacientes(info);
    }
}
