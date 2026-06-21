package cl.duoc.wishlist.controller;

import cl.duoc.wishlist.dto.ApiResponse;
import cl.duoc.wishlist.dto.WishlistRequestDTO;
import cl.duoc.wishlist.dto.WishlistResponseDTO;
import cl.duoc.wishlist.service.WishlistService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/wishlist")
public class WishlistController {

    private final WishlistService wishlistService;

    @GetMapping("/usuario/{usuarioId}")
    public ResponseEntity<ApiResponse<List<WishlistResponseDTO>>> listarPorUsuario(
            @PathVariable Long usuarioId) {
        return ResponseEntity.ok(
                new ApiResponse<>(200, "Wishlist del usuario", wishlistService.listarPorUsuario(usuarioId)));
    }

    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse<WishlistResponseDTO>> obtenerPorId(@PathVariable Long id) {
        return ResponseEntity.ok(
                new ApiResponse<>(200, "Item encontrado", wishlistService.obtenerPorId(id)));
    }

    @GetMapping("/usuario/{usuarioId}/existe/{productoId}")
    public ResponseEntity<ApiResponse<Boolean>> existeEnWishlist(
            @PathVariable Long usuarioId, @PathVariable Long productoId) {
        boolean existe = wishlistService.existeEnWishlist(usuarioId, productoId);
        return ResponseEntity.ok(new ApiResponse<>(200, "Verificación realizada", existe));
    }

    @PostMapping
    public ResponseEntity<ApiResponse<WishlistResponseDTO>> agregar(
            @Valid @RequestBody WishlistRequestDTO dto) {
        WishlistResponseDTO item = wishlistService.agregar(dto);
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(new ApiResponse<>(201, "Producto agregado a la wishlist", item));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse<String>> eliminar(@PathVariable Long id) {
        wishlistService.eliminar(id);
        return ResponseEntity.ok(new ApiResponse<>(200, "Item eliminado de la wishlist", null));
    }

    @DeleteMapping("/usuario/{usuarioId}/producto/{productoId}")
    public ResponseEntity<ApiResponse<String>> eliminarPorUsuarioYProducto(
            @PathVariable Long usuarioId, @PathVariable Long productoId) {
        wishlistService.eliminarPorUsuarioYProducto(usuarioId, productoId);
        return ResponseEntity.ok(new ApiResponse<>(200, "Producto eliminado de la wishlist", null));
    }

}
