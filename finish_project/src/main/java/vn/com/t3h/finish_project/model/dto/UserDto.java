package vn.com.t3h.finish_project.model.dto;

import lombok.Data;

@Data
public class UserDto extends AbtractDto{

    private String fullName;

    private String gender;

    private String email;

    private String birthdayStr;

    private String username;

    private String password;

    private String [] roleNames;
}
