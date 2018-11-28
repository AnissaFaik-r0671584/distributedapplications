package be.ucll.da.dentravak.model;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Table(name = "sandwich_orders")
public class Order {

    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private UUID id;
    private String mobilePhoneNumber;
    private UUID sandwichId;
    private String name;
    private Brood breadType;
    private BigDecimal price;
    private LocalDateTime creationDate;

    public Order(){

    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
    public String getMobilePhoneNumber() { return mobilePhoneNumber; }
    public void setMobilePhoneNumber(String GSM) {
        this.mobilePhoneNumber = GSM;
    }
    public UUID getSandwichId() {
        return sandwichId;
    }
    public void setSandwichId(UUID sandwichId) {
        this.sandwichId = sandwichId;
    }
    public UUID getId() {
        return id;
    }
    public void setId(UUID id) {
        this.id = id;
    }
    public Brood getBreadType() {
        return breadType;
    }
    public void setBreadType(Brood breadType) {
        this.breadType = breadType;
    }
    public LocalDateTime getCreationDate() { return creationDate; }
    public void setCreationDate(LocalDateTime creationDate) {
        this.creationDate = creationDate;
    }
    public BigDecimal getPrice() {  return price; }
    public void setPrice(BigDecimal price) { this.price = price; }

    @Override
    public String toString(){
        return name + " " + price + " " + mobilePhoneNumber + " " + id + " " + breadType + " " + creationDate;
    }

    private Order(OrderBuilder builder){
        this.mobilePhoneNumber = builder.GSM;
        this.sandwichId = builder.sandwichID;
        this.breadType = builder.brood;
        this.creationDate = builder.date;
        this.price = builder.price;
        this.name = builder.name;
    }


    public static class OrderBuilder {
        private String GSM;
        private UUID sandwichID;
        private Brood brood;
        private LocalDateTime date;
        private BigDecimal price;
        private String name;

        public OrderBuilder() {
        }

        public OrderBuilder buildPrice(BigDecimal price){
            this.price = price;
            return this;
        }
        public OrderBuilder buildName(String name){
            this.name = name;
            return this;
        }
        public OrderBuilder buildGSM(String GSM){
            this.GSM = GSM;
            return this;
        }
        public OrderBuilder buildSandwichID(UUID sandwichID){
            this.sandwichID = sandwichID;
            return this;
        }
        public OrderBuilder buildBrood(Brood brood){
            this.brood = brood;
            return this;
        }

        public OrderBuilder buildDate(LocalDateTime date){
            this.date = date;
            return this;
        }

        public Order build(){
            return new Order(this);
        }
    }
}
