package Rios.tech.Noctra.Controllers;

import Rios.tech.Noctra.dto.Response.SeasonResponseDTO;
import Rios.tech.Noctra.dto.SeasonRequestDTO;
import Rios.tech.Noctra.service.SeasonService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/seasons")
@RequiredArgsConstructor
public class SeasonController {

    private final SeasonService seasonService;

    @PostMapping
    public ResponseEntity<SeasonResponseDTO> create(@Valid @RequestBody SeasonRequestDTO dto) {
        return ResponseEntity.ok(seasonService.create(dto));
    }

    @GetMapping("/{id}")
    public ResponseEntity<SeasonResponseDTO> getById(@PathVariable Long id) {
        return ResponseEntity.ok(seasonService.getById(id));
    }

    @GetMapping("/series/{seriesId}")
    public ResponseEntity<List<SeasonResponseDTO>> getBySeries(@PathVariable Long seriesId) {
        return ResponseEntity.ok(seasonService.getBySeries(seriesId));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> delete(@PathVariable Long id) {
        seasonService.delete(id);
        return ResponseEntity.ok("Temporada eliminada");
    }
}