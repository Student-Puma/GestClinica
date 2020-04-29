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

    private int numCitasMedicas;
    private final CitaMedica[] citasMedicas;
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

        this.numCitasMedicas = 0;
        this.citasMedicas = new CitaMedica[maxCitas];
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

    public boolean existeNumHistorial(String numHistorial) {
        int i = 0;
        while (i < this.numPacientes && !this.pacientes[i].getNumHistorial().equals(numHistorial)) {
            i++;
        }
        return i != this.numPacientes;
    }

    public void eliminarPaciente(int index) throws ClinicaException {
        if (index < 0 || index >= this.numPacientes) {
            throw new ClinicaException("Paciente :: eliminar() : Error en la posición (" + (index + 1) + "/" + this.numPacientes + ")");
        }
        if (tienePacienteCitas(this.getPaciente(index))) {
            throw new ClinicaException("Paciente :: eliminar() : El paciente tiene citas pendientes");
        }
        this.pacientes[index] = this.pacientes[--this.numPacientes];
    }

    private boolean tienePacienteCitas(Paciente p) {
        int i = 0;
        while (i < this.numCitasMedicas && !this.relacionCitaMedicoPaciente[i][2].equals(p)) {
            i++;
        }
        return i != this.numCitasMedicas;
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

    public boolean existeNumColegiado(String numColegiado) {
        int i = 0;
        while (i < this.numMedicos && !this.medicos[i].getNumColegiado().equals(numColegiado)) {
            i++;
        }
        return i != this.numMedicos;
    }

    public void eliminarMedico(int index) throws ClinicaException {
        if (index < 0 || index >= this.numMedicos) {
            throw new ClinicaException("Medico :: eliminar() : Error en la posición (" + (index + 1) + "/" + this.numMedicos + ")");
        }
        if (this.tieneMedicoCitas(this.getMedico(index))) {
            throw new ClinicaException("Medico :: eliminar() : El medico tiene citas pendientes");
        }
        this.medicos[index] = this.medicos[--this.numMedicos];
    }

    private boolean tieneMedicoCitas(Medico m) {
        int i = 0;
        while (i < this.numCitasMedicas && !this.relacionCitaMedicoPaciente[i][1].equals(m)) {
            i++;
        }
        return i != this.numCitasMedicas;
    }

    public String toStringMedicos() {
        StringBuilder sb = new StringBuilder();
        if (this.numMedicos < 0) {
            sb.append("No hay medicos");
        } else {
            for (int i = 0; i < this.numMedicos; i++) {
                sb.append(i + 1)
                        .append(". ")
                        .append(this.medicos[i])
                        .append('\n');
            }
        }
        return sb.toString();
    }

    // ==============================================
    //  CITAS
    // ==============================================
    public int getNumCitas() {
        return this.numCitasMedicas;
    }

    public int getMaxCitas() {
        return this.citasMedicas.length;
    }

    public void insertarCitaMedica(CitaMedica cm, Medico m, Paciente p) throws ClinicaException {
        if (this.numCitasMedicas >= this.citasMedicas.length) {
            throw new ClinicaException("Cita Médica :: insertar(): Sobrepasa el máximo (" + this.citasMedicas.length + ")");
        }
        this.citasMedicas[this.numCitasMedicas] = cm;
        // Añadimos la relacion Cita - Medico - Paciente
        this.relacionCitaMedicoPaciente[this.numCitasMedicas++][0] = cm;    // Cita
        this.relacionCitaMedicoPaciente[this.numCitasMedicas++][1] = m;     // Medico
        this.relacionCitaMedicoPaciente[this.numCitasMedicas++][2] = p;     // Paciente
    }

    public CitaMedica getCitaMedica(int index) throws ClinicaException {
        if (index < 0 || index >= this.citasMedicas.length) {
            throw new ClinicaException("Cita Médica :: get() : Error en la posición (" + (index + 1) + "/" + this.citasMedicas.length + ")");
        }
        return this.citasMedicas[index];
    }

    public Object[] getCitaMedicaDetallada(int index) throws ClinicaException {
        if (index < 0 || index >= this.citasMedicas.length) {
            throw new ClinicaException("Cita Médica :: get() : Error en la posición (" + (index + 1) + "/" + this.citasMedicas.length + ")");
        }
        return this.relacionCitaMedicoPaciente[index];
    }

    public void eliminarCitaMedica(int index) throws ClinicaException {
        if (index < 0 || index >= this.numCitasMedicas) {
            throw new ClinicaException("Cita Médica :: eliminar() : Error en la posición (" + (index + 1) + "/" + this.numCitasMedicas + ")");
        }
        this.citasMedicas[index] = this.citasMedicas[--this.numCitasMedicas];
        // Eliminamos la relacion Cita - Medico - Paciente
        this.relacionCitaMedicoPaciente[index] = this.relacionCitaMedicoPaciente[this.numCitasMedicas];
    }

    public String toStringCitaMedicaDetallada(int index) throws ClinicaException {
        if (index < 0 || index >= this.numCitasMedicas) {
            throw new ClinicaException("Cita Médica :: toString() : Error en la posición (" + (index + 1) + "/" + this.numCitasMedicas + ")");
        }

        StringBuilder sb = new StringBuilder();

        sb.append(this.relacionCitaMedicoPaciente[index][0])
                .append(" ; ")
                .append(this.relacionCitaMedicoPaciente[index][1])
                .append(" ; ")
                .append(this.relacionCitaMedicoPaciente[index][2]);

        return sb.toString();
    }

    public String toStringCitasMedicas() {
        StringBuilder sb = new StringBuilder();
        if (this.numCitasMedicas < 0) {
            sb.append("No hay citas médicas");
        } else {
            for (int i = 0; i < this.numCitasMedicas; i++) {
                sb.append(i + 1)
                        .append(". ")
                        .append(this.relacionCitaMedicoPaciente[i][0])
                        .append(" ; ")
                        .append(this.relacionCitaMedicoPaciente[i][1])
                        .append(" ; ")
                        .append(this.relacionCitaMedicoPaciente[i][2])
                        .append('\n');
            }
        }
        return sb.toString();
    }
}
