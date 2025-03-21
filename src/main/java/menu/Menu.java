package menu;

import java.util.Map;
import java.util.Scanner;

public abstract class Menu {

    protected Scanner scanner = new Scanner(System.in);

    protected void displayMenu(Map<Integer, String> mainMenu) {
        mainMenu.forEach((key, value) -> System.out.println(key + " - " + value));
        System.out.print("Choose and option: ");
    }

    protected int getUserChoice(Map<Integer, String> menu){
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

    public abstract void run();
}
