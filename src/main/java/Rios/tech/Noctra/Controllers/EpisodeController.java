package Rios.tech.Noctra.Controllers;

import Rios.tech.Noctra.dto.EpisodeRequestDTO;
import Rios.tech.Noctra.dto.Response.EpisodeResponseDTO;
import Rios.tech.Noctra.service.EpisodeService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/episodes")
@RequiredArgsConstructor
public class EpisodeController {

    private final EpisodeService episodeService;

    @PostMapping
    public ResponseEntity<EpisodeResponseDTO> create(@Valid @RequestBody EpisodeRequestDTO dto) {
        return ResponseEntity.ok(episodeService.create(dto));
    }

    @GetMapping("/{id}")
    public ResponseEntity<EpisodeResponseDTO> getById(@PathVariable Long id) {
        return ResponseEntity.ok(episodeService.getById(id));
    }

    @GetMapping("/season/{seasonId}")
    public ResponseEntity<List<EpisodeResponseDTO>> getBySeason(@PathVariable Long seasonId) {
        return ResponseEntity.ok(episodeService.getBySeason(seasonId));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> delete(@PathVariable Long id) {
        episodeService.delete(id);
        return ResponseEntity.ok("Episodio eliminado");
    }
}