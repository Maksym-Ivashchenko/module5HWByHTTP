package ua.goit.models;

import com.google.gson.annotations.SerializedName;

import java.util.Objects;

public class Order {
    private Long id;
    private Long petId;
    private Long quantity;
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

    public Order() {}

    public Order(Long id, Long petId, Long quantity, String shipDate, Status status, boolean complete) {
        this.id = id;
        this.petId = petId;
        this.quantity = quantity;
        this.shipDate = shipDate;
        this.status = status;
        this.complete = complete;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getPetId() {
        return petId;
    }

    public void setPetId(Long petId) {
        this.petId = petId;
    }

    public Long getQuantity() {
        return quantity;
    }

    public void setQuantity(Long quantity) {
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
        return complete == order.complete && Objects.equals(id, order.id) && Objects.equals(petId, order.petId) && Objects.equals(quantity, order.quantity) && Objects.equals(shipDate, order.shipDate) && status == order.status;
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
