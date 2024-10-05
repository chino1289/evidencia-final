package citas_consultorio;

public class Administrador implements UsuarioAutentificacion {

    private String identificador;
    private String contraseña;

    public Administrador(String identificador, String contraseña) {
        this.identificador = identificador;
        this.contraseña = contraseña;
    }

    @Override
    public boolean autentificar(String identificador, String contraseña) {
        return this.identificador.equals(identificador) && this.contraseña.equals(contraseña);
    }

}
