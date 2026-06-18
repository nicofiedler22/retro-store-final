package cl.duoc.login.dto;

import lombok.Data;

@Data
public class AutenticadorUserDTO {

    private Long id;
    private String rut;
    private String nombre;
    private String apellido;
    private String correo;
    private String password;
    private RolDTO rol;

}