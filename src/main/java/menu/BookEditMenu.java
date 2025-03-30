package menu;

import service.BookService;
import service.ServiceFactory;

import java.util.InputMismatchException;

public class BookEditMenu extends Menu{

    private final BookService bs;
    private static final String TITLE_REGEX = "^[A-Za-zÀ-ÖØ-öø-ÿ' -]{2,100}$";
    private static final String NAME_REGEX = "^[A-Za-zÀ-ÖØ-öø-ÿ' -]{2,100}$";

    public BookEditMenu() {
        this.bs = ServiceFactory.getBookService();
    }

    @Override
    public void run() {

        System.out.println("\n===== EDIT BOOK =====");
        System.out.print("In order to edit a book information, it's necessary to enter a valid book ID");
        int bookId = readId();
        String newTitle = readStringInput("Enter the new title of the book: ", TITLE_REGEX);
        Integer newYear = readIntegerInput("Enter the new year of publication of the book : ");
        String newAuthor = readStringInput("Enter the new author of the book: ", NAME_REGEX);
        String newGenre = readStringInput("Enter the new genre of the book: ", TITLE_REGEX);

        bs.editBook(bookId, newTitle, newYear, newAuthor, newGenre);
    }

    private Integer readId() {
        while (true) {
            System.out.print("\nInsert the book ID: ");
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
