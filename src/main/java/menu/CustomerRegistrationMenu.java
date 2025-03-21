package menu;

import service.CustomerService;

public class CustomerRegistrationMenu extends Menu {

    private static CustomerService cs;

    @Override
    public void run(){
        System.out.print("Insert the customer name: ");
        String name = scanner.nextLine();
        scanner.nextLine();
        System.out.println("Insert the customer email: ");
        String email = scanner.nextLine();
        scanner.nextLine();

       cs.registerCustomer(name, email);
    }

}
