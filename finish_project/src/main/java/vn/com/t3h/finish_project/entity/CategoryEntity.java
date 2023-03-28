package vn.com.t3h.finish_project.entity;


import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import java.util.ArrayList;
import java.util.List;


@Entity
@Table(name = "category")
@Data
public class CategoryEntity extends BaseEntity{


    @Column(name = "name")
    private String name;

    @Column(name = "parentId")
    private Integer parentId;

    @OneToMany(mappedBy = "category")
    private List<ProductEntity> products = new ArrayList<>();

    @Override
    public String toString() {
        return "CategoryEntity{" +
                "Id='" + super.getId() + '\'' +
                "name='" + name + '\'' +
                ", parentId=" + parentId +
                '}';
    }
}
