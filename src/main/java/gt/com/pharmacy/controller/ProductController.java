package gt.com.pharmacy.controller;

import com.fasterxml.jackson.annotation.JsonView;
import gt.com.pharmacy.persistence.dto.ProductDTO;
import gt.com.pharmacy.persistence.view.Views;
import gt.com.pharmacy.service.implementation.ProductServiceImplementation;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/products")
@PreAuthorize("denyAll()")
@RequiredArgsConstructor
public class ProductController {

    private final ProductServiceImplementation productServiceImplementation;

    @PreAuthorize("hasAuthority(@permissionConstants.create())")
    @PostMapping
    @JsonView(Views.Public.class)
    public ResponseEntity<ProductDTO> createProduct(@Valid @RequestBody ProductDTO productDTO) {
        return ResponseEntity.status(HttpStatus.CREATED).body(productServiceImplementation.save(productDTO));
    }

    @PreAuthorize("hasAuthority(@permissionConstants.read())")
    @GetMapping("/{id}")
    @JsonView(Views.Public.class)
    public ResponseEntity<ProductDTO> getProductById(@PathVariable Long id) {
        return productServiceImplementation.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PreAuthorize("hasAuthority(@permissionConstants.read())")
    @GetMapping
    @JsonView(Views.Public.class)
    public ResponseEntity<List<ProductDTO>> getAllProducts() {
        return ResponseEntity.ok(productServiceImplementation.findAll());
    }

    @PreAuthorize("hasAuthority(@permissionConstants.update())")
    @PutMapping("/{id}")
    @JsonView(Views.Public.class)
    public ResponseEntity<ProductDTO> updateProduct(@PathVariable Long id, @Valid @RequestBody ProductDTO productDTO) {
        return ResponseEntity.ok(productServiceImplementation.update(productDTO, id));
    }

    @PreAuthorize("hasAuthority(@permissionConstants.delete())")
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteProduct(@PathVariable Long id) {
        productServiceImplementation.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}
