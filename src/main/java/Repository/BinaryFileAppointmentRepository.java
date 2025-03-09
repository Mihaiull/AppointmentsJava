package Repository;

import Domain.Appointment;
import java.io.*;

public class BinaryFileAppointmentRepository extends FileRepository<Integer, Appointment> {
    public BinaryFileAppointmentRepository(String fileName) {
        super(fileName);
    }

    @SuppressWarnings("unchecked")
    @Override
    public void loadData(){
        try (ObjectInputStream in = new ObjectInputStream(new FileInputStream(fileName))) {
            super.getHashMap().clear();
            super.getHashMap().putAll((java.util.HashMap<Integer, Appointment>) in.readObject());
        } catch (EOFException e) {
            //e.printStackTrace();
        } catch (IOException | ClassNotFoundException e) {
            throw new RuntimeException("Error reading from file", e);
        }
    }

    @Override
    public void saveData(){
        try (ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(fileName))) {
            out.reset();
            out.writeObject(super.getHashMap());
            // for (Appointment appointment : super.getAll()) {
            //     out.writeObject(appointment);
            //     out.flush();
            // }
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
    public void remove(Integer integer) {
        super.remove(integer);
        saveData();
    }

    @Override
    public void update(Integer integer, Appointment appointment) {
        super.update(integer, appointment);
        saveData();
    }
    
}