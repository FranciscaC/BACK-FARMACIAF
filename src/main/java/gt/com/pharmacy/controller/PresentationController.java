package gt.com.pharmacy.controller;

import com.fasterxml.jackson.annotation.JsonView;
import gt.com.pharmacy.persistence.dto.PresentationDTO;
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

    @PreAuthorize("hasAnyAuthority(@permissionConstants.delete())")
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletePresentation(@PathVariable Long id) {
        presentationService.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}
