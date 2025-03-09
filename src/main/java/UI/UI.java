package UI;

import Domain.Appointment;
import Service.Service;
import java.util.Scanner;

public class UI {

    private static final String EXIT = "0";
    private static final String ADD_APPOINTMENT = "1";
    private static final String REMOVE_APPOINTMENT = "2";
    private static final String UPDATE_APPOINTMENT = "3";
    private static final String SHOW_ALL_APPOINTMENTS = "5";
    private static final String FIND_APPOINTMENT = "4";
    private static final String FILTER_APPOINTMENTS = "6";

    private final Service service;
    private final Scanner scanner;

    //constructor
    public UI(Service service) {
        this.service = service;
        this.scanner = new Scanner(System.in);
    }

    //function to print the menu
    public static void printMenu(){
        System.out.println("\n\n");
        System.out.println("ADD(1) : Add new appointment");
        System.out.println("REMOVE(2) : Remove and existing appointment");
        System.out.println("UPDATE(3) : Update and existing appointment");
        System.out.println("FIND(4) : Find an existing appointment");
        System.out.println("SHOW(5) : Show all appointments in the database");
        System.out.println("FILTER(6): Filter appointments (CONDITION, PATIENT)");
        System.out.println("EXIT(0) : Exit the program");
        //System.out.println("7 : Save data to file");
        //System.out.println("8 : Load data from file");
        System.out.println("\n");
    }

    //just to start the code with some mock data so I have what to work with
    public void createTestData(){//deprecated

        Integer appointmentId = 1;
        Integer personId = 1;
        service.addNewAppointment(appointmentId, personId, "Popescu", "Ion", 23, 2020, "January", 12, 16, 30, "Covid", "");

        appointmentId = 2;
        personId = 2;
        service.addNewAppointment(appointmentId, personId, "Ionescu", "Maria", 45, 2020, "February", 15, 10, 30, "Bombardier", "");

        appointmentId=435;
        personId=3;
        service.addNewAppointment(appointmentId, personId, "Hanu", "Gabriel", 20, 2024, "March", 15, 11, 00, "Utilizator Linux", "He uses arch bdw");

        appointmentId=712;
        personId=4;
        service.addNewAppointment(appointmentId, personId, "Ghilencea", "Mihai", 20, 2020, "March", 15, 11, 00, "Sufera in general", "");

        appointmentId=3;
        personId=4;
        service.addNewAppointment(appointmentId, personId, "Ghilencea", "Mihai", 29, 2024, "June", 16, 17, 35, "Brainrot", "");

        appointmentId=4;
        personId=6;
        service.addNewAppointment(appointmentId, personId, "Catanas", "Teodora", 20, 2024, "March", 15, 14, 00, "Utilizator Windows", "She uses windows bdw");

        appointmentId=5;
        personId = 4;
        service.addNewAppointment(appointmentId, personId, "Ghilencea", "Mihai", 21, 2025, "May", 17, 13, 20, "Cancer", "e ca si mort gen");
    }


    //adding a new appointment
    public boolean addAppointment(){
        System.out.println("Enter the id of the appointment");
        Integer id = Integer.valueOf(scanner.nextLine());
        System.out.println("Enter the id of the person");
        Integer personId = Integer.valueOf(scanner.nextLine());
        if (service.findPersonById(personId) != null){
            System.out.println("Person found!\n Adding new appointment: \n");
            System.out.println("Enter the year of the appointment");
            int year = Integer.parseInt(scanner.nextLine());
            System.out.println("Enter the month of the appointment");
            String month = scanner.nextLine();
            System.out.println("Enter the day of the appointment");
            int day = Integer.parseInt(scanner.nextLine());
            System.out.println("Enter the hour of the appointment");
            int hour = Integer.parseInt(scanner.nextLine());
            System.out.println("Enter the minute of the appointment");
            int minute = Integer.parseInt(scanner.nextLine());
            System.out.println("Enter the condition of the person");
            String condition = scanner.nextLine();
            System.out.println("Do you want to add notes? (Y/N)");
            String notes = scanner.nextLine();
            if (notes.equals("Y")){
                System.out.println("Enter the notes of the appointment");
                notes = scanner.nextLine();
            }
            else{
                notes = "";
            }
            return service.addNewAppointment(id, service.findPersonById(personId), year, month, day, hour, minute, condition, notes);

        }
        else{
            System.out.println("Person not found\n Adding new person");
            System.out.println("Enter the surname of the person");
            String surname = scanner.nextLine();
            System.out.println("Enter the name of the person");
            String name = scanner.nextLine();
            System.out.println("Enter the age of the person");
            int age = Integer.parseInt(scanner.nextLine());
            System.out.println("Enter the year of the appointment");
            int year = Integer.parseInt(scanner.nextLine());
            System.out.println("Enter the month of the appointment");
            String month = scanner.nextLine();
            System.out.println("Enter the day of the appointment");
            int day = Integer.parseInt(scanner.nextLine());
            System.out.println("Enter the hour of the appointment");
            int hour = Integer.parseInt(scanner.nextLine());
            System.out.println("Enter the minute of the appointment");
            int minute = Integer.parseInt(scanner.nextLine());
            System.out.println("Enter the condition of the person");
            String condition = scanner.nextLine();
            System.out.println("Do you want to add notes? (Y/N)");
            String notes = scanner.nextLine();
            if (notes.equals("Y")){
                System.out.println("Enter the notes of the appointment");
                notes = scanner.nextLine();
            }
            else{
                notes = "";
            }
            return service.addNewAppointment(id, personId, surname, name, age, year, month, day, hour, minute, condition, notes);

        }   
    }

    //removing an appointment
    public boolean removeAppointment(){
        System.out.println("Enter the id of the appointment you want to remove");
        Integer id = Integer.valueOf(scanner.nextLine());
        return service.removeAppointment(id);
    }

    //updating an appointment
    public boolean updateAppointment(){
        System.out.println("Enter the id of the appointment you want to update");
        Integer id = Integer.valueOf(scanner.nextLine());

        if (service.CheckAppointmentExistance(id) == false){
            System.out.println("Appointment does not exist");
            return false;
        }
        System.out.println("Enter the id of the person");
        Integer personId = Integer.valueOf(scanner.nextLine());
        System.out.println("Enter the surname of the person");
        String surname = scanner.nextLine();
        System.out.println("Enter the name of the person");
        String name = scanner.nextLine();
        System.out.println("Enter the age of the person");
        int age = Integer.parseInt(scanner.nextLine());
        System.out.println("Enter the year of the appointment");
        int year = Integer.parseInt(scanner.nextLine());
        System.out.println("Enter the month of the appointment");
        String month = scanner.nextLine();
        System.out.println("Enter the day of the appointment");
        int day = Integer.parseInt(scanner.nextLine());
        System.out.println("Enter the hour of the appointment");
        int hour = Integer.parseInt(scanner.nextLine());
        System.out.println("Enter the minute of the appointment");
        int minute = Integer.parseInt(scanner.nextLine());
        System.out.println("Enter the condition of the person");
        String condition = scanner.nextLine();
        System.out.println("Enter the notes of the appointment");
        String notes = scanner.nextLine();
        return service.updateAppointment(id, personId, surname, name, age, year, month, day, hour, minute, condition, notes);
    }

    //finding an appointment by id
    public String findAppointment(){
        System.out.println("Enter the id of the appointment you want to find");
        Integer id = Integer.valueOf(scanner.nextLine());
        if (service.CheckAppointmentExistance(id) == false){
            return "Appointment does not exist";
        }
        return service.findAppointmentById(id).toString();
    }

    //show all appointments
    public String showAllAppointments(){
        String result = "";
        for (Appointment appointment : service.getAllAppointments()){
            result += appointment.toString() + "\n";
        }
        return result;
    }

    //filter by condition
    public String filterByCondition(){
        //using the filter repository to filter appointments by condition
        System.out.println("Enter the condition you want to filter by");
        String condition = scanner.nextLine();
        System.out.println("Appointments filtered by condition: ");
        String result = "";
        for (Appointment appointment : service.filterAppointmentsByCondition(condition)){
            result += appointment.toString() + "\n";
        }
        return result;
    }

    //filter by patient  
    public String filterByPatient(){
        //using the filter repository to filter appointments by patient
        System.out.println("Enter the id of the patient you want to filter by");
        Integer id = Integer.valueOf(scanner.nextLine());
        if (service.findPersonById(id) == null){
            return "Person not found";
        }
        System.out.println("Appointments filtered by patient: ");
        String result = "";
        for (Appointment appointment : service.filterAppointmentsByPatient(id)){
            result += appointment.toString() + "\n";
        }
        return result;
    }

    //start the shitshow
    public void start(){
        //createTestData();
        while (true){
            try {
            printMenu();
            String command = scanner.nextLine();
            switch (command){
                case "ADD" -> addAppointment();
                case "REMOVE" -> removeAppointment();
                case "UPDATE" -> updateAppointment();
                case "FIND" -> System.out.println(findAppointment());
                case "SHOW" -> System.out.println(showAllAppointments());
                case "FILTER" -> {
                    System.out.println("Enter the filter you want to use");
                    System.out.println("CONDITION : Filter by condition");
                    System.out.println("PATIENT : Filter by patient");
                    String filter = scanner.nextLine();
                    switch (filter){
                        case "CONDITION" -> System.out.println(filterByCondition());
                        case "PATIENT" -> System.out.println(filterByPatient());
                        case "1" -> System.out.println(filterByCondition());     //again in case you want to use the numbers
                        case "2" -> System.out.println(filterByPatient()); 
                        default -> System.out.println("Please don't invent filters that don't exist");
                    }
                }
                case "EXIT" -> { scanner.close();return; }

                case ADD_APPOINTMENT -> addAppointment();
                                case REMOVE_APPOINTMENT -> removeAppointment();
                                case UPDATE_APPOINTMENT -> updateAppointment();                             //these are here just in case the user wants to use the numbers instead of the words
                                case FIND_APPOINTMENT -> System.out.println(findAppointment());             //i know i'll be lazy enough to start using them soooo...
                                case SHOW_ALL_APPOINTMENTS -> System.out.println(showAllAppointments());
                                case EXIT -> { scanner.close();return;}
                                case FILTER_APPOINTMENTS -> {
                                    System.out.println("Enter the filter you want to use");
                                    System.out.println("CONDITION(1) : Filter by condition");
                                    System.out.println("PATIENT(2) : Filter by patient");
                                    String filter = scanner.nextLine();
                                    switch (filter){
                                        case "CONDITION" -> System.out.println(filterByCondition());
                                        case "PATIENT" -> System.out.println(filterByPatient());
                                        case "1" -> System.out.println(filterByCondition()); //again in case you want to use the numbers
                                        case "2" -> System.out.println(filterByPatient()); 
                                        default -> System.out.println("Please don't invent filters that don't exist");
                                    }
                                }
                                case "7"-> {
                                    int id = Integer.parseInt(scanner.nextLine());
                                    System.out.println(service.allAppointmentsForPerson(id));
                                }
                
                                case "8" -> {
                                    String condition = scanner.nextLine();
                                    System.out.println(service.allAgesForSameCondition(condition));
                                }
                
                                case "9" ->{
                                    String name = scanner.nextLine();
                                    System.out.println(service.allAppointmentsForSameSurname(name));
                                }
                                case "10" ->{
                                    int age = Integer.parseInt(scanner.nextLine());
                                    System.out.println(service.allPersonsOverGivenAge(age));
                                }
                                case "11" ->{
                                    int hour = Integer.parseInt(scanner.nextLine());
                                    System.out.println(service.allAppointmentsLaterThanHour(hour));
                                }
                
            }
            }catch (Exception e){
                System.out.println("Invalid command");
            }
        }
    }
}