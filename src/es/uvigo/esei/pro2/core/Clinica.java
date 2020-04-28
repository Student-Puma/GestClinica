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

    /**
     * Nueva Clinica con un num. max. de pacientes.
     *
     * @param maxPacientes el num. max. de pacientes, como entero.
     * @param maxMedicos
     * @param maxCitas
     */
    public Clinica(String nombre, int maxPacientes, int maxMedicos, int maxCitas) {
        this.nombreClinica = nombre;
        numPacientes = 0;
        pacientes = new Paciente[maxPacientes];

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
    public Paciente getPaciente(int pos) throws Exception {
        if (pos >= getNumPacientes()) {
            throw new Exception("get(): sobrepasa la pos: " + (pos + 1) + " / " + getMaxPacientes());

        }

        return pacientes[pos];
    }

    public int getNumPacientes() {
        return numPacientes;
    }

    public int getMaxPacientes() {
        return pacientes.length;
    }

    public void insertaPaciente(Paciente p) throws Exception {
        final int maxPacientes = getMaxPacientes();

        if (getNumPacientes() >= maxPacientes) {
            throw new Exception("inserta(): sobrepasa max.: " + getMaxPacientes());
        }

        pacientes[numPacientes] = p;
        ++numPacientes;
    }

    public void eliminaPaciente(int pos) throws Exception {
        if (pos >= getNumPacientes()) {
            throw new Exception("get() : sobrepasa la pos: " + (pos + 1) + " / " + getMaxPacientes());
        }

        if (tienePacienteCitas(getPaciente(pos))) {
            throw new Exception("elimina(): no se puede eliminar el paciente, tiene citas");
        }
        pacientes[pos] = pacientes[--numPacientes];
    }

    private boolean tienePacienteCitas(Paciente p) {
        int i = 0;
        while (i < this.getNumCitas() && !citamedica[i].getPaciente().equals(p)) {
            i++;
        }
        return (i != this.getNumCitas());
    }

    public String toStringPaciente() {
        StringBuilder toret;
        final int numPacientes = getNumPacientes();

        toret = new StringBuilder();
        toret.append("Clinica: ").append(nombreClinica).append("\n");
        toret.append("Pacientes: \n");
        if (numPacientes > 0) {
            for (int i = 0; i < numPacientes; i++) {
                toret.append((i + 1) + ". ");
                toret.append(pacientes[i].toString() + "\n");
            }
        } else {
            toret.append("No hay pacientes.");
        }

        return toret.toString();
    }

    public void listarPacientes(char c) {
        int numPac = getNumPacientes();

        switch (c) {
            case 'P':
                for (int i = 0; i < numPac; i++) {
                    if (pacientes[i] instanceof Privado) {
                        System.out.println(pacientes[i].toString());
                    }
                }
                break;
            case 'A':
                for (int i = 0; i < numPac; i++) {
                    if (pacientes[i] instanceof Asegurado) {
                        System.out.println(pacientes[i].toString());
                    }
                }
                break;
        }
    }

    public Paciente getPaciente(String numHistorial) throws Exception {
        int i = 0;
        while (i < this.numPacientes && !pacientes[i].getNumHistorial().equals(numHistorial)) {
            i++;
        }
        if (i == this.numPacientes) {
            throw new Exception("getMedico(): El colegiado buscado no se corresponde con un médico");
        }
        return pacientes[i];
    }

    public boolean existeHistorial(String numHistorial) {
        int i = 0;
        while (i < this.numPacientes && !pacientes[i].getNumHistorial().equals(numHistorial)) {
            i++;
        }
        return (i != this.numPacientes);
    }

//MEDICOS
    public Medico getMedico(int pos) throws Exception {
        if (pos >= getNumPacientes()) {
            throw new Exception("get(): sobrepasa la pos: " + (pos + 1) + " / " + getMaxMedicos());

        }

        return medicos[pos];
    }

    public int getNumMedicos() {
        return numPacientes;
    }

    public int getMaxMedicos() {
        return pacientes.length;
    }

    public Medico getMedico(String numColegiado) throws Exception {
        int i = 0;
        while (i < this.numMedicos && !medicos[i].getNumColegiado().equals(numColegiado)) {
            i++;
        }
        if (i == this.numMedicos) {
            throw new Exception("getMedico(): El colegiado buscado no se corresponde con un médico");
        }
        return medicos[i];
    }

    public void inserta(Medico p) throws Exception {
        final int maxMedicos = getMaxMedicos();

        if (getNumPacientes() >= maxMedicos) {
            throw new Exception("inserta(): sobrepasa max.: " + getMaxMedicos());
        }

        medicos[numMedicos] = p;
        ++numMedicos;
    }

    public void eliminaMedico(int pos) throws Exception {
        if (pos >= getNumMedicos()) {
            throw new Exception("get() : sobrepasa la pos: " + (pos + 1) + " / " + getMaxMedicos());
        }
        if (tieneMedicoCitas(getMedico(pos))) {
            throw new Exception("elimina(): no se puede eliminar el medico, tiene citas");
        }
        medicos[pos] = medicos[--numMedicos];
    }

    private boolean tieneMedicoCitas(Medico p) {
        int i = 0;
        while (i < this.getNumCitas() && !citamedica[i].getMedico().equals(p)) {
            i++;
        }
        return (i != this.getNumCitas());
    }

    public boolean existeColegiado(String numColegiado) {
        int i = 0;
        while (i < this.numMedicos && !medicos[i].getNumColegiado().equals(numColegiado)) {
            i++;
        }
        return (i != this.numMedicos);
    }

    public String toStringMedico() {
        StringBuilder toret;
        final int numMedicos = getNumMedicos();

        toret = new StringBuilder();
        toret.append("Clinica: ").append(nombreClinica).append("\n");
        toret.append("Medicos: \n");
        if (numMedicos > 0) {
            for (int i = 0; i < numMedicos; i++) {
                toret.append((i + 1) + ". ");
                toret.append(medicos[i].toString() + "\n");
            }
        } else {
            toret.append("No hay medicos.");
        }

        return toret.toString();

    }

    public void listarMedicos(char c) {
        int numM = getNumMedicos();
        for (int i = 0; i < numM; i++) {
            
                System.out.println(medicos[i].toString());
            
        }
    }

    //CITAS
    public CitaMedica getCitaMedica(int pos) throws Exception {
        if (pos >= getNumPacientes()) {
            throw new Exception("get(): sobrepasa la pos: " + (pos + 1) + " / " + getMaxCitas());

        }

        return citamedica[pos];
    }

    public int getNumCitas() {
        return numCitas;
    }

    public int getMaxCitas() {
        return citamedica.length;
    }

    public void inserta(CitaMedica p) throws Exception {
        final int maxCitas = getMaxCitas();

        if (getNumPacientes() >= maxCitas) {
            throw new Exception("inserta(): sobrepasa max.: " + getMaxCitas());
        }

        citamedica[numCitas] = p;
        ++numCitas;
    }

    public void eliminaCita(int pos) throws Exception {
        if (pos >= getNumCitas()) {
            throw new Exception("get() : sobrepasa la pos: " + (pos + 1) + " / " + getMaxCitas());
        }
        citamedica[pos] = citamedica[--numCitas];
    }

    public String toStringCita() {
        StringBuilder toret;
        final int numCitas = getNumCitas();

        toret = new StringBuilder();
        toret.append("Clinica: ").append(nombreClinica).append("\n");
        toret.append("Citas: \n");
        if (numCitas > 0) {
            for (int i = 0; i < numCitas; i++) {
                toret.append((i + 1) + ". ");
                toret.append(citamedica[i].toString() + "\n");
            }
        } else {
            toret.append("No hay citas.");
        }

        return toret.toString();
    }
    
        public void listarCitas(char c) {
        int numC = getNumCitas();
        for (int i = 0; i < numC; i++) {
            
                System.out.println(citamedica[i].toString());
            
        }
    }


}



//CITAS
