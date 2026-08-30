package Rios.tech.Noctra.Controllers;

import Rios.tech.Noctra.dto.HistoryRequestDTO;
import Rios.tech.Noctra.dto.Response.HistoryResponseDTO;
import Rios.tech.Noctra.entity.User;
import Rios.tech.Noctra.service.HistoryService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/history")
@RequiredArgsConstructor
public class HistoryController {

    private final HistoryService historyService;

    @PostMapping
    public ResponseEntity<String> saveProgress( @Valid @RequestBody HistoryRequestDTO dto){
        historyService.saveProgress(dto);
        return ResponseEntity.ok("Progreso guardado");
    }

    // HistoryController
    @GetMapping("/{profileId}")
    public ResponseEntity<List<HistoryResponseDTO>> getHistory(@AuthenticationPrincipal User user, @PathVariable Long profileId) {
        return ResponseEntity.ok(historyService.getHistory(user, profileId));
    }
}