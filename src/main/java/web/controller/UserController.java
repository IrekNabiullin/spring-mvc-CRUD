package web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import web.model.User;
import web.service.UserService;

import java.util.ResourceBundle;

@Controller
public class UserController {
    @Autowired
    private UserService userService;

    @GetMapping(value = "/")
    public String getUsers(@RequestParam(name = "locale", defaultValue = "en", required = false) String locale, ModelMap modelMap) {
        modelMap.addAttribute("users", userService.listUsers());
        ResourceBundle bundle = ResourceBundle.getBundle("language_" + locale);
        modelMap.addAttribute("usersHeadline", bundle.getString("usersHeadline"));
        return "users";
    }

    @GetMapping("/edit")
    public String editPage(@RequestParam(value = "id") String id, ModelMap modelMap) {
        Long userId = Long.parseLong(id);
        User user = userService.getUserById(userId);
        modelMap.addAttribute("users", userService.getUserById(userId));
        return "editPage";
    }

    @PostMapping(value = "/edit")
    public String editUser(@RequestParam(value = "name") String name,
                           @RequestParam(value = "last_name") String last_name,
                           @RequestParam(value = "email") String email,
                           @RequestParam(value = "login") String login,
                           @RequestParam(value = "password") String password) {
        User tempUser = new User(name, last_name, email, login, password);
        userService.updateUser(tempUser);
        return "editPage";
    }

    @GetMapping(value = "/add")
    public String addForm() {
        return "addPage";
    }

    @PostMapping(value = "/add")
    public String addNewUser(@RequestParam(value = "name") String name,
                             @RequestParam(value = "last_name") String last_name,
                             @RequestParam(value = "email") String email,
                             @RequestParam(value = "login") String login,
                             @RequestParam(value = "password") String password) {
        userService.addUser(new User(name, last_name, email, login, password));
        return "addPage";
    }

    @GetMapping("/delete")
    public String deleteUser(@RequestParam(value = "id") String id, ModelMap modelMap,
                             @RequestParam(name = "locale", defaultValue = "en", required = false) String locale) {
        System.out.println("Delete user with id = " + id);
        Long userId = Long.parseLong(id);
        userService.deleteUser(userId);
        ResourceBundle bundle = ResourceBundle.getBundle("language_" + locale);
        modelMap.addAttribute("usersHeadline", bundle.getString("usersHeadline"));
        System.out.println("headline = " + bundle.getString("usersHeadline"));
        modelMap.addAttribute("users", userService.listUsers());
        return "users";
    }
}
