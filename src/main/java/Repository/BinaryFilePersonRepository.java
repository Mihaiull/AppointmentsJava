package Repository;

import Domain.Person;
import java.io.*;

//class that extends the FileRepository class and implements the loadData and saveData methods to a text file.

public class BinaryFilePersonRepository extends FileRepository<Integer, Person> {
    public BinaryFilePersonRepository(String fileName) {
        super(fileName);
    }
    @SuppressWarnings("unchecked")
    @Override
    public void loadData() {
        try {
            ObjectInputStream ois = new ObjectInputStream(new FileInputStream(fileName));
            super.getHashMap().clear();
            super.getHashMap().putAll((java.util.HashMap<Integer, Person>) ois.readObject());
        } catch (IOException | ClassNotFoundException e) {
            throw new RuntimeException("Error reading from file", e);
        }
    }

    @Override
    public void saveData() {
        try {
            ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(fileName));
            oos.reset();
            oos.writeObject(super.getHashMap());
        } catch (IOException e) {
            throw new RuntimeException("Error writing to file ", e);
        }
    }

    @Override
    public void add(Integer integer, Person person) {
        super.add(integer, person);
        saveData();
    }

    @Override
    public void remove(Integer integer) {
        super.remove(integer);
        saveData();
    }

    @Override
    public void update(Integer integer, Person person) {
        super.update(integer, person);
        saveData();
    }


}