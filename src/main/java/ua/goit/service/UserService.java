package ua.goit.service;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import ua.goit.models.User;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.Base64;
import java.util.List;

public class UserService {
    private static final HttpClient CLIENT = HttpClient.newHttpClient();
    private static final Gson GSON = new GsonBuilder().setPrettyPrinting().create();

    public static HttpResponse<String> userLogin(URI uri, String username, String password) {
        HttpClient client = HttpClient.newBuilder()
                .build();

        HttpRequest request = HttpRequest.newBuilder()
                .GET()
                .uri(uri)
                .header("Authorization", getBasicAuthenticationHeader(username, password))
                .header("accept", "application/json")
                .build();
        try {
            return client.send(request, HttpResponse.BodyHandlers.ofString());
        } catch (IOException | InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    public static HttpResponse<String> userLogout(URI uri) {
        return getResponse(uri);
    }

    public static HttpResponse<String> getUserByName(URI uri) {
        return getResponse(uri);
    }

    public static HttpResponse<String> addUser(URI uri, User user) {
        return postResponse(uri, user);
    }

    public static HttpResponse<String> addUsersWithArray(URI uri, User[] users) {
        String petGson = GSON.toJson(users);
        HttpRequest request = HttpRequest.newBuilder()
                .uri(uri)
                .POST(HttpRequest.BodyPublishers.ofString(petGson))
                .header("Content-type", "application/json")
                .header("accept", "application/json")
                .build();
        HttpResponse<String> response = null;
        try {
            response = CLIENT.send(request, HttpResponse.BodyHandlers.ofString());
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }
        return response;
    }

    public static HttpResponse<String> addUsersWithList(URI uri, List<User> users) {
        String petGson = GSON.toJson(users);
        HttpRequest request = HttpRequest.newBuilder()
                .uri(uri)
                .POST(HttpRequest.BodyPublishers.ofString(petGson))
                .header("Content-type", "application/json")
                .header("accept", "application/json")
                .build();
        HttpResponse<String> response = null;
        try {
            response = CLIENT.send(request, HttpResponse.BodyHandlers.ofString());
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }
        return response;
    }

    public static HttpResponse<String> updateUser(URI uri, User user) {
        return postResponse(uri, user);
    }

    public static HttpResponse<String> deleteUser(URI uri) {
        HttpRequest request = HttpRequest.newBuilder()
                .uri(uri)
                .DELETE()
                .header("accept", "application/json")
                .build();
        try {
            return CLIENT.send(request, HttpResponse.BodyHandlers.ofString());
        } catch (IOException | InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    private static HttpResponse<String> getResponse(URI uri) {
        HttpRequest request = HttpRequest.newBuilder()
                .uri(uri)
                .GET()
                .header("accept", "application/json")
                .header("Content-type", "application/json")
                .build();
        try {
            return CLIENT.send(request, HttpResponse.BodyHandlers.ofString());
        } catch (IOException | InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    private static HttpResponse<String> postResponse(URI uri, User user) {
        String petGson = GSON.toJson(user);
        HttpRequest request = HttpRequest.newBuilder()
                .uri(uri)
                .POST(HttpRequest.BodyPublishers.ofString(petGson))
                .header("Content-type", "application/json")
                .build();
        HttpResponse<String> response = null;
        try {
            response = CLIENT.send(request, HttpResponse.BodyHandlers.ofString());
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }
        return response;
    }

    private static String getBasicAuthenticationHeader(String username, String password) {
        String valueToEncode = username + ":" + password;
        return "Basic " + Base64.getEncoder()
                .encodeToString(valueToEncode.getBytes());
    }
}
