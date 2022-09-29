package ua.goit.commands;

import ua.goit.models.Pet;
import ua.goit.requests.PetRequests;
import ua.goit.view.View;

import java.util.Set;

public class GetPetsByStatus implements Command {
    public static final String FIND_PETS_BY_STATUS = "find_pets_by_status";
    private final View view;
    private final PetRequests petRequests;

    public GetPetsByStatus(View view, PetRequests petRequests) {
        this.view = view;
        this.petRequests = petRequests;
    }

    @Override
    public boolean canExecute(String input) {
        return input.equals(FIND_PETS_BY_STATUS);
    }

    @Override
    public void execute() {
        String petStatus;
        while (true) {
            view.write("Enter pets status (choose from available, pending, sold)");
            petStatus = view.read();
            if (petStatus.equals("available") | petStatus.equals("pending") | petStatus.equals("sold")) {
                break;
            }
            view.write("Incorrect status. Please, try again");
        }
        Set<Pet> pets = petRequests.findPetsByStatus(petStatus);
        if (!pets.isEmpty()) {
            view.write("Pets with status " + petStatus + " list:");
            pets.forEach(pet -> view.write(pet.toString()));
        } else {
            view.write("No pets with status " + petStatus + " found");
        }
    }
}
