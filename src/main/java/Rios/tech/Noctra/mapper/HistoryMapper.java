package Rios.tech.Noctra.mapper;

import Rios.tech.Noctra.dto.HistoryRequestDTO;
import Rios.tech.Noctra.dto.Response.HistoryResponseDTO;
import Rios.tech.Noctra.entity.History;
import org.springframework.stereotype.Component;

@Component
public class HistoryMapper {

    public History toEntity(HistoryRequestDTO dto){
        History history = new History();
        history.setProgress(dto.getProgress());
        return history;
    }

    public HistoryResponseDTO toDTO(History history){
        return HistoryResponseDTO.builder()
                .id(history.getId())
                .progress(history.getProgress())
                .title(history.getContent().getTitle())
                .build();
    }
}
