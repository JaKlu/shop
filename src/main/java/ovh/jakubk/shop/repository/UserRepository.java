package ovh.jakubk.shop.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ovh.jakubk.shop.model.User;

public interface UserRepository extends JpaRepository<User, Long> {
    boolean existsByEmail(String email);

}
