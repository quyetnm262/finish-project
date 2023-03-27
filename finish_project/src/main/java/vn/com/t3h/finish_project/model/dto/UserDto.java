package vn.com.t3h.finish_project.model.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import vn.com.t3h.finish_project.entity.ShoppingCartEntity;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserDto extends AbtractDto{

    private String fullName;

    private String gender;

    private String email;

    private String birthdayStr;

    private String username;

    private String password;

    private String roleName;

    private Integer shoppingCartId;
}
