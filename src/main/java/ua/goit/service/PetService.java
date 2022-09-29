package ua.goit.service;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.apache.http.HttpEntity;
import org.apache.http.NameValuePair;
import org.apache.http.client.entity.EntityBuilder;
import org.apache.http.client.methods.HttpDelete;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.mime.MultipartEntityBuilder;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.jetbrains.annotations.Nullable;
import ua.goit.models.Pet;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class PetService {
    private static final HttpClient CLIENT = HttpClient.newHttpClient();
    private static final CloseableHttpClient CLOSEABLE_HTTP_CLIENT = HttpClients.createDefault();
    private static final Gson GSON = new GsonBuilder().setPrettyPrinting().create();

    public static HttpResponse<String> findByStatus(URI uri) {
        return getResponse(uri);
    }

    public static HttpResponse<String> findById(URI uri) {
        return getResponse(uri);
    }

    public static HttpResponse<String> addPet(URI uri, Pet pet) {
        return postResponse(uri, pet);
    }

    public static HttpResponse<String> updatePet(URI uri, Pet pet) {
        return postResponse(uri, pet);
    }

    public static void uploadPetPhoto(URI uri, File file) {
        HttpPost uploadFile = new HttpPost(uri);
        MultipartEntityBuilder builder = MultipartEntityBuilder.create();
        builder.addTextBody("additionalMetaData", "photo", ContentType.TEXT_PLAIN);
        try {
            builder.addBinaryBody("file", new FileInputStream(file),
                    ContentType.APPLICATION_OCTET_STREAM, file.getName());
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        HttpEntity multipart = builder.build();
        uploadFile.setEntity(multipart);
        try {
            CLOSEABLE_HTTP_CLIENT.execute(uploadFile);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void updatePetWithFormData(URI uri, String name, String status) {
        HttpPost updatePet = new HttpPost(uri);
        EntityBuilder builder = EntityBuilder.create();

        NameValuePair namePair = new NameValuePair() {
            @Override
            public String getName() {
                return "name";
            }

            @Override
            public String getValue() {
                return name;
            }
        };
        NameValuePair statusPair = new NameValuePair() {
            @Override
            public String getName() {
                return "status";
            }

            @Override
            public String getValue() {
                return status;
            }
        };
        builder.setParameters(namePair, statusPair);
        HttpEntity entity = builder.build();
        updatePet.setEntity(entity);
        try {
            CLOSEABLE_HTTP_CLIENT.execute(updatePet);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void deletePet(URI uri) {
        HttpDelete deletePet = new HttpDelete(uri);
        try {
            CLOSEABLE_HTTP_CLIENT.execute(deletePet);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static HttpResponse<String> getResponse(URI uri) {
        HttpRequest request = HttpRequest.newBuilder()
                .uri(uri)
                .GET()
                .header("Content-type", "application/json")
                .build();
        try {
            return CLIENT.send(request, HttpResponse.BodyHandlers.ofString());
        } catch (IOException | InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    @Nullable
    private static HttpResponse<String> postResponse(URI uri, Pet pet) {
        String petGson = GSON.toJson(pet);
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
}
