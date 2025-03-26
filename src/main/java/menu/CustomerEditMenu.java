package menu;

import service.CustomerService;
import service.ServiceFactory;

public class CustomerEditMenu extends Menu{

    private final CustomerService cs;

    public CustomerEditMenu(){
        this.cs = ServiceFactory.getCustomerService();
    }
    @Override
    public void run() {
        String emailRegex = "^[\\w.-]+@[a-zA-Z\\d.-]+\\.[a-zA-Z]{2,}$";

        System.out.println("\n===== EDIT CUSTOMER ====");
        System.out.print("In order to edit a customer information, it's necessary to provide his ID");
        System.out.print("\nUser ID:");
        int id = scanner.nextInt();
        scanner.nextLine();
        System.out.print("Insert his new email: ");
        String newEmail = scanner.nextLine();

        if(!newEmail.isBlank() && !newEmail.matches(emailRegex)){
            System.out.println("Please, insert the email correctly!");
        } else {
            cs.editCustomer(id, newEmail);
            System.out.println("The customer information was edited");
        }
    }
}
