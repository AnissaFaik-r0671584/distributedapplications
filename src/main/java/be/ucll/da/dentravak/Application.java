package be.ucll.da.dentravak;


import be.ucll.da.dentravak.model.Brood;
import be.ucll.da.dentravak.model.Order;
import be.ucll.da.dentravak.model.Sandwich;
import be.ucll.da.dentravak.repository.OrderRepository;
import be.ucll.da.dentravak.repository.SandwichRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.math.BigDecimal;
import java.util.Date;

@SpringBootApplication
public class Application {

    private static final Logger log = LoggerFactory.getLogger(Application.class);

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

    @Bean
    public CommandLineRunner demo(SandwichRepository repository, OrderRepository orderRepository){
        return (args) -> {
            Sandwich s1 = new Sandwich.SandwichBuilder()
                    .buildPrice(new BigDecimal(22.40))
                    .buildName("1")
                    .build();

            Sandwich s2 = new Sandwich.SandwichBuilder()
                    .buildPrice(new BigDecimal(22.40))
                    .buildName("1")
                    .build();

            Sandwich s3 = new Sandwich.SandwichBuilder()
                    .buildPrice(new BigDecimal(22.40))
                    .buildName("1")
                    .build();

            repository.save(s1);
            repository.save(s2);
            repository.save(s3);

            Order o = new Order.OrderBuilder()
                    .buildGSM("0480000000")
                    .buildDate(new Date(2018, 1, 1))
                    .buildBrood(Brood.Wrap)
                    .buildSandwichID(s1.getID())
                    .build();
            orderRepository.save(o);

        };
    }
}