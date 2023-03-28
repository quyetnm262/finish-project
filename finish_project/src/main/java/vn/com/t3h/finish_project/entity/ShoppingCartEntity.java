package vn.com.t3h.finish_project.entity;

import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.*;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "shopping_cart")
@Data
public class ShoppingCartEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private int totalItems;
    private double totalPrices;

    @OneToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "user_id")
    private UserEntity user;

    @OneToMany(mappedBy = "cart")
    private List<CartItemEntity> cartItem;

    @Override
    public String toString() {
        return "ShoppingCartEntity{" +
                "id=" + id +
                '}';
    }
}
