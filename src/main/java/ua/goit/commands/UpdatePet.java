package ua.goit.commands;

import ua.goit.models.Category;
import ua.goit.models.Pet;
import ua.goit.models.Tag;
import ua.goit.requests.PetRequests;
import ua.goit.view.View;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

public class UpdatePet implements Command {
    public static final String UPDATE_PET = "update_pet";
    private final View view;
    private final PetRequests petRequests;

    public UpdatePet(View view, PetRequests petRequests) {
        this.view = view;
        this.petRequests = petRequests;
    }

    @Override
    public boolean canExecute(String input) {
        return input.equals(UPDATE_PET);
    }

    @Override
    public void execute() {
        Long id = getLong("Enter pet id to update");
        Pet pet = petRequests.findPetById(id);

        if (Objects.isNull(pet)) {
            view.write("Pet with ID " + id + " doesn't exist.");
        } else {

            view.write("Enter new data to update pet or leave blank to leave previous data");

            Long categoryId = getLong(String.format("Actual pet category ID is %d. Enter pet category ID", pet.getCategory().getId()));
            if (Objects.isNull(categoryId)) {
                categoryId = pet.getCategory().getId();
            }

            String categoryName = getString("Actual pet category name is %s. Enter pet category name", pet.getCategory().getName());

            String name = getString("Actual pet name is %s. Enter pet name", pet.getName());

            Set<String> photoUrls = getUrls(pet.getPhotoUrls());

            Set<Tag> tags = getTags(pet.getTags());

            String status = pet.getStatus().toString();
            status = getStatus(status);
            Pet.Status petStatus = Pet.Status.valueOf(status.toUpperCase());

            Category category = new Category(categoryId, categoryName);

            Pet updatedPet = new Pet(id, category, name, photoUrls, tags, petStatus);
            petRequests.updatePet(updatedPet);
            view.write("Pet data updated.");
        }
    }

    private Set<Tag> getTags(Set<Tag> existedTags) {
        view.write(String.format("Actual pet tags are %s.", existedTags));

        Set<Tag> tags = new HashSet<>();
        Integer tagId;

        while (true) {

            tagId = getInteger("Enter pet tag ID");
            if (Objects.isNull(tagId) & tags.isEmpty()) {
                tags.addAll(existedTags);
                break;
            }

            view.write("Enter pet tag name");
            String tagName = view.read();
            tags.add(new Tag(tagId, tagName));
            String answer;
            while (true) {
                view.write("Would you like to add one more tag? Write yes or no:");
                answer = view.read();
                if (answer.equals("no") | answer.equals("yes")) {
                    break;
                }
                view.write("Incorrect command. Write yes or no, please");
            }
            if (answer.equals("no")) {
                break;
            }
        }
        return tags;
    }

    private String getStatus(String existedStatus) {
        String status;
        while (true) {
            view.write(String.format("Actual pet status is %s. Enter pets status (choose from available, pending, sold)", existedStatus));
            status = view.read();
            if (status.equals("")) {
                status = existedStatus;
                break;
            } else {
                if (status.equals("available") | status.equals("pending") | status.equals("sold")) {
                    break;
                }
                view.write("Incorrect status. Please, try again");
            }
        }
        return status;
    }
    private String getString(String message, String stringToUpdate) {
        view.write(String.format(message, stringToUpdate));
        String updatedString = view.read();
        if (updatedString.isBlank()) {
            updatedString = stringToUpdate;
        }
        return updatedString;
    }

    private Set<String> getUrls(Set<String> existedPhotoUrls) {
        Set<String> photoUrls = new HashSet<>();
        view.write(String.format("Actual pet urls are %s. Enter pet urls divided with spaces", existedPhotoUrls));
        String urls = view.read();
        if (urls.isBlank()) {
            photoUrls.addAll(existedPhotoUrls);
        } else {
            photoUrls.addAll(Arrays.asList(urls.split("\\s+")));
        }
        return photoUrls;
    }

    private Long getLong(String message) {
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
}
