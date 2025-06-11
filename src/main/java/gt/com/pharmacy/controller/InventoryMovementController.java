package gt.com.pharmacy.controller;

import gt.com.pharmacy.persistence.dto.InventoryMovementDTO;
import gt.com.pharmacy.service.implementation.InventoryMovementServiceImplementation;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@PreAuthorize("denyAll()")
@RestController
@RequestMapping("/api/inventory-movements")
@RequiredArgsConstructor
public class InventoryMovementController {

    private final InventoryMovementServiceImplementation inventoryMovementService;

    @PreAuthorize("hasAuthority(@permissionConstants.create())")
    @PostMapping
    public ResponseEntity<InventoryMovementDTO> createMovement(@Valid @RequestBody InventoryMovementDTO dto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(inventoryMovementService.save(dto));
    }

    @PreAuthorize("hasAuthority(@permissionConstants.read())")
    @GetMapping("/{id}")
    public ResponseEntity<InventoryMovementDTO> getMovementById(@PathVariable Long id) {
        return inventoryMovementService.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PreAuthorize("hasAuthority(@permissionConstants.read())")
    @GetMapping
    public ResponseEntity<List<InventoryMovementDTO>> getAllMovements() {
        return ResponseEntity.ok(inventoryMovementService.findAll());
    }

    @PreAuthorize("hasAuthority(@permissionConstants.update())")
    @PutMapping("/{id}")
    public ResponseEntity<InventoryMovementDTO> updateMovement(@PathVariable Long id, @Valid @RequestBody InventoryMovementDTO dto) {
        return ResponseEntity.ok(inventoryMovementService.update(dto, id));
    }

    @PreAuthorize("hasAuthority(@permissionConstants.delete())")
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteMovement(@PathVariable Long id) {
        inventoryMovementService.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}
