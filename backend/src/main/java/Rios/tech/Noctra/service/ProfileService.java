package Rios.tech.Noctra.service;

import Rios.tech.Noctra.dto.ProfileRequestDTO;
import Rios.tech.Noctra.dto.Response.ProfileResponseDTO;
import Rios.tech.Noctra.entity.User;

import java.util.List;

public interface ProfileService {

    ProfileResponseDTO create(User user, ProfileRequestDTO dto);

    List<ProfileResponseDTO> getByUser(Long userId);

    ProfileResponseDTO update(User user, Long profileId, ProfileRequestDTO dto);

    void delete(User user, Long profileId);
}