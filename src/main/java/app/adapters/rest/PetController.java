package app.adapters.rest;

import app.adapters.rest.dto.ApiResponse;
import app.adapters.rest.dto.PetRequest;
import app.domain.models.Pet;
import app.domain.models.Person;
import app.ports.PetPort;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/pets")
public class PetController {

    @Autowired
    private PetPort petPort;

    @PostMapping
    public ResponseEntity<ApiResponse<Pet>> createPet(@RequestBody PetRequest request) {
        try {
            Person owner = new Person();
            owner.setDocument(request.getOwnerDocument());
            Pet pet = new Pet();
            pet.setName(request.getName());
            pet.setOwner(owner);
            pet.setAge(request.getAge());
            pet.setSpecies(request.getSpecies());
            pet.setRace(request.getRace());
            pet.setWeight(request.getWeight());
            pet.setCaracteristicas(request.getCaracteristicas());
            petPort.save(pet);
            return ResponseEntity.status(HttpStatus.CREATED)
                .body(ApiResponse.created("Mascota registrada exitosamente", pet));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body(ApiResponse.serverError("Error al registrar mascota: " + e.getMessage()));
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse<Pet>> getPet(@PathVariable long id) {
        try {
            Pet pet = petPort.findByPetId(id);
            if (pet == null) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(ApiResponse.notFound("No se encontró la mascota con ID: " + id));
            }
            return ResponseEntity.ok(ApiResponse.ok("Mascota encontrada", pet));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body(ApiResponse.serverError("Error al buscar mascota: " + e.getMessage()));
        }
    }
}
