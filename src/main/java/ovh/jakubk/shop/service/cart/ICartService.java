package ovh.jakubk.shop.service.cart;

import ovh.jakubk.shop.model.Cart;

import java.math.BigDecimal;

public interface ICartService {
    Cart getCart(Long id);

    void clearCart(Long id);

    BigDecimal getTotalPrice(Long id);

    Long initializeNewCart();
}
