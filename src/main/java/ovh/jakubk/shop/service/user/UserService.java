package ovh.jakubk.shop.service.user;

import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import ovh.jakubk.shop.dto.UserDto;
import ovh.jakubk.shop.exceptions.AlreadyExistException;
import ovh.jakubk.shop.exceptions.ResourceNotFoundException;
import ovh.jakubk.shop.model.User;
import ovh.jakubk.shop.repository.UserRepository;
import ovh.jakubk.shop.request.CreateUserRequest;
import ovh.jakubk.shop.request.UserUpdateRequest;

import java.util.Optional;

@RequiredArgsConstructor
@Service
public class UserService implements IUserService {
    private final UserRepository userRepository;
    private final ModelMapper modelMapper;

    @Override
    public User getUserById(Long userId) {
        return userRepository.findById(userId)
                .orElseThrow(() -> new ResourceNotFoundException("User not found"));
    }

    @Override
    public User createUser(CreateUserRequest request) {

        return Optional.of(request)
                .filter(user -> !userRepository.existsByEmail(request.getEmail()))
                .map(req -> {
                    User user = new User();
                    user.setFirstName(req.getFirstName());
                    user.setLastName(req.getLastName());
                    user.setEmail(req.getEmail());
                    user.setPassword(req.getPassword());
                    return userRepository.save(user);
                })
                .orElseThrow(() -> new AlreadyExistException(request.getEmail() + " already exist"));
    }

    @Override
    public User updateUser(UserUpdateRequest request, Long userId) {
        userRepository.findById(userId)
                .map(existingUser -> {
                    existingUser.setFirstName(request.getFirstName());
                    existingUser.setLastName(request.getLastName());
                    return userRepository.save(existingUser);
                })
                .orElseThrow(() -> new ResourceNotFoundException("User not found"));
        return null;
    }

    @Override
    public void deleteUser(Long userId) {
        userRepository.findById(userId)
                .ifPresentOrElse(userRepository::delete,
                        () -> {
                            throw new ResourceNotFoundException("User not found");
                        });
    }

    @Override
    public UserDto convertUserToDto(User user) {
        return modelMapper.map(user, UserDto.class);
    }
}
