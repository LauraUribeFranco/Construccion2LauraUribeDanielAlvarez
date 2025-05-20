package app.adapters.rest;

import app.adapters.rest.dto.ApiResponse;
import app.adapters.rest.dto.LoginRequest;
import app.adapters.rest.dto.UserRequest;
import app.domain.models.User;
import app.domain.services.AdministratorService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/users")
public class UserController {

    @Autowired
    private AdministratorService administratorService;

    @PostMapping
    public ResponseEntity<ApiResponse<User>> registerUser(@Valid @RequestBody UserRequest request) {
        try {
            User user = new User();
            user.setDocument(request.getDocument());
            user.setName(request.getName());
            user.setAge(request.getAge());
            user.setUserName(request.getUserName());
            user.setPassword(request.getPassword());
            user.setRole(request.getRole());
            
            // Registrar según el rol
            if ("Veterinarian".equalsIgnoreCase(user.getRole())) {
                administratorService.createVeterinarian(user);
            } else if ("Seller".equalsIgnoreCase(user.getRole())) {
                administratorService.createSeller(user);
            } else {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body(ApiResponse.conflict("Rol no soportado"));
            }
            return ResponseEntity.status(HttpStatus.CREATED)
                .body(ApiResponse.created("Usuario registrado exitosamente", user));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body(ApiResponse.serverError("Error al registrar usuario: " + e.getMessage()));
        }
    }

    @PostMapping("/login")
    public ResponseEntity<ApiResponse<User>> login(@Valid @RequestBody LoginRequest request) {
        try {
            User user = administratorService.authenticateUser(request.getUserName(), request.getPassword());
            if (user == null) {
                return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                    .body(ApiResponse.unauthorized("Credenciales incorrectas"));
            }
            return ResponseEntity.ok(ApiResponse.ok("Inicio de sesión exitoso", user));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body(ApiResponse.serverError("Error al iniciar sesión: " + e.getMessage()));
        }
    }
}
