package Repository;
import Domain.Person;

import java.sql.*;

//database repository for the Person class
//sqlite database

public class DBPersonRepository extends MemoryRepository<Integer, Person> {
    public String URL;
    public DBPersonRepository(String repoPath) {
        this.URL = "jdbc:sqlite:" + repoPath;
        //readFromDB();
    }

    public void readFromDB() {
        try (Connection connection = DriverManager.getConnection(URL);
             Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery("SELECT * FROM persons")) {
            while (resultSet.next()) {
                int id = resultSet.getInt("Pid");
                String surname = resultSet.getString("surname");
                String name = resultSet.getString("name");
                int age = resultSet.getInt("age");
                Person person = new Person(id, surname, name, age);
                super.add(id, person);
            }
        } catch (SQLException e) {
            throw new RuntimeException("Error reading from database", e);
        }
    }

    public void writeToDB() {
        try (Connection connection = DriverManager.getConnection(URL);
             Statement statement = connection.createStatement()) {
            statement.executeUpdate("DELETE FROM persons");
            for (Person person : super.getAll()) {
                String sql = "INSERT INTO persons (Pid, surname, name, age) VALUES (" + person.getId() + ", '" + person.getSurname() + "', '" + person.getName() + "', " + person.getAge() + ")";
                statement.executeUpdate(sql);
            }
        } catch (SQLException e) {
            throw new RuntimeException("Error writing to database", e);
        }
    }


    @Override
    public void add(Integer integer, Person person) {
        super.add(integer, person);
        writeToDB();
    }

    @Override
    public void remove(Integer integer) {
        super.remove(integer);
        writeToDB();
    }

    @Override
    public void update(Integer integer, Person person) {
        super.update(integer, person);
        writeToDB();
    }

}
