package ua.goit.requests;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import ua.goit.service.PetService;
import ua.goit.models.Pet;

import java.io.File;
import java.net.URI;
import java.net.http.HttpResponse;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class PetRequests {
    private static final String BASE_URL = "https://petstore.swagger.io/v2";
    private static final String PET_URL = BASE_URL + "/pet";
    private static final String FIND_BY_STATUS = PET_URL + "/findByStatus?status=";
    private static final String UPLOAD_PET_PHOTO_URL = PET_URL + "/%d/uploadImage";
    private static final Gson GSON = new GsonBuilder().setPrettyPrinting().create();


    public Set<Pet> findPetsByStatus(String petStatus) {
        URI uri = URI.create(String.format("%s%s", FIND_BY_STATUS, petStatus));
        HttpResponse<String> response = PetService.findByStatus(uri);
        List<Pet> petsList = GSON.fromJson(response.body(), new TypeToken<List<Pet>>() {
        }.getType());
        return new HashSet<>(petsList);
    }

    public Pet findPetById(Long id) {
        URI uri = URI.create(PET_URL + "/" + id);
        Pet pet = null;
        HttpResponse<String> response;
        response = PetService.findById(uri);
        int responseStatus = response.statusCode();
        if (responseStatus == 200) {
            pet = GSON.fromJson(response.body(), Pet.class);
        }
        return pet;
    }

    public void addPet(Pet pet) {
        URI uri = URI.create(String.format("%s%s", PET_URL, "?"));
        HttpResponse<String> response = PetService.addPet(uri, pet);
        GSON.fromJson(response.body(), Pet.class);
    }

    public void updatePet(Pet pet) {
        URI uri = URI.create(String.format("%s%s", PET_URL, "?"));
        HttpResponse<String> response = PetService.updatePet(uri, pet);
        GSON.fromJson(response.body(), Pet.class);
    }

    public void uploadPetPhoto(Long id, File file) {
        URI uri = URI.create(String.format(UPLOAD_PET_PHOTO_URL, id));
        PetService.uploadPetPhoto(uri, file);
    }

    public void updatePetWithFormData(Long id, String name, String status) {
        URI uri = URI.create(String.format("%s%s%d", PET_URL, "/", id));
        PetService.updatePetWithFormData(uri, name, status);
    }

    public void deletePet(Long id) {
        URI uri = URI.create(String.format("%s%s%d", PET_URL, "/", id));
        PetService.deletePet(uri);
    }
}
