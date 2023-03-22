package vn.com.t3h.finish_project.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import vn.com.t3h.finish_project.model.dto.CategoryDto;
import vn.com.t3h.finish_project.model.dto.ProductDto;
import vn.com.t3h.finish_project.service.ICategoryService;
import vn.com.t3h.finish_project.service.IProductService;

import java.util.List;

@Controller
public class ProductController1 {

    @Autowired
    private IProductService iProductService;

    @Autowired
    private ICategoryService iCategoryService;

    @GetMapping("/products")
    public String getProducts(Model model){

        List<ProductDto> productDtos = iProductService.getProducts();
        List<CategoryDto> categoryDtos = iCategoryService.getCategories();
        model.addAttribute("categorys",categoryDtos);
        model.addAttribute("products",productDtos);
        return "guest/list";
    }

    @GetMapping("/products/{categoryId}")
    public String getProductsById(Model model, @PathVariable Integer categoryId){
        List<ProductDto> listProducts = iProductService.getProductsByCategoryId(categoryId);
        CategoryDto categoryDto = iCategoryService.getCategoryById(categoryId);
        List<CategoryDto> categoryDtos = iCategoryService.getCategories();
        model.addAttribute("categorys",categoryDtos);
        model.addAttribute("category",categoryDto);
        model.addAttribute("products",listProducts);
        return "guest/productById-list";
    }
}
