import java.util.Collection;

public interface IUserRepository {

    Collection<User> GetAll();

    boolean CheckId(int id);

    void Add(User personToAdd);

    void Update(User personToUpdate);

    void Read(int id);

    int CountPersonOverYrs(int yearsFromCount);

    int GetLastID();
}