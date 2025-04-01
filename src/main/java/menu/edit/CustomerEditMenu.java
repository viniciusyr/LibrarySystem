package menu.edit;

import menu.Menu;
import service.CustomerService;
import service.ServiceFactory;
import validation.CustomerValidation;

public class CustomerEditMenu extends Menu {

    private final CustomerService cs;
    private static final CustomerValidation validation = CustomerValidation.getInstance;
    private static final String nameRegex = "^[A-Za-zÀ-ÖØ-öø-ÿ' -]{2,100}$";
    private static final String emailRegex = "^[\\w.-]+@[a-zA-Z\\d.-]+\\.[a-zA-Z]{2,}$";

    public CustomerEditMenu(){
        this.cs = ServiceFactory.getCustomerService();
    }
    @Override
    public void run() {

        System.out.println("\n===== EDIT CUSTOMER ====");
        System.out.print("In order to edit a customer information, it's necessary to enter his ID");
        int id = readId();

        String newName = readStringInput("Enter the new customer name: ", nameRegex);
        String newEmail = readStringInput("Enter the new customer email: ", emailRegex);

        cs.editCustomer(id, newName, newEmail);
    }

    private Integer readId() {
        while (true) {
            System.out.print("\nEnter the customer ID: ");
            String input = scanner.nextLine().trim();

            if (input.isBlank()) {
                System.out.println("Please enter a valid ID!");
                continue;
            }

            try {
                return Integer.parseInt(input);
            } catch (NumberFormatException e) {
                System.out.println("Invalid input! Please enter a numeric ID.");
            }
        }
    }

    private Integer readIntegerInput(String message) {
        System.out.print(message);
        String input = scanner.nextLine().trim();

        if (input.isEmpty()) {
            return null;
        }

        try {
            return Integer.parseInt(input);
        } catch (NumberFormatException e) {
            System.out.println("Invalid input! The value will not be updated.");
            return null;
        }
    }

    private String readStringInput(String message, String regex){
        while(true){
            System.out.print(message);
            String input = scanner.nextLine().trim();

            if(input.isBlank()){
                return null;
            }

            if(input.matches(regex)){
                return input;
            }

            System.out.println("Invalid input! Please try again.");
        }
    }
}
