package app.adapters.rest.dto;

import jakarta.validation.constraints.*;

public class PersonRequest {
    @NotNull(message = "El documento es obligatorio")
    @Min(value = 1000000, message = "El documento debe ser un número válido mayor a 1000000")
    @Max(value = 9999999999L, message = "El documento no puede exceder los 10 dígitos")
    private long document;

    @NotBlank(message = "El nombre es obligatorio")
    @Size(min = 2, max = 100, message = "El nombre debe tener entre 2 y 100 caracteres")
    @Pattern(regexp = "^[a-zA-ZáéíóúÁÉÍÓÚñÑ\\s]+$", message = "El nombre solo debe contener letras y espacios")
    private String name;

    @NotNull(message = "La edad es obligatoria")
    @Min(value = 0, message = "La edad no puede ser negativa")
    @Max(value = 120, message = "La edad no puede ser mayor a 120 años")
    private int age;

    // Getters y setters
    public long getDocument() { return document; }
    public void setDocument(long document) { this.document = document; }
    public String getName() { return name; }
    public void setName(String name) { this.name = name; }
    public int getAge() { return age; }
    public void setAge(int age) { this.age = age; }
}
