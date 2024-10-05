package citas_consultorio;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class Archivobd {

    public static void escribirCSV(String archivo, List<String> lineas) throws IOException {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(archivo, true))) {
            for (String linea : lineas) {
                writer.write(linea);
                writer.newLine();
            }
        }

    }

    public static List<String> leerCSV(String archivo) throws IOException {
        List<String> lineas = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(archivo))) {
            String linea;
            while ((linea = reader.readLine()) != null) {
                lineas.add(linea);
            }
        }
        return lineas;
    }

    public static void escribirPacienteEnCSV(String archivo, Paciente paciente) throws IOException {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(archivo, true))) {
            writer.write(paciente.toCSV());
            writer.newLine(); 
        }
    }

    public static void escribirDoctorEnCSV(String archivo, Doctor doctor) throws IOException {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(archivo, true))) {
            writer.write(doctor.toCSV());
            writer.newLine(); 
        }
    }
    
     public static void guardarCitaEnCSV(String archivo, Cita cita) throws IOException {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(archivo, true))) {
            writer.write(cita.toCSV());
            writer.newLine(); 
        }
    }
    public static String obtenerUltimoId(String archivo) throws IOException {
        String ultimaLinea = null;

        try (BufferedReader reader = new BufferedReader(new FileReader(archivo))) {
            String linea;
            boolean primeraLinea = true;

            while ((linea = reader.readLine()) != null) {
                
                if (primeraLinea) {
                    primeraLinea = false;
                    continue;
                }
                ultimaLinea = linea; // guarda la ultima linea 
            }
        }

        // Si el archivo no está vacío, obtener el primer valor (ID) de la última línea
        if (ultimaLinea != null && !ultimaLinea.trim().isEmpty()) {
            String[] valores = ultimaLinea.split(",");
            return valores[0]; // El ID está en la primera posición
        }

        return null; // Si el archivo está vacío
    }

    public static List<Doctor> leerDoctoresDesdeCSV(String archivo) throws IOException {
        List<Doctor> doctores = new ArrayList<>();

        // 
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(new FileInputStream(archivo), "UTF-8"))) {
            String linea;
            boolean primeraLinea = true;

            while ((linea = reader.readLine()) != null) {
                // 
                if (primeraLinea) {
                    primeraLinea = false;
                    continue;
                }

                // 
                String[] valores = linea.split(",");

                // 
                if (valores.length >= 3) { 
                    String id = valores[0];
                    String nombreCompleto = valores[1];
                    String especialidad = valores[2];
                    Doctor doctor = new Doctor(id, nombreCompleto, especialidad);
                    doctores.add(doctor);
                }
            }
        }
        return doctores;
    }

    
    public static String listaDoctoresACadena(List<Doctor> doctores) {
        StringBuilder sb = new StringBuilder();

        
        for (Doctor doctor : doctores) {
            sb.append(doctor.toString()); 
            sb.append("\n"); 
        }

        return sb.toString();
    }

}
