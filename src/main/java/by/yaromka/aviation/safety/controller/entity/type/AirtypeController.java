package by.yaromka.aviation.safety.controller.entity.type;

import by.yaromka.aviation.safety.domain.entity.type.AirType;
import by.yaromka.aviation.safety.domain.util.page.Page;
import by.yaromka.aviation.safety.service.entity.type.AirTypeService;
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
@RequestMapping("/types")
@Validated
public class AirtypeController {
    @Autowired
    private AirTypeService service;

    @GetMapping
    @PreAuthorize("isAuthenticated()")
    public ResponseEntity<List<AirType>> findAll() {
        List<AirType> body = service.findAll();
        return ResponseEntity.ok(body);
    }

    @GetMapping("/{id}")
    @PreAuthorize("isAuthenticated()")
    public ResponseEntity<AirType> findById(@PathVariable @Min(1) Long id) {
        AirType body = service.findById(id).orElseThrow(() -> new HttpClientErrorException(HttpStatus.NOT_FOUND));
        return ResponseEntity.ok(body);
    }

    @GetMapping(params = {"page", "size"})
    @PreAuthorize("isAuthenticated()")
    public ResponseEntity<Page<AirType>> findForPage(@PathVariable @Min(1) Long page, @PathVariable @Min(1) Long size) {
        Page<AirType> body = service.findForPage(page, size);
        return ResponseEntity.ok(body);
    }

    @PostMapping
    @PreAuthorize("hasRole('superadmin')")
    public ResponseEntity create(@Valid @RequestBody AirType body) {
        Long location = service.create(body);
        return ResponseEntity.created(URI.create("/types/" + location)).build();
    }

    @PutMapping("/{id}")
    @PreAuthorize("hasRole('superadmin')")
    public ResponseEntity update(@PathVariable @Min(1) Long id, @Valid @RequestBody AirType body) {
        service.update(id, body);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('superadmin')")
    public ResponseEntity delete(@PathVariable @Min(1) Long id) {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }
}
