package vn.com.t3h.finish_project.model.dto;

import lombok.Data;

@Data
public class ProductDto extends AbtractDto{

    private String productCode;
    private String avatar;
    private String image;
    private Double price;
    private Double oldPrice;
    private String name;
    private String title;
    private String shortDescription;
    private String description;
    private Integer cartItemId;
    private Integer categoryId;
}
