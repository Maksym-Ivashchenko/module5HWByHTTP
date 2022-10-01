package ua.goit.commands;

import ua.goit.models.ApiResponse;
import ua.goit.requests.UserRequests;
import ua.goit.view.View;

public class DeleteUser implements Command {
    public static final String DELETE_USER = "delete_user";
    private final View view;
    private final UserRequests userRequests;

    public DeleteUser(View view, UserRequests userRequests) {
        this.view = view;
        this.userRequests = userRequests;
    }

    @Override
    public boolean canExecute(String input) {
        return input.equals(DELETE_USER);
    }

    @Override
    public void execute() {
        view.write("Enter user name");
        String username = view.read();
        ApiResponse apiResponse = userRequests.deleteUser(username);
        view.write(apiResponse.toString());
    }
}
