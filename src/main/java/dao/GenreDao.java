package dao;

import model.Genre;
import java.util.List;
import java.util.Map;

public interface GenreDao {
    public Genre insert(Genre genre);
    public void update(int genreId, Map<String, Object> fields);
    public Genre findById(int id);
    public void deleteById(int id);
    public List<Genre> findAll();
    public void updateMsg(int rowsAffected);
}
