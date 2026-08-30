package Rios.tech.Noctra.mapper;

import Rios.tech.Noctra.dto.Response.UserResponseDTO;
import Rios.tech.Noctra.dto.UserRequestDTO;
import Rios.tech.Noctra.entity.User;
import org.springframework.stereotype.Component;

@Component
public class UserMapper {

    public User toEntity(UserRequestDTO dto) {
        User user = new User();
        user.setUsername(dto.getUsername());
        user.setEmail(dto.getEmail());
        user.setPassword(dto.getPassword());

        return user;
    }

    public UserResponseDTO toResponse(User user) {
        return UserResponseDTO.builder()
                .id(user.getId())
                .username(user.getUsername())
                .email(user.getEmail())
                .build();
    }

    public void updateEntity(User user, UserRequestDTO dto){
        user.setUsername(dto.getUsername());
        user.setEmail(dto.getEmail());
    }
}