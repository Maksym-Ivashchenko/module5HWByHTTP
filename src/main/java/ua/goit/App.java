package ua.goit;

import ua.goit.commands.*;
import ua.goit.controller.Controller;
import ua.goit.requests.PetRequests;
import ua.goit.requests.StoreRequests;
import ua.goit.view.Console;
import ua.goit.view.View;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class App {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
//        String baseURL = "https://petstore.swagger.io/v2";

        PetRequests petRequests = new PetRequests();
        StoreRequests storeRequests = new StoreRequests();
        View view = new Console(scanner);
        List<Command> commands = new ArrayList<>();
        commands.add(new Help(view));
        commands.add(new Exit(view));
        commands.add(new GetPetsByStatus(view, petRequests));
        commands.add(new GetPetById(view, petRequests));
        commands.add(new AddPet(view, petRequests));
        commands.add(new UpdatePet(view, petRequests));
        commands.add(new UploadPetPhoto(view, petRequests));
        commands.add(new UpdatePetWithFormData(view, petRequests));
        commands.add(new DeletePet(view, petRequests));

        commands.add(new PlaceOrderPet(view, storeRequests));
        commands.add(new GetOrderById(view, storeRequests));
        commands.add(new GetPetInventoryByStatus(view, storeRequests));
        commands.add(new DeleteOrderById(view, storeRequests));

        Controller controller = new Controller(view, commands);
        controller.run();
    }
}
