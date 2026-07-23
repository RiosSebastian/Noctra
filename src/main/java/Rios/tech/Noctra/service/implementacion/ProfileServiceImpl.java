package Rios.tech.Noctra.service.implementacion;

import Rios.tech.Noctra.dto.ProfileRequestDTO;
import Rios.tech.Noctra.dto.Response.ProfileResponseDTO;
import Rios.tech.Noctra.entity.Profile;
import Rios.tech.Noctra.entity.User;
import Rios.tech.Noctra.exception.ProfileLimitExceededException;
import Rios.tech.Noctra.exception.ProfileNotFoundException;
import Rios.tech.Noctra.mapper.ProfileMapper;
import Rios.tech.Noctra.repository.ProfileRepository;
import Rios.tech.Noctra.service.ProfileService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.nio.file.AccessDeniedException;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ProfileServiceImpl implements ProfileService {

    private static final int MAX_PROFILES = 5;

    private final ProfileRepository profileRepository;
    private final ProfileMapper profileMapper;

    @Override
    public ProfileResponseDTO create(User user, ProfileRequestDTO dto) {
        List<Profile> existentes = profileRepository.findByUserId(user.getId());
        if (existentes.size() >= MAX_PROFILES) {
            throw new ProfileLimitExceededException(
                    "Se alcanzó el límite de " + MAX_PROFILES + " perfiles por cuenta");
        }

        Profile profile = profileMapper.toEntity(dto);
        profile.setUser(user);
        profile = profileRepository.save(profile);

        return profileMapper.toResponse(profile);
    }

    @Override
    public List<ProfileResponseDTO> getByUser(Long userId) {
        return profileRepository.findByUserId(userId)
                .stream()
                .map(profileMapper::toResponse)
                .toList();
    }

    @Override
    public ProfileResponseDTO update(User user, Long profileId, ProfileRequestDTO dto) {
        Profile profile = getOwnedProfile(user, profileId);
        profile.setName(dto.getName());
        profile.setAvatarUrl(dto.getAvatarUrl());
        profile = profileRepository.save(profile);
        return profileMapper.toResponse(profile);
    }

    @Override
    public void delete(User user, Long profileId) {
        Profile profile = getOwnedProfile(user, profileId);
        profileRepository.delete(profile);
    }

    private Profile getOwnedProfile(User user, Long profileId) {
        Profile profile = profileRepository.findById(profileId)
                .orElseThrow(() -> new ProfileNotFoundException("Perfil no encontrado"));

        if (!profile.getUser().getId().equals(user.getId())) {
            throw new AccessDeniedException("Este perfil no pertenece al usuario autenticado");
        }
        return profile;
    }
}
