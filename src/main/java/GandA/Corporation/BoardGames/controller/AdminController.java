package GandA.Corporation.BoardGames.controller;


import GandA.Corporation.BoardGames.domain.Role;
import GandA.Corporation.BoardGames.domain.User;
import GandA.Corporation.BoardGames.service.RoleService;
import GandA.Corporation.BoardGames.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
public class AdminController {

    @Autowired
    private RoleService roleService;

    @Autowired
    private UserService userService;

    @RequestMapping("/admin")
    public String viewHomePage(Model model) {
        List<User> listUser = userService.listAll();
        model.addAttribute("listUser", listUser);
        String search = null;
        model.addAttribute("mail", search);
        return "admin";
    }

    @RequestMapping("/searchUser")
    public String viewHomePage(Model model, @Param("keyword") String keyword) {
        List<User> listUser = userService.findByMailContaining(keyword);
        model.addAttribute("listUser", listUser);
        model.addAttribute("keyword", keyword);

        return "admin";
    }

    @RequestMapping("/editUser/{id}")
    public ModelAndView showEditUser(@PathVariable(name = "id") Long id) {
        ModelAndView mav = new ModelAndView("editUser");

        User user = userService.get(id);
        List<Role> roleList = roleService.listAll();
        mav.addObject("user", user);
        mav.addObject("roleList", roleList);

        return mav;
    }

    @RequestMapping(value = "/editUserSave{Userid}", method = RequestMethod.POST)
    public String editUserSave(@ModelAttribute("user") User user, @PathVariable(name = "Userid") Long Userid, @ModelAttribute("id") Long Roleid) {

        User userSave = userService.get(Userid);
        System.out.println(Userid);
        System.out.println(user.getId());
        userSave.setMail(user.getMail());
        userSave.setName(user.getName());
        userSave.setSurname(user.getSurname());
        userSave.setPatronymic(user.getPatronymic());
        userSave.setContact_number(user.getContact_number());
        userSave.setRegion(user.getRegion());
        userSave.setCity(user.getCity());
        userSave.setAddress(user.getAddress());
        userSave.setArchive(user.isArchive());
        System.out.println(user.isArchive());
        userSave.setRole(roleService.get(Roleid));
        userService.saveNotPassword(userSave);

        return "redirect:/admin";
    }

    @RequestMapping("/editUserPassword/{id}")
    public String showEditUserPassword(@PathVariable(name = "id") Long id, Model model) {
        User user = new User();
        user.setId(id);
        model.addAttribute("user",user);

        return "editUserPassword";
    }

    @RequestMapping(value = "/editUserPasswordSave{Userid}", method = RequestMethod.POST)
    public String editUserPasswordSave(@ModelAttribute("user") User user, @PathVariable(name = "Userid") Long Userid, Model model) {
        if (user.getPassword().length() < 8 || user.getPassword().length() > 32) {
            return "redirect:/editUserPassword/"+Userid+"?errorPassword=true";
        }

        if (!user.getConfirmPassword().equals(user.getPassword())) {
            return "redirect:/editUserPassword/"+Userid+"?errorPasswordConfirm=true";
        }

        User userSave = userService.get(Userid);
        userSave.setPassword(user.getPassword());
        userService.savePassword(userSave);

        return "redirect:/admin";
    }

    @RequestMapping("/deleteUser/{id}")
    public String deleteUser(@PathVariable(name = "id") Long id) {

        userService.delete(id);

        return "redirect:/admin";
    }
}
