package app.adapters.rest.dto;

import jakarta.validation.constraints.*;

public class UserRequest {
    @NotNull(message = "El documento es obligatorio")
    @Min(value = 1000000, message = "El documento debe ser un número válido mayor a 1000000")
    @Max(value = 9999999999L, message = "El documento no puede exceder los 10 dígitos")
    private long document;

    @NotBlank(message = "El nombre es obligatorio")
    @Size(min = 2, max = 100, message = "El nombre debe tener entre 2 y 100 caracteres")
    @Pattern(regexp = "^[a-zA-ZáéíóúÁÉÍÓÚñÑ\\s]+$", message = "El nombre solo debe contener letras y espacios")
    private String name;

    @NotNull(message = "La edad es obligatoria")
    @Min(value = 18, message = "La edad no puede ser menor de 18 años")
    @Max(value = 120, message = "La edad no puede ser mayor a 120 años")
    private int age;

    @NotBlank(message = "El nombre de usuario es obligatorio")
    @Size(min = 4, max = 20, message = "El nombre de usuario debe tener entre 4 y 20 caracteres")
    @Pattern(regexp = "^[a-zA-Z0-9_]+$", message = "El nombre de usuario solo debe contener letras, números y guiones bajos")
    private String userName;

    @NotBlank(message = "La contraseña es obligatoria")
    @Size(min = 6, message = "La contraseña debe tener al menos 6 caracteres")
    @Pattern(regexp = "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z]).*$", message = "La contraseña debe contener al menos un número, una letra minúscula y una letra mayúscula")
    private String password;

    @NotBlank(message = "El rol es obligatorio")
    @Pattern(regexp = "^(Administrator|Veterinarian|Seller)$", message = "El rol debe ser: Administrator, Veterinarian o Seller")
    private String role;

    // Getters y setters
    public long getDocument() { return document; }
    public void setDocument(long document) { this.document = document; }
    public String getName() { return name; }
    public void setName(String name) { this.name = name; }
    public int getAge() { return age; }
    public void setAge(int age) { this.age = age; }
    public String getUserName() { return userName; }
    public void setUserName(String userName) { this.userName = userName; }
    public String getPassword() { return password; }
    public void setPassword(String password) { this.password = password; }
    public String getRole() { return role; }
    public void setRole(String role) { this.role = role; }
}
