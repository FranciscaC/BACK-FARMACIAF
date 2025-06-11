package gt.com.pharmacy.controller;

import gt.com.pharmacy.persistence.dto.SaleItemDTO;
import gt.com.pharmacy.service.implementation.SaleItemServiceImplementation;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@PreAuthorize("denyAll()")
@RestController
@RequestMapping("/api/sale-items")
@RequiredArgsConstructor
public class SaleItemController {

    private final SaleItemServiceImplementation saleItemService;

    @PreAuthorize("hasAuthority(@permissionConstants.create())")
    @PostMapping
    public ResponseEntity<SaleItemDTO> createSaleItem(@Valid @RequestBody SaleItemDTO dto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(saleItemService.save(dto));
    }

    @PreAuthorize("hasAuthority(@permissionConstants.read())")
    @GetMapping("/{id}")
    public ResponseEntity<SaleItemDTO> getSaleItemById(@PathVariable Long id) {
        return saleItemService.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PreAuthorize("hasAuthority(@permissionConstants.update())")
    @PutMapping("/{id}")
    public ResponseEntity<SaleItemDTO> updateSaleItem(@PathVariable Long id, @Valid @RequestBody SaleItemDTO dto) {
        return ResponseEntity.ok(saleItemService.update(dto, id));
    }

    @PreAuthorize("hasAuthority(@permissionConstants.delete())")
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteSaleItem(@PathVariable Long id) {
        saleItemService.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}
