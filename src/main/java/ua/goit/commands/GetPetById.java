package ua.goit.commands;

import ua.goit.models.Pet;
import ua.goit.requests.PetRequests;
import ua.goit.view.View;

import java.util.Objects;

public class GetPetById implements Command {
    public static final String FIND_PET_BY_ID = "find_pet_by_id";
    private final View view;
    private final PetRequests petRequests;

    public GetPetById(View view, PetRequests petRequests) {
        this.view = view;
        this.petRequests = petRequests;
    }

    @Override
    public boolean canExecute(String input) {
        return input.equals(FIND_PET_BY_ID);
    }

    @Override
    public void execute() {
        long petId;
        while (true) {
            view.write("Enter pet id");
            String petIdString = view.read();
            try {
                petId = Long.parseLong(petIdString);
                break;
            } catch (NumberFormatException e) {
                view.write("Incorrect number. Please, try again");
            }
        }
        Pet pet = petRequests.findPetById(petId);
        if (!Objects.isNull(pet)) {
            view.write("Pet with ID " + petId + " data:");
            view.write(pet.toString());
        } else {
            view.write("Pet with ID " + petId + " not found");
        }
    }
}
