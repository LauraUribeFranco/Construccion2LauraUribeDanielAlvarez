package app.adapters.rest.dto;

import jakarta.validation.constraints.*;

public class CancelOrderRequest {
    @NotNull(message = "El documento del veterinario es obligatorio")
    @Min(value = 1000000, message = "El documento debe ser un número válido mayor a 1000000")
    @Max(value = 9999999999L, message = "El documento no puede exceder los 10 dígitos")
    private long veterinarianDocument;

    // Getter y setter
    public long getVeterinarianDocument() { return veterinarianDocument; }
    public void setVeterinarianDocument(long veterinarianDocument) { this.veterinarianDocument = veterinarianDocument; }
}
