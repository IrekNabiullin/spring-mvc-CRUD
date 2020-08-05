package web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import web.model.Car;
import web.service.ServiceCar;

import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

@Controller
public class CarController {

    @GetMapping(value = "/cars")
    public String getCars(@RequestParam(name="locale", defaultValue = "en", required = false)String locale, ModelMap modelMap) {
        modelMap.addAttribute("carList", new ServiceCar().getCars());
        ResourceBundle bundle = ResourceBundle.getBundle("language_" + locale);
        System.out.println("bundle = " + bundle);
        modelMap.addAttribute("headline", bundle.getString("headline"));
        System.out.println("headline = " + bundle.getString("headline"));
        return "cars";
    }
}
