package vn.com.t3h.finish_project.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import vn.com.t3h.finish_project.entity.ProductEntity;
import vn.com.t3h.finish_project.entity.ShoppingCartEntity;
import vn.com.t3h.finish_project.entity.UserEntity;
import vn.com.t3h.finish_project.model.dto.CategoryDto;
import vn.com.t3h.finish_project.repository.ProductRepository;
import vn.com.t3h.finish_project.repository.UserRepository;
import vn.com.t3h.finish_project.service.ICategoryService;
import vn.com.t3h.finish_project.service.IProductService;
import vn.com.t3h.finish_project.service.IShoppingCartService;
import vn.com.t3h.finish_project.service.IUserService;
import vn.com.t3h.finish_project.service.impl.ShoppingCartServiceImpl;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Controller
public class CartController {

    @Autowired
    private ICategoryService iCategoryService;

    @Autowired
    private IUserService iUserService;

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private IShoppingCartService iShoppingCartService;


    @GetMapping("/cart")
    public String getCart(Model model, Authentication authentication){

        List<CategoryDto> categoryDtos = iCategoryService.getCategories();

        UserEntity user = iUserService.findByUserName(authentication.getName());
        ShoppingCartEntity shoppingCart = user.getShoppingCart();
        if (shoppingCart.getTotalItems() == 0){
            model.addAttribute("NoItem","Cart is empty!");
        }
        String name = iUserService.getFullName(authentication);

        model.addAttribute("totalPrice",shoppingCart.getTotalPrices());
        model.addAttribute("shoppingCart",shoppingCart);

        model.addAttribute("name",name);
        model.addAttribute("categorys",categoryDtos);
        return "/guest/cart";
    }

    @PostMapping("/add-to-cart")
    public String addItemToCart(Model model,
            @RequestParam("productId") Integer productId,
            @RequestParam(value = "quantity", required = false, defaultValue = "1") Integer quantity,
            Authentication authentication,HttpServletRequest request){

        ProductEntity product =productRepository.findById(productId).get();
        String username = authentication.getName();
        UserEntity user = iUserService.findByUserName(username);

        ShoppingCartEntity cart = iShoppingCartService.addItem(product, quantity, user);

        return "redirect:/cart";

    }

    @RequestMapping(value = "/update-cart", method = RequestMethod.POST, params = "action=update")
    public String updateCart(@RequestParam("quantity") int quantity,
                             @RequestParam("id") Integer productId,
                             Model model, Authentication authentication){


        String username = authentication.getName();
        UserEntity user = iUserService.findByUserName(username);
        ProductEntity product = productRepository.findById(productId).get();
        ShoppingCartEntity cart = iShoppingCartService.updateItem(product, quantity, user);

        model.addAttribute("shoppingCart", cart);
        return "redirect:/cart";


    }


    @RequestMapping(value = "/update-cart", method = RequestMethod.POST, params = "action=delete")
    public String deleteItemFromCart(@RequestParam("id") Integer productId,
                                     Model model,Authentication authentication){

        String username = authentication.getName();
        UserEntity user = iUserService.findByUserName(username);
        ProductEntity product = productRepository.findById(productId).get();
        ShoppingCartEntity cart = iShoppingCartService.deleteItem(product, user);
        model.addAttribute("shoppingCart", cart);
        return "redirect:/cart";

    }
}
