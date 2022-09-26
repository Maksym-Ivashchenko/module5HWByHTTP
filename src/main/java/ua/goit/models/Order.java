package ua.goit.models;

import com.google.gson.annotations.SerializedName;

import java.util.Objects;

public class Order {
    private long id;
    private long petId;
    private long quantity;
    private String shipDate;
    private Status status;
    private boolean complete;

    public enum Status {
        @SerializedName("placed")
        PLACED,
        @SerializedName("approved")
        APPROVED,
        @SerializedName("delivered")
        DELIVERED
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getPetId() {
        return petId;
    }

    public void setPetId(long petId) {
        this.petId = petId;
    }

    public long getQuantity() {
        return quantity;
    }

    public void setQuantity(long quantity) {
        this.quantity = quantity;
    }

    public String getShipDate() {
        return shipDate;
    }

    public void setShipDate(String shipDate) {
        this.shipDate = shipDate;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public boolean isComplete() {
        return complete;
    }

    public void setComplete(boolean complete) {
        this.complete = complete;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Order)) return false;
        Order order = (Order) o;
        return id == order.id && petId == order.petId && quantity == order.quantity && complete == order.complete && Objects.equals(shipDate, order.shipDate) && status == order.status;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, petId, quantity, shipDate, status, complete);
    }

    @Override
    public String toString() {
        return "Order{" +
                "id=" + id +
                ", petId=" + petId +
                ", quantity=" + quantity +
                ", shipDate='" + shipDate + '\'' +
                ", status=" + status +
                ", complete=" + complete +
                '}';
    }
}
