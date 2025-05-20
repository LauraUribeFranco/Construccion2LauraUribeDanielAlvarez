package app.adapters.rest;

import app.adapters.rest.dto.ApiResponse;
import app.adapters.rest.dto.MedicalRecordRequest;
import app.domain.models.MedicalRecord;
import app.domain.models.Pet;
import app.domain.models.Order;
import app.domain.models.User;
import app.ports.MedicalRecordPort;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.sql.Timestamp;
import java.util.List;

@RestController
@RequestMapping("/medical-records")
public class MedicalRecordController {

    @Autowired
    private MedicalRecordPort medicalRecordPort;

    @PostMapping
    public ResponseEntity<ApiResponse<MedicalRecord>> createMedicalRecord(@RequestBody MedicalRecordRequest request) {
        try {
            Pet pet = new Pet();
            pet.setPetId(request.getPetId());
            User veterinarian = new User();
            veterinarian.setDocument(request.getVeterinarianDocument());
            Order order = null;
            if (request.getOrderId() != null) {
                order = new Order();
                order.setOrderId(request.getOrderId());
            }

            MedicalRecord record = new MedicalRecord();
            record.setPet(pet);
            record.setVeterinarian(veterinarian);
            record.setOrder(order);
            record.setDate(new Timestamp(System.currentTimeMillis()));
            record.setReason(request.getReason());
            record.setSymptomatology(request.getSymptomatology());
            record.setDiagnostic(request.getDiagnostic());
            record.setProcedure(request.getProcedure());
            record.setMedicine(request.getMedicine());
            record.setDose(request.getDose());
            record.setVaccination(request.getVaccination());
            record.setAllergyMedication(request.getAllergyMedication());
            record.setProcedureDetails(request.getProcedureDetails());

            medicalRecordPort.save(record);
            return ResponseEntity.status(HttpStatus.CREATED)
                .body(ApiResponse.created("Historia clínica creada exitosamente", record));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body(ApiResponse.serverError("Error al crear historia clínica: " + e.getMessage()));
        }
    }

    @GetMapping("/pet/{petId}")
    public ResponseEntity<ApiResponse<List<MedicalRecord>>> getRecordsByPet(@PathVariable long petId) {
        try {
            List<MedicalRecord> records = medicalRecordPort.findByPetId(petId);
            if (records == null || records.isEmpty()) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(ApiResponse.notFound("No se encontraron historias clínicas para la mascota con ID: " + petId));
            }
            return ResponseEntity.ok(ApiResponse.ok("Historias clínicas encontradas", records));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body(ApiResponse.serverError("Error al buscar historias clínicas: " + e.getMessage()));
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse<MedicalRecord>> getRecordById(@PathVariable long id) {
        try {
            MedicalRecord record = medicalRecordPort.findById(id);
            if (record == null) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(ApiResponse.notFound("No se encontró la historia clínica con ID: " + id));
            }
            return ResponseEntity.ok(ApiResponse.ok("Historia clínica encontrada", record));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body(ApiResponse.serverError("Error al buscar historia clínica: " + e.getMessage()));
        }
    }
}
