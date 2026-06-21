package cl.duoc.inventario.service;

import cl.duoc.inventario.dto.InventarioRequestDTO;
import cl.duoc.inventario.dto.InventarioResponseDTO;
import cl.duoc.inventario.model.Inventario;
import cl.duoc.inventario.repository.InventarioRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;

@Service
@RequiredArgsConstructor
public class InventarioService {

    private final InventarioRepository inventarioRepository;

    @Transactional(readOnly = true)
    public List<InventarioResponseDTO> listarTodos() {
        return inventarioRepository.findAll().stream()
                .map(this::toResponseDTO)
                .toList();
    }

    @Transactional(readOnly = true)
    public List<InventarioResponseDTO> listarActivos() {
        return inventarioRepository.findByActivoTrue().stream()
                .map(this::toResponseDTO)
                .toList();
    }

    @Transactional(readOnly = true)
    public InventarioResponseDTO obtenerPorId(Long id) {
        return inventarioRepository.findById(id)
                .map(this::toResponseDTO)
                .orElseThrow(() -> new EntityNotFoundException("Inventario no encontrado con id: " + id));
    }

    @Transactional(readOnly = true)
    public InventarioResponseDTO obtenerPorProducto(Long productoId) {
        return inventarioRepository.findByProductoId(productoId)
                .map(this::toResponseDTO)
                .orElseThrow(() -> new EntityNotFoundException("Inventario no encontrado para productoId: " + productoId));
    }

    @Transactional(readOnly = true)
    public List<InventarioResponseDTO> listarBajoStock() {
        return inventarioRepository.findBajoStockMinimo().stream()
                .map(this::toResponseDTO)
                .toList();
    }

    @Transactional(readOnly = true)
    public List<InventarioResponseDTO> buscarPorNombre(String nombre) {
        return inventarioRepository.findByNombreContainingIgnoreCase(nombre).stream()
                .map(this::toResponseDTO)
                .toList();
    }

    @Transactional
    public InventarioResponseDTO crear(InventarioRequestDTO dto) {
        Inventario inventario = Inventario.builder()
                .productoId(dto.getProductoId())
                .nombre(dto.getNombre())
                .cantidad(dto.getCantidad())
                .stockMinimo(dto.getStockMinimo())
                .ubicacion(dto.getUbicacion())
                .activo(dto.getActivo() != null ? dto.getActivo() : true)
                .build();
        return toResponseDTO(inventarioRepository.save(inventario));
    }

    @Transactional
    public InventarioResponseDTO actualizar(Long id, InventarioRequestDTO dto) {
        Inventario inventario = inventarioRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Inventario no encontrado con id: " + id));
        inventario.setNombre(dto.getNombre());
        inventario.setCantidad(dto.getCantidad());
        inventario.setStockMinimo(dto.getStockMinimo());
        inventario.setUbicacion(dto.getUbicacion());
        inventario.setActivo(dto.getActivo());
        return toResponseDTO(inventarioRepository.save(inventario));
    }

    @Transactional
    public InventarioResponseDTO ajustarCantidad(Long id, Integer cantidad) {
        Inventario inventario = inventarioRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Inventario no encontrado con id: " + id));
        int nuevaCantidad = inventario.getCantidad() + cantidad;
        if (nuevaCantidad < 0) {
            throw new IllegalArgumentException("Stock insuficiente. Stock actual: " + inventario.getCantidad());
        }
        inventario.setCantidad(nuevaCantidad);
        return toResponseDTO(inventarioRepository.save(inventario));
    }

    @Transactional
    public void eliminar(Long id) {
        if (!inventarioRepository.existsById(id)) {
            throw new EntityNotFoundException("Inventario no encontrado con id: " + id);
        }
        inventarioRepository.deleteById(id);
    }

    private InventarioResponseDTO toResponseDTO(Inventario i) {
        return InventarioResponseDTO.builder()
                .id(i.getId())
                .productoId(i.getProductoId())
                .nombre(i.getNombre())
                .cantidad(i.getCantidad())
                .stockMinimo(i.getStockMinimo())
                .ubicacion(i.getUbicacion())
                .activo(i.getActivo())
                .bajoPorStockMinimo(i.getCantidad() <= i.getStockMinimo())
                .fechaActualizacion(i.getFechaActualizacion())
                .build();
    }
}