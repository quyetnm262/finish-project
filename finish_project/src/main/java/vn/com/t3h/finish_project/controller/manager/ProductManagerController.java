package vn.com.t3h.finish_project.controller.manager;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import vn.com.t3h.finish_project.model.dto.ProductDto;
import vn.com.t3h.finish_project.service.IProductService;
import vn.com.t3h.finish_project.service.IUserService;

import java.util.List;

@Controller
@RequestMapping("/product")
public class ProductManagerController {

    @Autowired
    private IProductService iProductService;

    @Autowired
    private IUserService iUserService;

    @GetMapping("/product-info/{id}")
    private String getProductInfo(Model model, @PathVariable Integer id, Authentication authentication){
        ProductDto dto = iProductService.getProductById(id);
        UserDetails userDetails = (UserDetails) authentication.getPrincipal();
        String username = userDetails.getUsername();
        String role = userDetails.getAuthorities().toString();
        String name = iUserService.findByUserName(username).getFullName();
        model.addAttribute("name",name);
        model.addAttribute("role",role);
        model.addAttribute("product",dto);

        return "/user-admin/product-manager/product-info";
    }
    @GetMapping("/product-info")
    private String detailProduct(Model model, Authentication authentication){
        ProductDto dto = new ProductDto();
        UserDetails userDetails = (UserDetails) authentication.getPrincipal();
        String username = userDetails.getUsername();
        String role = userDetails.getAuthorities().toString();
        String name = iUserService.findByUserName(username).getFullName();
        model.addAttribute("name",name);
        model.addAttribute("role",role);
        model.addAttribute("product",dto);
        return "/user-admin/product-manager/product-info";
    }
    @GetMapping
    private String getProduct(Model model, Authentication authentication){
        List<ProductDto> list = iProductService.getProducts();
        UserDetails userDetails = (UserDetails) authentication.getPrincipal();
        String username = userDetails.getUsername();
        String role = userDetails.getAuthorities().toString();
        String name = iUserService.findByUserName(username).getFullName();
        model.addAttribute("name",name);
        model.addAttribute("role",role);
        model.addAttribute("products",list);
        return "/user-admin/product-manager/product-list";
    }

}
