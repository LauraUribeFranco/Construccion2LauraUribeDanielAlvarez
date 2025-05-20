package app.adapters.rest;

import app.adapters.rest.dto.ApiResponse;
import app.adapters.rest.request.UserRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AdminController {
    
    @GetMapping("/")
    public ResponseEntity<ApiResponse<String>> getAdmin() {
        return ResponseEntity.ok(ApiResponse.ok("Página de administración cargada correctamente", "Admin page"));
    }

    @PostMapping("/user")
    public ResponseEntity<ApiResponse<String>> createAdmin(@Validated UserRequest userRequest) {
        try {
            // Aquí iría la lógica para crear un administrador
            return ResponseEntity.status(HttpStatus.CREATED)
                .body(ApiResponse.created("Administrador creado exitosamente", "Exito"));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body(ApiResponse.serverError("Error al crear administrador: " + e.getMessage()));
        }
    }
}
