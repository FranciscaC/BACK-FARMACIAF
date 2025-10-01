package gt.com.pharmacy.controller;

import com.fasterxml.jackson.annotation.JsonView;
import gt.com.pharmacy.persistence.dto.SaleDTO;
import gt.com.pharmacy.persistence.view.Views;
import gt.com.pharmacy.service.implementation.SaleServiceImplementation;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/api/sales")
@PreAuthorize("denyAll()")
@RequiredArgsConstructor
public class SaleController {

    private final SaleServiceImplementation saleService;

    @PreAuthorize("hasAuthority(@permissionConstants.create())")
    @PostMapping
    @JsonView(Views.Public.class)
    public ResponseEntity<SaleDTO> createSale(@Valid @RequestBody SaleDTO dto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(saleService.save(dto));
    }

    @PreAuthorize("hasAuthority(@permissionConstants.read())")
    @GetMapping("/by-date/{date}")
    @JsonView(Views.Public.class)
    public ResponseEntity<List<SaleDTO>> getSalesByDate(@PathVariable @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate date) {
        List<SaleDTO> sales = saleService.findSalesByDate(date);
        return ResponseEntity.ok(sales);
    }

    @PreAuthorize("hasAnyAuthority(@permissionConstants.read())")
    @GetMapping("/total-by-date-range")
    public ResponseEntity<BigDecimal> getTotalSalesByDateRange(
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate startDate,
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate endDate
    ) {
        BigDecimal total = saleService.getTotalSalesByDateRange(startDate, endDate);
        return ResponseEntity.ok(total);
    }
}
