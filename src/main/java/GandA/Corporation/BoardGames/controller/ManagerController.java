package GandA.Corporation.BoardGames.controller;

import GandA.Corporation.BoardGames.domain.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
public class ManagerController {
    @RequestMapping("/manager")
    public String viewHomePage(Model model) {
        return "manager";
    }
}
