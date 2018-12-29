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
import org.springframework.web.client.RestTemplate;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import static be.ucll.da.dentravak.model.Order.OrderBuilder.aOrder;
import static be.ucll.da.dentravak.model.Sandwich.SandwichBuilder.aSandwich;

@EnableDiscoveryClient
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
    public RestTemplate restTemplate() {
        return new RestTemplate();
    }

    @Bean
    public CommandLineRunner demo(SandwichRepository repository, OrderRepository orderRepository){
        return (args) -> {
            Sandwich s1 = aSandwich()
                    .withPrice(new BigDecimal("77.40"))
                    .withName("Sandwich Anissa").buildIngredients("Ham, Kaas")
                    .build();

            Sandwich s2 = aSandwich()
                    .withPrice(new BigDecimal("11.40"))
                    .withName("Sandwich Joran").buildIngredients("Broodje gezond")
                    .build();

            repository.save(s1);
            repository.save(s2);

            Order o = aOrder()
                    .withMobilePhoneNumber("0480000000")
                    .withCreationDate(LocalDateTime.of(2018, 3, 3, 3, 3, 3))
                    .withBreadType(Brood.WRAP)
                    .withSandwichId(s1.getId())
                    .withPrice(new BigDecimal(2.00))
                    .withName("testnaam")
                    .build();

            Order o2 = aOrder()
                    .withMobilePhoneNumber("0480000044")
                    .withCreationDate(LocalDateTime.of(2018, 4, 4, 3, 3, 3))
                    .withBreadType(Brood.WRAP)
                    .withSandwichId(s2.getId())
                    .withPrice(new BigDecimal(2.00))
                    .withName("testnaam")
                    .build();


            orderRepository.save(o);
            orderRepository.save(o2);
        };
    }
}