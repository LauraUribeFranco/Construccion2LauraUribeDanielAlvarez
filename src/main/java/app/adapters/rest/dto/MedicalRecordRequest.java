package app.adapters.rest.dto;

import jakarta.validation.constraints.*;

public class MedicalRecordRequest {
    @NotNull(message = "El ID de la mascota es obligatorio")
    @Min(value = 1, message = "El ID de la mascota debe ser un número válido positivo")
    private long petId;

    @Min(value = 1, message = "El ID de la orden debe ser un número válido positivo")
    private Long orderId;

    @NotNull(message = "El documento del veterinario es obligatorio")
    @Min(value = 1000000, message = "El documento debe ser un número válido mayor a 1000000")
    @Max(value = 9999999999L, message = "El documento no puede exceder los 10 dígitos")
    private long veterinarianDocument;

    @NotBlank(message = "La razón de la visita es obligatoria")
    @Size(min = 5, max = 200, message = "La razón debe tener entre 5 y 200 caracteres")
    private String reason;

    @NotBlank(message = "La sintomatología es obligatoria")
    @Size(min = 5, max = 500, message = "La sintomatología debe tener entre 5 y 500 caracteres")
    private String symptomatology;

    @NotBlank(message = "El diagnóstico es obligatorio")
    @Size(min = 5, max = 500, message = "El diagnóstico debe tener entre 5 y 500 caracteres")
    private String diagnostic;

    @Size(max = 500, message = "El procedimiento debe tener máximo 500 caracteres")
    private String procedure;

    @Size(max = 100, message = "El medicamento debe tener máximo 100 caracteres")
    private String medicine;

    @Size(max = 100, message = "La dosis debe tener máximo 100 caracteres")
    private String dose;

    @Size(max = 200, message = "La vacunación debe tener máximo 200 caracteres")
    private String vaccination;

    @Size(max = 200, message = "Las alergias a medicamentos deben tener máximo 200 caracteres")
    private String allergyMedication;

    @Size(max = 500, message = "Los detalles del procedimiento deben tener máximo 500 caracteres")
    private String procedureDetails;

    // Getters y setters
    public long getPetId() { return petId; }
    public void setPetId(long petId) { this.petId = petId; }
    public Long getOrderId() { return orderId; }
    public void setOrderId(Long orderId) { this.orderId = orderId; }
    public long getVeterinarianDocument() { return veterinarianDocument; }
    public void setVeterinarianDocument(long veterinarianDocument) { this.veterinarianDocument = veterinarianDocument; }
    public String getReason() { return reason; }
    public void setReason(String reason) { this.reason = reason; }
    public String getSymptomatology() { return symptomatology; }
    public void setSymptomatology(String symptomatology) { this.symptomatology = symptomatology; }
    public String getDiagnostic() { return diagnostic; }
    public void setDiagnostic(String diagnostic) { this.diagnostic = diagnostic; }
    public String getProcedure() { return procedure; }
    public void setProcedure(String procedure) { this.procedure = procedure; }
    public String getMedicine() { return medicine; }
    public void setMedicine(String medicine) { this.medicine = medicine; }
    public String getDose() { return dose; }
    public void setDose(String dose) { this.dose = dose; }
    public String getVaccination() { return vaccination; }
    public void setVaccination(String vaccination) { this.vaccination = vaccination; }
    public String getAllergyMedication() { return allergyMedication; }
    public void setAllergyMedication(String allergyMedication) { this.allergyMedication = allergyMedication; }
    public String getProcedureDetails() { return procedureDetails; }
    public void setProcedureDetails(String procedureDetails) { this.procedureDetails = procedureDetails; }
}
