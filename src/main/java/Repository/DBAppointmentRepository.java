package Repository;
import Domain.Appointment;
import Domain.Person;
import java.sql.*;

public class DBAppointmentRepository extends MemoryRepository<Integer, Appointment> {
    public String URL;

    public DBAppointmentRepository(String repoPath) {
        this.URL = "jdbc:sqlite:" + repoPath;
        //readFromDB();
    }

    public void readFromDB() {
        try (Connection connection = DriverManager.getConnection(URL);
             Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery("SELECT * FROM appointments")) {
            while (resultSet.next()) {
                int id = resultSet.getInt("Aid");
                int pid = resultSet.getInt("Pid");
                int year = resultSet.getInt("year");
                String month = resultSet.getString("month");
                int day = resultSet.getInt("day");
                int hour = resultSet.getInt("hour");
                int minute = resultSet.getInt("minute");
                String condition = resultSet.getString("condition");
                String notes = resultSet.getString("notes");
                Appointment appointment = new Appointment(id, new Person(pid, "yeyea", "yeyea", 1), year, month, day, hour, minute, condition, notes);
                //update the person
                DBPersonRepository personRepository = new DBPersonRepository("src/main/java/Data/SQLite/Persons.sqlite");
                for (Person person : personRepository.getAll()) {
                    if (person.getId() == pid) {
                        appointment.setPerson(person);
                    }
                }
                super.add(id, appointment);
            }
        } catch (SQLException e) {
            throw new RuntimeException("Error reading from database", e);
        }
    }

    public void writeToDB() {
        try (Connection connection = DriverManager.getConnection(URL);
             Statement statement = connection.createStatement()) {
            statement.executeUpdate("DELETE FROM appointments");
            for (Appointment appointment : super.getAll()) {
                int id = appointment.getId();
                if (id == 0) {
                    ResultSet resultSet = statement.executeQuery("SELECT MAX(Aid) FROM appointments");
                    if (resultSet.next()) {
                        id = resultSet.getInt(1) + 1;
                        appointment.setId(id);
                    }
                    else {
                        id = 1;
                        appointment.setId(id);
                    }
                }
                int pid = appointment.getPerson().getId();
                int year = appointment.getYear();
                String month = appointment.getMonth();
                int day = appointment.getDay();
                int hour = appointment.getHour();
                int minute = appointment.getMinute();
                String condition = appointment.getCondition();
                String notes = appointment.getNotes();
                if (notes == null) {
                    notes = "";
                }

                String sql = "INSERT INTO appointments (Aid, Pid, year, month, day, hour, minute, condition, notes) VALUES (" + id + ", " + pid + ", " + year + ", '" + month + "', " + day + ", " + hour + ", " + minute + ", '" + condition + "', '" + notes + "')";
                statement.executeUpdate(sql);
            }
        } catch (SQLException e) {
            throw new RuntimeException("Error writing to database", e);
        }
    }

    @Override
    public void add(Integer integer, Appointment appointment) {
        super.add(integer, appointment);
        writeToDB();
    }

    @Override
    public void remove(Integer integer) {
        super.remove(integer);
        writeToDB();
    }

    @Override
    public void update(Integer integer, Appointment appointment) {
        super.update(integer, appointment);
        writeToDB();
    }


}
