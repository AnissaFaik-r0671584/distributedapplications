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
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@SpringBootApplication
public class Application {

    private static final Logger log = LoggerFactory.getLogger(Application.class);

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

    @Configuration
    public class WebConfig implements WebMvcConfigurer {

        @Override
        public void addCorsMappings(CorsRegistry registry) {
            registry.addMapping("/**");
        }
    }
    @Bean
    public CommandLineRunner demo(SandwichRepository repository, OrderRepository orderRepository){
        return (args) -> {
            Sandwich s1 = new Sandwich.SandwichBuilder()
                    .buildPrice(new BigDecimal(22.40))
                    .buildName("Sandwich Anissa").buildIngredients("Ham, Kaas")
                    .build();

            Sandwich s2 = new Sandwich.SandwichBuilder()
                    .buildPrice(new BigDecimal(22.40))
                    .buildName("Sandwich Joran").buildIngredients("Broodje gezond")
                    .build();

            Sandwich s3 = new Sandwich.SandwichBuilder()
                    .buildPrice(new BigDecimal(22.40))
                    .buildName("Sandwich Lector").buildIngredients("you get the point")
                    .build();

            repository.save(s1);
            repository.save(s2);
            repository.save(s3);

            Order o = new Order.OrderBuilder()
                    .buildGSM("0480000000")
                    .buildDate(LocalDateTime.of(2018, 3, 3, 3, 3, 3))
                    .buildBrood(Brood.WRAP)
                    .buildSandwichID(s1.getId())
                    .buildPrice(new BigDecimal(2.00))
                    .buildName("testnaam")
                    .build();

            Order o2 = new Order.OrderBuilder()
                    .buildGSM("0480000044")
                    .buildDate(LocalDateTime.of(2018, 4, 4, 3, 3, 3))
                    .buildBrood(Brood.WRAP)
                    .buildSandwichID(s2.getId())
                    .buildPrice(new BigDecimal(2.00))
                    .buildName("testnaam")
                    .build();

            Order o3 = new Order.OrderBuilder()
                    .buildGSM("0480004994")
                    .buildDate(LocalDateTime.of(2018, 4, 4, 3, 3, 3))
                    .buildBrood(Brood.TURKISH_BREAD)
                    .buildSandwichID(s1.getId())
                    .buildPrice(new BigDecimal(2.00))
                    .buildName("testnaam")
                    .build();

            orderRepository.save(o);
            orderRepository.save(o2);
            orderRepository.save(o3);

        };
    }
}