package ovh.jakubk.shop.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ovh.jakubk.shop.model.CartItem;

public interface CartItemRepository extends JpaRepository<CartItem, Long> {
    void deleteAllByCartId(Long id);
}

