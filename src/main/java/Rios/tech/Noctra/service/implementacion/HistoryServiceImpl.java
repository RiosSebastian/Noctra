package Rios.tech.Noctra.service.implementacion;

import Rios.tech.Noctra.dto.HistoryRequestDTO;
import Rios.tech.Noctra.dto.Response.HistoryResponseDTO;
import Rios.tech.Noctra.entity.Content;
import Rios.tech.Noctra.entity.History;
import Rios.tech.Noctra.entity.Profile;
import Rios.tech.Noctra.exception.ProfileNotFoundException;
import Rios.tech.Noctra.repository.ContentRepository;
import Rios.tech.Noctra.repository.HistoryRepository;
import Rios.tech.Noctra.repository.ProfileRepository;
import Rios.tech.Noctra.service.HistoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class HistoryServiceImpl implements HistoryService {

    private final HistoryRepository historyRepository;
    private final ProfileRepository profileRepository;
    private final ContentRepository contentRepository;

    @Override
    public void saveProgress(HistoryRequestDTO dto) {

        Profile profile = profileRepository.findById(dto.getProfileId())
                .orElseThrow(() ->  new ProfileNotFoundException("Perfil no encontrado"));

        Content content = contentRepository.findById(dto.getContentId())
                .orElseThrow(() -> new ProfileNotFoundException("Contenido no encontrado"));

        History history = new History();
        history.setProfile(profile);
        history.setContent(content);
        history.setProgress(dto.getProgress());
        history.setLastWatched(LocalDateTime.now());

        historyRepository.save(history);
    }

    @Override
    public List<HistoryResponseDTO> getHistory(Long profileId) {
        return historyRepository.findByProfileId(profileId)
                .stream()
                .map(h -> HistoryResponseDTO.builder()
                        .id(h.getId())
                        .progress(h.getProgress())
                        .title(h.getContent().getTitle())
                        .build())
                .toList();
    }
}
