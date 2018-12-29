package be.ucll.da.dentravak.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.math.BigDecimal;
import java.util.UUID;

@Entity
public class Sandwich {

    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private UUID id;
    private String name;
    private BigDecimal price;
    private String ingredients;

    public Sandwich(){

    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public String getIngredients() {
        return ingredients;
    }

    public void setIngredients(String ingredients) {
        this.ingredients = ingredients;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public static class SandwichBuilder {
        private UUID id;
        private String name;
        private BigDecimal price;
        private String ingredients;

        private SandwichBuilder() {
        }

        public static SandwichBuilder aSandwich() {
            return new SandwichBuilder();
        }

        public SandwichBuilder withId(UUID id) {
            this.id = id;
            return this;
        }

        public SandwichBuilder withName(String name){
            this.name = name;
            return this;
        }
        public SandwichBuilder withPrice(BigDecimal price){
            this.price = price;
            return this;
        }
        public SandwichBuilder buildIngredients(String ingredients){
            this.ingredients = ingredients;
            return this;
        }

        public Sandwich build(){
            Sandwich sandwich = new Sandwich();
            sandwich.id = id;
            sandwich.price = price;
            sandwich.name = name;
            sandwich.ingredients = ingredients;
            return sandwich;
        }
    }
}
