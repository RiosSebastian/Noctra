package Rios.tech.Noctra.Controllers;

import Rios.tech.Noctra.dto.FavoriteRequestDTO;
import Rios.tech.Noctra.dto.Response.FavoriteResponseDTO;
import Rios.tech.Noctra.service.FavoriteService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/favorites")
@RequiredArgsConstructor
public class FavoriteController {

    private final FavoriteService favoriteService;

    @PostMapping
    public ResponseEntity<String> addFavorite(@RequestBody FavoriteRequestDTO dto){
        favoriteService.addFavorite(dto);
        return ResponseEntity.ok("Agregado a favoritos");
    }

    @DeleteMapping
    public ResponseEntity<String> removeFavorite(
            @RequestParam Long userId,
            @RequestParam Long contentId){

        favoriteService.removeFavorite(userId, contentId);
        return ResponseEntity.ok("Eliminado de favoritos");
    }

    @GetMapping("/{userId}")
    public ResponseEntity<List<FavoriteResponseDTO>> getFavorites(@PathVariable Long userId){
        return ResponseEntity.ok(favoriteService.getFavorites(userId));
    }
}
