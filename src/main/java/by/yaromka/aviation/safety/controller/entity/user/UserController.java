package by.yaromka.aviation.safety.controller.entity.user;

import by.yaromka.aviation.safety.domain.entity.user.User;
import by.yaromka.aviation.safety.domain.util.page.Page;
import by.yaromka.aviation.safety.service.entity.user.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.HttpClientErrorException;

import javax.validation.Valid;
import javax.validation.constraints.Min;
import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/users")
@Validated
public class UserController {
    @Autowired
    private UserService service;

    @GetMapping
    @PreAuthorize("isAuthenticated()")
    public ResponseEntity<List<User>> findAll() {
        List<User> body = service.findAll();
        return ResponseEntity.ok(body);
    }

    @GetMapping("/{id}")
    @PreAuthorize("isAuthenticated()")
    public ResponseEntity<User> findById(@PathVariable @Min(1) Long id) {
        User body = service.findById(id).orElseThrow(() -> new HttpClientErrorException(HttpStatus.NOT_FOUND));
        return ResponseEntity.ok(body);
    }

    @GetMapping(params = {"page", "size"})
    @PreAuthorize("isAuthenticated()")
    public ResponseEntity<Page<User>> findForPage(@RequestParam @Min(1) Long page, @RequestParam @Min(1) Long size) {
        Page<User> body = service.findForPage(page, size);
        return ResponseEntity.ok(body);
    }

    @PostMapping
    @PreAuthorize("hasPermission(#body, 'create')")
    public ResponseEntity create(@Valid @RequestBody User body) {
        Long location = service.create(body);
        return ResponseEntity.created(URI.create("/users/" + location)).build();
    }

    @PutMapping("/{id}")
    @PreAuthorize("hasPermission(#body, 'update')")
    public ResponseEntity update(@PathVariable @Min(1) Long id, @Valid @RequestBody User body) {
        service.update(id, body);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasPermission(#id, 'User', 'delete')")
    public ResponseEntity delete(@PathVariable @Min(1) Long id) {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }
}
