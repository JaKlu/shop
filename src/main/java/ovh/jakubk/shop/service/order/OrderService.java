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
import ovh.jakubk.shop.service.cart.CartService;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.List;


@Service
@RequiredArgsConstructor
public class OrderService implements IOrderService {
    private final OrderRepository orderRepository;
    private final ProductRepository productRepository;
    private final CartService cartService;

    @Override
    public Order placeOrder(Long userId) {
        Cart cart = cartService.getCartByUserId(userId);
        Order order = createOrder(cart);
        List<OrderItem> orderItemList = createOrderItems(order, cart);
        order.setOrderItems(new HashSet<>(orderItemList));
        order.setTotalAmount(this.calculateTotalAmount(orderItemList));

        Order savedOrder = orderRepository.save(order);
        cartService.clearCart(cart.getId());

        return savedOrder;
    }

    private Order createOrder(Cart cart) {
        Order order = new Order();
        order.setUser(cart.getUser());
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

    @Override
    public List<Order> getUserOrders(Long userId) {
        return orderRepository.findByUserId(userId);
    }
}
