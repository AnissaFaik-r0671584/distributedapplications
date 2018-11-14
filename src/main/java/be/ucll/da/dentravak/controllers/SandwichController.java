package be.ucll.da.dentravak.controllers;

import be.ucll.da.dentravak.model.Sandwich;
import be.ucll.da.dentravak.repository.SandwichRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.List;
import java.util.UUID;

@RestController
public class SandwichController {

    @Autowired
    SandwichRepository swRepo;


    @RequestMapping("")
    public String home() {
       return "hello world";
    }


    @RequestMapping("/sandwiches")
    public List<Sandwich> sandwich() {
        Sandwich sandwich1 = new Sandwich("Smoske", new BigDecimal(12.12),
                 "Sla, Komkommer, ...", UUID.randomUUID());
        Sandwich sandwich2 = new Sandwich("Smoske", new BigDecimal(12.12),
                "Sla, Komkommer, ...", UUID.randomUUID());
        Sandwich sandwich3 = new Sandwich("Smoske", new BigDecimal(12.12),
                "Sla, Komkommer, ...", UUID.randomUUID());
        Sandwich sandwich4 = new Sandwich("Smoske", new BigDecimal(12.12),
                "Sla, Komkommer, ...", UUID.randomUUID());

        return Arrays.asList(sandwich1, sandwich2, sandwich3, sandwich4);
    }

    @RequestMapping(value="/sandwiches", method = RequestMethod.POST)
    public Sandwich postSandwich(@RequestBody Sandwich sandwich) {

        return sandwich;
    }
}
