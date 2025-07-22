package gt.com.pharmacy.controller;

import com.fasterxml.jackson.annotation.JsonView;
import gt.com.pharmacy.persistence.dto.CustomerDTO;
import gt.com.pharmacy.persistence.view.Views;
import gt.com.pharmacy.service.implementation.CustomerServiceImplementation;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/customers")
@PreAuthorize("denyAll()")
@RequiredArgsConstructor
public class CustomerController {

    private final CustomerServiceImplementation customerServiceImplementation;

    @PreAuthorize("hasAnyAuthority(@permissionConstants.create())")
    @PostMapping
    @JsonView(Views.Public.class)
    public ResponseEntity<CustomerDTO> createCustomer(@Valid @RequestBody CustomerDTO customerDTO) {
        return ResponseEntity.status(HttpStatus.CREATED).body(customerServiceImplementation.save(customerDTO));
    }

    @PreAuthorize("hasAnyAuthority(@permissionConstants.read())")
    @GetMapping("/{id}")
    @JsonView(Views.Public.class)
    public ResponseEntity<CustomerDTO> getCustomerById(@PathVariable Long id) {
        return customerServiceImplementation.findById(id).map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build());
    }

    @PreAuthorize("hasAuthority(@permissionConstants.read())")
    @GetMapping
    @JsonView(Views.Public.class)
    public ResponseEntity<List<CustomerDTO>> getAllCustomers() {
        return ResponseEntity.ok(customerServiceImplementation.findAll());
    }

    @PreAuthorize("hasAuthority(@permissionConstants.update())")
    @PutMapping("/{id}")
    @JsonView(Views.Public.class)
    public ResponseEntity<CustomerDTO> updateCustomer(@PathVariable Long id, @Valid @RequestBody CustomerDTO customerDTO) {
        return ResponseEntity.ok(customerServiceImplementation.update(customerDTO, id));
    }

    @PreAuthorize("hasAuthority(@permissionConstants.delete())")
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCustomer(@PathVariable Long id) {
        customerServiceImplementation.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}
