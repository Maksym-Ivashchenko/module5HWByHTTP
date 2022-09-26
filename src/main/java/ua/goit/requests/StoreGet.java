package ua.goit.requests;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import ua.goit.Method;
import ua.goit.Response;
import ua.goit.URLBuilder;
import ua.goit.models.Order;

import java.io.IOException;
import java.lang.reflect.Type;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.LinkedHashMap;
import java.util.List;

public class StoreGet {
    private final String BASE_URL;
    private static final String GET_FIND_PURCHASE_ORDER_BY_ID_URL = "/store/order/";
    private static final String GET_RETURNS_PET_INVENTORIES_BY_STATUS_URL = "/store/inventory";
    private static final String STATUS = "status";

    public StoreGet(String baseURL) {
        BASE_URL = baseURL;
    }

    public Order findPurchaseOrderById(long id) {
        try {
            URL url = new URL(BASE_URL + GET_FIND_PURCHASE_ORDER_BY_ID_URL + id);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod(Method.GET.name());
            connection.connect();
            if (HttpURLConnection.HTTP_OK == connection.getResponseCode()) {
                Type type = Order.class;
                String message = new Response().getResponse(connection);
                return new Gson().fromJson(message, type);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    public LinkedHashMap<String, Integer> returnsPetInventoriesByStatus(List<Order.Status> status) {
        try {
            URLBuilder urlBuilder = new URLBuilder(BASE_URL + GET_RETURNS_PET_INVENTORIES_BY_STATUS_URL);
            for (Order.Status value : status) {
                urlBuilder.withParam(STATUS, value.name().toLowerCase());
            }
            URL url = urlBuilder.build();
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod(Method.GET.name());
            connection.connect();
            if (HttpURLConnection.HTTP_OK == connection.getResponseCode()) {
                Type type = new TypeToken<LinkedHashMap<String, Integer>>() {}.getType();
                String message = new Response().getResponse(connection);
                return new Gson().fromJson(message, type);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
}
