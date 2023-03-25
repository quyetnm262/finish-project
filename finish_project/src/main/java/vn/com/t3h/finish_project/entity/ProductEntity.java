package vn.com.t3h.finish_project.entity;


import com.fasterxml.jackson.databind.JsonNode;
import lombok.Data;

import javax.persistence.*;



@Entity
@Table(name = "product")
@Data
public class ProductEntity extends BaseEntity{


    @Column(name = "productCode")
    private String productCode;

    @Column(name = "avatarUrl")
    private String avatarUrl;

    @Column(name = "image")
    private String image;


    @Column(name = "price")
    private Double price;

    @Column(name = "oldPrice")
    private Double oldPrice;

    @Column(name = "name")
    private String name;

    @Column(name = "title")
    private String title;

    @Column(name = "shortDescription")
    private String shortDescription;

    @Column(name = "description")
    private String description;

    @ManyToOne
    @JoinColumn(name = "category_id")
    private CategoryEntity category;

    public Integer getCategoryId() {
        if (category != null) {
            return category.getId();
        }
        return null;
    }

}
