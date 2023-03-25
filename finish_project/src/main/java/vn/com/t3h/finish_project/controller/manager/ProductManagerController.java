package vn.com.t3h.finish_project.controller.manager;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import vn.com.t3h.finish_project.model.dto.ProductDto;
import vn.com.t3h.finish_project.service.IProductService;

import java.util.List;

@Controller
@RequestMapping("/product")
public class ProductManagerController {

    @Autowired
    private IProductService iProductService;

    @GetMapping("/product-info/{id}")
    private String getProductInfo(Model model, @PathVariable Integer id){
        ProductDto dto = iProductService.getProductById(id);
        model.addAttribute("product",dto);

        return "/product-manager/product-info";
    }
    @GetMapping("/product-info")
    private String detailProduct(Model model){
        ProductDto dto = new ProductDto();
        model.addAttribute("product",dto);
        return "/product-manager/product-info";
    }
    @GetMapping
    private String getProduct(Model model){
        List<ProductDto> list = iProductService.getProducts();
        model.addAttribute("products",list);
        return "/product-manager/product-list";
    }
//    @GetMapping("/{index}")
//    private String pageProduct(Model model, @PathVariable Integer index){
//        Page<ProductEntity> products = iProductService.pageProducts(index);
//        List<ProductDto> list = iProductService.getProducts();
//        model.addAttribute("products",list);
//        model.addAttribute("size",products.getSize());
//        model.addAttribute("totalPages",products.getTotalPages());
//        model.addAttribute("currentPage",index);
//        return "/product-manager/product-list";
//    }

}
