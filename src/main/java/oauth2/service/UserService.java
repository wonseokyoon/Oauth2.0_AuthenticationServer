package oauth2.service;

import lombok.RequiredArgsConstructor;
import oauth2.domain.Role;
import oauth2.domain.UserEntity;
import oauth2.dto.UserDTO;
import oauth2.dto.UserResponse;
import oauth2.repository.UserRepository;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;

    public UserResponse join(UserDTO dto) {
        UserEntity userEntity = new UserEntity();
        userEntity.setUsername(dto.getUsername());
        userEntity.setPassword(bCryptPasswordEncoder.encode(dto.getPassword()));
        userEntity.setNiceName(dto.getUsername());
        userEntity.setEmail(dto.getEmail());
        userEntity.setRole(Role.ADMIN);

        userRepository.save(userEntity);

        UserResponse response = new UserResponse();
        response.setUsername(userEntity.getUsername());
        response.setNickname(userEntity.getNiceName());
        response.setEmail(userEntity.getEmail());
        response.setRole(userEntity.getRole().name());

        return response;
    }



}
