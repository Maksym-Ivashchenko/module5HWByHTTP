package ua.goit.commands;

import ua.goit.view.View;

public class Help implements Command {
    public static final String HELP = "help";
    private final View view;

    public Help(View view) {
        this.view = view;
    }

    @Override
    public boolean canExecute(String input) {
        return input.equals(HELP);
    }

    @Override
    public void execute() {
        view.write(String.format("Enter %s to see all command", Help.HELP));
        view.write(String.format("Enter %s to exit program", Exit.EXIT));
        view.write(String.format("Enter %s to find pets by status", GetPetsByStatus.FIND_PETS_BY_STATUS));
        view.write(String.format("Enter %s to find pets by id", GetPetById.FIND_PET_BY_ID));
        view.write(String.format("Enter %s to add pet", AddPet.ADD_PET));
        view.write(String.format("Enter %s to update the pet", UpdatePet.UPDATE_PET));
        view.write(String.format("Enter %s to upload the pet photo", UploadPetPhoto.UPLOAD_PET_PHOTO));
        view.write(String.format("Enter %s to update the pet with form data",
                UpdatePetWithFormData.UPDATE_PET_WITH_FORM_DATA));
        view.write(String.format("Enter %s to delete the pet", DeletePet.DELETE_PET));
        view.write(String.format("Enter %s to order placed for purchasing the pet", PlaceOrderPet.PLACE_ORDER_PET));
        view.write(String.format("Enter %s to find purchase order by id", GetOrderById.FIND_ORDER_BY_ID));
        view.write(String.format("Enter %s to get pet inventory by status", GetPetInventoryByStatus.PET_INVENTORY));
        view.write(String.format("Enter %s to delete purchase order by id", DeleteOrderById.DELETE_ORDER_BY_ID));
        view.write(String.format("Enter %s for user authorization", UserLogin.LOGIN));
        view.write(String.format("Enter %s to logout", UserLogout.LOGOUT));
        view.write(String.format("Enter %s to get user by user name", GetUserByName.GET_USER_BY_NAME));
        view.write(String.format("Enter %s to create user", AddUser.ADD_USER));
        view.write(String.format("Enter %s to creates list of users with given input array",
                AddUsersWithArray.ADD_USER_WITH_ARRAY));
        view.write(String.format("Enter %s to creates list of users with given input list",
                AddUsersWithList.ADD_USER_WITH_LIST));
        view.write(String.format("Enter %s to update user", UpdateUser.UPDATE_USER));
        view.write(String.format("Enter %s to delete user by name", DeleteUser.DELETE_USER));
    }
}