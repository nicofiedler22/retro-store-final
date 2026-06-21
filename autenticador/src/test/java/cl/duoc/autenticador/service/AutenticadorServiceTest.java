package cl.duoc.autenticador.service;

import cl.duoc.autenticador.model.Autenticador;
import cl.duoc.autenticador.repository.AutenticadorRepository;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class AutenticadorServiceTest {

    @Test
    void deberiaListarAutenticadores() {
        AutenticadorRepository repository = mock(AutenticadorRepository.class);
        AutenticadorService service = new AutenticadorService(repository);

        when(repository.findAll()).thenReturn(List.of(new Autenticador()));

        List<Autenticador> resultado = service.findAll();

        assertNotNull(resultado);
        assertEquals(1, resultado.size());
        verify(repository).findAll();
    }
}