package GandA.Corporation.BoardGames.controller;

import GandA.Corporation.BoardGames.domain.*;
import GandA.Corporation.BoardGames.service.OrderService;
import GandA.Corporation.BoardGames.service.OrderedProductService;
import GandA.Corporation.BoardGames.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import java.io.IOException;
import java.util.ArrayList;
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

    @RequestMapping("/descriptionProduct/{id}")
    public String showDescriptionProductProduct(@PathVariable(name = "id") Long id, Model model) {
        Product product = productService.get(id);
        model.addAttribute("product", product);
        return "descriptionProduct";
    }

    @RequestMapping("/shortDescriptionProduct/{id}")
    public String showShortDescriptionProduct(@PathVariable(name = "id") Long id, Model model) {
        Product product = productService.get(id);
        model.addAttribute("product", product);
        return "shortDescriptionProduct";
    }

    @RequestMapping("/rulesProduct/{id}")
    public String showRulesProduct(@PathVariable(name = "id") Long id, Model model) {
        Product product = productService.get(id);
        model.addAttribute("product", product);
        return "rulesProduct";
    }

    @RequestMapping("/newProduct")
    public String showNewProduct(Model model) {

        Product product = new Product();
        model.addAttribute("product",product);

        return "addProduct";
    }

    @RequestMapping(value = "/productSave", method = RequestMethod.POST)
    public String ProductSave(@ModelAttribute("product") Product product, @RequestParam("filePhoto1") MultipartFile filePhoto1, @RequestParam("filePhoto2") MultipartFile filePhoto2, @RequestParam("filePhoto3") MultipartFile filePhoto3, @RequestParam("filePhoto4") MultipartFile filePhoto4) throws IOException {

        productService.save(product, filePhoto1, filePhoto2, filePhoto3, filePhoto4);

        return "redirect:/product";
    }

    @RequestMapping("/editProduct/{id}")
    public ModelAndView showEditProduct(@PathVariable(name = "id") Long id) {
        ModelAndView mav = new ModelAndView("editProduct");

        Product product = productService.get(id);
        mav.addObject("product", product);

        return mav;
    }

    @RequestMapping(value = "/editProductSave{id}", method = RequestMethod.POST)
    public String editProductSave(@PathVariable(name = "id") Long id,@ModelAttribute("product") Product product) throws IOException {

        System.out.println();
        Product productSave = productService.get(id);
        productSave.setDescription(product.getDescription());
        productSave.setDiscount(product.getDiscount());
        productSave.setName(product.getName());
        productSave.setNumber_of_players(product.getNumber_of_players());
        productSave.setPrice(product.getPrice());
        productSave.setQuantity_in_stock(product.getQuantity_in_stock());
        productSave.setRules(product.getRules());
        productSave.setShort_description(product.getShort_description());

        productService.saveNotPhoto(productSave);

        return "redirect:/product";
    }

    @RequestMapping("/editPhotoProduct/{id}")
    public ModelAndView showeditPhotoProduct(@PathVariable(name = "id") Long id) {
        ModelAndView mav = new ModelAndView("editPhotoProduct");

        Product product = productService.get(id);
        mav.addObject("product", product);

        return mav;
    }

    @RequestMapping(value = "/editPhotoProductSave{id}", method = RequestMethod.POST)
    public String editPhotoProductSave(@PathVariable(name = "id") Long id, @RequestParam("filePhoto1") MultipartFile filePhoto1, @RequestParam("filePhoto2") MultipartFile filePhoto2, @RequestParam("filePhoto3") MultipartFile filePhoto3, @RequestParam("filePhoto4") MultipartFile filePhoto4) throws IOException {

        Product product = productService.get(id);

        productService.delete(product.getId());

        Product productSave = new Product();
        productSave.setDescription(product.getDescription());
        productSave.setDiscount(product.getDiscount());
        productSave.setName(product.getName());
        productSave.setNumber_of_players(product.getNumber_of_players());
        productSave.setPrice(product.getPrice());
        productSave.setQuantity_in_stock(product.getQuantity_in_stock());
        productSave.setRules(product.getRules());
        productSave.setShort_description(product.getShort_description());

        productService.save(productSave, filePhoto1, filePhoto2, filePhoto3, filePhoto4);

        return "redirect:/product";
    }

    @RequestMapping("/searchProduct")
    public String searchProduct(Model model, @Param("keyword") String keyword) {
        List<Product> listProduct = productService.findByNameContaining(keyword);
        System.out.println(listProduct);
        model.addAttribute("listProduct", listProduct);
        model.addAttribute("keyword", keyword);

        return "redirect:/product";
    }

    @RequestMapping("/deleteProduct/{id}")
    public String deleteProduct(@PathVariable(name = "id") Long id) {

        productService.delete(id);

        return "redirect:/product";
    }

    @RequestMapping("/Order")
    public String showOrder(Model model) {
        List<Order> listOrder = orderService.listAll();
        model.addAttribute("listOrder", listOrder);
        return "order";
    }

    @RequestMapping("/moreOrder/{id}")
    public String showMoreOrder(@PathVariable(name = "id") Long id, Model model) {
        Order order = orderService.get(id);
        List<Product> product = productService.listAll();
        model.addAttribute("order", order);
        model.addAttribute("product", product);
        return "order";
    }

    @RequestMapping(value = "/saveOrderProduct", method = RequestMethod.POST)
    public String saveOrderProduct(@PathVariable(name = "id") Long id, @Param("amount") int amount) throws IOException {

        OrderedProduct orderedProduct = orderedProductService.get(id);
        orderedProduct.setAmount(amount);

        orderedProductService.save(orderedProduct);

        return "redirect:/order";
    }

    @RequestMapping(value = "/addOrderProduct", method = RequestMethod.POST)
    public String saveOrderProduct(@PathVariable(name = "id") Long id, @Param("idProduct") Long idProduct) throws IOException {

        OrderedProduct orderedProduct = orderedProductService.get(id);
        orderedProduct.setProduct(productService.get(idProduct));

        orderedProductService.save(orderedProduct);

        return "redirect:/order";
    }

    @RequestMapping("/editOrder/{id}")
    public ModelAndView showEditOrder(@PathVariable(name = "id") Long id) {
        ModelAndView mav = new ModelAndView("editOrder");

        Order order = orderService.get(id);
        mav.addObject("order", order);

        return mav;
    }

    @RequestMapping(value = "/editOrderSave", method = RequestMethod.POST)
    public String editOrderSave(@ModelAttribute("order") Order order) throws IOException {

        orderService.save(order);

        return "redirect:/order";
    }

    @RequestMapping("/searchOrder")
    public String searchOrder(Model model, @Param("key") Long key) {
        List<Order> listOrder = orderService.findByIdContaining(key);
        model.addAttribute("listOrder", listOrder);
        model.addAttribute("key", key);

        return "redirect:/order";
    }

    @RequestMapping("/deleteOrder/{id}")
    public String deleteUser(@PathVariable(name = "id") Long id) {

        orderService.delete(id);

        return "redirect:/order";
    }


}
