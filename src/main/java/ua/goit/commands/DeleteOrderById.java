package ua.goit.commands;

import ua.goit.models.ApiResponse;
import ua.goit.requests.StoreRequests;
import ua.goit.view.View;

public class DeleteOrderById implements Command {
    public static final String DELETE_ORDER_BY_ID = "delete_order_by_id";
    private final View view;
    private final StoreRequests storeRequests;

    public DeleteOrderById(View view, StoreRequests storeRequests) {
        this.view = view;
        this.storeRequests = storeRequests;
    }

    @Override
    public boolean canExecute(String input) {
        return input.equals(DELETE_ORDER_BY_ID);
    }

    @Override
    public void execute() {
        int orderId;
        while (true) {
            view.write("Enter order id");
            String orderIdString = view.read();
            try {
                orderId = Integer.parseInt(orderIdString);
                if (orderId <= 0) {
                    view.write("Invalid ID supplied");
                } else {
                    break;
                }
            } catch (NumberFormatException e) {
                view.write("Incorrect number. Please, try again");
            }
        }
        ApiResponse apiResponse = storeRequests.deleteOrderById(orderId);
        view.write(apiResponse.toString());
    }
}
