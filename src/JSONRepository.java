import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileOutputStream;
import java.io.PrintWriter;
import java.lang.reflect.Type;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Collection;
import java.util.Iterator;

class JsonPersonRepository implements IUserRepository {

    private final String dbFile = "users.json";

    @Override
    public Collection<User> GetAll() {
        try {
            Gson gson = new Gson();
            String json = new String(Files.readAllBytes(Paths.get(dbFile)),
                    StandardCharsets.UTF_8);
            Type listType = new TypeToken<Collection<User>>() {
            }.getType();

            return gson.fromJson(json, listType);
        } catch (Exception ex) {
            return null;
        }
    }

    @Override
    public boolean CheckId(int id) {
        Collection<User> json = GetAll();
        boolean isInCollectionFlag = false;
        for (User user: json) {
            if(user.getId()==id) {
                isInCollectionFlag = true;
            }
        }
        System.out.println(isInCollectionFlag);
        return isInCollectionFlag;
    }

    @Override
    public void Add(User personToAdd) {
        Collection<User> list = GetAll();

        list.add(personToAdd);

        SaveData(list);
    }

    @Override
    public void Update(User personToUpdate) {
        // TODO implement update function
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void Remove(int id) {
        // TODO implement remove function
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.

    }

    @Override
    public int CountPersonOverYrs(int yearsFromCount) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public int GetLastID() {
        Collection<User> json = GetAll();
        Iterator itr = json.iterator();
        User lastElement = (User) itr.next(); 
        while(itr.hasNext()) {
            lastElement = (User) itr.next();
        }
        System.out.println();
        return lastElement.getId()+1;
    }

    private void SaveData(Collection<User> list) {
        try {
            Gson gson = new Gson();
            String jsonNew = gson.toJson(list);

            PrintWriter pw = new PrintWriter(new FileOutputStream(
                    new File(dbFile)));
            pw.print(jsonNew);
            pw.close();

        } catch (Exception ex) {

        }

    }

}
