package GandA.Corporation.BoardGames.controller;

import GandA.Corporation.BoardGames.domain.*;
import GandA.Corporation.BoardGames.service.OrderService;
import GandA.Corporation.BoardGames.service.OrderedProductService;
import GandA.Corporation.BoardGames.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@Controller
public class ManagerController {

    @Autowired
    private ProductService productService;

    @Autowired
    private OrderService orderService;

    @Autowired
    private OrderedProductService orderedProductService;

    @RequestMapping("/manager")
    public String viewManagerPage(Model model) {
        return "manager";
    }

    @RequestMapping("/product")
    public String viewProductPage(Model model) {
        List<Product> listProduct = productService.listAll();
        model.addAttribute("listProduct", listProduct);
        return "product";
    }

    @RequestMapping("/newProduct")
    public String showNewProduct(Model model) {
        Product product = new Product();
        model.addAttribute("product", product);
        return "addProduct";
    }

    @RequestMapping(value = "/productSave", method = RequestMethod.POST)
    public String ProductSave(@ModelAttribute("product") Product product, @RequestParam("filePhoto1") MultipartFile filePhoto1, @RequestParam("filePhoto2") MultipartFile filePhoto2, @RequestParam("filePhoto3") MultipartFile filePhoto3, @RequestParam("filePhoto4") MultipartFile filePhoto4) throws IOException {
        System.out.println(product.getDescription());
        productService.save(product, filePhoto1, filePhoto2, filePhoto3, filePhoto4);

        return "redirect:/product";
    }


}
