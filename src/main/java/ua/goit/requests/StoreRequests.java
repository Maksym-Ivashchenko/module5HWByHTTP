package ua.goit.requests;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import ua.goit.models.ApiResponse;
import ua.goit.models.Order;
import ua.goit.service.StoreService;

import java.lang.reflect.Type;
import java.net.URI;
import java.net.http.HttpResponse;
import java.util.HashMap;
import java.util.Map;

public class StoreRequests {
    private static final String BASE_URL = "https://petstore.swagger.io/v2";
    private static final String STORE_URL = BASE_URL + "/store";
    private static final String ORDER_URL = STORE_URL + "/order";
    private static final String INVENTORY_URL = STORE_URL + "/inventory";

    private static final Gson GSON = new GsonBuilder().setPrettyPrinting().create();

    public void placeOrderPet(Order order) {
        URI uri = URI.create(String.format("%s%s", ORDER_URL, "?"));
        HttpResponse<String> response = StoreService.placeOrderPet(uri, order);
        GSON.fromJson(response.body(), Order.class);
    }

    public Order findOrderById(Integer id) {
        URI uri = URI.create(ORDER_URL + "/" + id);
        Order order = new Order();
        HttpResponse<String> response;
        response = StoreService.findOrderById(uri);
        int responseStatus = response.statusCode();
        if (responseStatus == 200) {
            order = GSON.fromJson(response.body(), Order.class);
        }
        return order;
    }

    public Map<String, Integer> getPetInventoryByStatus() {
        URI uri = URI.create(INVENTORY_URL);
        Map<String, Integer> statusCodes = new HashMap<>();
        HttpResponse<String> response = StoreService.getPetInventoryByStatus(uri);
        Type mapType = new TypeToken<Map<String, Integer>>() {}.getType();
        int responseStatus = response.statusCode();
        if (responseStatus == 200) {
            statusCodes = GSON.fromJson(response.body(), mapType);
        }
        return statusCodes;
    }

    public ApiResponse deleteOrderById(Integer id) {
        ApiResponse api = new ApiResponse();
        URI uri = URI.create(String.format("%s%s%d", ORDER_URL, "/", id));
        HttpResponse<String> response = StoreService.deleteOrderById(uri);
        int responseStatus = response.statusCode();
        switch (responseStatus) {
            case 200 -> api = GSON.fromJson(response.body(), ApiResponse.class);
            case 400 -> api = GSON.fromJson(response.body(), ApiResponse.class);
            case 404 -> api = GSON.fromJson(response.body(), ApiResponse.class);
        }
        return api;
    }
}
