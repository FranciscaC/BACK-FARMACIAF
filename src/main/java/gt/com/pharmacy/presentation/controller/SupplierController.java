package gt.com.pharmacy.presentation.controller;

import gt.com.pharmacy.presentation.dto.SupplierDTO;
import gt.com.pharmacy.service.implementation.SupplierServiceImplementation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@PreAuthorize("denyAll()")
@RestController
@RequestMapping("/api/suppliers")
@RequiredArgsConstructor
public class SupplierController {

    private final SupplierServiceImplementation supplierServiceImplementation;

    @PreAuthorize("hasAuthority(@permissionConstants.create())")
    @PostMapping
    public ResponseEntity<SupplierDTO> createSupplier(@RequestBody SupplierDTO supplierDTO) {
        return ResponseEntity.status(HttpStatus.CREATED).body(supplierServiceImplementation.save(supplierDTO));
    }

    @PreAuthorize("hasAuthority(@permissionConstants.read())")
    @GetMapping("/{id}")
    public ResponseEntity<SupplierDTO> getSupplierById(@PathVariable Long id) {
        return supplierServiceImplementation.findById(id).map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build());
    }

    @PreAuthorize("hasAuthority(@permissionConstants.read())")
    @GetMapping()
    public ResponseEntity<List<SupplierDTO>>  getAllSuppliers() {
        return ResponseEntity.ok(supplierServiceImplementation.findAll());
    }

    @PreAuthorize("hasAuthority(@permissionConstants.update())")
    @PutMapping("/{id}")
    public ResponseEntity<SupplierDTO> updateSupplier(@PathVariable Long id, @RequestBody SupplierDTO supplierDTO) {
        return ResponseEntity.ok(supplierServiceImplementation.update(supplierDTO, id));
    }

    @PreAuthorize("hasAuthority(@permissionConstants.delete())")
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteSupplier(@PathVariable Long id) {
        supplierServiceImplementation.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}
