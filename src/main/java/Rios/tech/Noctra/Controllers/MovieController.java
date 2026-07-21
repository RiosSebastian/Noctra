package Rios.tech.Noctra.Controllers;

import Rios.tech.Noctra.dto.MovieRequestDTO;
import Rios.tech.Noctra.dto.Response.MovieResponseDTO;
import Rios.tech.Noctra.service.MovieService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/movies")
@RequiredArgsConstructor
public class MovieController {

    private final MovieService movieService;

    @PostMapping
    public ResponseEntity<MovieResponseDTO> create(@Valid @RequestBody MovieRequestDTO dto) {
        return ResponseEntity.ok(movieService.create(dto));
    }

    @GetMapping
    public ResponseEntity<List<MovieResponseDTO>> getAll() {
        return ResponseEntity.ok(movieService.getAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<MovieResponseDTO> getById(@PathVariable Long id) {
        return ResponseEntity.ok(movieService.getById(id));
    }

    @PutMapping("/{id}")
    public ResponseEntity<MovieResponseDTO> update(@PathVariable Long id,
                                                   @Valid @RequestBody MovieRequestDTO dto) {
        return ResponseEntity.ok(movieService.update(id, dto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> delete(@PathVariable Long id) {
        movieService.delete(id);
        return ResponseEntity.ok("Película eliminada");
    }
}