package app.adapters.rest;

import app.adapters.rest.dto.ApiResponse;
import app.adapters.rest.dto.BillRequest;
import app.domain.models.Bill;
import app.domain.models.Order;
import app.domain.models.Pet;
import app.ports.BillPort;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.sql.Timestamp;
import java.util.List;

@RestController
@RequestMapping("/bills")
public class BillController {

    @Autowired
    private BillPort billPort;

    @PostMapping
    public ResponseEntity<ApiResponse<Bill>> createBill(@RequestBody BillRequest request) {
        try {
            Pet pet = null;
            if (request.getPetId() != null) {
                pet = new Pet();
                pet.setPetId(request.getPetId());
            }
            Order order = null;
            if (request.getOrderId() != null) {
                order = new Order();
                order.setOrderId(request.getOrderId());
            }
            Bill bill = new Bill();
            bill.setPetId(pet);
            bill.setOrderId(order);
            bill.setProductName(request.getProductName());
            bill.setValue(request.getValue());
            bill.setAmount(request.getAmount());
            bill.setDate(new Timestamp(System.currentTimeMillis()));

            billPort.save(bill);
            return ResponseEntity.status(HttpStatus.CREATED)
                .body(ApiResponse.created("Factura creada exitosamente", bill));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body(ApiResponse.serverError("Error al crear factura: " + e.getMessage()));
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse<Bill>> getBill(@PathVariable long id) {
        try {
            Bill bill = billPort.findByBillId(id);
            if (bill == null) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(ApiResponse.notFound("No se encontró la factura con ID: " + id));
            }
            return ResponseEntity.ok(ApiResponse.ok("Factura encontrada", bill));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body(ApiResponse.serverError("Error al buscar factura: " + e.getMessage()));
        }
    }

    @GetMapping("/pet/{petId}")
    public ResponseEntity<ApiResponse<List<Bill>>> getBillsByPet(@PathVariable long petId) {
        try {
            List<Bill> bills = billPort.findByPetId(petId);
            if (bills == null || bills.isEmpty()) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(ApiResponse.notFound("No se encontraron facturas para la mascota con ID: " + petId));
            }
            return ResponseEntity.ok(ApiResponse.ok("Facturas encontradas", bills));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body(ApiResponse.serverError("Error al buscar facturas: " + e.getMessage()));
        }
    }

    @GetMapping("/order/{orderId}")
    public ResponseEntity<ApiResponse<List<Bill>>> getBillsByOrder(@PathVariable long orderId) {
        try {
            List<Bill> bills = billPort.findByOrderId(orderId);
            if (bills == null || bills.isEmpty()) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(ApiResponse.notFound("No se encontraron facturas para la orden con ID: " + orderId));
            }
            return ResponseEntity.ok(ApiResponse.ok("Facturas encontradas", bills));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body(ApiResponse.serverError("Error al buscar facturas: " + e.getMessage()));
        }
    }
}
