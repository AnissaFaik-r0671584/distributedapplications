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
        Sandwich sandwich = new Sandwich("Smoske", new BigDecimal(12.12),
                 "Sla, Komkommer, ...", UUID.randomUUID());
        Sandwich sandwich2 = new Sandwich("Smoske", new BigDecimal(12.12),
                "Sla, Komkommer, ...", UUID.randomUUID());
        Sandwich sandwich3 = new Sandwich("Smoske", new BigDecimal(12.12),
                "Sla, Komkommer, ...", UUID.randomUUID());
        Sandwich sandwich4 = new Sandwich("Smoske", new BigDecimal(12.12),
                "Sla, Komkommer, ...", UUID.randomUUID());
        return Arrays.asList(sandwich, sandwich2, sandwich3, sandwich4);
    }
}
