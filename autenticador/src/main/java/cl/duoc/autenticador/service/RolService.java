package cl.duoc.autenticador.service;

import cl.duoc.autenticador.model.Rol;
import cl.duoc.autenticador.repository.RolRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RolService {

    private final RolRepository rolRepository;

    public RolService(RolRepository rolRepository) {
        this.rolRepository = rolRepository;
    }

    public List<Rol> findAll() {
        return rolRepository.findAll();
    }

    public Rol save(Rol rol) {
        return rolRepository.save(rol);
    }

    public Rol findById(Long id) {
        return rolRepository.findById(id).orElse(null);
    }

    public void deleteById(Long id) {
        rolRepository.deleteById(id);
    }
}