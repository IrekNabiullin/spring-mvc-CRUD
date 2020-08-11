package web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import web.model.User;
import web.service.ServiceCar;
import web.service.UserService;

import java.util.ResourceBundle;

@Controller
public class UserController {
    @Autowired
    private UserService userService;
    @GetMapping(value = "/")
    public String getUsers(@RequestParam(name="locale", defaultValue = "en", required = false)String locale, ModelMap modelMap) {
        modelMap.addAttribute("users", userService.listUsers());
        ResourceBundle bundle = ResourceBundle.getBundle("language_" + locale);
        System.out.println("bundle = " + bundle);
        modelMap.addAttribute("usersHeadline", bundle.getString("usersHeadline"));
        System.out.println("headline = " + bundle.getString("usersHeadline"));
        return "users";
    }



//    @GetMapping(value = "/")
//    public String getUsersPage(ModelMap model) {
//        model.addAttribute("users", userService.listUsers());
//        return "users-page";
//    }

    @GetMapping(value = "/editPage")
    public String getForm() {
        return "editPage";
    }

    @PostMapping(value = "/editPage")
    public String addNewUser(@RequestParam(value = "name") String name,
                             @RequestParam(value = "last_name") String last_name,
                             @RequestParam(value = "email") String email) {
        userService.addUser(new User(name, last_name, email));
        return "redirect:/";
    }

    @GetMapping("/edit")
    public String editPage(@RequestParam(value = "id") String id, ModelMap model) {
        Long userId = Long.parseLong(id);
        User user = userService.getUserById(userId);
        model.addAttribute("user", user);
        return "editPage";
    }
/*
    @PostMapping("/edit")
    public String editUser(@RequestParam(value = "id") String id,
                           @RequestParam(value = "login") String login,
                           @RequestParam(value = "password") String password,
                           @RequestParam(value = "email") String email) {
        userService.updateUser(new User(Long.parseLong(id), login, password, email));
        return "redirect:/";
    }


 */
    @GetMapping("/delete")
    public String deleteUser(@RequestParam(value = "id") String id) {
        Long userId = Long.parseLong(id);
        userService.deleteUser(userId);
        return "redirect:/";
    }


}
