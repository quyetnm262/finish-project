package vn.com.t3h.finish_project.service;


import org.apache.catalina.User;
import vn.com.t3h.finish_project.model.dto.UserDto;

import java.util.List;

public interface IUserService {

    List<UserDto> getUsers();

    UserDto createUser(UserDto userDto);

    UserDto updateUser(UserDto userDto, Integer id);

    void deleteUser(Integer id);

}
