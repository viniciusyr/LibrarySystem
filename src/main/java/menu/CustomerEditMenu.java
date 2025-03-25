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
        System.out.println("\n===== EDIT CUSTOMER ====");
        System.out.print("In order to edit a customer information, it's necessary to provide his ID");
        System.out.print("User ID:");
        int id = scanner.nextInt();
        System.out.print("Insert his new email");
        String newEmail = scanner.nextLine();
        cs.editCustomer(id, newEmail);

    }
}
