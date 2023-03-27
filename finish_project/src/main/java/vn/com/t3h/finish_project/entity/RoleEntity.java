package vn.com.t3h.finish_project.entity;

import lombok.Data;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "role")
@Data
public class RoleEntity extends BaseEntity{

    @Column(name = "roleName")
    private String name;

    @OneToMany(mappedBy = "role",fetch = FetchType.EAGER)
    private List<UserEntity> users = new ArrayList<>();
}
