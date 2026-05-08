package Rios.tech.Noctra.service;

import Rios.tech.Noctra.dto.Response.UserResponseDTO;
import Rios.tech.Noctra.dto.UserRequestDTO;

import java.util.List;

public interface UserService {
    public UserResponseDTO getUser(Long id);

    UserResponseDTO create(UserRequestDTO userDTOReq);

    // Firma del método para obtener un usuario por su ID
    UserResponseDTO getById(Long id);

    // Firma del método para actualizar un usuario
    UserResponseDTO updateUser(Long id, UserRequestDTO userDTOReq);

    // Firma del método para eliminar un usuario por su ID
    void deleteUser(Long id);

    // Firma del método para obtener todos los usuarios
    List<UserResponseDTO> getAllUsers();

    UserResponseDTO updateProfileImage(Long id, String imageUrl);
}
