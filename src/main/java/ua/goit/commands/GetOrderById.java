package ua.goit.commands;

import ua.goit.models.Order;
import ua.goit.requests.StoreRequests;
import ua.goit.view.View;

import java.util.Objects;

public class GetOrderById implements Command {
    public static final String FIND_ORDER_BY_ID = "find_order_by_id";
    private final View view;
    private final StoreRequests storeRequests;

    public GetOrderById(View view, StoreRequests storeRequests) {
        this.view = view;
        this.storeRequests = storeRequests;
    }

    @Override
    public boolean canExecute(String input) {
        return input.equals(FIND_ORDER_BY_ID);
    }

    @Override
    public void execute() {
        int orderId;
        while (true) {
            view.write("Enter order id (value >= 1 and <= 10)");
            String petIdString = view.read();
            try {
                orderId = Integer.parseInt(petIdString);
                if (orderId >= 1 && orderId <= 10) {
                    break;
                } else {
                    view.write("Incorrect id. Please, try again");
                }
            } catch (NumberFormatException e) {
                view.write("Incorrect number. Please, try again");
            }
        }
        Order order = storeRequests.findOrderById(orderId);
        if (!Objects.isNull(order)) {
            view.write(String.format("Order with ID %s data:", orderId));
            view.write(order.toString());
        } else {
            view.write(String.format("Order with ID %s not found", orderId));
        }
    }
}
