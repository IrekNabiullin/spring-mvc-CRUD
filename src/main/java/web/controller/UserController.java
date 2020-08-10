package web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import web.service.ServiceCar;

import java.util.ResourceBundle;

@Controller
public class UserController {
    @GetMapping(value = "/")
    public String getUsers(@RequestParam(name="locale", defaultValue = "en", required = false)String locale, ModelMap modelMap) {
        modelMap.addAttribute("carList", new ServiceCar().getCars());
        ResourceBundle bundle = ResourceBundle.getBundle("language_" + locale);
        System.out.println("bundle = " + bundle);
        modelMap.addAttribute("usersHeadline", bundle.getString("usersHeadline"));
        System.out.println("headline = " + bundle.getString("usersHeadline"));
        return "users";
    }
}
