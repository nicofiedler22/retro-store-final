package cl.duoc.login.dto;

public class AutenticadorUserDTO {

    private Long id;
    private String rut;
    private String nombre;
    private String apellido;
    private String correo;
    private String password;
    private RolDTO rol;

    public AutenticadorUserDTO() {
    }

    public Long getId() {
        return id;
    }

    public String getRut() {
        return rut;
    }

    public String getNombre() {
        return nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public String getCorreo() {
        return correo;
    }

    public String getPassword() {
        return password;
    }

    public RolDTO getRol() {
        return rol;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setRut(String rut) {
        this.rut = rut;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setRol(RolDTO rol) {
        this.rol = rol;
    }
}