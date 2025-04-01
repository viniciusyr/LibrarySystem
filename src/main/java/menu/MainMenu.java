package menu;

import menu.registration.RegistrationMenu;

import java.util.HashMap;
import java.util.Map;

public class MainMenu extends Menu{

    public void run() {
        Map<Integer, String> mainMenu = new HashMap<>();
        mainMenu.put(1, "Registration");
        mainMenu.put(2, "Library");
        mainMenu.put(3, "Loans");
        mainMenu.put(4, "Exit");

        while (true) {
            System.out.println("\n===== MAIN MENU =====");
            super.displayMenu(mainMenu);

            int choice = getUserChoice(mainMenu);

            switch (choice) {
                case 1 -> new RegistrationMenu().run();
                case 2 -> new LibraryMenu().run();
                case 3 -> new LoansMenu().run();
                case 4 -> {
                    System.out.println("Goodbye!");
                    return;
                }
            }
        }
    }

}
