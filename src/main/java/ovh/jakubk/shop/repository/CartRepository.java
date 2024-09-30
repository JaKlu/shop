package ovh.jakubk.shop.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ovh.jakubk.shop.model.Cart;

public interface CartRepository extends JpaRepository<Cart, Long> {
}
