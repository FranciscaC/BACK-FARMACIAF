package gt.com.pharmacy.controller;

import gt.com.pharmacy.persistence.dto.AuthCreateUserRequestDTO;
import gt.com.pharmacy.persistence.dto.AuthResponseDTO;
import gt.com.pharmacy.persistence.entity.UserEntity;
import gt.com.pharmacy.service.implementation.UserDetailServiceImplementation;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@PreAuthorize("denyAll()")
@RestController
@RequestMapping("/api/user")
@RequiredArgsConstructor
public class UserController {

    private final UserDetailServiceImplementation userDetailServiceImplementation;

    @PreAuthorize("hasAuthority(@permissionConstants.create())")
    @PostMapping
    public ResponseEntity<AuthResponseDTO> register(@RequestBody @Valid AuthCreateUserRequestDTO authCreateUserRequestDTO) {
        return new ResponseEntity<>(this.userDetailServiceImplementation.createUser(authCreateUserRequestDTO), HttpStatus.CREATED);
    }

    @PreAuthorize("hasAnyAuthority(@permissionConstants.read())")
    @GetMapping
    public ResponseEntity<List<UserEntity>> findAllUsers() {
        return new ResponseEntity<>(this.userDetailServiceImplementation.findAllUsers(), HttpStatus.OK);
    }

    @PreAuthorize("hasAuthority(@permissionConstants.update())")
    @PutMapping("/{username}")
    public ResponseEntity<AuthResponseDTO> updateUser(@PathVariable String username, @RequestBody @Valid AuthCreateUserRequestDTO authCreateUserRequestDTO) {
        return new ResponseEntity<>(this.userDetailServiceImplementation.updateUser(username, authCreateUserRequestDTO), HttpStatus.OK);
    }

    @PreAuthorize("hasAuthority(@permissionConstants.delete())")
    @DeleteMapping("/{username}")
    public ResponseEntity<Void> deleteUser(@PathVariable String username) {
        this.userDetailServiceImplementation.deleteUser(username);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
