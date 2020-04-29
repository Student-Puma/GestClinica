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
        String nombre = this.leerCadena("Nombre de la clinica");
        int maxPacientes = this.leerNum("Num. max. pacientes");
        int maxMedicos = this.leerNum("Num. max. medicos");
        int maxCitas = this.leerNum("Num. max. citas");

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
                    + "3. Gestión citas médicas\n"
                    + "0. Salir\n");
            toret = this.leerNum("> Selecciona la opción deseada");
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
            toret = this.leerNum("> Selecciona la opción deseada");
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
            toret = this.leerNum("> Selecciona la opción deseada");
        } while (toret < 0 || toret > 4);
        System.out.println();
        return toret;
    }

    private int menuCitasMedicas(Clinica clinica) {
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
            toret = this.leerNum("> Selecciona la opción deseada");
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
                        if (clinica.getNumPacientes() == 0) {
                            System.out.println("No hay pacientes");
                        } else {
                            int index = this.seleccionarPaciente(clinica);
                            this.modificarPaciente(clinica.getPaciente(index));
                        }
                        break;
                    case 3:
                        if (clinica.getNumPacientes() == 0) {
                            System.out.println("No hay pacientes");
                        } else {
                            int index = this.seleccionarPaciente(clinica);
                            clinica.eliminarPaciente(index);
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
                        this.insertarMedico(clinica);
                        break;
                    case 2:
                        if (clinica.getNumMedicos() == 0) {
                            System.out.println("No hay médicos");
                        } else {
                            int index = this.seleccionarMedico(clinica);
                            this.modificarMedico(clinica.getMedico(index));
                        }
                        break;
                    case 3:
                        if (clinica.getNumMedicos() == 0) {
                            System.out.println("No hay médicos");
                        } else {
                            int index = this.seleccionarMedico(clinica);
                            clinica.eliminarMedico(index);
                        }
                        break;
                    case 4:
                        System.out.println(clinica.toStringMedicos());
                        break;
                }
            } catch (ClinicaException err) {
                System.err.println("[!] ERROR { " + err.getMessage() + " }");
            }
            System.out.println();
        } while (op != 0);
    }

    private void opcionesCitas(Clinica clinica) {
        int op;
        do {
            op = menuCitasMedicas(clinica);

            try {
                switch (op) {
                    case 1:
                        this.insertarCitaMedica(clinica);
                        break;
                    case 2:
                        if (clinica.getNumCitasMedicas() == 0) {
                            System.out.println("No hay citas médicas");
                        } else {
                            int index = this.seleccionarCitaMedica(clinica);
                            this.modificarCitaMedica(clinica.getCitaMedica(index));
                        }
                        break;
                    case 3:
                        if (clinica.getNumCitasMedicas() == 0) {
                            System.out.println("No hay citas médicas");
                        } else {
                            int index = this.seleccionarCitaMedica(clinica);
                            clinica.eliminarCitaMedica(index);
                        }
                    case 4:
                        System.out.println(clinica.toStringCitasMedicas());
                        break;
                }
            } catch (ClinicaException err) {
                System.err.println("[!] ERROR { " + err.getMessage() + " }");
            }
            System.out.println();
        } while (op != 0);
    }

    // ==============================================
    //  PERSONAS
    // ==============================================
    private void modificarPersona(Persona p) {
        String nombre = this.leerCadena("Apellidos, Nombre");
        p.setNombre(nombre);
        String domicilio = this.leerCadena("Domicilio");
        p.setDomicilio(domicilio);
    }

    // ==============================================
    //  PACIENTES
    // ==============================================    
    private void insertarPaciente(Clinica clinica) throws ClinicaException {
        int tipo = this.leerTipoPaciente();
        Paciente p = tipo == 1
                ? new Asegurado(null, null, null, null, null, null)
                : new Privado(null, null, null, null, null);
        this.modificarPaciente(p);
        clinica.insertarPaciente(p);
    }

    private void modificarPaciente(Paciente p) {
        this.modificarPersona(p);

        String numHistorial = this.leerCadena("Num. Historial");
        p.setNumHistorial(numHistorial);
        Fecha fechaNac = this.leerFecha("Fecha de Nacimiento");
        p.setFechaNac(fechaNac);

        if (p instanceof Asegurado) {
            String poliza = this.leerCadena("Póliza");
            ((Asegurado) p).setPoliza(poliza);
            String compañia = this.leerCadena("Compañía aseguradora");
            ((Asegurado) p).setCompañia(compañia);
        } else {
            String dni = this.leerDNI();
            ((Privado) p).setDni(dni);
        }
    }

    private int seleccionarPaciente(Clinica clinica) {
        int index;
        do {
            System.out.println("Pacientes:\n----------");
            System.out.println(clinica.toStringPacientes());
            index = this.leerNum("> Selecciona el paciente deseado");
        } while (index < 1 || index > clinica.getNumPacientes());
        return index - 1;
    }

    // ==============================================
    //  MÉDICOS
    // ============================================== 
    private void insertarMedico(Clinica clinica) throws ClinicaException {
        Medico m = new Medico(null, null, null);
        this.modificarMedico(m);
        clinica.insertarMedico(m);
    }

    private void modificarMedico(Medico m) {
        this.modificarPersona(m);

        String numColegiado = this.leerCadena("Num. Colegiado");
        m.setNumColegiado(numColegiado);
    }

    private int seleccionarMedico(Clinica clinica) {
        int index;
        do {
            System.out.println("Medicos:\n----------");
            System.out.println(clinica.toStringMedicos());
            index = this.leerNum("> Selecciona el médico deseado");
        } while (index < 1 || index > clinica.getNumMedicos());
        return index - 1;
    }

    // ==============================================
    //  CITAS MÉDICAS
    // ============================================== 
    private void insertarCitaMedica(Clinica clinica) throws ClinicaException {
        CitaMedica cm = new CitaMedica(null, null);
        this.modificarCitaMedica(cm);

        System.out.println("~ Médico responsable de la cita:");
        int idxMedico = clinica.getNumMedicos() > 0
                ? this.seleccionarMedico(clinica) : -1;

        System.out.println("~ Paciente responsable de la cita:");
        int idxPaciente = clinica.getNumPacientes() > 0
                ? this.seleccionarPaciente(clinica) : -1;

        if (idxPaciente < 0 || idxMedico < 0) {
            throw new ClinicaException("CitaMedica :: insertar(): Debe haber al menos un médico y un paciente en la clínica");
        } else {
            clinica.insertarCitaMedica(cm,
                    clinica.getMedico(idxMedico), clinica.getPaciente(idxPaciente));
        }
    }

    private void modificarCitaMedica(CitaMedica cm) {
        Fecha fecha = this.leerFecha("Fecha de la cita");
        cm.setFecha(fecha);
        Hora hora = this.leerHora("Hora de la cita");
        cm.setHora(hora);
    }

    private int seleccionarCitaMedica(Clinica clinica) {
        int index;
        do {
            System.out.println("Citas Médicas:\n----------");
            System.out.println(clinica.toStringCitasMedicas());
            index = this.leerNum("> Selecciona la cita médica deseada deseada");
        } while (index < 1 || index > clinica.getNumMedicos());
        return index - 1;
    }

    // ==============================================
    //  ENTRADAS DE TECLADO (ESTÁNDAR)
    // ==============================================
    private int leerNum(String msg) {
        int toret = -1;
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

    private String leerCadena(String msg) {
        Scanner teclado = new Scanner(System.in);
        String toret;
        do {
            System.out.print(msg + ": ");
            toret = teclado.nextLine().trim();
        } while (toret.equals(""));
        return toret;
    }

    // ==============================================
    //  ENTRADAS DE TECLADO (ESPECIALES)
    // ==============================================
    private int leerTipoPaciente() {
        int tipo;
        do {
            tipo = this.leerNum("Introduce tipo de paciente:\n"
                    + "1. Asegurado\n2. Privado\n\n"
                    + "> Selecciona la opción deseada");
        } while (tipo != 1 && tipo != 2);
        return tipo;
    }

    private Hora leerHora(String msg) {
        int h, m;
        System.out.println(msg + ":");

        do {
            h = this.leerNum("\tHora");
        } while (h < 0 || h > 24);
        do {
            m = this.leerNum("\tMinutos");
        } while (m < 0 || m > 60);

        return new Hora(h, m);
    }

    private Fecha leerFecha(String msg) {
        System.out.println(msg + ":");
        int a, m, d;
        // Año
        do {
            a = leerNum("\tAño");
        } while (a < 1900 || a > 2020);
        // Mes
        do {
            m = leerNum("\tMes");
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
            d = leerNum("\tDia");
        } while (d < 0 || d > max);

        return new Fecha(d, m, a);
    }

    private String leerDNI() {
        String dni;

        do {
            dni = this.leerCadena("DNI");
        } while (dni.length() != 9);

        return dni;
    }
}
