package menu.edit;

import menu.Menu;

import java.util.HashMap;
import java.util.Map;

public class EditMenu extends Menu {
    @Override
    public void run() {
        Map<Integer, String> editMenu = new HashMap<>();
        editMenu.put(1, "Edit customer");
        editMenu.put(2, "Return");

        while(true){
            System.out.println("\n===== EDIT MENU =====");
            super.displayMenu(editMenu);
            int choice = getUserChoice(editMenu);
            switch (choice){
                case 1 -> new CustomerEditMenu().run();
                case 2 -> {return;}
            }

        }
    }
}
