package GandA.Corporation.BoardGames.controller;

import GandA.Corporation.BoardGames.domain.Product;
import GandA.Corporation.BoardGames.service.ProductService;
import GandA.Corporation.BoardGames.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@Controller
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    private ProductService productService;

    @GetMapping("/")
    public String viewHomePage(Model model) {
        List<Product> productList = productService.listAll();
        model.addAttribute("productList", productList);

        return "index";
    }

    @GetMapping("/index")
    public String viewHomePageLogin(Model model) {
        String name = userService.getAuntUser().getName();
        model.addAttribute("name",name);
        List<Product> productList = productService.listAll();
        model.addAttribute("productList", productList);


        return "index_login";
    }

    @GetMapping("/productPage/{id}")
    public String viewProductPage(@PathVariable(name = "id") Long id,Model model) {
        Product product = productService.get(id);
        model.addAttribute("product", product);
        return "productPage";
    }



}
