package be.ucll.da.dentravak.controllers;

import be.ucll.da.dentravak.model.Sandwich;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.List;
import java.util.UUID;

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
        sandwich.setID(UUID.randomUUID());
        sandwich.setIngredients("Sla, Komkommer, ...");
        sandwich.setPrice(new BigDecimal(12.12));
        return Arrays.asList(sandwich);
    }
}
