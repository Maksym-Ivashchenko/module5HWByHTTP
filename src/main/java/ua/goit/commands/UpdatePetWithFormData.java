package ua.goit.commands;

import ua.goit.models.Pet;
import ua.goit.requests.PetRequests;
import ua.goit.view.View;

import java.util.Objects;

public class UpdatePetWithFormData implements Command {
    public static final String UPDATE_PET_WITH_FORM_DATA = "update_pet_with_form_data";
    private final View view;
    private final PetRequests petRequests;

    public UpdatePetWithFormData(View view, PetRequests petRequests) {
        this.view = view;
        this.petRequests = petRequests;
    }

    @Override
    public boolean canExecute(String input) {
        return input.equals(UPDATE_PET_WITH_FORM_DATA);
    }

    @Override
    public void execute() {
        Long id;
        Pet pet;
        while (true) {
            view.write("Enter pet id to update");
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
        view.write(String.format("Enter new pet name. Actual pet name is %s", pet.getName()));
        String name = view.read();
        view.write(String.format("Enter new pet status. Actual pet status is %s", pet.getStatus()));
        String status = view.read();
        petRequests.updatePetWithFormData(id, name, status);
        view.write("Pet data updated");
    }
}