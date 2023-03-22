package vn.com.t3h.finish_project.service.impl;


import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import vn.com.t3h.finish_project.entity.RoleEntity;
import vn.com.t3h.finish_project.entity.UserEntity;
import vn.com.t3h.finish_project.model.dto.RoleDto;
import vn.com.t3h.finish_project.repository.RoleRepository;
import vn.com.t3h.finish_project.repository.UserRepository;
import vn.com.t3h.finish_project.service.IRoleService;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class RoleServiceImpl implements IRoleService {

    /*----------Inject start-----------*/
    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private UserRepository userRepository;

    /*----------Inject end-----------*/



    @Override
    public List<RoleDto> getRoles() {

        List<RoleEntity> roleEntityList = roleRepository.findAll();
        List<RoleDto> roleDtoList = roleEntityList.stream().map(this::roleEntityToDto).collect(Collectors.toList());
        return roleDtoList;
    }

    @Override
    public RoleDto createRole(RoleDto roleDto) {

        RoleEntity entity = roleDtoToEntity(roleDto);
        entity = roleRepository.save(entity);
        RoleDto dto = roleEntityToDto(entity);
        return dto;
    }

    @Override
    public RoleDto updateRole(RoleDto roleDto, Integer id) {
        RoleEntity entity = roleDtoToEntity(roleDto);
        entity.setId(id);
        entity = roleRepository.save(entity);
        RoleDto dto = roleEntityToDto(entity);
        return dto;
    }

    @Override
    public void deleteRole(Integer id) {
        roleRepository.deleteById(id);
    }
    /*----------Converter start-----------*/

    public RoleDto roleEntityToDto(RoleEntity entity){
        RoleDto dto = modelMapper.map(entity, RoleDto.class);

        if (entity.getUsers() != null){
            String [] userNames = entity.getUsers().stream().map(UserEntity::getFullName).toArray(String[]::new);
            dto.setUserNames(userNames);
        }
        return dto;

    }

    public RoleEntity roleDtoToEntity(RoleDto dto){
        RoleEntity entity = modelMapper.map(dto, RoleEntity.class);
        List<UserEntity> userEntityList = new ArrayList<>();
        if (dto.getUserNames() != null){
            List<String> userNames = Arrays.asList(dto.getUserNames());
            userNames.forEach(fullName ->{
                UserEntity userEntity = userRepository.findByFullName(fullName);
                userEntityList.add(userEntity);
            });
            entity.setUsers(userEntityList);

        }

        return entity;
    }

    /*----------Converter end-----------*/

}
