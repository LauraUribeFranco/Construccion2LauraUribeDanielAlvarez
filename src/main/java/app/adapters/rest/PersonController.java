package app.adapters.rest;

import app.adapters.rest.dto.ApiResponse;
import app.adapters.rest.dto.PersonRequest;
import app.domain.models.Person;
import app.ports.PersonPort;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/owners")
public class PersonController {

    @Autowired
    private PersonPort personPort;

    @PostMapping
    public ResponseEntity<ApiResponse<Person>> createOwner(@Valid @RequestBody PersonRequest request) {
        try {
            Person person = new Person(request.getDocument(), request.getName(), request.getAge(), "Owner");
            personPort.save(person);
            return ResponseEntity.status(HttpStatus.CREATED)
                .body(ApiResponse.created("Dueño creado exitosamente", person));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body(ApiResponse.serverError("Error al crear dueño: " + e.getMessage()));
        }
    }

    @GetMapping("/{document}")
    public ResponseEntity<ApiResponse<Person>> getOwner(@PathVariable long document) {
        try {
            Person person = personPort.findByPersonId(document);
            if (person == null) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(ApiResponse.notFound("No se encontró el dueño con documento: " + document));
            }
            return ResponseEntity.ok(ApiResponse.ok("Dueño encontrado", person));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body(ApiResponse.serverError("Error al buscar dueño: " + e.getMessage()));
        }
    }
}
