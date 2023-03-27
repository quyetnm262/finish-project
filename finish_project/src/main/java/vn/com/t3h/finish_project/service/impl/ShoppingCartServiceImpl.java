package vn.com.t3h.finish_project.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import vn.com.t3h.finish_project.entity.CartItemEntity;
import vn.com.t3h.finish_project.entity.ProductEntity;
import vn.com.t3h.finish_project.entity.ShoppingCartEntity;
import vn.com.t3h.finish_project.entity.UserEntity;
import vn.com.t3h.finish_project.repository.CartItemRepository;
import vn.com.t3h.finish_project.repository.ShoppingCartRepository;
import vn.com.t3h.finish_project.service.IShoppingCartService;

import java.util.HashSet;
import java.util.Set;

@Service
public class ShoppingCartServiceImpl implements IShoppingCartService {

    @Autowired
    private ShoppingCartRepository shoppingCartRepository;

    @Autowired
    private CartItemRepository cartItemRepository;

    @Override
    public ShoppingCartEntity addItem(ProductEntity product, Integer quantity, UserEntity user) {

        ShoppingCartEntity cart = user.getShoppingCart();

        if (cart == null){
            cart = new ShoppingCartEntity();
        }
        Set<CartItemEntity> cartItemEntities = cart.getCartItem();
        CartItemEntity cartItem = findCartItem(cartItemEntities, product.getId());

        if (cartItemEntities == null){
            cartItemEntities = new HashSet<>();
            if (cartItem == null){
                buildCartItem(product, quantity, cart, cartItemEntities);

            }
        }else {
            if (cartItem == null){
                buildCartItem(product, quantity, cart, cartItemEntities);

            }else {
                cartItem.setQuantity(cartItem.getQuantity() + quantity);
                cartItem.setTotalPrice(cartItem.getTotalPrice() + quantity * product.getPrice());
                cartItemRepository.save(cartItem);
            }
        }
        cart.setCartItem(cartItemEntities);

        int totalItem = totalItems(cart.getCartItem());
        double totalPrice = totalPrice(cart.getCartItem());

        cart.setTotalPrices(totalPrice);
        cart.setTotalItems(totalItem);
        cart.setUser(user);

        return shoppingCartRepository.save(cart);
    }

    private void buildCartItem(ProductEntity product, Integer quantity, ShoppingCartEntity cart, Set<CartItemEntity> cartItemEntities) {
        CartItemEntity cartItem;
        cartItem = new CartItemEntity();
        cartItem.setQuantity(quantity);
        cartItem.setCart(cart);
        cartItem.setProduct(product);
        cartItem.setTotalPrice(quantity * product.getPrice());

        cartItemEntities.add(cartItem);
        cartItemRepository.save(cartItem);
    }

    private CartItemEntity findCartItem(Set<CartItemEntity> cartItems, Integer productId) {
        if (cartItems == null) {
            return null;
        }
        CartItemEntity cartItem = null;

        for (CartItemEntity item : cartItems) {
            if (item.getProduct().getId() == productId) {
                cartItem = item;
            }
        }
        return cartItem;
    }

    private int totalItems(Set<CartItemEntity> cartItems){
        int totalItems = 0;
        for(CartItemEntity item : cartItems){
            totalItems += item.getQuantity();
        }
        return totalItems;
    }

    private double totalPrice(Set<CartItemEntity> cartItems){
        double totalPrice = 0.0;

        for(CartItemEntity item : cartItems){
            totalPrice += item.getTotalPrice();
        }

        return totalPrice;
    }
}
