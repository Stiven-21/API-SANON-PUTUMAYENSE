package backend.sasonptumayense.security;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import backend.sasonptumayense.Controllers.DetallesPedidoController;
import backend.sasonptumayense.config.GlobalMappingConfig;
import backend.sasonptumayense.response.ApiResponse;
import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
public class DetallePedidoSecurity extends GlobalMappingConfig {
    private final DetallesPedidoController detallesPedidoController;

    @GetMapping("/detalles-pedidos")
    public ResponseEntity<ApiResponse> getDetallesPedidos(
        @RequestParam(name = "pedidoId", required = false) String pedidoId, 
        @RequestParam(name = "menuId", required = false) String menuId) {
        return detallesPedidoController.getDetallesPedidos(pedidoId, menuId);
    }
}
