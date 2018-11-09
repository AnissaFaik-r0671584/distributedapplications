package be.ucll.da.dentravak.model;

import java.util.Date;
import java.util.UUID;

public class Order {
    private String GSM;
    private UUID sandwichID;
    private UUID orderID;
    private Brood brood;
    private Date Date;

    public Order(String GSM, UUID sandwichID, UUID orderID, Brood brood, java.util.Date date) {
        this.GSM = GSM;
        this.sandwichID = sandwichID;
        this.orderID = orderID;
        this.brood = brood;
        Date = date;
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

    public java.util.Date getDate() {
        return Date;
    }

    public void setDate(java.util.Date date) {
        Date = date;
    }
}
