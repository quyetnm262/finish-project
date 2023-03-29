package vn.com.t3h.finish_project.service.impl;


import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;
import vn.com.t3h.finish_project.entity.RoleEntity;
import vn.com.t3h.finish_project.entity.ShoppingCartEntity;
import vn.com.t3h.finish_project.entity.UserEntity;
import vn.com.t3h.finish_project.model.dto.UserDto;
import vn.com.t3h.finish_project.repository.RoleRepository;
import vn.com.t3h.finish_project.repository.ShoppingCartRepository;
import vn.com.t3h.finish_project.repository.UserRepository;
import vn.com.t3h.finish_project.service.IShoppingCartService;
import vn.com.t3h.finish_project.service.IUserService;
import vn.com.t3h.finish_project.utils.DateUtil;

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

    @Autowired
    private ShoppingCartRepository shoppingCartRepository;

    @Autowired
    private IShoppingCartService iShoppingCartService;

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
        userEntity = userRepository.save(userEntity); // save userEntity first
        ShoppingCartEntity shoppingCart = new ShoppingCartEntity();
        shoppingCart.setUser(userEntity);
        shoppingCartRepository.save(shoppingCart);
        userEntity.setShoppingCart(shoppingCart);
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
        Integer cartId = iShoppingCartService.getCartIdByUserId(id);
        shoppingCartRepository.deleteById(cartId);
        userRepository.deleteById(id);
    }

    @Override
    public void save(UserDto userDto){
        UserEntity userEntity =  userDtoToEntity(userDto);
        userRepository.save(userEntity);
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

        if (entity.getRole() != null){
            dto.setRoleName(entity.getRole().getName());
        }
        if (entity.getShoppingCart() != null){
            dto.setShoppingCartId(entity.getShoppingCart().getId());
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

        if (dto.getRoleName() != null){
            RoleEntity role = roleRepository.findByName(dto.getRoleName());
            entity.setRole(role);
        }
        if (dto.getShoppingCartId() != null){
            entity.setShoppingCart(shoppingCartRepository.findById(dto.getShoppingCartId()).get());
        }

        return entity;
    }

    /*----------Converter end-------------------*/

    @Override
    public String getFullName(Authentication authentication){
        if (authentication != null){
            UserDetails userDetails = (UserDetails) authentication.getPrincipal();
            String username = userDetails.getUsername();
            return findByUserName(username).getFullName();
        }
        return null;
    }

    @Override
    public String getRole(Authentication authentication) {
        if (authentication != null){
            UserDetails userDetails = (UserDetails) authentication.getPrincipal();
            return userDetails.getAuthorities().toString();
        }
        return null;
    }
}
