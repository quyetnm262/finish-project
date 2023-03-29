package vn.com.t3h.finish_project.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import vn.com.t3h.finish_project.model.dto.UserDto;
import vn.com.t3h.finish_project.service.IUserService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


@Controller
public class LoginController {

    @Autowired
    private IUserService iUserService;



    @GetMapping("/login")
    public String loginForm(Model model){
        return "/guest/login";
    }

    @GetMapping("/logout")
    public String logoutPage (HttpServletRequest request, HttpServletResponse response) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if (auth != null){
            new SecurityContextLogoutHandler().logout(request, response, auth);
        }
        return "redirect:/login?logout";
    }

   @GetMapping("/register")
    public String register(Model model){
        model.addAttribute("userDto",new UserDto());
        return "guest/register";
   }

   @PostMapping("/register")
    public String registerUser(Model model, @ModelAttribute("userDto")UserDto userDto){
        iUserService.createUser(userDto);
        return "redirect:/login";

   }

}
