package Rios.tech.Noctra.Controllers;

import Rios.tech.Noctra.dto.Response.WatchlistResponseDTO;
import Rios.tech.Noctra.dto.WatchlistRequestDTO;
import Rios.tech.Noctra.service.WatchlistService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/watchlist")
@RequiredArgsConstructor
public class WatchlistController {

    private final WatchlistService watchlistService;

    @PostMapping
    public ResponseEntity<WatchlistResponseDTO> addToWatchlist(@Valid @RequestBody WatchlistRequestDTO dto) {
        return ResponseEntity.ok(watchlistService.addToWatchlist(dto));
    }

    @DeleteMapping
    public ResponseEntity<String> removeFromWatchlist(@RequestParam Long profileId,
                                                      @RequestParam Long contentId) {
        watchlistService.removeFromWatchlist(profileId, contentId);
        return ResponseEntity.ok("Eliminado de la watchlist");
    }

    @GetMapping("/{profileId}")
    public ResponseEntity<WatchlistResponseDTO> getWatchlist(@PathVariable Long profileId) {
        return ResponseEntity.ok(watchlistService.getWatchlist(profileId));
    }
}