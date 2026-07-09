package Rios.tech.Noctra.service.implementacion;

import Rios.tech.Noctra.dto.Response.UserResponseDTO;
import Rios.tech.Noctra.dto.UserRequestDTO;
import Rios.tech.Noctra.entity.User;
import Rios.tech.Noctra.exception.UserNotFoundException;
import Rios.tech.Noctra.mapper.UserMapper;
import Rios.tech.Noctra.repository.UserRepository;
import Rios.tech.Noctra.service.UserService;
import Rios.tech.Noctra.util.Rol;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserServisImpl implements UserService {

    @Autowired
    private PasswordEncoder passwordEncoder;

    private final UserRepository userRepository;
    @Autowired
    private UserMapper userMapper;  // Para convertir entre entidades y DTOs

    public UserServisImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserResponseDTO getUser(Long id) {

        User user = userRepository.findById(id)
                .orElseThrow(() ->  new UserNotFoundException("Usuario no encontrado"));

        return userMapper.toResponse(user);
    }


    @Override
    public UserResponseDTO create(UserRequestDTO userDTOReq) {
        User user = userMapper.toEntity(userDTOReq);

        // if (user.getProfileImage() == null || user.getProfileImage().isEmpty()) {
        //     user.setProfileImage("https://res.cloudinary.com/dmwsuzs94/image/upload/v1728906889/user_n1laeq.jpg");
        // }

        // Asegúrate de que estás codificando la contraseña
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        user.setRol(Rol.USER);
        user = userRepository.save(user);
        return userMapper.toResponse(user);
    }

    public UserResponseDTO getById(Long id) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new UserNotFoundException("Usuario no encontrado"));
        return userMapper.toResponse(user);
    }

    public UserResponseDTO updateUser(Long id, UserRequestDTO userDTOReq) {
        User existingUser = userRepository.findById(id)
                .orElseThrow(() -> new UserNotFoundException("Usuario no encontrado"));
        existingUser.setPassword(userDTOReq.getPassword());
        existingUser.setEmail(userDTOReq.getEmail());
        //existingUser.setUsername(Rol.valueOf(userDTOReq.getRol()));

        existingUser = userRepository.save(existingUser);
        return userMapper.toResponse(existingUser);
    }

    public void deleteUser(Long id) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new UserNotFoundException("Usuario no encontrado"));
        userRepository.delete(user);
    }

    public List<UserResponseDTO> getAllUsers() {
        List<User> users = userRepository.findAll();
        return users.stream()
                .map(userMapper::toResponse)
                .collect(Collectors.toList());
    }

    public UserResponseDTO updateProfileImage(Long id, String imageUrl) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new UserNotFoundException("Usuario no encontrado"));
        //user.setProfileImage(imageUrl);
        userRepository.save(user);
        return userMapper.toResponse(user);
    }
}
