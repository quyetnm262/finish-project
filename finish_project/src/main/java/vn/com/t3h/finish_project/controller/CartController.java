package vn.com.t3h.finish_project.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import vn.com.t3h.finish_project.entity.ShoppingCartEntity;
import vn.com.t3h.finish_project.entity.UserEntity;
import vn.com.t3h.finish_project.model.dto.CategoryDto;
import vn.com.t3h.finish_project.service.ICategoryService;
import vn.com.t3h.finish_project.service.IUserService;

import java.util.List;

@Controller
public class CartController {

    @Autowired
    private ICategoryService iCategoryService;

    @Autowired
    private IUserService iUserService;

    @GetMapping("/cart")
    public String getCart(Model model, Authentication authentication){

        List<CategoryDto> categoryDtos = iCategoryService.getCategories();

        UserEntity user = iUserService.findByUserName(authentication.getName());
        ShoppingCartEntity shoppingCart = user.getShoppingCart();
        if (shoppingCart == null){
            model.addAttribute("NoItem","Cart is empty!");
        }
        String name = iUserService.getFullName(authentication);

        model.addAttribute("totalPrice",shoppingCart.getTotalPrices());
        model.addAttribute("shoppingCart",shoppingCart);

        model.addAttribute("name",name);
        model.addAttribute("categorys",categoryDtos);
        return "/guest/cart";
    }
}
