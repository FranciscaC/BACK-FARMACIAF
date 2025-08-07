package gt.com.pharmacy.controller;

import gt.com.pharmacy.persistence.dto.PurchaseDTO;
import gt.com.pharmacy.service.implementation.PurchaseServiceImplementation;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/api/purchases")
@PreAuthorize("denyAll()")
@RequiredArgsConstructor
public class PurchaseController {

    private final PurchaseServiceImplementation purchaseService;

    @PreAuthorize("hasAuthority(@permissionConstants.create())")
    @PostMapping
    public ResponseEntity<PurchaseDTO> createPurchase(@Valid @RequestBody PurchaseDTO dto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(purchaseService.save(dto));
    }

    @PreAuthorize("hasAuthority(@permissionConstants.read())")
    @GetMapping("/by-date/{date}")
    public ResponseEntity<List<PurchaseDTO>> getPurchasesByDate(@PathVariable LocalDate date) {
        return ResponseEntity.ok(purchaseService.findByPurchaseDate(date));
    }
}
