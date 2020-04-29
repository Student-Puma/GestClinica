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
                        if (clinica.getNumPacientes() == 0) {
                            System.out.println("No hay pacientes");
                        } else {
                            int index;
                            do {
                                System.out.println("Pacientes:\n----------");
                                System.out.println(clinica.toStringPacientes());
                                index = this.leeNum("> Selecciona el paciente deseado");
                            } while (index < 1 || index > clinica.getNumPacientes());
                            this.modificarPaciente(clinica.getPaciente(index - 1));
                        }
                        break;
                    case 3:
                        if (clinica.getNumPacientes() == 0) {
                            System.out.println("No hay pacientes");
                        } else {
                            int index;
                            do {
                                System.out.println("Pacientes:\n----------");
                                System.out.println(clinica.toStringPacientes());
                                index = this.leeNum("> Selecciona el paciente deseado");
                            } while (index < 1 || index > clinica.getNumPacientes());
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
                        this.insertarMedico(clinica);
                        break;
                    case 2:
                        if (clinica.getNumMedicos() == 0) {
                            System.out.println("No hay médicos");
                        } else {
                            int index;
                            do {
                                System.out.println("Medicos:\n----------");
                                System.out.println(clinica.toStringMedicos());
                                index = this.leeNum("> Selecciona el médico deseado");
                            } while (index < 1 || index > clinica.getNumMedicos());
                            this.modificarMedico(clinica.getMedico(index - 1));
                        }
                        break;
                    case 3:
                        if (clinica.getNumMedicos() == 0) {
                            System.out.println("No hay médicos");
                        } else {
                            int index;
                            do {
                                System.out.println("Medicos:\n----------");
                                System.out.println(clinica.toStringMedicos());
                                index = this.leeNum("> Selecciona el médico deseado");
                            } while (index < 1 || index > clinica.getNumMedicos());
                            clinica.eliminarMedico(index - 1);
                        }
                        break;
                    case 4:
                        System.out.println(clinica.toStringMedicos());
                        break;
                }
            } catch (ClinicaException err) {//ClinicaException err) {
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
        p.setNombre(nombre);
        String domicilio = this.leeCadena("Domicilio");
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

        String numHistorial = this.leeCadena("Num. Historial");
        p.setNumHistorial(numHistorial);
        Fecha fechaNac = this.leerFecha("Fecha de Nacimiento");
        p.setFechaNac(fechaNac);

        if (p instanceof Asegurado) {
            String poliza = this.leeCadena("Póliza");
            ((Asegurado) p).setPoliza(poliza);
            String compañia = this.leeCadena("Compañía aseguradora");
            ((Asegurado) p).setCompañia(compañia);
        } else {
            String dni = this.leerDNI();
            ((Privado) p).setDni(dni);
        }
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

        String numColegiado = this.leeCadena("Num. Colegiado");
        m.setNumColegiado(numColegiado);
    }

    // ==============================================
    //  ENTRADAS DE TECLADO (ESPECIALES)
    // ==============================================
    private int leerTipoPaciente() {
        int tipo;
        do {
            tipo = this.leeNum("Introduce tipo de paciente:\n"
                    + "1. Asegurado\n2. Privado\n\n"
                    + "> Selecciona la opción deseada");
        } while (tipo != 1 && tipo != 2);
        return tipo;
    }

    private Fecha leerFecha(String msg) {
        System.out.println(msg + ":");
        int a, m, d;
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
        } while (dni.length() == 9);

        return dni;
    }

    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
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

    private void insertaCita(Clinica coleccion) throws Exception {
        CitaMedica p;
        p = new CitaMedica(new Hora(0, 0), new Fecha(0, 0, 0), new Medico("", "", ""), new Privado("", "", "", "", new Fecha(0, 0, 0)));

        if (coleccion.getNumMedicos() == 0 || coleccion.getNumPacientes() == 0) {
            throw new Exception("Falta gente : No hay médicos o no hay pacientes");
        }
        modificaCita(p, coleccion);
        coleccion.insertaCita(p);

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
}
