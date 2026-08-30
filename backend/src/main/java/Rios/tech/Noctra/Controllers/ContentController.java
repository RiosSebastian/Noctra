package Rios.tech.Noctra.Controllers;

import Rios.tech.Noctra.dto.Response.ContentResponseDTO;
import Rios.tech.Noctra.service.ContentService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/content")
@RequiredArgsConstructor
public class ContentController {

    private final ContentService contentService;

    @GetMapping
    public ResponseEntity<List<ContentResponseDTO>> getAll() {
        return ResponseEntity.ok(contentService.getAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<ContentResponseDTO> getById(@PathVariable Long id) {
        return ResponseEntity.ok(contentService.getById(id));
    }

    @GetMapping("/search")
    public ResponseEntity<List<ContentResponseDTO>> search(@RequestParam String title) {
        return ResponseEntity.ok(contentService.searchByTitle(title));
    }

    @GetMapping("/genre")
    public ResponseEntity<List<ContentResponseDTO>> getByGenre(@RequestParam String genre) {
        return ResponseEntity.ok(contentService.getByGenre(genre));
    }
}