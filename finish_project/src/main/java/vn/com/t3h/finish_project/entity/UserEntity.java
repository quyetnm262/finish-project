package vn.com.t3h.finish_project.entity;

import lombok.Data;

import javax.persistence.*;
import java.util.*;


@Entity
@Table(name = "user")
@Data
public class UserEntity extends BaseEntity{

    @Column(name = "fullName")
    private String fullName;

    @Column(name = "gender")
    private String gender;

    @Column(name = "email")
    private String email;

    public UserEntity(String username, String password, RoleEntity role) {
        this.username = username;
        this.password = password;
        this.role = role;
    }

    public UserEntity() {
    }

    @Column(name = "birthday")
    private Date birthday;

    @Column(name = "username")
    private String username;

    @Column(name = "password")
    private String password;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "role_id")
    private RoleEntity role;

    @OneToOne(mappedBy = "user")
    private ShoppingCartEntity shoppingCart;

}
