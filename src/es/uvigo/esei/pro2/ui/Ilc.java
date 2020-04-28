package es.uvigo.esei.pro2.ui;

import es.uvigo.esei.pro2.core.Clinica;
import es.uvigo.esei.pro2.core.*;
import es.uvigo.esei.pro2.core.Paciente;

import java.util.Scanner;

/**
 * Interfaz de lin. de comando
 */
public class Ilc {

    /**
     * Realiza el reparto de la funcionalidad ler = lee, evalua, repite
     */
    public void ler() {
        int op;

        // Lee el num. max. de pacientes
        int maxPacientes = leeNum("Num. max. pacientes: ");

        // Prepara
        Clinica coleccion = new Clinica(maxPacientes);

        // Bucle ppal
        do {
            System.out.println("\nGestión de una clínica hospitalaria");

            op = menu(coleccion);

            try {
                switch (op) {
                    case 0:
                        System.out.println("Fin.");
                        break;
                    case 1:
                        insertaPaciente(coleccion);
                        break;
                    case 2:
                        modificaPaciente(coleccion);
                        break;
                    case 3:
                        eliminaPaciente(coleccion);
                        break;
                    case 4:
                        //visualiza( coleccion );
                        System.out.println(coleccion.toString());
                        break;
                    case 5:
                        listarPorTipo(coleccion);
                        break;
                    default:
                        System.out.println("No es correcta esa opción ( "
                                + op + " )");
                }
            } catch (Exception e) {
                System.err.println("\nERROR: " + e.getMessage());
            }
        } while (op != 0);

    }

    /**
     * Lee un num. de teclado
     *
     * @param msg El mensaje a visualizap.
     * @return El num., como entero
     */
    private int leeNum(String msg) {
        boolean repite;
        int toret = 0;
        Scanner teclado = new Scanner(System.in);

        do {
            repite = false;
            System.out.print(msg);

            try {
                toret = Integer.parseInt(teclado.nextLine());
            } catch (NumberFormatException exc) {
                repite = true;
            }
        } while (repite);

        return toret;
    }

    /**
     * Presenta un menu con las opciones, y permite seleccionar una.
     *
     * @return la opcion seleccionada, como entero
     */
    private int menu(Clinica coleccion) {
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
                    + "0. Salir\n");
            toret = leeNum("Selecciona: ");
        } while (toret < 0
                && toret > 5);

        System.out.println();
        return toret;
    }

    /**
     * Crea un nuevo paciente y lo inserta en la coleccion
     *
     * @param coleccion La coleccion en la que se inserta el paciente.
     */
    private void insertaPaciente(Clinica coleccion) throws Exception {
        Paciente p;
        p = new Asegurado("", "", "", "", "", new Fecha(0, 0, 0));

        switch (leerTipoPaciente()) {
            case 'P':
                p = new Privado("","","","",new Fecha(0,0,0));
                break;
            case 'A':
                p = new Asegurado("", "", "", "", "", new Fecha(0, 0, 0));
                break;

        }
        modificaPaciente(p);
        coleccion.inserta(p);
    }

    private char leerTipoPaciente() {
        char tipoPac;
        Scanner entrada = new Scanner(System.in);

        do {
            tipoPac = leeCadena("Introduce tipo de paciente ( A: Asegurado y P: Privado): ").toUpperCase().trim().charAt(0);
        } while ((tipoPac != 'P') && (tipoPac != 'A'));

        return tipoPac;
    }

    private String leeCadena(String msg) {
        Scanner teclado = new Scanner(System.in);
        String toret = "";
        do {
            System.out.print(msg);
            toret = teclado.nextLine().trim();
        } while (toret.equals(""));
        return toret;
    }

    /**
     * Borra un paciente por su posicion en la colección.
     *
     * @param coleccion La coleccion en la que se elimina el paciente
     */
    private void eliminaPaciente(Clinica coleccion) {
        if (coleccion.getNumPacientes() > 0) {
            coleccion.elimina(leePosPaciente(coleccion));
        } else {
            System.out.println("La coleccion no contiene pacientes.");
        }
    }

    /**
     * Modifica un paciente existente.
     *
     * @param coleccion La coleccion de la cual modificar un paciente.
     */
    private void modificaPaciente(Clinica coleccion) throws Exception {
        if (coleccion.getNumPacientes() > 0) {
            this.modificaPaciente(coleccion.get(leePosPaciente(coleccion)));
        } else {
            System.out.println("La coleccion no contiene pacientes.");
        }
    }
    
    private void modificaPersona(Persona p){
        String info;
        Scanner teclado = new Scanner (System.in);
        
        System.out.println("Nombre del paciente: ");
        if(p.getNombre().length()>0){
            System.out.println("[" + p.getNombre() + "]");
        }
        
        info = leeCadena(": ");
        p.setNombre(info);
        
           System.out.println("Domicilio del paciente: ");
        if(p.getDomicilio().length()>0){
            System.out.println("[" + p.getDomicilio() + "]");
        }
        
        info = leeCadena(": ");
        p.setDomicilio(info);
    }
    
 private void modificaPacienteComun(Paciente p){
        String info;
        Scanner teclado = new Scanner (System.in);
        
        
        System.out.print("Numero de historial del paciente");
        if (p.getNumHistorial().length() > 0){
            System.out.println("[" + p.getNumHistorial() + "]");           
        }
        
        info = leeCadena(": ");
        
        p.setNumHistorial(info);
        
        System.out.print("Fecha de nacimiento");
        if (p.getFechaNac().getAño()!= 0){
            System.out.println("[" + p.getFechaNac().toString() + "]");           
        }
        
        p.setFechaNac(leerFecha());
    }
    
    private void modificaPacientePrivado(Privado p){
        String info;
        Scanner teclado = new Scanner (System.in);
        
        //DNI
        
        System.out.print("DNI");
        if (p.getDni().length() > 0){
            System.out.println("[" + p.getDni() + "]");           
        }
        
        info = leeCadena(": ");
        
        p.setDni(info);
    }
    
    
    private void modificaPacienteAsegurado(Asegurado p){
        String info;
        Scanner teclado = new Scanner (System.in);
        
        System.out.println("Poliza");
        if(p.getPoliza().length() > 0){
            System.out.println("[" + p.getPoliza() + "]");
        }
        
        info = leeCadena(": ");
        p.setPoliza(info);
        
        System.out.println("Compañia");
        if(p.getCompañia().length() > 0){
            System.out.println("[" + p.getCompañia() + "]");
        }
        
        info = leeCadena(": ");
        p.setCompañia(info);
    }
    
    private void modificaPaciente(Paciente p) {
        
        modificaPersona(p);
        modificaPacienteComun(p);
        
        if(p instanceof Privado){
            modificaPacientePrivado((Privado)p);
        }else{
            modificaPacienteAsegurado((Asegurado)p);
        }

    }

    /**
     * Lee del teclado la posición de un paciente en la colección
     *
     * @param coleccion La colección de la que se obtiene el max.
     * @return la posición del paciente, como entero.
     */
    private int leePosPaciente(Clinica coleccion) {
        final int numPacientes = coleccion.getNumPacientes();
        int toret;

        do {
            toret = leeNum("Introduzca posición del paciente (1..." + numPacientes + "): ");
        } while (toret < 1
                || toret > numPacientes);

        return toret - 1;
    }

    /**
     * Lee un caracter del teclado
     *
     * @param men Mensaje a visualizar
     * @return el caracter introducido por el usuario
     */
    private char leeCaracter(String men) {
        Scanner teclado = new Scanner(System.in);

        System.out.print(men);
        return (teclado.nextLine().trim().charAt(0));
    }

    /**
     * Visualiza los pacentes almacenados en la coleccion por la salida std.
     *
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
     * Lista las pacientes de la coleccion por el tipo de seguro
     *
     * @param coleccion La Clinica de la que se listan los pacientes.
     */
    
    private Fecha leerFecha(){
        int a,m,d;
        
        do{
            a = leeNum("\n\tAño: ");
            
        }while(a<0);
        
        do{
            m = leeNum("\tMes: ");
            
        }while(m<0 || m>12);
        
        int max = -1;
        switch(m){
            case 1: case 3: case 5: case 7:
            case 8: case 10: case 12:
                max =31;
                break;
            case 2:
                max = 29;
                break;
            default: 
                max= 30;
        }
        
        do{
            d = leeNum("\tDía: ");
            
        }while(d<0 || d>max);
        
        return new Fecha(d,m,a);
    }
    
    public void listarPorTipo(Clinica coleccion) {
        char info;

        Scanner entrada = new Scanner(System.in);

        do {
            System.out.print("Indica el tipo de pacientes a listar: (Privado (P) o Asegurados (A)): ");
            info = entrada.nextLine().trim().toUpperCase().charAt(0);
        } while ((info != 'P') && (info != 'A'));

        coleccion.listarPacientes(info);
    }
}
