package be.ucll.da.dentravak;


import be.ucll.da.dentravak.model.Sandwich;
import be.ucll.da.dentravak.repository.SandwichRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.math.BigDecimal;
import java.util.UUID;

@SpringBootApplication
public class Application {

    private static final Logger log = LoggerFactory.getLogger(Application.class);

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

    @Bean
    public CommandLineRunner demo(SandwichRepository repository){
        return (args) -> {
            Sandwich s1 = new Sandwich.SandwichBuilder().buildPrice(new BigDecimal(22.40)).buildName("1").build();
            repository.save(new Sandwich("1", new BigDecimal(22.40),"1"));
            repository.save(new Sandwich("2", new BigDecimal(22.40),"1"));
        for(Sandwich sandwich : repository.findAll()){
            log.info(sandwich.toString());
        }

        };
    }
}