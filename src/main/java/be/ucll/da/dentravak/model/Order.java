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

    public String getMobilePhoneNumber() {
        return mobilePhoneNumber;
    }

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

    public LocalDateTime getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(LocalDateTime creationDate) {
        this.creationDate = creationDate;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public static class OrderBuilder {
        private UUID id;
        private String mobilePhoneNumber;
        private UUID sandwichId;
        private String name;
        private Brood breadType;
        private BigDecimal price;
        private LocalDateTime creationDate;

        public OrderBuilder() {
        }

        public static OrderBuilder aOrder() {
            return new OrderBuilder();
        }

        public OrderBuilder withId(UUID id){
            this.id = id;
            return this;
        }

        public OrderBuilder withMobilePhoneNumber(String mobilePhoneNumber){
            this.mobilePhoneNumber = mobilePhoneNumber;
            return this;
        }

        public OrderBuilder withSandwichId(UUID sandwichId){
            this.sandwichId = sandwichId;
            return this;
        }

        public OrderBuilder withName(String name){
            this.name = name;
            return this;
        }

        public OrderBuilder withBreadType(Brood breadType){
            this.breadType = breadType;
            return this;
        }

        public OrderBuilder withPrice(BigDecimal price){
            this.price = price;
            return this;
        }

        public OrderBuilder withCreationDate(LocalDateTime creationDate){
            this.creationDate = creationDate;
            return this;
        }

        public Order build(){
            Order order = new Order();
            order.id = id;
            order.mobilePhoneNumber = mobilePhoneNumber;
            order.sandwichId = sandwichId;
            order.name = name;
            order.breadType = breadType;
            order.price = price;
            order.creationDate = creationDate;
            return order;
        }
    }
}
