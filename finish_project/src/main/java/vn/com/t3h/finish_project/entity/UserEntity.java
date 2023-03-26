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

    public UserEntity(String username, String password, List<RoleEntity> roles) {
        this.username = username;
        this.password = password;
        this.roles = roles;
    }

    public UserEntity() {
    }

    @Column(name = "birthday")
    private Date birthday;

    @Column(name = "username")
    private String username;

    @Column(name = "password")
    private String password;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "user_role",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "role_id"))
    private List<RoleEntity> roles = new ArrayList<>();

}
