/*  Definición de la clase Clinica
 *  En una clínica tendremos una serie de pacientes
 */
package es.uvigo.esei.pro2.core;

/**
 *
 * @author Suraei
 */
public class Clinica {

    // ==============================================
    //  ATRIBUTOS
    // ==============================================
    private String nombre;

    private int numPacientes;
    private final Paciente[] pacientes;

    private int numMedicos;
    private final Medico[] medicos;

    private int numCitas;
    private final CitaMedica[] citamedica;
    private final Object[][] relacionCitaMedicoPaciente;

    // ==============================================
    //  CONSTRUCTOR
    // ==============================================
    public Clinica(String nombre, int maxPacientes, int maxMedicos, int maxCitas) {
        this.nombre = nombre;

        this.numPacientes = 0;
        this.pacientes = new Paciente[maxPacientes];

        this.numMedicos = 0;
        this.medicos = new Medico[maxMedicos];

        this.numCitas = 0;
        this.citamedica = new CitaMedica[maxCitas];
        this.relacionCitaMedicoPaciente = new Object[maxCitas][3];
    }

    // ==============================================
    //  CLINICA
    // ==============================================
    public String getNombre() {
        return this.nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    // ==============================================
    //  PACIENTES
    // ==============================================
    public int getNumPacientes() {
        return this.numPacientes;
    }

    public int getMaxPacientes() {
        return this.pacientes.length;
    }

    public void insertarPaciente(Paciente p) throws ClinicaException {
        if (this.numPacientes >= this.pacientes.length) {
            throw new ClinicaException("Paciente :: insertar(): Sobrepasa el máximo (" + this.pacientes.length + ")");
        }
        this.pacientes[this.numPacientes++] = p;
    }

    public Paciente getPaciente(int index) throws ClinicaException {
        if (index < 0 || index >= this.pacientes.length) {
            throw new ClinicaException("Paciente :: get() : Error en la posición (" + (index + 1) + "/" + this.pacientes.length + ")");
        }
        return pacientes[index];
    }

    public Paciente getPaciente(String numHistorial) throws ClinicaException {
        int i = 0;
        while (i < this.numPacientes && !this.pacientes[i].getNumHistorial().equals(numHistorial)) {
            i++;
        }
        if (i == this.numPacientes) {
            throw new ClinicaException("Paciente :: get(): No existe el número de historial " + numHistorial);
        }
        return this.pacientes[i];
    }

    public boolean existeHistorial(String numHistorial) {
        int i = 0;
        while (i < this.numPacientes && !this.pacientes[i].getNumHistorial().equals(numHistorial)) {
            i++;
        }
        return i != this.numPacientes;
    }

    public void eliminarPaciente(int index) throws ClinicaException {
        if (index < 0 || index >= this.pacientes.length) {
            throw new ClinicaException("Paciente :: eliminar() : Error en la posición (" + (index + 1) + "/" + this.pacientes.length + ")");
        }
        if (tienePacienteCitas(this.getPaciente(index))) {
            throw new ClinicaException("Paciente :: eliminar() : El paciente tiene citas pendientes");
        }
        this.pacientes[index] = this.pacientes[--this.numPacientes];
    }

    private boolean tienePacienteCitas(Paciente p) {
        int i = 0;
        while (i < this.numCitas && !this.relacionCitaMedicoPaciente[i][2].equals(p)) {
            i++;
        }
        return i != this.numCitas;
    }

    public String toStringPacientes() {
        StringBuilder sb = new StringBuilder();
        if (this.numPacientes < 0) {
            sb.append("No hay pacientes");
        } else {
            for (int i = 0; i < this.numPacientes; i++) {
                sb.append(i + 1)
                        .append(". ")
                        .append(this.pacientes[i])
                        .append('\n');
            }
        }
        return sb.toString();
    }

    public String toStringPacientes(char tipo) {
        int index = 0;
        StringBuilder sb = new StringBuilder();

        switch (tipo) {
            case 'P':
                for (int i = 0; i < this.numPacientes; i++) {
                    if (pacientes[i] instanceof Privado) {
                        sb.append(++index)
                                .append(". ")
                                .append(pacientes[i])
                                .append('\n');
                    }
                }
                break;
            case 'A':
                for (int i = 0; i < this.numPacientes; i++) {
                    if (pacientes[i] instanceof Asegurado) {
                        sb.append(++index)
                                .append(". ")
                                .append(pacientes[i])
                                .append('\n');
                    }
                }
                break;
        }

        if (sb.length() == 0) {
            sb.append("No hay pacientes de tipo especificado");
        }

        return sb.toString();
    }

    // ==============================================
    //  MÉDICOS
    // ==============================================
    public int getNumMedicos() {
        return this.numMedicos;
    }

    public int getMaxMedicos() {
        return this.medicos.length;
    }

    public void insertarMedico(Medico m) throws ClinicaException {
        if (this.numMedicos >= this.medicos.length) {
            throw new ClinicaException("Medico :: insertar(): Sobrepasa el máximo (" + this.medicos.length + ")");
        }
        this.medicos[this.numMedicos++] = m;
    }

    public Medico getMedico(int index) throws ClinicaException {
        if (index < 0 || index >= this.medicos.length) {
            throw new ClinicaException("Medico :: get() : Error en la posición (" + (index + 1) + "/" + this.medicos.length + ")");
        }
        return medicos[index];
    }

    public Medico getMedico(String numColegiado) throws ClinicaException {
        int i = 0;
        while (i < this.numMedicos && !this.medicos[i].getNumColegiado().equals(numColegiado)) {
            i++;
        }
        if (i == this.numMedicos) {
            throw new ClinicaException("Medico :: get(): No existe el número de colegiado " + numColegiado);
        }
        return this.medicos[i];
    }
}

/*
//MEDICOS

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

    public void insertaMedico(Medico p) throws Exception {
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

    public void insertaCita(CitaMedica p) throws Exception {
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

 */

//CITAS
