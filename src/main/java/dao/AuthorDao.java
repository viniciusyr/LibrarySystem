package dao;

import model.Author;
import java.util.List;
import java.util.Map;

public interface AuthorDao {
    public void insert(Author author);
    public void update(int authorId, Map<String, Object> fields);
    public Author findById(int id);
    public void deleteById(int id);
    public List<Author> findAll();
    public void updateMsg(int rowsAffected);
}
