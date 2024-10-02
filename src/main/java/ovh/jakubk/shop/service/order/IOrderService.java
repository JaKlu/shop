package ovh.jakubk.shop.service.order;

import ovh.jakubk.shop.dto.OrderDto;
import ovh.jakubk.shop.model.Order;

import java.util.List;

public interface IOrderService {
    Order placeOrder(Long id);

    OrderDto getOrder(Long id);

    List<OrderDto> getUserOrders(Long userId);
}
