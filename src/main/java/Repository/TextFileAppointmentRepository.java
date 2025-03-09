package Repository;

import Domain.Appointment;
import Domain.Person;
import java.io.*;

public class TextFileAppointmentRepository extends FileRepository<Integer, Appointment> {
    public TextFileAppointmentRepository(String fileName) {
        super(fileName);
    }

    @SuppressWarnings("resource")
    @Override
    public void loadData(){
        try {
            BufferedReader br = new BufferedReader(new FileReader(fileName));
            String line;
            while ((line = br.readLine()) != null) {
                String[] fields = line.split(";");
                if (fields.length != 9) {
                    System.err.println("Line is not valid: " + line);
                    continue;
                }
                int id = Integer.parseInt(fields[0]);
                int personId = Integer.parseInt(fields[1]);
                int year = Integer.parseInt(fields[2]);
                String month = fields[3];
                int day = Integer.parseInt(fields[4]);
                int hour = Integer.parseInt(fields[5]);
                int minute = Integer.parseInt(fields[6]);
                String condition = fields[7];
                String notes = fields[8];
                //create a mock person, as the person is not loaded yet
                //remember to update the person field after everything gets loaded to the memory repository
                Person person = new Person(personId, "mock", "mock", 0);
                Appointment appointment = new Appointment(id, person, year, month, day, hour, minute, condition, notes);
                //find the person and update the appointment
                TextFilePersonRepository personRepository = new TextFilePersonRepository("src/main/java/Data/Text/persons.txt");
                for (Person p : personRepository.getAll()) {
                    if (p.getId() == personId) {
                        appointment.setPerson(p);
                    }
                }
                super.add(id, appointment);

            }
        } catch (IOException e) {
            throw new RuntimeException("Error reading from file", e);
        }
    }

    @Override
    public void saveData(){
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(fileName))) {
            for (Appointment appointment : super.getAll()) {
                String notes = appointment.getNotes();
                if ("".equals(notes)) {
                    notes = "none";
                }
                String line = appointment.getId() + ";" + appointment.getPerson().getId() + ";" + appointment.getYear() + ";" + appointment.getMonth() + ";" + appointment.getDay() + ";" + appointment.getHour() + ";" + appointment.getMinute() + ";" + appointment.getCondition() + ";" + notes;
                bw.write(line);
                bw.newLine();
            }
        } catch (IOException e) {
            throw new RuntimeException("Error writing to file ", e);
        }
    }

    @Override
    public void add(Integer integer, Appointment appointment) {
        super.add(integer, appointment);
        saveData();
    }

    @Override
    public void update(Integer integer, Appointment appointment) {
        super.update(integer, appointment);
        saveData();
    }

    @Override
    public void remove(Integer integer) {
        super.remove(integer);
        saveData();
    }

}

