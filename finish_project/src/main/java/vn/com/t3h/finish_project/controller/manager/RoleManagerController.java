package vn.com.t3h.finish_project.controller.manager;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import vn.com.t3h.finish_project.model.dto.RoleDto;
import vn.com.t3h.finish_project.model.dto.UserDto;
import vn.com.t3h.finish_project.service.IRoleService;
import vn.com.t3h.finish_project.service.IUserService;

import java.util.List;

@Controller
@RequestMapping("/admin/role")
public class RoleManagerController {

    @Autowired
    private IRoleService iRoleService;

    @GetMapping("/role-info/{id}")
    private String getRoleInfo(Model model, @PathVariable Integer id){
        RoleDto dto = iRoleService.getRoleById(id);
        model.addAttribute("role",dto);

        return "/role-manager/role-info";
    }
    @GetMapping("/role-info")
    private String detailRole(Model model){
        RoleDto dto = new RoleDto();
        model.addAttribute("role",dto);
        return "/role-manager/role-info";
    }
    @GetMapping
    private String getRoles(Model model){
        List<RoleDto> list = iRoleService.getRoles();
        model.addAttribute("roles",list);
        return "/role-manager/role-list";
    }

}
