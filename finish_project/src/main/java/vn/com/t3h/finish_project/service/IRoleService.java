package vn.com.t3h.finish_project.service;

import vn.com.t3h.finish_project.model.dto.RoleDto;

import java.util.List;

public interface IRoleService {

    List<RoleDto> getRoles();

    RoleDto createRole(RoleDto roleDto);

    RoleDto updateRole(RoleDto roleDto, Integer id);

    void deleteRole(Integer id);
}
