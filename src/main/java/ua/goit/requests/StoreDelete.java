package ua.goit.requests;

import com.google.gson.Gson;
import okhttp3.OkHttpClient;
import ua.goit.models.ApiResponse;

import java.io.IOException;
import java.lang.reflect.Type;
import java.net.HttpURLConnection;
import java.util.concurrent.TimeUnit;

public class StoreDelete {
    private final String BASE_URL;
    private static final String DELETE_URL = "/store/order";
    private static final String FORM_JSON = "application/json";
    private static final String NAME_RESPONSE_TYPE = "accept";

    public StoreDelete(String baseURL) {
        BASE_URL = baseURL;
    }

    public ApiResponse deletePurchaseOrderById(long id) {
        OkHttpClient okHttpClient = new OkHttpClient()
                .newBuilder()
                .connectTimeout(20, TimeUnit.SECONDS)
                .build();
        okhttp3.Request request = new okhttp3.Request.Builder()
                .url(BASE_URL + DELETE_URL + "/" + id)
                .addHeader(NAME_RESPONSE_TYPE, FORM_JSON)
                .delete()
                .build();
        okhttp3.Response response;
        try {
            response = okHttpClient.newCall(request).execute();
            if (HttpURLConnection.HTTP_OK == response.code()) {
                Type type = ApiResponse.class;
                String message = response.body().string();
                return new Gson().fromJson(message, type);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
}
