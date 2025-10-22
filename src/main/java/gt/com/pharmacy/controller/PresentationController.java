package gt.com.pharmacy.controller;

import com.fasterxml.jackson.annotation.JsonView;
import gt.com.pharmacy.persistence.dto.PresentationDTO;
import gt.com.pharmacy.persistence.dto.StockAdjustmentDTO;
import gt.com.pharmacy.persistence.view.Views;
import gt.com.pharmacy.service.implementation.PresentationServiceImplementation;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/presentations")
@PreAuthorize("denyAll()")
@RequiredArgsConstructor
public class PresentationController {

    private final PresentationServiceImplementation presentationService;

    @PreAuthorize("hasAuthority(@permissionConstants.create())")
    @PostMapping
    @JsonView(Views.Public.class)
    public ResponseEntity<PresentationDTO> createPresentation(@Valid @RequestBody PresentationDTO dto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(presentationService.save(dto));
    }

    @PreAuthorize("hasAuthority(@permissionConstants.update())")
    @PatchMapping("/{id}/stock")
    @JsonView(Views.Public.class)
    public ResponseEntity<PresentationDTO> adjustStock(
            @PathVariable Long id,
            @Valid @RequestBody StockAdjustmentDTO stockAdjustment) {

        if (!id.equals(stockAdjustment.getPresentationId())) {
            return ResponseEntity.badRequest().build();
        }

        PresentationDTO updated = presentationService.adjustStock(
                stockAdjustment.getPresentationId(),
                stockAdjustment.getQuantity()
        );

        return ResponseEntity.ok(updated);
    }

    @PreAuthorize("hasAuthority(@permissionConstants.read())")
    @GetMapping("/{id}")
    @JsonView(Views.Public.class)
    public ResponseEntity<PresentationDTO> getPresentationById(@PathVariable Long id) {
        return presentationService.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PreAuthorize("hasAuthority(@permissionConstants.read())")
    @GetMapping
    @JsonView(Views.Public.class)
    public ResponseEntity<List<PresentationDTO>> getAllPresentations() {
        return ResponseEntity.ok(presentationService.findAll());
    }

    @PreAuthorize("hasAuthority(@permissionConstants.update())")
    @PutMapping("/{id}")
    @JsonView(Views.Public.class)
    public ResponseEntity<PresentationDTO> updatePresentation(@PathVariable Long id, @Valid @RequestBody PresentationDTO dto) {
        if (dto.getId() != null && !dto.getId().equals(id)) {
            return ResponseEntity.badRequest().build();
        }
        dto.setId(id);
        PresentationDTO updated = presentationService.update(dto, id);
        return ResponseEntity.ok(updated);
    }

    @PreAuthorize("hasAnyAuthority(@permissionConstants.delete())")
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletePresentation(@PathVariable Long id) {
        presentationService.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}
