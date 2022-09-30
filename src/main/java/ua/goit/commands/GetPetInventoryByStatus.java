package ua.goit.commands;

import ua.goit.requests.StoreRequests;
import ua.goit.view.View;

import java.util.Map;

public class GetPetInventoryByStatus implements Command {
    public static final String PET_INVENTORY = "pet_inventory";
    private final View view;
    private final StoreRequests storeRequests;

    public GetPetInventoryByStatus(View view, StoreRequests storeRequests) {
        this.view = view;
        this.storeRequests = storeRequests;
    }

    @Override
    public boolean canExecute(String input) {
        return input.equals(PET_INVENTORY);
    }

    @Override
    public void execute() {
        Map<String, Integer> petInventoryByStatus = storeRequests.getPetInventoryByStatus();
        view.write(petInventoryByStatus.toString());
    }
}
