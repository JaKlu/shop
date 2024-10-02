package ovh.jakubk.shop.service.order;

import ovh.jakubk.shop.model.Order;

import java.util.List;

public interface IOrderService {
    Order placeOrder(Long id);

    Order getOrder(Long id);

    List<Order> getUserOrders(Long userId);
}
