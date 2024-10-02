package ovh.jakubk.shop.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ovh.jakubk.shop.model.Order;

import java.util.List;

public interface OrderRepository extends JpaRepository<Order, Long> {
    List<Order> findByUserId(Long userId);

}
