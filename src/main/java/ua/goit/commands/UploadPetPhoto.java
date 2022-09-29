package ua.goit.commands;

import ua.goit.models.Pet;
import ua.goit.requests.PetRequests;
import ua.goit.view.View;

import java.io.File;
import java.util.Objects;

public class UploadPetPhoto implements Command {
    public static final String UPLOAD_PET_PHOTO = "upload_pet_photo";
    private final View view;
    private final PetRequests petRequests;

    public UploadPetPhoto(View view, PetRequests petRequests) {
        this.view = view;
        this.petRequests = petRequests;
    }

    @Override
    public boolean canExecute(String input) {
        return input.equals(UPLOAD_PET_PHOTO);
    }

    @Override
    public void execute() {
        Long id;
        Pet pet;
        File file;
        while (true) {
            view.write("Enter pet id to update photo:");
            String petIdString = view.read();
            try {
                id = Long.parseLong(petIdString);
                pet = petRequests.findPetById(id);
                if (!Objects.isNull(pet)) {
                    break;
                } else {
                    view.write("Pet with ID " + id + " doesn't exist.");
                }
            } catch (NumberFormatException e) {
                view.write("Incorrect number. Please, try again");
            }
        }
        while (true) {
            view.write("Enter file path and name");
            String filename = view.read();
            file = new File(filename);
            if(file.exists()) {
                break;
            } else {
                view.write("File doesn't exist. Please, try again");
            }
        }
        petRequests.uploadPetPhoto(id, file);
        view.write("Photo successfully added");
    }
}
