package be.ucll.da.dentravak.controllers;

import be.ucll.da.dentravak.model.Sandwich;
import be.ucll.da.dentravak.repository.SandwichRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
public class SandwichController {

    @Autowired
    private SandwichRepository swRepo;

    @RequestMapping("")
    public String home() {
       return "hello world";
    }


    @RequestMapping(value="/sandwiches", method = RequestMethod.GET)
    public Iterable<Sandwich> sandwich() {
        return swRepo.findAll();
    }

    @RequestMapping(value="/sandwiches", method = RequestMethod.POST)
    public Sandwich postSandwich(@RequestBody Sandwich sandwich) {
        return swRepo.save(sandwich);
    }

    @RequestMapping(value="/sandwiches/{id}", method = RequestMethod.GET)
    public Sandwich getSandwich(@PathVariable UUID id) {
        return swRepo.findById(id).get();
    }

    @RequestMapping(value="/sandwiches/{id}", method = RequestMethod.PUT)
    public Sandwich putSandwich(@RequestBody Sandwich sandwich,@PathVariable UUID id ) {
        if(swRepo.findById(id).get() != null){
            return swRepo.save(sandwich);
        }else {
            return null;
        }
    }


}
