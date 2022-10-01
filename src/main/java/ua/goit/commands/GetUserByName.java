package ua.goit.commands;

import ua.goit.models.ApiResponse;
import ua.goit.models.User;
import ua.goit.requests.UserRequests;
import ua.goit.view.View;

public class GetUserByName implements Command {
    public static final String GET_USER_BY_NAME = "get_user_by_name";
    private final View view;
    private final UserRequests userRequests;

    public GetUserByName(View view, UserRequests userRequests) {
        this.view = view;
        this.userRequests = userRequests;
    }

    @Override
    public boolean canExecute(String input) {
        return input.equals(GET_USER_BY_NAME);
    }

    @Override
    public void execute() {
        view.write("Enter user name:");
        String username = view.read();
        User user = userRequests.getUserByName(username);
        view.write(user.toString());
    }
}
