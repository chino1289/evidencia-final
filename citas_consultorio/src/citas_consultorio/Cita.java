package citas_consultorio;

import java.time.LocalDateTime;

public class Cita {

    private String id_cita;
    private String fecha;
    private String hora;
    private String motivo;
    private String id_doctor;
    private String id_paciente;

    public Cita() {

    }

    public Cita(String id, String fecha, String hora, String motivo, String doctor, String paciente) {
        this.id_cita = id;
        this.fecha = fecha;
        this.hora = hora;
        this.motivo = motivo;
        this.id_doctor = doctor;
        this.id_paciente = paciente;
    }

    public String getId() {
        return id_cita;
    }

    public void setId(String id) {
        this.id_cita = id;
    }

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha= fecha;
    }

    public String getHora() {
        return hora;
    }

    public void setHora(String hora) {
        this.hora= hora;
    }
    
    public String getMotivo() {
        return motivo;
    }

    public void setMotivo(String motivo) {
        this.motivo = motivo;
    }

    public String getIdDoctor() {
        return id_doctor;
    }

    public void setIdDoctor(String idDoc) {
        this.id_doctor = idDoc;
    }

    public String getIdpaciente() {
        return id_paciente;
    }

    public void setIdpaciente(String idpaciente) {
        this.id_paciente = idpaciente;
    }

    
       public String toCSV() {
        return id_cita + "," +fecha+","+hora+","+motivo+","+id_doctor+","+id_paciente;
    }
    
}
