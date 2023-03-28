package vn.com.t3h.finish_project.entity;


import lombok.Data;

import javax.persistence.*;



@Entity
@Table(name = "product")
@Data
public class ProductEntity extends BaseEntity{


    @Column(name = "productCode")
    private String productCode;

    @Column(name = "avatar")
    private String avatar;

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

    @OneToOne(mappedBy = "product")
    private CartItemEntity cartItem;


    @ManyToOne
    @JoinColumn(name = "category_id")
    private CategoryEntity category;

    @Override
    public String toString() {
        return "ProductEntity{" +
                "Id='" + super.getId() + '\'' +
                "productCode='" + productCode + '\'' +
                ", avatar='" + avatar + '\'' +
                ", image='" + image + '\'' +
                ", price=" + price +
                ", oldPrice=" + oldPrice +
                ", name='" + name + '\'' +
                ", title='" + title + '\'' +
                ", shortDescription='" + shortDescription + '\'' +
                ", description='" + description + '\'' +
                '}';
    }
}
