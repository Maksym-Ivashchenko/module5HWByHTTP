package ua.goit.commands;

import ua.goit.models.Category;
import ua.goit.models.Pet;
import ua.goit.models.Tag;
import ua.goit.requests.PetRequests;
import ua.goit.view.View;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

public class AddPet implements Command {
    public static final String ADD_PET = "add_pet";
    private final View view;
    private final PetRequests petRequests;

    public AddPet(View view, PetRequests petRequests) {
        this.view = view;
        this.petRequests = petRequests;
    }

    @Override
    public boolean canExecute(String input) {
        return input.equals(ADD_PET);
    }

    @Override
    public void execute() {
        Long id = getLong("Enter pet id:");
        Long categoryId = getLong("Enter pet category id:");
        String categoryName = getString("Enter pet category name:");
        String name = getString("Enter pet name:");
        view.write("Enter pet url links divided with blank:");
        Set<String> photoUrls = new HashSet<>(Arrays.asList(view.read().split("\\s+")));
        Set<Tag> tags = new HashSet<>();
        while (true) {
            Integer tagId = getInteger("Enter pet tag id:");
            String tagName = getString("Enter pet tag name:");
            tags.add(new Tag(tagId, tagName));
            String answer;
            while (true) {
                answer = getString("Would you like to add one more tag? Write yes or no:");
                if (answer.equals("no") | answer.equals("yes")) {
                    break;
                }
                view.write("Incorrect command. Write yes or no, please:");
            }
            if (answer.equals("no")) {
                break;
            }
        }
        String status;
        while (true) {
            status = getString("Enter pets status (choose from available, pending, sold)");

            if (status.equals("available") | status.equals("pending") | status.equals("sold")) {
                break;
            }
            view.write("Incorrect status. Please, try again");
        }
        Pet.Status petStatus = Pet.Status.valueOf(status.toUpperCase());
        Category category = new Category(categoryId, categoryName);
        Pet pet = new Pet(id, category, name, photoUrls, tags, petStatus);
        petRequests.addPet(pet);
        view.write("Pet added.");
    }

    private Integer getInteger(String message) {
        String inputString;
        Integer input;
        while (true) {
            view.write(message);
            inputString = view.read();
            if (inputString.isBlank()) {
                input = null;
                break;
            } else {
                try {
                    input = Integer.parseInt(inputString);
                    break;
                } catch (NumberFormatException e) {
                    e.printStackTrace();
                    view.write("Incorrect number. Please, try again");
                }
            }
        }
        return input;
    }

    String getString(String message) {
        view.write(message);
        return view.read();
    }

    Long getLong(String message) {
        String inputString;
        Long input;
        while (true) {
            view.write(message);
            inputString = view.read();
            if (inputString.isBlank()) {
                input = null;
                break;
            } else {
                try {
                    input = Long.parseLong(inputString);
                    break;
                } catch (NumberFormatException e) {
                    e.printStackTrace();
                    view.write("Incorrect number. Please, try again");
                }
            }
        }
        return input;
    }
}
