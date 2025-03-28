package dao;

import model.Book;
import java.util.List;
import java.util.Map;

public interface BookDao {
    public Book insert(Book book);
    public void update(int bookId, Map<String, Object> fields);
    public Book findById(int id);
    public void deleteById(int id);
    public List<Book> findAll();
    public void updateMsg(int rowsAffected);
}
