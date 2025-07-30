package helloworld.model;

import java.time.LocalDate;
import java.time.LocalDateTime;

public class Inventory {
    private int itemId;
    private String item_name;
    private int quantity;
    private String unit;
    private LocalDateTime lastUpdated;

    public Inventory() {}

    public Inventory(int itemId, String item_name, int quantity,String unit,  LocalDateTime lastUpdated) {
        this.itemId = itemId;
        this.item_name = item_name;
        this.quantity = quantity;
        this.unit = unit;
        this.lastUpdated = lastUpdated;
    }
    public int getItemId() {
        return itemId;
    }

    public void setItemId(int itemId) {
        this.itemId = itemId;
    }

    public String getItemName() {
        return item_name;
    }

    public void setName(String item_name) {
        this.item_name = item_name;
    }
    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public String getUnit() {
        return unit;
    }

    public void setUnit(String unit) {
        this.unit = unit;
    }
    public LocalDateTime getLastUpdated() {
        return lastUpdated;
    }

    public void setLastUpdated(LocalDateTime lastUpdated) {
        this.lastUpdated = lastUpdated;
    }
    @Override
    public String toString() {
        return "Item ID: " + itemId +
                ", Name: " + item_name +
                ", Quantity: " + quantity +
                ", Unit: " + unit +
                ", Last Updated: " + lastUpdated;
    }

}

