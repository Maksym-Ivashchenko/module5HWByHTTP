package ua.goit.commands;

import ua.goit.models.ApiResponse;
import ua.goit.requests.UserRequests;
import ua.goit.view.View;

public class UserLogout implements Command {
    public static final String LOGOUT = "logout";
    private final View view;
    private final UserRequests userRequests;

    public UserLogout(View view, UserRequests userRequests) {
        this.view = view;
        this.userRequests = userRequests;
    }

    @Override
    public boolean canExecute(String input) {
        return input.equals(LOGOUT);
    }

    @Override
    public void execute() {
        ApiResponse apiResponse = userRequests.userLogout();
        view.write(apiResponse.toString());
    }
}
