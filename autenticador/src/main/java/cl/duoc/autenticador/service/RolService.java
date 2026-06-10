package cl.duoc.autenticador.service;

import java.util.List;
import org.springframework.stereotype.Service;
import cl.duoc.autenticador.model.Rol;
import cl.duoc.autenticador.repository.RolRepository;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class RolService {

    private final RolRepository rolRepository;

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
