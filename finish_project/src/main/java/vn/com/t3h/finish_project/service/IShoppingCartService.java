package vn.com.t3h.finish_project.service;

import vn.com.t3h.finish_project.entity.ProductEntity;
import vn.com.t3h.finish_project.entity.ShoppingCartEntity;
import vn.com.t3h.finish_project.entity.UserEntity;

public interface IShoppingCartService {

    ShoppingCartEntity addItem(ProductEntity product, Integer quantity, UserEntity user);
    public ShoppingCartEntity updateItem(ProductEntity product, int quantity, UserEntity user);
    public ShoppingCartEntity deleteItem(ProductEntity product, UserEntity user);
}
