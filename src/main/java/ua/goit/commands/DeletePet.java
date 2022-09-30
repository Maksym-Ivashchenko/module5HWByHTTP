package ua.goit.commands;

import ua.goit.requests.PetRequests;
import ua.goit.view.View;

public class DeletePet implements Command {
    public static final String DELETE_PET = "delete_pet";
    private final View view;
    private final PetRequests petRequests;

    public DeletePet(View view, PetRequests petRequests) {
        this.view = view;
        this.petRequests = petRequests;
    }

    @Override
    public boolean canExecute(String input) {
        return input.equals(DELETE_PET);
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
        petRequests.deletePet(petId);
        view.write(String.format("Pet with id %s deleted.", petId));
    }
}
