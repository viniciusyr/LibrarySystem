package dao;

import model.Book;
import model.Customer;

import java.util.List;

public interface BookDao {
    public Book insert(Book book);
    public void update(Book book);
    public Book findById(int id);
    public void deleteById(int id);
    public List<Book> findAll();
    public void updateMsg(int rowsAffected);
}
