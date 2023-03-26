package vn.com.t3h.finish_project.controller.manager;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
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

    @Autowired
    private IUserService iUserService;

    @GetMapping("/role-info/{id}")
    private String getRoleInfo(Model model, @PathVariable Integer id, Authentication authentication){
        RoleDto dto = iRoleService.getRoleById(id);
        UserDetails userDetails = (UserDetails) authentication.getPrincipal();
        String username = userDetails.getUsername();
        String role = userDetails.getAuthorities().toString();
        String name = iUserService.findByUserName(username).getFullName();
        model.addAttribute("name",name);
        model.addAttribute("role",role);
        model.addAttribute("role",dto);

        return "/user-admin/role-manager/role-info";
    }
    @GetMapping("/role-info")
    private String detailRole(Model model, Authentication authentication){
        RoleDto dto = new RoleDto();
        UserDetails userDetails = (UserDetails) authentication.getPrincipal();
        String username = userDetails.getUsername();
        String role = userDetails.getAuthorities().toString();
        String name = iUserService.findByUserName(username).getFullName();
        model.addAttribute("name",name);
        model.addAttribute("role",role);
        model.addAttribute("role",dto);
        return "/user-admin/role-manager/role-info";
    }
    @GetMapping
    private String getRoles(Model model, Authentication authentication){
        List<RoleDto> list = iRoleService.getRoles();
        UserDetails userDetails = (UserDetails) authentication.getPrincipal();
        String username = userDetails.getUsername();
        String role = userDetails.getAuthorities().toString();
        String name = iUserService.findByUserName(username).getFullName();
        model.addAttribute("name",name);
        model.addAttribute("role",role);
        model.addAttribute("roles",list);
        return "/user-admin/role-manager/role-list";
    }

}
