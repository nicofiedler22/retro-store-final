package cl.duoc.autenticador.service;

import cl.duoc.autenticador.model.Autenticador;
import cl.duoc.autenticador.repository.AutenticadorRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class AutenticadorService {

    private final AutenticadorRepository autenticadorRepository;

    public List<Autenticador> findAll() {
        return autenticadorRepository.findAll();
    }

    public Autenticador save(Autenticador autenticador) {
        return autenticadorRepository.save(autenticador);
    }

    public Autenticador findById(Long id) {
        return autenticadorRepository.findById(id).orElse(null);
    }

    public void deleteById(Long id) {
        autenticadorRepository.deleteById(id);
    }
}