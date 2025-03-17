package menu;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class MainMenu {

    public static Scanner scanner = new Scanner(System.in);

    public static void runMainMenu() {
        Map<Integer, String> mainMenu = new HashMap<>();
        mainMenu.put(1, "Registration");
        mainMenu.put(2, "Library");
        mainMenu.put(3, "Loans");
        mainMenu.put(4, "Exit");

        while (true) {
            System.out.println("\n===== MAIN MENU =====");
            displayMenu(mainMenu);

            int choice = getUserChoice(mainMenu);

            switch (choice) {
                case 1 -> runRegistrationMenu();
                case 2 -> runLibraryMenu();
                case 3 -> runLoansMenu();
                case 4 -> {
                    System.out.println("Goodbye!");
                    return;
                }
            }
        }
    }

    private static void displayMenu(Map<Integer, String> mainMenu) {
        mainMenu.forEach((key, value) -> System.out.println(key + " - " + value));
        System.out.print("Choose and option: ");
    }

    private static int getUserChoice(Map<Integer, String> menu){
        while(true) {
            try {
                int choice = Integer.parseInt(scanner.nextLine());
                if(menu.containsKey(choice)){
                    return choice;
                }
                System.out.println("Invalid option. Please, try again: ");
            } catch (NumberFormatException e){
                System.out.println("Invalid input. Enter a Number: ");
            }
        }
    }
}
