package ovh.jakubk.shop.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ovh.jakubk.shop.model.Order;

public interface OrderRepository extends JpaRepository<Order, Long> {
}
