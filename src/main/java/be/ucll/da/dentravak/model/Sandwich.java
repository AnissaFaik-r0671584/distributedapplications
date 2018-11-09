package be.ucll.da.dentravak.model;

import java.math.BigDecimal;
import java.util.UUID;

public class Sandwich {

    private String name;
    private BigDecimal price;
    private String ingredients;
    private UUID ID;

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
}
