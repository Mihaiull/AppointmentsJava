package Main;

import Repository.*;
import Service.Service;
import UI.UI;
import java.io.IOException;
import java.util.Properties;

public class main {
    public static void main(String[] args) {
        Properties properties = new Properties();
        try {
            properties.load(main.class.getResourceAsStream("settings.properties"));
        } catch (IOException e) {
            System.out.println("Error loading properties");
        }
        String repositoryType = properties.getProperty("REPOSITORY_TYPE");
        switch (repositoryType) {
            case "MEMORY":{
                System.out.println("REPOSITORY MODE: ");
                //blue color
                System.out.println("\033[0;31m");
                System.out.println("MEMORY");
                //reset color
                System.out.println("\033[0m");
                AppointmentMemoryRepository appointmentRepository = new AppointmentMemoryRepository();
                PersonMemoryRepository personRepository = new PersonMemoryRepository();
                Service service = new Service(appointmentRepository, personRepository);
                UI ui = new UI(service);
                ui.createTestData();
                ui.start();
                break;}
            case "BINARY":{
                System.out.println("REPOSITORY MODE: ");
                //red color
                System.out.println("\033[0;34m");
                System.out.println("BINARY");
                //reset color
                BinaryFileAppointmentRepository appointmentRepository = new BinaryFileAppointmentRepository(properties.getProperty("APPOINTMENT_FILE_BIN"));
                BinaryFilePersonRepository personRepository = new BinaryFilePersonRepository(properties.getProperty("PERSON_FILE_BIN"));
                Service service = new Service(appointmentRepository, personRepository);
                UI ui = new UI(service);
                ui.start();
                break;}
            case "TEXT":{
                System.out.println("REPOSITORY MODE: ");
                //green color
                System.out.println("\033[0;32m");
                System.out.println("TEXT");
                //reset color
                System.out.println("\033[0m");
                TextFileAppointmentRepository appointmentRepository = new TextFileAppointmentRepository(properties.getProperty("APPOINTMENT_FILE_TXT"));
                TextFilePersonRepository personRepository = new TextFilePersonRepository(properties.getProperty("PERSON_FILE_TXT"));
                Service service = new Service(appointmentRepository, personRepository);
                UI ui = new UI(service);
                ui.start();
                break;}
            case "DATABASE":{
                System.out.println("REPOSITORY MODE: ");
                //yellow color
                System.out.println("\033[0;33m");
                System.out.println("DATABASE");
                //reset color
                System.out.println("\033[0m");
                DBAppointmentRepository appointmentRepository = new DBAppointmentRepository(properties.getProperty("APPOINTMENT_FILE_DATABASE"));
                DBPersonRepository personRepository = new DBPersonRepository(properties.getProperty("PERSON_FILE_DATABASE"));
                Service service = new Service(appointmentRepository, personRepository);
                UI ui = new UI(service);
                ui.start();
                break;}
            case "":
                System.err.println("Repository type not specified");
            default:{
                System.out.println("Invalid repository type");
                break;}
        }
        
        //AppointmentMemoryRepository appointmentRepository = new AppointmentMemoryRepository();
        //PersonMemoryRepository personRepository = new PersonMemoryRepository();
        //BinaryFileAppointmentRepository appointmentRepository = new BinaryFileAppointmentRepository("Data/Binary/appointments.bin");
        //BinaryFilePersonRepository personRepository = new BinaryFilePersonRepository("Data/Binary/persons.bin");
        //TextFileAppointmentRepository appointmentRepository = new TextFileAppointmentRepository("Data/Text/appointments.txt");
        //TextFilePersonRepository personRepository = new TextFilePersonRepository("Data/Text/persons.txt");

    }
}