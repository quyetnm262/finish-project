package vn.com.t3h.finish_project.controller.manager;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import vn.com.t3h.finish_project.model.dto.ProductDto;
import vn.com.t3h.finish_project.model.dto.UserDto;
import vn.com.t3h.finish_project.service.IProductService;
import vn.com.t3h.finish_project.service.IUserService;

import java.util.List;

@Controller
@RequestMapping("/admin/user")
//@PreAuthorize("hasRole('admin')")
public class UserManagerController {

    @Autowired
    private IUserService iUserService;

    @GetMapping("/user-info/{id}")
    private String getUserInfo(Model model, @PathVariable Integer id, Authentication authentication){
        UserDto dto = iUserService.getUserById(id);
        String role = iUserService.getRole(authentication);
        String name = iUserService.getFullName(authentication);
        model.addAttribute("name",name);
        model.addAttribute("role",role);
        model.addAttribute("user",dto);
        return "/user-admin/user-manager/user-info";
    }
    @GetMapping("/user-info")
    private String detailUser(Model model, Authentication authentication){
        UserDto dto = new UserDto();
        String role = iUserService.getRole(authentication);
        String name = iUserService.getFullName(authentication);
        model.addAttribute("name",name);
        model.addAttribute("role",role);
        model.addAttribute("user",dto);
        return "/user-admin/user-manager/user-info";
    }
    @GetMapping
    private String getUsers(Model model, Authentication authentication){
        List<UserDto> list = iUserService.getUsers();
        String role = iUserService.getRole(authentication);
        String name = iUserService.getFullName(authentication);
        model.addAttribute("name",name);
        model.addAttribute("role",role);
        model.addAttribute("users",list);
        return "/user-admin/user-manager/user-list";
    }

}
