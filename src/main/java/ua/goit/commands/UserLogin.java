package ua.goit.commands;

import ua.goit.models.ApiResponse;
import ua.goit.requests.UserRequests;
import ua.goit.view.View;

public class UserLogin implements Command {
    public static final String LOGIN = "login";
    private final View view;
    private final UserRequests userRequests;

    public UserLogin(View view, UserRequests userRequests) {
        this.view = view;
        this.userRequests = userRequests;
    }

    @Override
    public boolean canExecute(String input) {
        return input.equals(LOGIN);
    }

    @Override
    public void execute() {
        String userName = getString("Enter user name for login:");
        String userPassword = getString("Enter password for login in clear text:");
        ApiResponse apiResponse = userRequests.userLogin(userName, userPassword);
        view.write(apiResponse.toString());
    }

    String getString(String message) {
        view.write(message);
        return view.read();
    }
}
