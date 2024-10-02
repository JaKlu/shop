package ovh.jakubk.shop.service.order;

import ovh.jakubk.shop.model.Order;

public interface IOrderService {
    Order placeOrder(Long id);

    Order getOrder(Long id);
}
