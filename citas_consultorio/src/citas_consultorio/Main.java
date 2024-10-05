package citas_consultorio;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) throws IOException {
        HashMap<String, String> pacientes = null;
        Scanner scanner = new Scanner(System.in);
        Consultorio consultorio = new Consultorio();
        int idPaciente = 0;
        int idCita = 0;
        int idDoctor = 0;
        Administrador admin = new Administrador("admin", "1234");

        while (true) { // Bucle principal del menú
            System.out.println("'Bienvenido a sistema de citas medicas'");
            System.out.println("1.- Si eres Administrador presiona 1");
            System.out.println("2.- Si deseas agendar cita presiona 2");
            System.out.println("3.- Si deseas salir presiona 3");
            String tecla = scanner.nextLine();

            if (tecla.equals("1")) {
                // Lógica de autenticación de administrador
                System.out.print("Ingrese el identificador de administrador: ");
                String id = scanner.nextLine();
                System.out.print("Ingrese la contraseña: ");
                String pass = scanner.nextLine();

                if (admin.autentificar(id, pass)) {
                    System.out.println("Bienvenido al sistema de administracion de citas.");
                    while (true) { // Menu de administrador
                        System.out.println("1.- Si deseas dar de alta a un medico presiona 1:");
                        System.out.println("2.- Salir al menu principal presiona 2");
                        String opcion = scanner.nextLine();

                        if (opcion.equals("1")) {
                            
                            try {
                                String archivoDoctores = "./bd/doctores.csv";
                                String ultimoIdDoctor = Archivobd.obtenerUltimoId(archivoDoctores);

                                if (ultimoIdDoctor != null) {
                                    idDoctor = Integer.parseInt(ultimoIdDoctor) + 1; // Generar un nuevo id incrementando el último
                                } else {
                                    System.out.println("No hay doctores registrados. Eres el primero.");
                                    idDoctor = 1;
                                }
                            } catch (IOException e) {
                                e.printStackTrace();
                            }

                            System.out.println("Proporciona el nombre del médico:");
                            String nomMedico = scanner.nextLine();
                            System.out.println("Proporciona el nombre de la especialidad:");
                            String especialidad = scanner.nextLine();
                            Doctor doctor = new Doctor(String.valueOf(idDoctor), nomMedico, especialidad);

                            String archivoDoctor = "./bd/doctores.csv";
                            // Guardar el doctor en el archivo CSV
                            try {
                                Archivobd.escribirDoctorEnCSV(archivoDoctor, doctor);
                                System.out.println("Doctor guardado exitosamente.");
                            } catch (IOException e) {
                                System.err.println("Error al guardar el doctor: " + e.getMessage());
                            }
                        } else if (opcion.equals("2")) {
                            // Regresar al menú principal
                            break;
                        } else {
                            System.out.println("Opcion no valida. Intente nuevamente.");
                        }
                    }
                } else {
                    System.out.println("Acceso denegado.");
                }
            } else if (tecla.equals("2")) {
                // para citas
                try {
                    String archivoPacientes = "./bd/pacientes.csv";
                    String ultimoId = Archivobd.obtenerUltimoId(archivoPacientes);

                    if (ultimoId != null) {
                        idPaciente = Integer.parseInt(ultimoId) + 1; // Generar un nuevo id incrementando el último
                    } else {
                        System.out.println("No hay pacientes registrados. Eres el primero.");
                        idPaciente = 1;
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }

                Cita cita = new Cita();
                System.out.println("Ingresa tu nombre completo:");
                String nombrePaciente = scanner.nextLine();
                Paciente paciente = new Paciente(String.valueOf(idPaciente), nombrePaciente);
                cita.setIdpaciente(String.valueOf(idPaciente));

                String archivoPacientes = "./bd/pacientes.csv";
                // para guardar el paciente
                try {
                    Archivobd.escribirPacienteEnCSV(archivoPacientes, paciente);
                    System.out.println("Paciente guardado exitosamente.");
                } catch (IOException e) {
                    System.err.println("Error al guardar el paciente: " + e.getMessage());
                }

                String archivoDoctores = "./bd/doctores.csv";

                try {
                    // ver doctores
                    List<Doctor> doctores = Archivobd.leerDoctoresDesdeCSV(archivoDoctores);
                    
                    System.out.println("De la siguiente lista de doctores, ingresa el ID del doctor para la cita medica:");
                    System.out.println(Archivobd.listaDoctoresACadena(doctores));
                   String idDoc = scanner.nextLine();
                    cita.setIdDoctor(idDoc);
                    System.out.println("Ingresa el motivo de la cita:");
                    String motivo = scanner.nextLine();
                    cita.setMotivo(motivo);
                    System.out.println("Ingresa la fecha en el siguiente formato dd-mm-aa:");
                    String fecha = scanner.nextLine();
                    cita.setFecha(fecha);
                    System.out.println("Ingresa la hora en el siguiente formato 00:00:");
                    String hora = scanner.nextLine();
                    cita.setHora(hora);
                    String archivoCitas = "./bd/citas.csv";
                    String ultimoIdCitas = Archivobd.obtenerUltimoId(archivoCitas);
                    if (ultimoIdCitas != null) {
                        idCita = Integer.parseInt(ultimoIdCitas) + 1; 
                    } else {
                        idCita = 1;
                    }
                    cita.setId(String.valueOf(idCita));
                    Archivobd.guardarCitaEnCSV(archivoCitas, cita);
                    System.out.println("Cita guardada exitosamente.");
                } catch (IOException e) {
                    System.err.println("Error al leer el archivo de doctores: " + e.getMessage());
                }
            } else if (tecla.equals("3")) {
                // para salir
                System.out.println("Saliendo del sistema. ¡Adios!");
                break;
            } else {
                System.out.println("Opcion no valida. Intente nuevamente.");
            }
        }
    }
}
