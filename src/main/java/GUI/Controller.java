package GUI;

import java.util.ArrayList;

import Domain.Appointment;
import Domain.Person;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;

import Service.Service;

public class Controller{
    private Service service;
    private ObservableList<Person> personObservableList;
    private ObservableList<Appointment> appointmentObservableList;

    public Controller(Service service){
        this.service = service;
    }

    @FXML
    private Button addAppointmentButton;

    @FXML
    private Button addPersonButton;

    @FXML
    private Button findAppointmentButton;

    @FXML
    private Button findPersonButton;

    @FXML
    private Button removeAppointmentButton;

    @FXML
    private Button removePersonButton;

    @FXML
    private Button updateAppointmentButton;

    @FXML
    private Button updatePersonButton;

    @FXML
    private TextField ageTextField;

    @FXML
    private ListView<Appointment> appoimentsListView;

    @FXML
    private TextField connditionTextField;

    @FXML
    private TextField dayTextField;

    @FXML
    private TextField hourTextField;

    @FXML
    private TextField idPersonTextField;

    @FXML
    private TextField idAppointmentTextField;

    @FXML
    private TextField minTextField;

    @FXML
    private TextField monthTextField;

    @FXML
    private TextField nameTextField;

    @FXML
    private TextField notesTextField;

    @FXML
    private ListView<Person> personListView;

    @FXML
    private TextField surnameTextField;

    @FXML
    private TextField yeartextField;

    void resetObservableList(){
        ArrayList<Person> persons = (ArrayList<Person>) this.service .getAllPersons();
        ArrayList<Appointment> appointments = (ArrayList<Appointment>) this.service.getAllAppointments();
        personObservableList = FXCollections.observableArrayList(persons);
        appointmentObservableList = FXCollections.observableArrayList(appointments);
        personListView.setItems(personObservableList);
        appoimentsListView.setItems(appointmentObservableList);
    }

    @FXML
    void addAppointmentButtonClicked() {
        int id = Integer.parseInt(idAppointmentTextField.getText());
        int pid = Integer.parseInt(idPersonTextField.getText());
        int year = Integer.parseInt(yeartextField.getText());
        String month = monthTextField.getText();
        int day = Integer.parseInt(dayTextField.getText());
        int hour = Integer.parseInt(hourTextField.getText());
        int minute = Integer.parseInt(minTextField.getText());
        String condition = connditionTextField.getText();
        String notes = notesTextField.getText();
        Person person = service.findPersonById(pid);
        if (person == null){
            System.out.println("Person not found, please add the person first");
            return;
        }
        service.addNewAppointment(id, person, year, month, day, hour, minute, condition, notes);
        for (Appointment appointment : service.getAllAppointments()){
            appointmentObservableList.add(appointment);
        }
        resetObservableList();
    }

    @FXML
    void addPersonButtonClicked() {
        int id = Integer.parseInt(idPersonTextField.getText());
        String name = nameTextField.getText();
        String surname = surnameTextField.getText();
        int age = Integer.parseInt(ageTextField.getText());
        service.addNewPerson(id, name, surname, age);
        for (Person person : service.getAllPersons()){
            personObservableList.add(person);
        }
        resetObservableList();
    }

    @FXML
    void findAppointmentButtonClicked() {
        int id = Integer.parseInt(idAppointmentTextField.getText());
        Appointment appointment = service.findAppointmentById(id);
        if (appointment == null){
            //how in tarnation do I do a popup
            System.out.println("Appointment not found");
            return;
        }
        appoimentsListView.getItems().clear();
        appoimentsListView.getItems().add(appointment);
        resetObservableList();
    }

    @FXML
    void findPersonButtonClicked() {
        int id = Integer.parseInt(idAppointmentTextField.getText());
        Person person = service.findPersonById(id);
        if (person == null){

            System.out.println("Person not found");
            return;
        }
        personListView.getItems().clear();
        personListView.getItems().add(person);
        resetObservableList();
    }

    @FXML
    void removeAppointmentButtonClicked() {
        int id = Integer.parseInt(idAppointmentTextField.getText());
        Appointment appointment = service.findAppointmentById(id);
        if (appointment == null){
            System.out.println("Appointment not found");
            return;
        }
        service.removeAppointment(id);
        appoimentsListView.getItems().clear();
        for (Appointment appointment1 : service.getAllAppointments()){
            appoimentsListView.getItems().add(appointment1);
        }
        resetObservableList();
    }

    @FXML
    void removePersonButtonClicked() {
        int id = Integer.parseInt(idAppointmentTextField.getText());
        Person person = service.findPersonById(id);
        if (person == null){
            System.out.println("Person not found");
            return;
        }
        service.removePerson(id);
        personListView.getItems().clear();
        for (Person person1 : service.getAllPersons()){
            personListView.getItems().add(person1);
        }
        resetObservableList();
    }

    @FXML
    void updateAppointmentButtonClicked() {
        int id = Integer.parseInt(idAppointmentTextField.getText());
        int pid = Integer.parseInt(idPersonTextField.getText());
        int year = Integer.parseInt(yeartextField.getText());
        String month = monthTextField.getText();
        int day = Integer.parseInt(dayTextField.getText());
        int hour = Integer.parseInt(hourTextField.getText());
        int minute = Integer.parseInt(minTextField.getText());
        String condition = connditionTextField.getText();
        String notes = notesTextField.getText();
        Person person = service.findPersonById(pid);
        if (person == null){
            System.out.println("Person not found, please add the person first");
            return;
        }
        if (service.findAppointmentById(id) == null)
        {
            System.out.println("Appointment not found");
            return;
        }
        //oh how old bad code comes back to bite my ass
        service.updateAppointment(id, pid, person.getSurname(), person.getName(), person.getAge() , year, month, day, hour, minute, condition, notes);
        appoimentsListView.getItems().clear();
        for (Appointment appointment : service.getAllAppointments()){
            appoimentsListView.getItems().add(appointment);
        }
        resetObservableList();
    }

    @FXML
    void updatePersonButtonClicked(){
        int id = Integer.parseInt(idPersonTextField.getText());
        String name = nameTextField.getText();
        String surname = surnameTextField.getText();
        int age = Integer.parseInt(ageTextField.getText());
        Person person = service.findPersonById(id);
        if (person == null)
        {
            System.out.println("Ayo fuck ya doin modifying the inexistence u a philosopher or something?");
            return;
        }
        service.updatePerson(id, surname, name, age);
        personListView.getItems().clear();
        for (Person person2 : service.getAllPersons()){
            personListView.getItems().add(person2);
        }
        resetObservableList();
    }
}
