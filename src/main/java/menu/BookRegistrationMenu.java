package menu;

import enums.BookStatus;
import model.Book;
import service.BookService;
import service.ServiceFactory;

public class BookRegistrationMenu extends Menu{

    private final BookService bs;

    public BookRegistrationMenu(){
        this.bs = ServiceFactory.getBookService();
    }

    @Override
    public void run() {
        System.out.print("Insert the book title: ");
        String title = scanner.nextLine();
        System.out.print("Insert the year of publication of the book: ");
        int year = scanner.nextInt();
        scanner.nextLine();
        System.out.print("Insert the book author: ");
        String author = scanner.nextLine();
        System.out.print("Insert the book genre: ");
        String genre = scanner.nextLine();
        BookStatus status = BookStatus.AVAILABLE;

        bs.registerBook(title, year, author, genre, status);

    }
}
