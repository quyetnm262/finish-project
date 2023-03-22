package vn.com.t3h.finish_project.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import vn.com.t3h.finish_project.model.dto.CategoryDto;
import vn.com.t3h.finish_project.service.ICategoryService;

import java.util.List;

@Controller
public class HomeController {

    @Autowired
    private ICategoryService iCategoryService;

    @GetMapping("/")
    public String home(Model model){
        List<CategoryDto> categoryDtos = iCategoryService.getCategories();
        model.addAttribute("categorys",categoryDtos);
        return "home";
    }
}
