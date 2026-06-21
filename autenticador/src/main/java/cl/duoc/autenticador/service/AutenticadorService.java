package cl.duoc.autenticador.service;

import cl.duoc.autenticador.model.Autenticador;
import cl.duoc.autenticador.repository.AutenticadorRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AutenticadorService {

    private final AutenticadorRepository autenticadorRepository;

    public AutenticadorService(AutenticadorRepository autenticadorRepository) {
        this.autenticadorRepository = autenticadorRepository;
    }

    public List<Autenticador> findAll() {
        return autenticadorRepository.findAll();
    }

    public Autenticador save(Autenticador autenticador) {
        return autenticadorRepository.save(autenticador);
    }

    public Autenticador findById(Long id) {
        return autenticadorRepository.findById(id).orElse(null);
    }

    public Autenticador buscarPorCorreo(String correo) {
        return autenticadorRepository.findByCorreo(correo)
                .orElseThrow(() -> new RuntimeException("Usuario no encontrado"));
    }

    public void deleteById(Long id) {
        autenticadorRepository.deleteById(id);
    }
}