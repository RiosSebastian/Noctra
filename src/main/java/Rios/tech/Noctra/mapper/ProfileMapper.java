package Rios.tech.Noctra.mapper;

import Rios.tech.Noctra.dto.ProfileRequestDTO;
import Rios.tech.Noctra.dto.Response.ProfileResponseDTO;
import Rios.tech.Noctra.entity.Profile;
import org.springframework.stereotype.Component;

@Component
public class ProfileMapper {
    public Profile toEntity(ProfileRequestDTO dto) {
        Profile profile = new Profile();
        profile.setName(dto.getName());
        profile.setAvatarUrl(dto.getAvatarUrl());
        return profile;
    }

    public ProfileResponseDTO toResponse(Profile profile) {
        return ProfileResponseDTO.builder()
                .id(profile.getId())
                .name(profile.getName())
                .avatarUrl(profile.getAvatarUrl())
                .build();
    }
}