package ua.goit.commands;

import ua.goit.models.Order;
import ua.goit.requests.StoreRequests;
import ua.goit.view.View;

import java.time.LocalDate;

public class PlaceOrderPet implements Command {
    public static final String PLACE_ORDER_PET = "place_order_pet";
    private final View view;
    private final StoreRequests storeRequests;

    public PlaceOrderPet(View view, StoreRequests storeRequests) {
        this.view = view;
        this.storeRequests = storeRequests;
    }

    @Override
    public boolean canExecute(String input) {
        return input.equals(PLACE_ORDER_PET);
    }

    @Override
    public void execute() {
        Long id = getLong("Enter id:");
        Long petId = getLong("Enter pet id:");
        Long quantity = getLong("Enter quantity:");
        String shipDate = LocalDate.now().toString();
        String status;
        while (true) {
            view.write("Enter order status (choose from placed, approved, delivered)");
            status = view.read();
            if (status.equals("placed") | status.equals("approved") | status.equals("delivered")) {
                break;
            }
            view.write("Incorrect status. Please, try again");
        }
        Order.Status orderStatus = Order.Status.valueOf(status.toUpperCase());

        Order order = new Order(id, petId, quantity, shipDate, orderStatus, true);
        storeRequests.placeOrderPet(order);
        view.write("Order placed successfully.");
    }

    Long getLong(String message) {
        String inputString;
        Long input;
        while (true) {
            view.write(message);
            inputString = view.read();
            if (inputString.isBlank()) {
                input = null;
                break;
            } else {
                try {
                    input = Long.parseLong(inputString);
                    break;
                } catch (NumberFormatException e) {
                    e.printStackTrace();
                    view.write("Incorrect number. Please, try again");
                }
            }
        }
        return input;
    }
}
