package cl.duoc.inventario.controller;

import cl.duoc.inventario.dto.ApiResponse;
import cl.duoc.inventario.dto.InventarioRequestDTO;
import cl.duoc.inventario.dto.InventarioResponseDTO;
import cl.duoc.inventario.service.InventarioService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/inventario")
@RequiredArgsConstructor

public class InventarioController {

    private final InventarioService inventarioService;

    @GetMapping
    public ResponseEntity<ApiResponse<List<InventarioResponseDTO>>> listarTodos() {
        return ResponseEntity.ok(new ApiResponse<>(200, "Lista de inventario", inventarioService.listarTodos()));
    }

    @GetMapping("/activos")
    public ResponseEntity<ApiResponse<List<InventarioResponseDTO>>> listarActivos() {
        return ResponseEntity.ok(new ApiResponse<>(200, "Inventario activo", inventarioService.listarActivos()));
    }

    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse<InventarioResponseDTO>> obtenerPorId(@PathVariable Long id) {
        return ResponseEntity.ok(new ApiResponse<>(200, "Inventario encontrado", inventarioService.obtenerPorId(id)));
    }

    @GetMapping("/producto/{productoId}")
    public ResponseEntity<ApiResponse<InventarioResponseDTO>> obtenerPorProducto(@PathVariable Long productoId) {
        return ResponseEntity.ok(new ApiResponse<>(200, "Inventario del producto", inventarioService.obtenerPorProducto(productoId)));
    }

    @GetMapping("/bajo-stock")
    public ResponseEntity<ApiResponse<List<InventarioResponseDTO>>> listarBajoStock() {
        return ResponseEntity.ok(new ApiResponse<>(200, "Productos bajo stock mínimo", inventarioService.listarBajoStock()));
    }

    @GetMapping("/buscar")
    public ResponseEntity<ApiResponse<List<InventarioResponseDTO>>> buscarPorNombre(@RequestParam String nombre) {
        return ResponseEntity.ok(new ApiResponse<>(200, "Resultados de búsqueda", inventarioService.buscarPorNombre(nombre)));
    }

    @PostMapping
    public ResponseEntity<ApiResponse<InventarioResponseDTO>> crear(@Valid @RequestBody InventarioRequestDTO dto) {
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(new ApiResponse<>(201, "Inventario creado exitosamente", inventarioService.crear(dto)));
    }

    @PutMapping("/{id}")
    public ResponseEntity<ApiResponse<InventarioResponseDTO>> actualizar(
            @PathVariable Long id, @Valid @RequestBody InventarioRequestDTO dto) {
        return ResponseEntity.ok(new ApiResponse<>(200, "Inventario actualizado exitosamente", inventarioService.actualizar(id, dto)));
    }

    @PatchMapping("/{id}/ajustar")
    public ResponseEntity<ApiResponse<InventarioResponseDTO>> ajustarCantidad(
            @PathVariable Long id, @RequestParam Integer cantidad) {
        return ResponseEntity.ok(new ApiResponse<>(200, "Stock ajustado exitosamente", inventarioService.ajustarCantidad(id, cantidad)));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse<String>> eliminar(@PathVariable Long id) {
        inventarioService.eliminar(id);
        return ResponseEntity.ok(new ApiResponse<>(200, "Inventario eliminado exitosamente", null));
    }

}
