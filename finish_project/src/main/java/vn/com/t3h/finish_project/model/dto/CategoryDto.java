package vn.com.t3h.finish_project.model.dto;

import lombok.Data;


@Data
public class CategoryDto extends AbtractDto{
    private String name;

    private Integer parentId;

    private String [] productName;
}
