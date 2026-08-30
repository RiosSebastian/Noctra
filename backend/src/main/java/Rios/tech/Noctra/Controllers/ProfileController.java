package Rios.tech.Noctra.Controllers;

import Rios.tech.Noctra.dto.ProfileRequestDTO;
import Rios.tech.Noctra.dto.Response.ProfileResponseDTO;
import Rios.tech.Noctra.entity.User;
import Rios.tech.Noctra.service.ProfileService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/profiles")
@RequiredArgsConstructor
public class ProfileController {

    private final ProfileService profileService;

    @PostMapping
    public ResponseEntity<ProfileResponseDTO> create(@AuthenticationPrincipal User user, @Valid @RequestBody ProfileRequestDTO dto) {
        return ResponseEntity.ok(profileService.create(user, dto));
    }

    @GetMapping("/user/{userId}")
    public ResponseEntity<List<ProfileResponseDTO>> getByUser(@PathVariable Long userId) {
        return ResponseEntity.ok(profileService.getByUser(userId));
    }

    @PutMapping("/{profileId}")
    public ResponseEntity<ProfileResponseDTO> update(@AuthenticationPrincipal User user, @PathVariable Long profileId, @Valid @RequestBody ProfileRequestDTO dto) {
        return ResponseEntity.ok(profileService.update(user, profileId, dto));
    }

    @DeleteMapping("/{profileId}")
    public ResponseEntity<String> delete(@AuthenticationPrincipal User user, @PathVariable Long profileId) {
        profileService.delete(user, profileId);
        return ResponseEntity.ok("Perfil eliminado");
    }
}