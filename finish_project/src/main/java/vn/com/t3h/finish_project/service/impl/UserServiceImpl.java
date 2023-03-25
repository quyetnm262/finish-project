package vn.com.t3h.finish_project.service.impl;


import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Service;
import vn.com.t3h.finish_project.entity.RoleEntity;
import vn.com.t3h.finish_project.entity.UserEntity;
import vn.com.t3h.finish_project.model.dto.UserDto;
import vn.com.t3h.finish_project.repository.RoleRepository;
import vn.com.t3h.finish_project.repository.UserRepository;
import vn.com.t3h.finish_project.service.IUserService;
import vn.com.t3h.finish_project.utils.DateUtil;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements IUserService {

    /*---------Inject start----------------*/
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private RoleRepository roleRepository;

    /*------------Inject end-------------*/
    @Override
    public List<UserDto> getUsers() {
        List<UserEntity> userEntities = userRepository.findAll();
        List<UserDto> userDtos = userEntities.stream().map(this::userEntityToDto)
                .collect(Collectors.toList());

        return userDtos;
    }

    @Override
    public UserEntity findByUserName(String username) {
        return userRepository.findByUsername(username);
    }

    @Override
    public UserDto getUserById(Integer id) {
        UserEntity entity = userRepository.findById(id).get();
        return userEntityToDto(entity);
    }

    @Override
    public UserDto createUser(UserDto userDto) {

        UserEntity userEntity = userDtoToEntity(userDto);
        userEntity = userRepository.save(userEntity);
        return userEntityToDto(userEntity);
    }

    @Override
    public UserDto updateUser(UserDto userDto, Integer id) {

        UserEntity entity = userDtoToEntity(userDto);
        entity.setId(id);
        entity = userRepository.save(entity);
        return userEntityToDto(entity);
    }

    @Override
    public void deleteUser(Integer id) {
        userRepository.deleteById(id);
    }





    /*----------Converter start-------------------*/

    private UserDto userEntityToDto(UserEntity entity){
        UserDto dto = modelMapper.map(entity, UserDto.class);

        if (entity.getBirthday() != null){
            try {
                dto.setBirthdayStr(DateUtil.convertDateToString(entity.getBirthday(),DateUtil.FORMAT_DATE));
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        }

        if (entity.getRoles() != null){
            String [] roleNames = entity.getRoles().stream().map(RoleEntity::getName)
                    .toArray(String[]::new);
            dto.setRoleNames(roleNames);
        }

        return dto;
    }

    public UserEntity userDtoToEntity(UserDto dto){
        UserEntity entity = modelMapper.map(dto, UserEntity.class);
        if (dto.getBirthdayStr() != null){
            try {
                entity.setBirthday(DateUtil.convertStringToDate(dto.getBirthdayStr(),DateUtil.FORMAT_DATE));
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        }

        if (dto.getRoleNames() != null){
            List<RoleEntity> entityList = new ArrayList<>();
            List<String> roleNames = Arrays.asList(dto.getRoleNames());
            roleNames.forEach(roleName ->{
                RoleEntity roleEntity = roleRepository.findByName(roleName);
                entityList.add(roleEntity);
            });
            entity.setRoles(entityList);

        }

        return entity;
    }

    /*----------Converter end-------------------*/

}
