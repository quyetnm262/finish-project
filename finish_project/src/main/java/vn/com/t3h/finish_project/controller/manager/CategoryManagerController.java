package vn.com.t3h.finish_project.controller.manager;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import vn.com.t3h.finish_project.model.dto.CategoryDto;
import vn.com.t3h.finish_project.service.ICategoryService;

import java.util.List;

@Controller
@RequestMapping("/category")
public class CategoryManagerController {

    @Autowired
    private ICategoryService iCategoryService;

    @GetMapping("category-info/{id}")
    private String getCategoryInfo(Model model, @PathVariable Integer id){
        CategoryDto dto = iCategoryService.getCategoryById(id);
        model.addAttribute("category",dto);

        return "/category-manager/category-info";
    }
    @GetMapping("category-info")
    private String addCategory(Model model){
        CategoryDto dto = new CategoryDto();
        model.addAttribute("category",dto);
        return "/category-manager/category-info";
    }
    @GetMapping
    private String getCategory(Model model){
        List<CategoryDto> list = iCategoryService.getCategories();
        model.addAttribute("categories",list);
        return "/category-manager/category-list";
    }

}
