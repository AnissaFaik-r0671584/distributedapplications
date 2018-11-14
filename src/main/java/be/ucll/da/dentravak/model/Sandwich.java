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
    private UUID ID;
    private String name;
    private BigDecimal price;
    private String ingredients;


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

    public UUID getID() {
        return ID;
    }

    public void setID(UUID ID) {
        this.ID = ID;
    }

    @Override
    public String toString(){
        return "" + name + " " + price + " " + ingredients;
    }

    private Sandwich(SandwichBuilder builder){
        this.name=builder.name;
        this.price=builder.price;
        this.ingredients=builder.ingredients;

    }

    public static class SandwichBuilder {
        private String name;
        private BigDecimal price;
        private String ingredients;

        public SandwichBuilder(String name, BigDecimal price, String ingredients) {
            this.name = name;
            this.price = price;
            this.ingredients = ingredients;
        }

        public SandwichBuilder buildName(String name){
            this.name = name;
            return this;
        }
        public SandwichBuilder buildPrice(BigDecimal price){
            this.price = price;
            return this;
        }
        public SandwichBuilder buildIngredients(String ingredients){
            this.ingredients = ingredients;
            return this;
        }

        public Sandwich build(){
            return new Sandwich(this);
        }
    }
}
