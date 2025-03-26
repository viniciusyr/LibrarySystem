package menu;

import java.util.HashMap;
import java.util.Map;

public class RegistrationMenu extends Menu{
    @Override
    public void run(){
        Map<Integer, String> registrationMenu = new HashMap<>();
        registrationMenu.put(1, "Register a new customer");
        registrationMenu.put(2, "Register a new book");
        registrationMenu.put(3, "Edit a customer");
        registrationMenu.put(4, "Edit a book");
        registrationMenu.put(5, "Return");

        while(true){
            System.out.println("\n===== REGISTRATION MENU =====");
            super.displayMenu(registrationMenu);
            int choice = getUserChoice(registrationMenu);
            switch (choice){
                case 1 -> new CustomerRegistrationMenu().run();
                case 2 -> new BookRegistrationMenu().run();
                case 3 -> new EditMenu().run();
                case 4 -> new BookEditMenu().run();
                case 5 -> {return;}
            }
        }
    }
}
