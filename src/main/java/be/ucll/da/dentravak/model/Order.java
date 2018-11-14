package be.ucll.da.dentravak.model;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Table(name = "sandwich_orders")
public class Order {

    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private UUID orderID;
    private String GSM;
    private UUID sandwichID;
    private Brood brood;
    private LocalDateTime date;

    public Order(){

    }

    public String getGSM() {
        return GSM;
    }

    public void setGSM(String GSM) {
        this.GSM = GSM;
    }

    public UUID getSandwichID() {
        return sandwichID;
    }

    public void setSandwichID(UUID sandwichID) {
        this.sandwichID = sandwichID;
    }

    public UUID getOrderID() {
        return orderID;
    }

    public void setOrderID(UUID orderID) {
        this.orderID = orderID;
    }

    public Brood getBrood() {
        return brood;
    }

    public void setBrood(Brood brood) {
        this.brood = brood;
    }

    public LocalDateTime getDate() {
        return date;
    }

    public void setDate(LocalDateTime date) {
        this.date = date;
    }

    @Override
    public String toString(){
        return GSM + " " + orderID + " " + brood + " " + date;
    }

    private Order(OrderBuilder builder){
        this.GSM = builder.GSM;
        this.sandwichID = builder.sandwichID;
        this.brood = builder.brood;
        this.date = builder.date;

    }


    public static class OrderBuilder {
        private String GSM;
        private UUID sandwichID;
        private Brood brood;
        private LocalDateTime date;

        public OrderBuilder() {
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
