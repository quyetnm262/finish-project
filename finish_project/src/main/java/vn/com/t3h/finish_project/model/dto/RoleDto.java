package vn.com.t3h.finish_project.model.dto;

import lombok.Data;

@Data
public class RoleDto extends AbtractDto{
    private String name;

    private String [] userNames;
}
