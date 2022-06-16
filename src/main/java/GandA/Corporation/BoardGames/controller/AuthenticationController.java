package GandA.Corporation.BoardGames.controller;

import GandA.Corporation.BoardGames.domain.User;
import GandA.Corporation.BoardGames.service.SecurityService;
import GandA.Corporation.BoardGames.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class AuthenticationController {

    @Autowired
    private UserService userService;

    @Autowired
    private SecurityService securityService;

    @RequestMapping(value = "/registration", method = RequestMethod.GET)
    public String registration(Model model) {
        User user = new User();
        model.addAttribute("user", user);

        return "registration";
    }

    @RequestMapping(value = "/registration", method = RequestMethod.POST)
    public String registration(@ModelAttribute("user") User user, Model model) {
        user.setMail(user.getMail().toLowerCase());

        if (userService.findByEmail(user.getMail()) != null) {
            return "/registration?errorMail=true";
        }

        if (user.getPassword().length() < 8 || user.getPassword().length() > 32) {
            return "/registration?errorPassword=true";
        }

        if (!user.getConfirmPassword().equals(user.getPassword())) {
            return "/registration?errorPasswordConfirm=true";
        }

        userService.newUserSave(user);

        securityService.autoLogin(user.getMail(), user.getConfirmPassword());

        return "redirect:/index";
    }

    @GetMapping("/login")
    public String get(Model model) {
        return "/login";
    }

}
