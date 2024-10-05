
package citas_consultorio;

import java.util.ArrayList;
import java.util.List;

public class Consultorio {
     private List<Doctor> doctores = new ArrayList<>();
    private List<Paciente> pacientes = new ArrayList<>();
    private List<Cita> citas = new ArrayList<>();

    public void agregarDoctor(Doctor doctor) {
        doctores.add(doctor);
      
    }

    public void agregarPaciente(Paciente paciente) {
        pacientes.add(paciente);
        
    }

    public void agendarCita(Cita cita) {
        citas.add(cita);
        
    }

    public List<Doctor> getDoctores() { return doctores; }
    public List<Paciente> getPacientes() { return pacientes; }
    public List<Cita> getCitas() { return citas; }
}
