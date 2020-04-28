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

        System.out.println("Configuracion de la clínica");
        String nombre = leeCadena("Nombre de la clinica");
        int maxPacientes = leeNum("Num. max. pacientes: ");
        int maxMedicos = leeNum("Num. max. medicos: ");
        int maxCitas = leeNum("Num. max. citas: ");

        // Prepara
        Clinica coleccion = new Clinica(nombre, maxPacientes, maxMedicos, maxCitas);

        // Bucle ppal
        do {
            System.out.println("--- Gestión de la clínica " + coleccion.getNombreClinica() + " ---");

            op = menuPrincipal(coleccion);
            try {
                switch (op) {

                    case 1:
                        opcionesPacientes(coleccion);
                        break;
                    case 2:
                        opcionesMedicos(coleccion);
                        break;
                    case 3:
                        opcionesCitas(coleccion);
                        break;

                }
            } catch (Exception e) {
                System.err.println("\nERROR: " + e.getMessage());
            }
        } while (op != 0);

    }

    private int menuPrincipal(Clinica c) {
        int toret;

        do {
            System.out.println("\n-- Gestión de la clínica " + c.getNombreClinica() + " --");
            System.out.println(
                    "\n1. Gestión pacientes\n"
                    + "2. Gestión médicos\n"
                    + "0. Salir\n");
            toret = leeNum("Selecciona: ");
        } while (toret < 0
                && toret > 3);

        System.out.println();
        return toret;
    }

    private void opcionesPacientes(Clinica coleccion) throws Exception {
        int op = -1;
        do {
            op = menuPacientes(coleccion);

            try {
                switch (op) {
                    case 0:
                        System.out.println("Volver al menu principal");
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
                        System.out.println(coleccion.toStringPaciente());
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

    private int menuPacientes(Clinica coleccion) {
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

    private void opcionesMedicos(Clinica coleccion) throws Exception {
        int op = -1;
        do {
            op = menuMedicos(coleccion);

            try {
                switch (op) {
                    case 0:
                        System.out.println("Volver al menu principal");
                        break;
                    case 1:
                        insertaMedico(coleccion);
                        break;
                    case 2:
                        modificaMedico(coleccion);
                        break;
                    case 3:
                        eliminaMedico(coleccion);
                        break;
                    case 4:
                        //visualiza( coleccion );
                        System.out.println(coleccion.toStringMedico());
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

    private int menuMedicos(Clinica coleccion) {
        int toret;

        do {
            System.out.println("Número de medicos: "
                    + coleccion.getNumMedicos()
                    + " / " + coleccion.getMaxMedicos());
            System.out.println(
                    "\n1. Inserta un nuevo medico\n"
                    + "2. Modifica un medico\n"
                    + "3. Elimina un medico\n"
                    + "4. Listar medicos\n"
                    + "0. Salir\n");
            toret = leeNum("Selecciona: ");
        } while (toret < 0
                && toret > 4);

        System.out.println();
        return toret;
    }

    private void opcionesCitas(Clinica coleccion) throws Exception {
        int op = -1;
        do {
            op = menuCitas(coleccion);

            try {
                switch (op) {
                    case 0:
                        System.out.println("Volver al menu principal");
                        break;
                    case 1:
                        insertaCita(coleccion);
                        break;
                    case 2:
                        modificaCita(coleccion);
                        break;
                    case 3:
                        eliminaCita(coleccion);
                        break;
                    case 4:
                        //visualiza( coleccion );
                        System.out.println(coleccion.toStringCita());
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

    private int menuCitas(Clinica coleccion) {
        int toret;

        do {
            System.out.println("Número de citas: "
                    + coleccion.getNumMedicos()
                    + " / " + coleccion.getMaxCitas());
            System.out.println(
                    "\n1. Inserta una nueva cita\n"
                    + "2. Modifica una cita\n"
                    + "3. Elimina una cita\n"
                    + "4. Listar citas\n"
                    + "0. Salir\n");
            toret = leeNum("Selecciona: ");
        } while (toret < 0
                && toret > 4);

        System.out.println();
        return toret;
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
                p = new Privado("", "", "", "", new Fecha(0, 0, 0));
                break;
            case 'A':
                p = new Asegurado("", "", "", "", "", new Fecha(0, 0, 0));
                break;

        }
        modificaPaciente(p);
        coleccion.insertaPaciente(p);
    }

    private void insertaMedico(Clinica coleccion) throws Exception {
        Medico p;
        p = new Medico("", "", "");

        modificaMedico(p);
        coleccion.insertaMedico(p);
    }

    private void insertaCita(Clinica coleccion) throws Exception {
        CitaMedica p;
        p = new CitaMedica(new Hora(0, 0), new Fecha(0, 0, 0), new Medico("", "", ""), new Privado("", "", "", "", new Fecha(0, 0, 0)));

        if (coleccion.getNumMedicos() == 0 || coleccion.getNumPacientes() == 0) {
            throw new Exception("Falta gente : No hay médicos o no hay pacientes");
        }
        modificaCita(p, coleccion);
        coleccion.insertaCita(p);

    }

    private void modificaPaciente(Paciente p) {

        modificaPersona(p);
        modificaPacienteComun(p);

        if (p instanceof Privado) {
            modificaPacientePrivado((Privado) p);
        } else {
            modificaPacienteAsegurado((Asegurado) p);
        }

    }

    private void modificaMedico(Medico m) {
        String info;
        modificaPersona(m);

        System.out.println("Num. Colegiado");
        if (m.getNumColegiado().length() > 0) {
            System.out.println("[" + m.getNumColegiado() + "]");
        }

        info = leeCadena(": ");
        m.setNumColegiado(info);

    }

    private void modificarCita(CitaMedica cm, Clinica coleccion) throws Exception {
        String info;
        do {
            System.out.println("\nSelecciona Médico (numColegiado)\n");
            if (cm.getMedico().getNumColegiado().length() > 0) {
                System.out.println("[" + cm.getMedico().getNumColegiado() + "]");
            }
            info = leeCadena(": ");
        } while (!coleccion.existeColegiado(info));
        cm.setMedico(coleccion.getMedico(info));

        do {
            System.out.println("\nSelecciona Paciente(numHistorial)\n");
            if (cm.getPaciente().getNumHistorial().length() > 0) {
                System.out.println("[" + cm.getPaciente().getNumHistorial() + "]");
            }
            info = leeCadena(": ");
        } while (!coleccion.existeHistorial(info));
        cm.setPaciente(coleccion.getPaciente(info));
        
        Fecha f;
        Hora h;
        System.out.println("Fecha\n");
        if(cm.getFecha().getDia() != 0){
        System.out.println("["+ cm.getFecha().toString() + "]");
    }
        f = leerFecha();
        
       System.out.println("Hora\n");
        if(cm.getHora().getHora() != 0){
        System.out.println("["+ cm.getHora().toString() + "]");
    }
        h = leerHora();
        cm.setFecha(f);
        cm.setHora(h);
    }
    
    private Hora leerHora(){
        int h=0;
        int m=0;
        do{
            h= leeNum("\nHora:");
        }while(h<0 ||h>24);
         do{
            h= leeNum("\nMinuto:");
        }while(m<0 ||m>60);
        
        return new Hora(h,m);
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
    private void eliminaPaciente(Clinica coleccion) throws Exception {
        if (coleccion.getNumPacientes() > 0) {
            coleccion.eliminaPaciente(leePosPaciente(coleccion));
        } else {
            System.out.println("La clinica no tiene pacientes.");
        }
    }

    private void eliminaMedico(Clinica coleccion) throws Exception {
        if (coleccion.getNumMedicos() > 0) {
            coleccion.eliminaMedico(leePosMedico(coleccion));
        } else {
            System.out.println("La clinica no tiene medicos.");
        }
    }

    private void eliminaCita(Clinica coleccion) throws Exception {
        if (coleccion.getNumCitas() > 0) {
            coleccion.eliminaCita(leePosCitas(coleccion));
        } else {
            System.out.println("La clinica no tiene citas.");
        }
    }

    private void modificaPaciente(Clinica coleccion) throws Exception {
        if (coleccion.getNumPacientes() > 0) {
            this.modificaPaciente(coleccion.getPaciente(leePosPaciente(coleccion)));
        } else {
            System.out.println("La coleccion no contiene pacientes.");
        }
    }

    private void modificaMedico(Clinica coleccion) throws Exception {
        if (coleccion.getNumMedicos() > 0) {
            this.modificaMedico(coleccion.getMedico(leePosMedico(coleccion)));
        } else {
            System.out.println("La clínica no tiene médicos.");
        }
    }

    private void modificaCita(Clinica coleccion) throws Exception {
        if (coleccion.getNumCitas() > 0) {
            coleccion.modificaCita(coleccion.getCitaMedica(leePosCitas(coleccion)));
        } else {
            System.out.println("La clínica no tiene médicos.");
        }
    }

    private void modificaPersona(Persona p) {
        String info;
        Scanner teclado = new Scanner(System.in);

        System.out.println("Nombre del paciente: ");
        if (p.getNombre().length() > 0) {
            System.out.println("[" + p.getNombre() + "]");
        }

        info = leeCadena(": ");
        p.setNombre(info);

        System.out.println("Domicilio del paciente: ");
        if (p.getDomicilio().length() > 0) {
            System.out.println("[" + p.getDomicilio() + "]");
        }

        info = leeCadena(": ");
        p.setDomicilio(info);
    }

    private void modificaPacienteComun(Paciente p) {
        String info;
        Scanner teclado = new Scanner(System.in);

        System.out.print("Numero de historial del paciente");
        if (p.getNumHistorial().length() > 0) {
            System.out.println("[" + p.getNumHistorial() + "]");
        }

        info = leeCadena(": ");

        p.setNumHistorial(info);

        System.out.print("Fecha de nacimiento");
        if (p.getFechaNac().getAño() != 0) {
            System.out.println("[" + p.getFechaNac().toString() + "]");
        }

        p.setFechaNac(leerFecha());
    }

    private void modificaPacientePrivado(Privado p) {
        String info;
        Scanner teclado = new Scanner(System.in);

        //DNI
        System.out.print("DNI");
        if (p.getDni().length() > 0) {
            System.out.println("[" + p.getDni() + "]");
        }

        info = leeCadena(": ");

        p.setDni(info);
    }

    private void modificaPacienteAsegurado(Asegurado p) {
        String info;
        Scanner teclado = new Scanner(System.in);

        System.out.println("Poliza");
        if (p.getPoliza().length() > 0) {
            System.out.println("[" + p.getPoliza() + "]");
        }

        info = leeCadena(": ");
        p.setPoliza(info);

        System.out.println("Compañia");
        if (p.getCompañia().length() > 0) {
            System.out.println("[" + p.getCompañia() + "]");
        }

        info = leeCadena(": ");
        p.setCompañia(info);
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

    private int leePosMedico(Clinica coleccion) {
        final int numMedicos = coleccion.getNumMedicos();
        int toret;

        do {
            toret = leeNum("Introduzca posición del medico (1..." + numMedicos + "): ");
        } while (toret < 1
                || toret > numMedicos);

        return toret - 1;
    }

    private int leePosCitas(Clinica coleccion) {
        final int numCitas = coleccion.getNumCitas();
        int toret;

        do {
            toret = leeNum("Introduzca posición de la cita (1..." + numCitas + "): ");
        } while (toret < 1
                || toret > numCitas);

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
    private Fecha leerFecha() {
        int a, m, d;

        do {
            a = leeNum("\n\tAño: ");

        } while (a < 0);

        do {
            m = leeNum("\tMes: ");

        } while (m < 0 || m > 12);

        int max = -1;
        switch (m) {
            case 1:
            case 3:
            case 5:
            case 7:
            case 8:
            case 10:
            case 12:
                max = 31;
                break;
            case 2:
                max = 29;
                break;
            default:
                max = 30;
        }

        do {
            d = leeNum("\tDía: ");

        } while (d < 0 || d > max);

        return new Fecha(d, m, a);
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
