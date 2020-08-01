package web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.ArrayList;
import java.util.List;

@Controller
public class CarController {

    @GetMapping(value = "/cars")
    public String printCars(ModelMap model) {
        List<String> cars = new ArrayList<>();
        cars.add("Toyota Crown");
        cars.add("Porshe Panamera");
        cars.add("Suzuki Vitara");
        model.addAttribute("cars", cars);
        return "cars";
    }
}
