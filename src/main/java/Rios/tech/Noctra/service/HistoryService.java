package Rios.tech.Noctra.service;

import Rios.tech.Noctra.dto.HistoryRequestDTO;
import Rios.tech.Noctra.dto.Response.HistoryResponseDTO;

import java.util.List;

public interface HistoryService {
    void saveProgress(HistoryRequestDTO dto);

    List<HistoryResponseDTO> getHistory(Long profileId);
}
