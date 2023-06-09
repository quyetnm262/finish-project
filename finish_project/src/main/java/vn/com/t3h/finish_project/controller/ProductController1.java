package vn.com.t3h.finish_project.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import vn.com.t3h.finish_project.model.dto.CategoryDto;
import vn.com.t3h.finish_project.model.dto.ProductDto;
import vn.com.t3h.finish_project.service.ICategoryService;
import vn.com.t3h.finish_project.service.IProductService;
import vn.com.t3h.finish_project.service.IUserService;

import java.util.List;

@Controller
public class ProductController1 {

    @Autowired
    private IProductService iProductService;

    @Autowired
    private ICategoryService iCategoryService;

    @Autowired
    private IUserService iUserService;

    @GetMapping("/products")
    public String getProducts(Model model, Authentication authentication){

        List<ProductDto> productDtos = iProductService.getProducts();
        List<CategoryDto> categoryDtos = iCategoryService.getCategories();

        String name = iUserService.getFullName(authentication);
        model.addAttribute("name",name);
        model.addAttribute("name",name);

        model.addAttribute("categorys",categoryDtos);
        model.addAttribute("products",productDtos);
        return "guest/list";
    }

    @GetMapping("/products/{categoryId}")
    public String getProductsByCategoryId(Model model, @PathVariable Integer categoryId, Authentication authentication){
        List<ProductDto> listProducts = iProductService.getProductsByCategoryId(categoryId);
        CategoryDto categoryDto = iCategoryService.getCategoryById(categoryId);
        List<CategoryDto> categoryDtos = iCategoryService.getCategories();

        String name = iUserService.getFullName(authentication);
        model.addAttribute("name",name);

        model.addAttribute("categorys",categoryDtos);
        model.addAttribute("category",categoryDto);
        model.addAttribute("products",listProducts);
        return "guest/productById-list";
    }

    @GetMapping("/products/detail/{productName}")
    public String getProductsById(Model model, @PathVariable String productName, Authentication authentication){
        List<CategoryDto> categoryDtos = iCategoryService.getCategories();
        model.addAttribute("categorys",categoryDtos);
        ProductDto productDto = iProductService.getProductByName(productName);

        String name = iUserService.getFullName(authentication);
        model.addAttribute("name",name);

        model.addAttribute("product",productDto);
        return "guest/product-details";
    }


}
