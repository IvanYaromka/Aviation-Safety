package by.yaromka.aviation.safety.controller.entity.pilot;

import by.yaromka.aviation.safety.domain.entity.pilot.Pilot;
import by.yaromka.aviation.safety.domain.util.page.Page;
import by.yaromka.aviation.safety.service.entity.pilot.PilotService;
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
@RequestMapping("/pilots")
@Validated
public class PilotController {
    @Autowired
    private PilotService service;

    @GetMapping
    @PreAuthorize("isAuthenticated()")
    public ResponseEntity<List<Pilot>> findAll() {
        List<Pilot> body = service.findAll();
        return ResponseEntity.ok(body);
    }

    @GetMapping("/{id}")
    @PreAuthorize("isAuthenticated()")
    public ResponseEntity<Pilot> findById(@PathVariable @Min(1) Long id) {
        Pilot body = service.findById(id).orElseThrow(() -> new HttpClientErrorException(HttpStatus.NOT_FOUND));
        return ResponseEntity.ok(body);
    }

    @GetMapping(params = {"page", "size"})
    @PreAuthorize("isFullyAuthenticated()")
    public ResponseEntity<Page<Pilot>> findForPage(@RequestParam @Min(1) Long page, @RequestParam @Min(1) Long size) {
        Page<Pilot> body = service.findForPage(page, size);
        return ResponseEntity.ok(body);
    }

    @PostMapping
    @PreAuthorize("hasPermission(#body, 'create')")
    public ResponseEntity create(@Valid @RequestBody Pilot body) {
        Long location = service.create(body);
        return ResponseEntity.created(URI.create("/pilots/" + location)).build();
    }

    @PutMapping("/{id}")
    @PreAuthorize("hasPermission(#body, 'update')")
    public ResponseEntity update(@PathVariable @Min(1) Long id, @Valid @RequestBody Pilot body) {
        service.update(id, body);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasPermission(#id, 'Pilot', 'delete')")
    public ResponseEntity delete(@PathVariable @Min(1) Long id) {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }
}
