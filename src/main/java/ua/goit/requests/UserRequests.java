package ua.goit.requests;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import ua.goit.models.ApiResponse;
import ua.goit.models.User;
import ua.goit.service.UserService;

import java.net.URI;
import java.net.http.HttpResponse;
import java.util.List;

public class UserRequests {
    private static final String BASE_URL = "https://petstore.swagger.io/v2";
    private static final String USER_URL = BASE_URL + "/user";
    private static final String LOGIN_URL = USER_URL + "/login";
    private static final String LOGOUT_URL = USER_URL + "/logout";
    private static final String CREATE_WITH_ARRAY_URL = USER_URL + "/createWithArray";
    private static final String CREATE_WITH_LIST_URL = USER_URL + "/createWithList";
    private static final Gson GSON = new GsonBuilder().setPrettyPrinting().create();

    public ApiResponse userLogin(String username, String password) {
        ApiResponse api = new ApiResponse();
        URI uri = URI.create(String.format("%s%s%s%s%s", LOGIN_URL, "?username=", username, "&password=", password));
        HttpResponse<String> response = UserService.userLogin(uri, username, password);
        int responseStatus = response.statusCode();
        switch (responseStatus) {
            case 200 -> api = GSON.fromJson(response.body(), ApiResponse.class);
            case 400 -> api = GSON.fromJson(response.body(), ApiResponse.class);
        }
        return api;
    }

    public ApiResponse userLogout() {
        ApiResponse api = new ApiResponse();
        URI uri = URI.create(LOGOUT_URL);
        HttpResponse<String> response = UserService.userLogout(uri);
        int responseStatus = response.statusCode();
        if (responseStatus == 200) {
            api = GSON.fromJson(response.body(), ApiResponse.class);
        }
        return api;
    }

    public User getUserByName(String username) {
        URI uri = URI.create(String.format("%s%s%s", USER_URL, "/", username));
        HttpResponse<String> response = UserService.getUserByName(uri);
        User user = new User();
        int responseStatus = response.statusCode();
        switch (responseStatus) {
            case 200 -> user = GSON.fromJson(response.body(), User.class);
            case 400 -> user = GSON.fromJson(response.body(), User.class);
            case 404 -> user = GSON.fromJson(response.body(), User.class);
        }
        return user;
    }

    public ApiResponse addUser(User user) {
        URI uri = URI.create(USER_URL);
        HttpResponse<String> response = UserService.addUser(uri, user);
        int statusCode = response.statusCode();
        ApiResponse apiResponse = new ApiResponse();
        if (userLogin(user.getUsername(), user.getPassword()).getCode() == 200) {
            if (statusCode == 200) {
                apiResponse = GSON.fromJson(response.body(), ApiResponse.class);
            }
        }
        return apiResponse;
    }

    public ApiResponse addUsersWithArray(User[] users) {
        URI uri = URI.create(CREATE_WITH_ARRAY_URL);
        HttpResponse<String> response = UserService.addUsersWithArray(uri, users);
        int statusCode = response.statusCode();
        ApiResponse apiResponse = new ApiResponse();
        for (User user : users) {
            if (userLogin(user.getUsername(), user.getPassword()).getCode() == 200) {
                if (statusCode == 200) {
                    apiResponse = GSON.fromJson(response.body(), ApiResponse.class);
                }
            }
            return apiResponse;
        }
        return apiResponse;
    }

    public ApiResponse addUsersWithList(List<User> users) {
        URI uri = URI.create(CREATE_WITH_LIST_URL);
        HttpResponse<String> response = UserService.addUsersWithList(uri, users);
        int statusCode = response.statusCode();
        ApiResponse apiResponse = new ApiResponse();
        for (User user : users) {
            if (userLogin(user.getUsername(), user.getPassword()).getCode() == 200) {
                if (statusCode == 200) {
                    apiResponse = GSON.fromJson(response.body(), ApiResponse.class);
                }
            }
            return apiResponse;
        }
        return apiResponse;
    }

    public ApiResponse updateUser(User user) {
        URI uri = URI.create(String.format("%s%s%s", USER_URL, "/", user));
        HttpResponse<String> response = UserService.updateUser(uri, user);
        int statusCode = response.statusCode();
        ApiResponse apiResponse = new ApiResponse();
        if (userLogin(user.getUsername(), user.getPassword()).getCode() == 200) {
            switch (statusCode) {
                case 200 -> apiResponse = GSON.fromJson(response.body(), ApiResponse.class);
                case 400 -> apiResponse = GSON.fromJson(response.body(), ApiResponse.class);
                case 404 -> apiResponse = GSON.fromJson(response.body(), ApiResponse.class);
            }
        }
        return apiResponse;
    }

    public ApiResponse deleteUser(String username) {
        URI uri = URI.create(String.format("%s%s%s", USER_URL, "/", username));
        HttpResponse<String> response = UserService.deleteUser(uri);
        int statusCode = response.statusCode();
        ApiResponse apiResponse = new ApiResponse();
        User user = getUserByName(username);
        if (userLogin(user.getUsername(), user.getPassword()).getCode() == 200) {
            if (statusCode == 200) {
                apiResponse = GSON.fromJson(response.body(), ApiResponse.class);
            }
        }
        return apiResponse;
    }
}
