package vn.com.t3h.finish_project.service;


import vn.com.t3h.finish_project.entity.UserEntity;
import vn.com.t3h.finish_project.model.dto.UserDto;

import java.util.List;

public interface IUserService {

    List<UserDto> getUsers();

    UserEntity findByUserName(String username);

    UserDto getUserById(Integer id);

    UserDto createUser(UserDto userDto);

    UserDto updateUser(UserDto userDto, Integer id);

    void deleteUser(Integer id);
    void save(UserDto userDto);

}
