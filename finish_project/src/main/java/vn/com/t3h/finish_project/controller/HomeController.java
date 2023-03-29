package vn.com.t3h.finish_project.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import vn.com.t3h.finish_project.model.dto.CategoryDto;
import vn.com.t3h.finish_project.model.dto.ProductDto;
import vn.com.t3h.finish_project.service.ICategoryService;
import vn.com.t3h.finish_project.service.IProductService;
import vn.com.t3h.finish_project.service.IUserService;

import java.util.List;

@Controller
public class HomeController {

    @Autowired
    private ICategoryService iCategoryService;

    @Autowired
    private IUserService iUserService;

    @Autowired
    private IProductService iProductService;

    @GetMapping(value = {"/home","/"})
    public String home(Model model, Authentication authentication){
        List<ProductDto> productDtos = iProductService.getProducts();
        if (productDtos.size() >3){
            productDtos = iProductService.getProducts().subList(0,5);
        }
        List<CategoryDto> categoryDtos = iCategoryService.getCategories();

        String name = iUserService.getFullName(authentication);
        model.addAttribute("name",name);
        model.addAttribute("name",name);

        model.addAttribute("categorys",categoryDtos);
        model.addAttribute("products",productDtos);
        return "/guest/home";
    }

}
