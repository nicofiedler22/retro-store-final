package cl.duoc.login.security;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class JwtUtilTest {

    private final JwtUtil jwtUtil = new JwtUtil();

    @Test
    void shouldGenerateValidToken() {

        String correo = "admin@retrostore.cl";

        String token = jwtUtil.generateToken(correo);

        assertNotNull(token);
        assertTrue(jwtUtil.validateToken(token));
    }

    @Test
    void shouldReturnCorreoFromToken() {

        String correo = "admin@retrostore.cl";

        String token = jwtUtil.generateToken(correo);

        String correoExtraido = jwtUtil.getCorreoFromToken(token);

        assertEquals(correo, correoExtraido);
    }

    @Test
    void shouldRejectInvalidToken() {

        String tokenInvalido = "esto.no.es.un.jwt";

        assertFalse(jwtUtil.validateToken(tokenInvalido));
    }
}