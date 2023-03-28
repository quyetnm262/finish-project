package vn.com.t3h.finish_project.controller.manager;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import vn.com.t3h.finish_project.model.dto.CategoryDto;
import vn.com.t3h.finish_project.service.ICategoryService;
import vn.com.t3h.finish_project.service.IUserService;

import java.util.List;

@Controller
@RequestMapping("/admin/category")
public class CategoryManagerController {

    @Autowired
    private ICategoryService iCategoryService;

    @Autowired
    private IUserService iUserService;

    @GetMapping("category-info/{id}")
    private String getCategoryInfo(Model model, @PathVariable Integer id, Authentication authentication){
        CategoryDto dto = iCategoryService.getCategoryById(id);
        List<CategoryDto> list = iCategoryService.getCategories();
        String name = iUserService.getFullName(authentication);
        String role = iUserService.getRole(authentication);
        model.addAttribute("name",name);
        model.addAttribute("role",role);
        model.addAttribute("categories",list);
        model.addAttribute("category",dto);

        return "/user-admin/category-manager/category-info";
    }
    @GetMapping("category-info")
    private String addCategory(Model model, Authentication authentication){
        CategoryDto dto = new CategoryDto();
        List<CategoryDto> list = iCategoryService.getCategories();
        String name = iUserService.getFullName(authentication);
        String role = iUserService.getRole(authentication);
        model.addAttribute("name",name);
        model.addAttribute("role",role);
        model.addAttribute("category",dto);
        model.addAttribute("categories",list);
        return "/user-admin/category-manager/category-info";
    }
    @GetMapping
    private String getCategory(Model model, Authentication authentication){
        List<CategoryDto> list = iCategoryService.getCategories();
        String name = iUserService.getFullName(authentication);
        String role = iUserService.getRole(authentication);
        model.addAttribute("name",name);
        model.addAttribute("role",role);

        model.addAttribute("categories",list);
        return "/user-admin/category-manager/category-list";
    }

}
