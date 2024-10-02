package ovh.jakubk.shop.service.order;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ovh.jakubk.shop.enums.OrderStatus;
import ovh.jakubk.shop.exceptions.ResourceNotFoundException;
import ovh.jakubk.shop.model.Cart;
import ovh.jakubk.shop.model.Order;
import ovh.jakubk.shop.model.OrderItem;
import ovh.jakubk.shop.model.Product;
import ovh.jakubk.shop.repository.OrderRepository;
import ovh.jakubk.shop.repository.ProductRepository;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;


@Service
@RequiredArgsConstructor
public class OrderService implements IOrderService {
    private final OrderRepository orderRepository;
    private final ProductRepository productRepository;

    @Override
    public Order placeOrder(Long userId) {
        return null;
    }

    private Order createOrder(Cart cart) {
        Order order = new Order();
        order.setOrderStatus(OrderStatus.PENDING);
        order.setOrderDate(LocalDate.now());
        return order;
    }

    private List<OrderItem> createOrderItems(Order order, Cart cart) {
        return cart.getItems().stream()
                .map(cartItem -> {
                    Product product = cartItem.getProduct();
                    product.setInventory(product.getInventory() - cartItem.getQuantity());
                    productRepository.save(product);
                    return new OrderItem(
                            order, product, cartItem.getQuantity(), cartItem.getUnitPrice()
                    );
                })
                .toList();
    }

    private BigDecimal calculateTotalAmount(List<OrderItem> orderItems) {
        return orderItems.stream()
                .map(item -> item.getPrice().multiply(new BigDecimal(item.getQuantity())))
                .reduce(BigDecimal.ZERO, BigDecimal::add);
    }

    @Override
    public Order getOrder(Long id) {

        return orderRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Order not found"));
    }
}
