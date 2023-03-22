package vn.com.t3h.finish_project.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import vn.com.t3h.finish_project.model.dto.UserDto;
import vn.com.t3h.finish_project.service.IUserService;

import java.util.List;


@Controller
public class AdminController {

    @Autowired
    private IUserService iUserService;

    @GetMapping("/admin")
    public String getProducts(Model model){
        List<UserDto> dtoList = iUserService.getUsers();
        model.addAttribute("users",dtoList);

        return "/usermanager/index";
    }
}
