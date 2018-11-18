package be.ucll.da.dentravak.controllers;

import be.ucll.da.dentravak.model.Order;
import be.ucll.da.dentravak.model.Sandwich;
import be.ucll.da.dentravak.repository.OrderRepository;
import be.ucll.da.dentravak.repository.SandwichRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@RestController
public class SandwichController {

    @Autowired
    private SandwichRepository swRepo;

    @Autowired
    private OrderRepository orderRepository;

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

    @RequestMapping(value = "/orders", method = RequestMethod.POST)
    public Order addOrder (@RequestBody Order order){ return orderRepository.save(order);}

    @RequestMapping(value = "/orders", method = RequestMethod.GET)
    public List<Order> getOrderByDate (@RequestParam("date") String date){
        List<Order> orders = new ArrayList<>();

        for(Order o: orderRepository.findAll()){
            if(o.getDate().toLocalDate().equals(LocalDate.parse(date))){
                orders.add(o);
            }
        }
        return orders;

    }
}
