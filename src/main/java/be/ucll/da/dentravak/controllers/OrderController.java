package be.ucll.da.dentravak.controllers;

import be.ucll.da.dentravak.model.Order;
import be.ucll.da.dentravak.repository.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@RestController
public class OrderController {

    @Autowired
    private OrderRepository orderRepository;

    @RequestMapping(value = "/orders", method = RequestMethod.POST)
    public Order addOrder (@RequestBody Order order){
        order.setCreationDate(LocalDateTime.now());
        order.setPrinted(false);
        return orderRepository.save(order);
    }

    @RequestMapping(value = "/orders", method = RequestMethod.GET)
    public List<Order> getOrderByDate (@RequestParam(value = "date", required = false) String date){
        List<Order> orders = new ArrayList<>();

        if(date == null) {
            for (Order o : orderRepository.findAll()) {
                orders.add(o);
            }
        } else {
            for (Order o : orderRepository.findAll()) {
                if (o.getCreationDate().toLocalDate().equals(LocalDate.parse(date))) {
                    orders.add(o);
                }
            }
        }
        return orders;
    }

    @RequestMapping(value = "/orders/{id}", method = RequestMethod.PUT)
    public Order updatePrinted (@RequestBody Order order, @PathVariable UUID id){
        if(orderRepository.findById(id).get() != null){
            return orderRepository.save(order);
        } else {
            return null;
        }
    }
}
