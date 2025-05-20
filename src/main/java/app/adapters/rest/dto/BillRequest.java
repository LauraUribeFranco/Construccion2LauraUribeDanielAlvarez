package app.adapters.rest.dto;

import jakarta.validation.constraints.*;

public class BillRequest {
    @Min(value = 1, message = "El ID de la mascota debe ser un número válido positivo")
    private Long petId;

    @Min(value = 1, message = "El ID de la orden debe ser un número válido positivo")
    private Long orderId;

    @NotBlank(message = "El nombre del producto es obligatorio")
    @Size(min = 2, max = 100, message = "El nombre del producto debe tener entre 2 y 100 caracteres")
    private String productName;

    @NotNull(message = "El valor es obligatorio")
    @DecimalMin(value = "0.1", message = "El valor debe ser mayor a 0")
    @DecimalMax(value = "10000000.0", message = "El valor debe ser razonable")
    private double value;

    @NotNull(message = "La cantidad es obligatoria")
    @Min(value = 1, message = "La cantidad debe ser al menos 1")
    @Max(value = 1000, message = "La cantidad no puede exceder 1000")
    private int amount;

    // Getters y setters
    public Long getPetId() { return petId; }
    public void setPetId(Long petId) { this.petId = petId; }
    public Long getOrderId() { return orderId; }
    public void setOrderId(Long orderId) { this.orderId = orderId; }
    public String getProductName() { return productName; }
    public void setProductName(String productName) { this.productName = productName; }
    public double getValue() { return value; }
    public void setValue(double value) { this.value = value; }
    public int getAmount() { return amount; }
    public void setAmount(int amount) { this.amount = amount; }
}
