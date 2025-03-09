package Repository;

import Domain.Person;
import java.io.*;
import java.util.Iterator;

//class that extends the FileRepository class and implements the loadData and saveData methods to a text file.
//The used file is defined under TEXT_FILE in settings.properties

public class TextFilePersonRepository extends FileRepository<Integer, Person> {
    public TextFilePersonRepository(String fileName) {
        super(fileName);
    }

    @Override
    public void loadData() {
        try {
            BufferedReader br = new BufferedReader(new FileReader(fileName));
            String line;
            while ((line = br.readLine()) != null) {
                String[] fields = line.split(";");
                if (fields.length != 4) {
                    System.err.println("Line is not valid: " + line);
                    continue;
                }
                int id = Integer.parseInt(fields[0]);
                String surname = fields[1];
                String name = fields[2];
                int age = Integer.parseInt(fields[3]);
                Person person = new Person(id, surname, name, age);
                super.add(id, person);
            }
            br.close();
        } catch (IOException e) {
            throw new RuntimeException("Error reading from file", e);
        }
    }

    @Override
    public void saveData() {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(fileName))) {
            Iterator<Person> it = super.getAll().iterator();
            while (it.hasNext()) {
                Person person = it.next();
                String line = person.getId() + ";" + person.getSurname() + ";" + person.getName() + ";" + person.getAge();
                bw.write(line);
                bw.newLine();
            }
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
    public void update(Integer integer, Person person) {
        super.update(integer, person);
        saveData();
    }

    @Override
    public void remove(Integer integer) {
        super.remove(integer);
        saveData();
    }
}