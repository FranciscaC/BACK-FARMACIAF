package gt.com.pharmacy.controller;

import gt.com.pharmacy.persistence.dto.SaleDTO;
import gt.com.pharmacy.service.implementation.SaleServiceImplementation;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@PreAuthorize("denyAll()")
@RestController
@RequestMapping("/api/sales")
@RequiredArgsConstructor
public class SaleController {

    private final SaleServiceImplementation saleService;

    @PreAuthorize("hasAuthority(@permissionConstants.create())")
    @PostMapping
    public ResponseEntity<SaleDTO> createSale(@Valid @RequestBody SaleDTO dto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(saleService.save(dto));
    }

    @PreAuthorize("hasAuthority(@permissionConstants.read())")
    @GetMapping("/{id}")
    public ResponseEntity<SaleDTO> getSaleById(@PathVariable Long id) {
        return saleService.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PreAuthorize("hasAuthority(@permissionConstants.read())")
    @GetMapping
    public ResponseEntity<List<SaleDTO>> getAllSales() {
        return ResponseEntity.ok(saleService.findAll());
    }

    @PreAuthorize("hasAuthority(@permissionConstants.update())")
    @PutMapping("/{id}")
    public ResponseEntity<SaleDTO> updateSale(@PathVariable Long id, @Valid @RequestBody SaleDTO dto) {
        return ResponseEntity.ok(saleService.update(dto, id));
    }

    @PreAuthorize("hasAuthority(@permissionConstants.delete())")
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteSale(@PathVariable Long id) {
        saleService.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}
