package by.yaromka.aviation.safety.controller.entity.airport;

import by.yaromka.aviation.safety.domain.entity.airport.Airport;
import by.yaromka.aviation.safety.domain.util.page.Page;
import by.yaromka.aviation.safety.service.entity.airport.AirportService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.HttpClientErrorException;

import javax.validation.Valid;
import javax.validation.constraints.Min;
import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/airports")
@Validated
public class AirportController {
    @Autowired
    private AirportService service;

    @GetMapping
    @PreAuthorize("isAuthenticated()")
    public ResponseEntity<List<Airport>> findAll() {
        List<Airport> body = service.findAll();
        return ResponseEntity.ok(body);
    }

    @GetMapping("/{id}")
    @PreAuthorize("isAuthenticated()")
    public ResponseEntity<Airport> findById(@PathVariable @Min(1) Long id) {
        Airport body = service.findById(id).orElseThrow(() -> new HttpClientErrorException(HttpStatus.NOT_FOUND));
        return ResponseEntity.ok(body);
    }

    @GetMapping(params = {"page", "size"})
    @PreAuthorize("isAuthenticated()")
    public ResponseEntity<Page<Airport>> findForPage(@RequestParam @Min(1) Long page, @RequestParam @Min(1) Long size) {
        Page<Airport> body = service.findForPage(page, size);
        return ResponseEntity.ok(body);
    }

    @PostMapping
    @PreAuthorize("hasRole('superadmin')")
    public ResponseEntity create(@Valid @RequestBody Airport body) {
        Long location = service.create(body);
        return ResponseEntity.created(URI.create("/airports/" + location)).build();
    }

    @PutMapping("/{id}")
    @PreAuthorize("hasRole('superadmin')")
    public ResponseEntity update(@PathVariable @Min(1) Long id, @Valid @RequestBody Airport body) {
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
