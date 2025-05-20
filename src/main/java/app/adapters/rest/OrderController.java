package app.adapters.rest;

import app.adapters.rest.dto.ApiResponse;
import app.adapters.rest.dto.CancelOrderRequest;
import app.adapters.rest.dto.OrderRequest;
import app.domain.models.Order;
import app.domain.models.Person;
import app.domain.models.Pet;
import app.domain.models.User;
import app.ports.OrderPort;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.sql.Timestamp;

@RestController
@RequestMapping("/orders")
public class OrderController {

    @Autowired
    private OrderPort orderPort;

    @PostMapping
    public ResponseEntity<ApiResponse<Order>> createOrder(@RequestBody OrderRequest request) {
        try {
            Pet pet = new Pet();
            pet.setPetId(request.getPetId());
            Person owner = new Person();
            owner.setDocument(request.getOwnerDocument());
            User veterinarian = new User();
            veterinarian.setDocument(request.getVeterinarianDocument());

            Order order = new Order();
            order.setPet(pet);
            order.setOwner(owner);
            order.setVeterinarian(veterinarian);
            order.setMedicine(request.getMedicine());
            order.setDose(request.getDose());
            order.setDate(new Timestamp(System.currentTimeMillis()));
            order.setCancelled(false);

            orderPort.save(order);
            return ResponseEntity.status(HttpStatus.CREATED)
                .body(ApiResponse.created("Orden médica creada exitosamente", order));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body(ApiResponse.serverError("Error al crear orden médica: " + e.getMessage()));
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse<Order>> getOrder(@PathVariable long id) {
        try {
            Order order = orderPort.findByOrderId(id);
            if (order == null) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(ApiResponse.notFound("No se encontró la orden con ID: " + id));
            }
            return ResponseEntity.ok(ApiResponse.ok("Orden encontrada", order));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body(ApiResponse.serverError("Error al buscar orden: " + e.getMessage()));
        }
    }

    @PostMapping("/{id}/cancel")
    public ResponseEntity<ApiResponse<Order>> cancelOrder(@PathVariable long id, @RequestBody CancelOrderRequest request) {
        try {
            Order order = orderPort.findByOrderId(id);
            if (order == null) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(ApiResponse.notFound("No se encontró la orden con ID: " + id));
            }
            
            if (order.isCancelled()) {
                return ResponseEntity.status(HttpStatus.CONFLICT)
                    .body(ApiResponse.conflict("La orden ya ha sido anulada"));
            }
            
            User veterinarian = new User();
            veterinarian.setDocument(request.getVeterinarianDocument());
            order.setVeterinarian(veterinarian);
            order.setCancelled(true);
            orderPort.save(order);
            
            return ResponseEntity.ok(ApiResponse.ok("Orden anulada exitosamente", order));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body(ApiResponse.serverError("Error al anular orden: " + e.getMessage()));
        }
    }
}
