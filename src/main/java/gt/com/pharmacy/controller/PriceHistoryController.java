package gt.com.pharmacy.controller;

import gt.com.pharmacy.persistence.dto.PriceHistoryDTO;
import gt.com.pharmacy.service.implementation.PriceHistoryServiceImplementation;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@PreAuthorize("denyAll()")
@RestController
@RequestMapping("/api/price-history")
@RequiredArgsConstructor
public class PriceHistoryController {

    private final PriceHistoryServiceImplementation priceHistoryService;

    @PreAuthorize("hasAuthority(@permissionConstants.create())")
    @PostMapping
    public ResponseEntity<PriceHistoryDTO> createPriceHistory(@Valid @RequestBody PriceHistoryDTO dto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(priceHistoryService.save(dto));
    }

    @PreAuthorize("hasAuthority(@permissionConstants.read())")
    @GetMapping("/{id}")
    public ResponseEntity<PriceHistoryDTO> getPriceHistoryById(@PathVariable Long id) {
        return priceHistoryService.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }
}
