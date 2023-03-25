package vn.com.t3h.finish_project.controller.manager;

import org.springframework.beans.factory.annotation.Autowired;
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
public class UserManagerController {

    @Autowired
    private IUserService iUserService;

    @GetMapping("/user-info/{id}")
    private String getUserInfo(Model model, @PathVariable Integer id){
        UserDto dto = iUserService.getUserById(id);
        model.addAttribute("user",dto);

        return "/user-manager/user-info";
    }
    @GetMapping("/user-info")
    private String detailUser(Model model){
        UserDto dto = new UserDto();
        model.addAttribute("user",dto);
        return "/user-manager/user-info";
    }
    @GetMapping
    private String getUsers(Model model){
        List<UserDto> list = iUserService.getUsers();
        model.addAttribute("users",list);
        return "/user-manager/user-list";
    }

}
