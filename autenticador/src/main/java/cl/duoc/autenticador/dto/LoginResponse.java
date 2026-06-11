package cl.duoc.autenticador.dto;

import lombok.Data;

@Data
public class LoginResponse {
    private int code;
    private String message;
    private String data;
}