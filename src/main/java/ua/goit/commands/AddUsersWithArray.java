package ua.goit.commands;

import ua.goit.models.ApiResponse;
import ua.goit.models.User;
import ua.goit.requests.UserRequests;
import ua.goit.view.View;

import java.util.Arrays;

public class AddUsersWithArray implements Command {
    public static final String ADD_USER_WITH_ARRAY = "add_user_with_array";
    private final View view;
    private final UserRequests userRequests;

    public AddUsersWithArray(View view, UserRequests userRequests) {
        this.view = view;
        this.userRequests = userRequests;
    }

    @Override
    public boolean canExecute(String input) {
        return input.equals(ADD_USER_WITH_ARRAY);
    }

    @Override
    public void execute() {
        int count = 0;
        User[] users = new User[1];
        while (true) {
            if (count != 0) {
                users = Arrays.copyOf(users, users.length + 1);
            }
            view.write("Enter user id|name|first name|last name|email|password|phone|status");
            String[] userData = view.read().split("\\|");
            Long id = -1L;
            String username = null;
            String firstName = null;
            String lastName = null;
            String email = null;
            String password = null;
            String phone = null;
            Integer userStatus = -1;
            int countOfFields = 8;
            if (userData.length == countOfFields) {
                for (int i = 0; i <= userData.length - 1; i++) {
                    String s = userData[i].replace(",", "").strip();
                    userData[i] = s;
                    switch (i) {
                        case 0 -> id = getLong(userData[i]);
                        case 1 -> username = userData[i];
                        case 2 -> firstName = userData[i];
                        case 3 -> lastName = userData[i];
                        case 4 -> email = userData[i];
                        case 5 -> password = userData[i];
                        case 6 -> phone = userData[i];
                        case 7 -> userStatus = getInteger(userData[i]);
                    }
                }
                while (true) {
                    try {
                        User user = new User(id, username, firstName, lastName, email, password, phone, userStatus);
                        users[count] = user;
                        count++;
                        ApiResponse apiResponse = userRequests.addUsersWithArray(users);
                        view.write(apiResponse.toString());
                        break;
                    } catch (RuntimeException exception) {
                        view.write(exception.getMessage());
                    }
                }
            } else {
                view.write("User not added. Try again.");
            }
            String answer;
            while (true) {
                view.write("Would you like to add one more user? Write yes or no:");
                answer = view.read();
                if (answer.equals("no") | answer.equals("yes")) {
                    break;
                }
                view.write("Incorrect command. Write yes or no, please:");
            }
            if (answer.equals("no")) {
                break;
            }
        }
        view.write(String.format("Enter %s to see all command", Help.HELP));
    }

    private Integer getInteger(String inputString) {
        int input;
        while (true) {
            try {
                input = Integer.parseInt(inputString);
                break;
            } catch (NumberFormatException e) {
                e.printStackTrace();
                view.write("Incorrect number. Please, try again");
            }
        }
        return input;
    }

    Long getLong(String inputString) {
        long input;
        while (true) {
            try {
                input = Long.parseLong(inputString);
                break;
            } catch (NumberFormatException e) {
                e.printStackTrace();
                view.write("Incorrect number. Please, try again");
            }
        }
        return input;
    }
}
