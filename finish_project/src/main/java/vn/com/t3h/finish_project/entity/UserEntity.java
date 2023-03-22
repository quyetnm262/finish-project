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
    private Integer gender;

    @Column(name = "email")
    private String email;

    @Column(name = "birthday")
    private Date birthday;

    @Column(name = "username")
    private String username;

    @Column(name = "password")
    private String password;

    @ManyToMany
    @JoinTable(name = "user_role",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "role_id"))
    private List<RoleEntity> roles = new ArrayList<>();

}
