package ovh.jakubk.shop.service.user;

import ovh.jakubk.shop.model.User;
import ovh.jakubk.shop.request.CreateUserRequest;
import ovh.jakubk.shop.request.UserUpdateRequest;

public interface IUserService {
    User getUserById(Long userId);

    User createUser(CreateUserRequest request);

    User updateUser(UserUpdateRequest request, Long userId);

    void deleteUser(Long userId);
}
