package app.adapters.rest.dto;

import jakarta.validation.constraints.*;

public class PetRequest {
    @NotBlank(message = "El nombre es obligatorio")
    @Size(min = 2, max = 50, message = "El nombre debe tener entre 2 y 50 caracteres")
    private String name;

    @NotNull(message = "El documento del dueño es obligatorio")
    @Min(value = 1000000, message = "El documento debe ser un número válido mayor a 1000000")
    @Max(value = 9999999999L, message = "El documento no puede exceder los 10 dígitos")
    private long ownerDocument;

    @NotNull(message = "La edad es obligatoria")
    @Min(value = 0, message = "La edad no puede ser negativa")
    @Max(value = 30, message = "La edad debe ser un valor razonable para una mascota")
    private int age;

    @NotBlank(message = "La especie es obligatoria")
    @Pattern(regexp = "^(perro|gato|ave|pez|reptil|otro)$", message = "La especie debe ser: perro, gato, ave, pez, reptil u otro", flags = Pattern.Flag.CASE_INSENSITIVE)
    private String species;

    @Size(max = 50, message = "La raza debe tener máximo 50 caracteres")
    private String race;

    @NotNull(message = "El peso es obligatorio")
    @DecimalMin(value = "0.1", message = "El peso debe ser mayor a 0")
    @DecimalMax(value = "200.0", message = "El peso debe ser un valor razonable")
    private double weight;

    @Size(max = 200, message = "Las características deben tener máximo 200 caracteres")
    private String caracteristicas;

    // Getters y setters
    public String getName() { return name; }
    public void setName(String name) { this.name = name; }
    public long getOwnerDocument() { return ownerDocument; }
    public void setOwnerDocument(long ownerDocument) { this.ownerDocument = ownerDocument; }
    public int getAge() { return age; }
    public void setAge(int age) { this.age = age; }
    public String getSpecies() { return species; }
    public void setSpecies(String species) { this.species = species; }
    public String getRace() { return race; }
    public void setRace(String race) { this.race = race; }
    public double getWeight() { return weight; }
    public void setWeight(double weight) { this.weight = weight; }
    public String getCaracteristicas() { return caracteristicas; }
    public void setCaracteristicas(String caracteristicas) { this.caracteristicas = caracteristicas; }
}
