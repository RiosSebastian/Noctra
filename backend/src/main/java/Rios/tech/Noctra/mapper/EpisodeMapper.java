package Rios.tech.Noctra.mapper;

import Rios.tech.Noctra.dto.EpisodeRequestDTO;
import Rios.tech.Noctra.dto.Response.EpisodeResponseDTO;
import Rios.tech.Noctra.entity.Episode;
import org.springframework.stereotype.Component;

@Component
public class EpisodeMapper {

    public Episode toEntity(EpisodeRequestDTO dto){

        Episode episode = new Episode();

        episode.setTitle(dto.getTitle());
        episode.setDuration(dto.getDuration());
        episode.setEpisodeNumber(dto.getEpisodeNumber());

        return episode;
    }

    public EpisodeResponseDTO toResponse(Episode episode){

        return EpisodeResponseDTO.builder()
                .id(episode.getId())
                .title(episode.getTitle())
                .duration(episode.getDuration())
                .episodeNumber(episode.getEpisodeNumber())
                .build();
    }
}
