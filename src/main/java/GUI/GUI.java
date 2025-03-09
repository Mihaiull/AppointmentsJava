package GUI;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;
import java.util.Properties;

import Repository.*;
import Service.Service;


public class GUI extends Application {
        
    @Override
    public void start(Stage stage) throws Exception{
        FXMLLoader loader = new FXMLLoader(getClass().getResource("view.fxml"));
        Properties properties = new Properties();
        try{
        properties.load(GUI.class.getResourceAsStream("settings.properties"));
        }catch(Exception e){
            System.out.println("Error loading properties");
        }

        String repositoryType = properties.getProperty("REPOSITORY_TYPE");
        // String repositoryType = "MEMORY";
//        switch(repositoryType){
//            case "MEMORY" :{
//                AppointmentMemoryRepository appointmentRepository = new AppointmentMemoryRepository();
//                PersonMemoryRepository personRepository = new PersonMemoryRepository();
//                Service service = new Service(appointmentRepository, personRepository);
//                Controller controller = new Controller(service);
//                loader.setController(controller);
//                Scene scene = new Scene(loader.load());
//                stage.setScene(scene);
//                stage.show();
//            }
//            case "BINARY" :{
//                BinaryFileAppointmentRepository appointmentRepository = new BinaryFileAppointmentRepository(properties.getProperty("APPOINTMENT_FILE_BIN"));
//                BinaryFilePersonRepository personRepository = new BinaryFilePersonRepository(properties.getProperty("PERSON_FILE_BIN"));
//                Service service = new Service(appointmentRepository, personRepository);
//                Controller controller = new Controller(service);
//                loader.setController(controller);
//                Scene scene = new Scene(loader.load());
//                stage.setScene(scene);
//                stage.show();
//            }
//            case "TEXT" :{
//                TextFileAppointmentRepository appointmentRepository = new TextFileAppointmentRepository(properties.getProperty("APPOINTMENT_FILE_TXT"));
//                TextFilePersonRepository personRepository = new TextFilePersonRepository(properties.getProperty("PERSON_FILE_TXT"));
//                Service service = new Service(appointmentRepository, personRepository);
//                Controller controller = new Controller(service);
//                loader.setController(controller);
//                Scene scene = new Scene(loader.load());
//                stage.setScene(scene);
//                stage.show();
//            }
//            case "DATABASE" :{
//                DBAppointmentRepository appointmentRepository = new DBAppointmentRepository(properties.getProperty("APPOINTMENT_FILE_DATABASE"));
//                DBPersonRepository personRepository = new DBPersonRepository(properties.getProperty("PERSON_FILE_DATABASE"));
//                Service service = new Service(appointmentRepository, personRepository);
//                Controller controller = new Controller(service);
//                loader.setController(controller);
//                Scene scene = new Scene(loader.load());
//                stage.setScene(scene);
//                stage.show();
//            }
//            case "":{
//                System.out.println("No repository type specified");
//            }
//            default:{
//                System.out.println("Invalid repository type");
//                break;
//            }
//        }
        AppointmentMemoryRepository appointmentRepository = new AppointmentMemoryRepository();
        PersonMemoryRepository personRepository = new PersonMemoryRepository();
        Service service = new Service(appointmentRepository, personRepository);
        Controller controller = new Controller(service);
        loader.setController(controller);
        Scene scene = new Scene(loader.load());
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args){
        launch(args);
    }
}
