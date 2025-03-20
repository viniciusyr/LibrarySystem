import java.util.List;

public interface CustomerDao {
    public void insert(Customer customer);
    public void update(Customer customer, String newEmail);
    public Customer findById(int id);
    public void deleteById(int id);
    public List<Customer> findAll();
    public void updateMsg(int rowsAffected);
}
