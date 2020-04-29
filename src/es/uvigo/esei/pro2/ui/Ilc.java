package es.uvigo.esei.pro2.ui;

import java.util.Scanner;
import es.uvigo.esei.pro2.core.*;

/**
 * Interfaz de lin. de comando
 */
public class Ilc {

    /**
     * Realiza el reparto de la funcionalidad ler = lee, evalua, repite
     */
    public void ler() {
        // --- Configuración
        System.out.println("Configuracion de la clínica");
        String nombre = this.leeCadena("Nombre de la clinica");
        int maxPacientes = this.leeNum("Num. max. pacientes");
        int maxMedicos = this.leeNum("Num. max. medicos");
        int maxCitas = this.leeNum("Num. max. citas");

        // -- Preparación
        Clinica clinica = new Clinica(nombre, maxPacientes, maxMedicos, maxCitas);

        // --- Menú
        int op;
        do {
            op = this.menuPrincipal(clinica);
            try {
                switch (op) {
                    case 1:
                        this.opcionesPacientes(clinica);
                        break;
                    case 2:
                        this.opcionesMedicos(clinica);
                        break;
                    case 3:
                        this.opcionesCitas(clinica);
                        break;
                }
            } catch (Exception err) {
                System.err.println("[!] ERROR { " + err.getMessage() + " }");
            }
        } while (op != 0);
        System.out.println("-- Gracias por usar Gestión Clínica --");
    }

    // ==============================================
    //  MENÚS
    // ==============================================
    private int menuPrincipal(Clinica c) {
        int toret;
        do {
            System.out.println("\n-- Gestión de la clínica " + c.getNombre() + " --");
            System.out.println(
                    "\n1. Gestión pacientes\n"
                    + "2. Gestión médicos\n"
                    + "0. Salir\n");
            toret = this.leeNum("> Selecciona la opción deseada");
        } while (toret < 0 || toret > 3);
        System.out.println();
        return toret;
    }

    private int menuPacientes(Clinica c) {
        int toret;
        do {
            System.out.println("Número de pacientes: "
                    + c.getNumPacientes()
                    + " / " + c.getMaxPacientes());
            System.out.println(
                    "\n1. Inserta un nuevo paciente\n"
                    + "2. Modifica un paciente\n"
                    + "3. Elimina un paciente\n"
                    + "4. Listar todos los pacientes\n"
                    + "5. Listar pacientes asegurados\n"
                    + "6. Listar pacientes privados\n"
                    + "0. Salir\n");
            toret = this.leeNum("> Selecciona la opción deseada");
        } while (toret < 0 || toret > 6);
        System.out.println();
        return toret;
    }

    private int menuMedicos(Clinica clinica) {
        int toret;

        do {
            System.out.println("Número de médicos: "
                    + clinica.getNumMedicos()
                    + " / " + clinica.getMaxMedicos());
            System.out.println(
                    "\n1. Inserta un nuevo médico\n"
                    + "2. Modifica un médico\n"
                    + "3. Elimina un médico\n"
                    + "4. Listar médicos\n"
                    + "0. Salir\n");
            toret = this.leeNum("> Selecciona la opción deseada");
        } while (toret < 0 || toret > 4);
        System.out.println();
        return toret;
    }

    private int menuCitas(Clinica clinica) {
        int toret;
        do {
            System.out.println("Número de citas médicas: "
                    + clinica.getNumCitasMedicas()
                    + " / " + clinica.getNumCitasMedicas());
            System.out.println(
                    "\n1. Inserta una nueva cita médica\n"
                    + "2. Modifica una cita médica\n"
                    + "3. Elimina una cita médica\n"
                    + "4. Listar citas médicas\n"
                    + "0. Salir\n");
            toret = this.leeNum("> Selecciona la opción deseada");
        } while (toret < 0 || toret > 4);
        System.out.println();
        return toret;
    }

    // ==============================================
    //  OPCIONES
    // ==============================================
    private void opcionesPacientes(Clinica clinica) {
        int op;
        do {
            op = this.menuPacientes(clinica);

            try {
                switch (op) {
                    case 1:
                        this.insertarPaciente(clinica);
                        break;
                    case 2:
                        if(clinica.getNumPacientes() == 0) {
                            System.out.println("No hay pacientes");
                        } else {
                            int index;
                            do {
                                System.out.println("Pacientes:\n----------");
                                System.out.println(clinica.toStringPacientes());
                                index = leeNum("> Selecciona el paciente deseado");
                            } while(index < 1 || index > clinica.getNumPacientes());
                            this.modificarPaciente(clinica.getPaciente(index - 1));
                        }
                        break;
                    case 3:
                        if(clinica.getNumPacientes() == 0) {
                            System.out.println("No hay pacientes");
                        } else {
                            int index;
                            do {
                                System.out.println("Pacientes:\n----------");
                                System.out.println(clinica.toStringPacientes());
                                index = leeNum("> Selecciona el paciente deseado");
                            } while(index < 1 || index > clinica.getNumPacientes());
                            clinica.eliminarPaciente(index - 1);
                        }
                        break;
                    case 4:
                        System.out.println(clinica.toStringPacientes());
                        break;
                    case 5:
                        System.out.println(clinica.toStringPacientes('A'));
                        break;
                    case 6:
                        System.out.println(clinica.toStringPacientes('P'));
                        break;
                }
            } catch (ClinicaException err) {
                System.err.println("[!] ERROR { " + err.getMessage() + " }");
            }

            System.out.println();
        } while (op != 0);
    }

    private void opcionesMedicos(Clinica clinica) {
        int op;
        do {
            op = this.menuMedicos(clinica);

            try {
                switch (op) {
                    case 1:
                        // insertaMedico(clinica);
                        break;
                    case 2:
                        // modificaMedico(clinica);
                        break;
                    case 3:
                        // eliminaMedico(clinica);
                        break;
                    case 4:
                        System.out.println(clinica.toStringMedicos());
                        break;
                }
            } catch (Exception err) {//ClinicaException err) {
                System.err.println("[!] ERROR { " + err.getMessage() + " }");
            }
            System.out.println();
        } while (op != 0);
    }
    
    private void opcionesCitas(Clinica clinica) {
        int op;
        do {
            op = menuCitas(clinica);

            try {
                switch (op) {
                    case 1:
                        insertaCita(clinica);
                        break;
                    case 2:
                        modificaCita(clinica);
                        break;
                    case 3:
                        eliminaCita(clinica);
                        break;
                    case 4:
                        System.out.println(clinica.toStringCitasMedicas());
                        break;
                }
            } catch (Exception err) {//ClinicaException err) {
                System.err.println("[!] ERROR { " + err.getMessage() + " }");
            }
            System.out.println();
        } while (op != 0);
    }
    
    // ==============================================
    //  PERSONAS
    // ==============================================
    private void modificarPersona(Persona p) {
        String nombre = this.leeCadena("Apellidos, Nombre");
        String domicilio = this.leeCadena("Domicilio");
        
        p.setNombre(nombre);
        p.setDomicilio(domicilio);
    }
    
    // ==============================================
    //  PACIENTES
    // ==============================================    
    private void insertarPaciente(Clinica clinica) throws ClinicaException {
        int tipo = this.leerTipoPaciente();
        Paciente p = tipo == 1 ? 
                    new Asegurado(null, null, null, null, null, null) :
                    new Privado(null, null, null, null, null);
        this.modificarPaciente(p);
        clinica.insertarPaciente(p);
    } 
    
    private void modificarPaciente(Paciente p) {
        this.modificarPersona(p);
        
        String numHistorial = this.leeCadena("Num. Historial");
        Fecha fechaNac = this.leerFecha("Fecha de Nacimiento");
        
        p.setNumHistorial(numHistorial);
        p.setFechaNac(fechaNac);
        
        if(p instanceof Asegurado) {
            String poliza = this.leeCadena("Póliza");
            String compañia = this.leeCadena("Compañía aseguradora");
        
            ((Asegurado) p).setPoliza(poliza);
            ((Asegurado) p).setCompañia(compañia);
        } else {
            String dni = this.leerDNI();
        
            ((Privado) p).setDni(dni);
        }
    }
    
    // ==============================================
    //  ENTRADAS DE TECLADO (ESPECIALES)
    // ==============================================
    private int leerTipoPaciente() {
        int tipo;
        do {
            tipo = this.leeNum("Introduce tipo de paciente:\n" +
                    "1. Asegurado\n2. Privado\n\n" +
                    "> Selecciona la opción deseada");
        } while (tipo != 1 && tipo != 2);
        return tipo;
    }
    
    private Fecha leerFecha(String msg) {
        System.out.println(msg + ":");
        int a,m,d;
        // Año
        do {
            a = leeNum("\tAño");
        } while (a < 1900 || a > 2020);
        // Mes
        do {
            m = leeNum("\tMes");
        } while (m < 0 || m > 12);
        // Día
        int max;
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
            d = leeNum("\tDia");
        } while (d < 0 || d > max);

        return new Fecha(d, m, a);
    }
    
    private String leerDNI() {
        String dni;
        
        do {
            dni = this.leeCadena("DNI");
        } while(dni.length() == 9);
        
        return dni;
    }
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    

    /**
     * Lee un num. de teclado
     *
     * @param msg El mensaje a visualizap.
     * @return El num., como entero
     */
    private int leeNum(String msg) {
        int toret = 0;
        boolean repite;
        Scanner teclado = new Scanner(System.in);

        do {
            System.out.print(msg + ": ");
            try {
                toret = Integer.parseInt(teclado.nextLine());
                repite = toret < 0;
            } catch (NumberFormatException exc) {
                repite = true;
            }
        } while (repite);

        return toret;
    }

    /**
     * Crea un nuevo paciente y lo inserta en la coleccion
     *
     * @param coleccion La coleccion en la que se inserta el paciente.
     */
    

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
        if (cm.getFecha().getDia() != 0) {
            System.out.println("[" + cm.getFecha().toString() + "]");
        }
        f = leerFecha();

        System.out.println("Hora\n");
        if (cm.getHora().getHora() != 0) {
            System.out.println("[" + cm.getHora().toString() + "]");
        }
        h = leerHora();
        cm.setFecha(f);
        cm.setHora(h);
    }

    private Hora leerHora() {
        int h = 0;
        int m = 0;
        do {
            h = leeNum("\nHora:");
        } while (h < 0 || h > 24);
        do {
            h = leeNum("\nMinuto:");
        } while (m < 0 || m > 60);

        return new Hora(h, m);
    }

    

    private String leeCadena(String msg) {
        Scanner teclado = new Scanner(System.in);
        String toret = "";
        do {
            System.out.print(msg + ": ");
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
