package app.adapters.rest.dto;

import jakarta.validation.constraints.*;

public class OrderRequest {
    @NotNull(message = "El ID de la mascota es obligatorio")
    @Min(value = 1, message = "El ID de la mascota debe ser un número válido positivo")
    private long petId;

    @NotNull(message = "El documento del dueño es obligatorio")
    @Min(value = 1000000, message = "El documento debe ser un número válido mayor a 1000000")
    @Max(value = 9999999999L, message = "El documento no puede exceder los 10 dígitos")
    private long ownerDocument;

    @NotNull(message = "El documento del veterinario es obligatorio")
    @Min(value = 1000000, message = "El documento debe ser un número válido mayor a 1000000")
    @Max(value = 9999999999L, message = "El documento no puede exceder los 10 dígitos")
    private long veterinarianDocument;

    @NotBlank(message = "El medicamento es obligatorio")
    @Size(min = 2, max = 100, message = "El nombre del medicamento debe tener entre 2 y 100 caracteres")
    private String medicine;

    @NotBlank(message = "La dosis es obligatoria")
    @Size(min = 2, max = 100, message = "La dosis debe tener entre 2 y 100 caracteres")
    private String dose;

    // Getters y setters
    public long getPetId() { return petId; }
    public void setPetId(long petId) { this.petId = petId; }
    public long getOwnerDocument() { return ownerDocument; }
    public void setOwnerDocument(long ownerDocument) { this.ownerDocument = ownerDocument; }
    public long getVeterinarianDocument() { return veterinarianDocument; }
    public void setVeterinarianDocument(long veterinarianDocument) { this.veterinarianDocument = veterinarianDocument; }
    public String getMedicine() { return medicine; }
    public void setMedicine(String medicine) { this.medicine = medicine; }
    public String getDose() { return dose; }
    public void setDose(String dose) { this.dose = dose; }
}
