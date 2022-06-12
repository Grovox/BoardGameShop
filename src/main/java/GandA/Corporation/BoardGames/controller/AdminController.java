package GandA.Corporation.BoardGames.controller;

import GandA.corporation.APK.model.Role;
import GandA.corporation.APK.model.User;
import GandA.corporation.APK.service.RoleService;
import GandA.corporation.APK.service.UserService;
import GandA.corporation.APK.vilidator.UserValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
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

    @Autowired
    private UserValidator userValidator;

    @RequestMapping("/admin")
    public String viewHomePage(Model model) {
        List<User> listUser = userService.listAll();
        model.addAttribute("listUser", listUser);
        return "admin";
    }

    @RequestMapping("/editUser/{id}")
    public ModelAndView showEditUser(@PathVariable(name = "id") Long id) {
        ModelAndView mav = new ModelAndView("edit_user");

        User user = userService.get(id);
        List<Role> rolelist = roleService.listAll();
        mav.addObject("user", user);
        mav.addObject("rolelist", rolelist);

        return mav;
    }

    @RequestMapping(value = "/editUserSave{Userid}", method = RequestMethod.POST)
    public String editUserSave(@ModelAttribute("user") User user,@PathVariable(name = "Userid") Long Userid,@ModelAttribute("id") Long Roleid) {

        User userSave = userService.get(Userid);
        userSave.setEmail(user.getEmail());
        userSave.setUsername(user.getUsername());
        userSave.setSurname(user.getSurname());
        userSave.setPatronymic(user.getPatronymic());
        userSave.setPhone(user.getPhone());
        userSave.setRegion(user.getRegion());
        userSave.setEnabled(user.isEnabled());
        userSave.setRoleUser(roleService.get(Roleid));
        userService.saveNotPassword(userSave);

        return "redirect:/admin";
    }

    @RequestMapping("/editUserPassword/{id}")
    public String showEditUserPassword(@PathVariable(name = "id") Long id, Model model) {
        User user = new User();
        user.setId(id);
        model.addAttribute("user",user);

        return "edit_UserPasword";
    }

    @RequestMapping(value = "/editUserPasswordSave{Userid}", method = RequestMethod.POST)
    public String editUserPasswordSave(@ModelAttribute("user") User user, @PathVariable(name = "Userid") Long Userid, BindingResult bindingResult) {
        userValidator.validate(user, bindingResult);
        if (bindingResult.hasErrors()) {
            return "error_password";
        }

        User userSave = userService.get(Userid);
        userSave.setPassword(user.getPassword());
        userService.save(userSave);

        return "redirect:/admin";
    }

    @RequestMapping("/deleteUser/{id}")
    public String deleteUser(@PathVariable(name = "id") Long id) {

        userService.delete(id);

        return "redirect:/admin";
    }
}
