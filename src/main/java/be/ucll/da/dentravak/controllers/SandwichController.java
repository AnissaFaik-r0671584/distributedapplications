package be.ucll.da.dentravak.controllers;

import be.ucll.da.dentravak.model.Sandwich;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;
import java.util.List;

@RestController
public class SandwichController {

    @RequestMapping("")
    public String home() {
       return "hello world";
    }

    @RequestMapping("/sandwiches")
    public List<Sandwich> sandwich() {
        Sandwich sandwich = new Sandwich();
        sandwich.setName("Smoske");

        return Arrays.asList(sandwich);
    }
}
