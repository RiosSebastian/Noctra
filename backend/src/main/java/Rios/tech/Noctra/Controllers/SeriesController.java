package Rios.tech.Noctra.Controllers;

import Rios.tech.Noctra.dto.Response.SeriesResponseDTO;
import Rios.tech.Noctra.dto.SeriesRequestDTO;
import Rios.tech.Noctra.service.SeriesService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/series")
@RequiredArgsConstructor
public class SeriesController {

    private final SeriesService seriesService;

    @PostMapping
    public ResponseEntity<SeriesResponseDTO> create(@Valid @RequestBody SeriesRequestDTO dto) {
        return ResponseEntity.ok(seriesService.create(dto));
    }

    @GetMapping
    public ResponseEntity<List<SeriesResponseDTO>> getAll() {
        return ResponseEntity.ok(seriesService.getAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<SeriesResponseDTO> getById(@PathVariable Long id) {
        return ResponseEntity.ok(seriesService.getById(id));
    }

    @PutMapping("/{id}")
    public ResponseEntity<SeriesResponseDTO> update(@PathVariable Long id,
                                                    @Valid @RequestBody SeriesRequestDTO dto) {
        return ResponseEntity.ok(seriesService.update(id, dto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> delete(@PathVariable Long id) {
        seriesService.delete(id);
        return ResponseEntity.ok("Serie eliminada");
    }
}