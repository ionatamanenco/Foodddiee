package ia.ffy.foodforyou.sitting_tables;

import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(value = "/api/sittingTables")
public class SittingTablesController {

    private final SittingTablesService sittingTablesService;

    public SittingTablesController(final SittingTablesService sittingTablesService) {
        this.sittingTablesService = sittingTablesService;
    }

    @GetMapping
    public ResponseEntity<List<SittingTablesDTO>> getAllSittingTables() {
        return ResponseEntity.ok(sittingTablesService.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<SittingTablesDTO> getSittingTables(@PathVariable final String id) {
        return ResponseEntity.ok(sittingTablesService.get(id));
    }

    @PostMapping
    public ResponseEntity<Void> createSittingTables(
            @RequestBody @Valid final SittingTablesDTO sittingTablesDTO) {
        sittingTablesService.create(sittingTablesDTO);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Void> updateSittingTables(@PathVariable final String id,
                                                    @RequestBody @Valid final SittingTablesDTO sittingTablesDTO) {
        sittingTablesService.update(id, sittingTablesDTO);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteSittingTables(@PathVariable final String id) {
        sittingTablesService.delete(id);
        return ResponseEntity.noContent().build();
    }

}
