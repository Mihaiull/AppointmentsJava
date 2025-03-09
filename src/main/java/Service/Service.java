package Service;

import Domain.*;
import Filter.*;
import Repository.*;
import java.util.ArrayList;
import java.util.Arrays;


public class Service {

    //Repository object to store the repository that the service will use
    //these should be compatible with both memory repo and file repo
    private Repository<Integer, Appointment> appointmentRepository;
    private Repository<Integer, Person> personRepository;

    //load properties file
    public Service() {
    }

    //constructor
    public Service(AppointmentMemoryRepository appointmentrepository, PersonMemoryRepository personRepository) {
        if (appointmentrepository == null) {
            throw new RuntimeException("Repository cannot be null");
        }
        if (!appointmentrepository.getClass().getName().equals("Repository.AppointmentMemoryRepository")) {
            throw new RuntimeException("Repository must be of type AppointmentMemoryRepository");
        }

        if (personRepository == null) {
            throw new RuntimeException("Repository cannot be null");
        }
        if (!personRepository.getClass().getName().equals("Repository.PersonMemoryRepository")) {
            throw new RuntimeException("Repository must be of type PersonMemoryRepository");
        }
        this.appointmentRepository = appointmentrepository;
        this.personRepository = personRepository;
    }

    public Service(BinaryFileAppointmentRepository appointmentrepository, BinaryFilePersonRepository personRepository) {
        if (appointmentrepository == null) {
            throw new RuntimeException("Repository cannot be null");
        }
        if (!appointmentrepository.getClass().getName().equals("Repository.BinaryFileAppointmentRepository")) {
            throw new RuntimeException("Repository must be of type BinaryFileAppointmentRepository");
        }

        if (personRepository == null) {
            throw new RuntimeException("Repository cannot be null");
        }
        if (!personRepository.getClass().getName().equals("Repository.BinaryFilePersonRepository")) {
            throw new RuntimeException("Repository must be of type BinaryFilePersonRepository");
        }
        this.appointmentRepository = appointmentrepository;
        this.personRepository = personRepository;
    }

    public Service(TextFileAppointmentRepository appointmentrepository, TextFilePersonRepository personRepository) {
        if (appointmentrepository == null) {
            throw new RuntimeException("Repository cannot be null");
        }
        if (!appointmentrepository.getClass().getName().equals("Repository.TextFileAppointmentRepository")) {
            throw new RuntimeException("Repository must be of type TextFileAppointmentRepository");
        }

        if (personRepository == null) {
            throw new RuntimeException("Repository cannot be null");
        }
        if (!personRepository.getClass().getName().equals("Repository.TextFilePersonRepository")) {
            throw new RuntimeException("Repository must be of type TextFilePersonRepository");
        }
        this.appointmentRepository = appointmentrepository;
        this.personRepository = personRepository;
    }

    public Service(DBAppointmentRepository appointmentrepository, DBPersonRepository personRepository) {
        if (appointmentrepository == null) {
            throw new RuntimeException("Repository cannot be null");
        }
        if (!appointmentrepository.getClass().getName().equals("Repository.DBAppointmentRepository")) {
            throw new RuntimeException("Repository must be of type DBAppointmentRepository");
        }

        if (personRepository == null) {
            throw new RuntimeException("Repository cannot be null");
        }
        if (!personRepository.getClass().getName().equals("Repository.DBPersonRepository")) {
            throw new RuntimeException("Repository must be of type DBPersonRepository");
        }
        this.appointmentRepository = appointmentrepository;
        this.personRepository = personRepository;
    }
    
    //set the appointment repository that the service will use
    public boolean  setAppointmentRepository(AppointmentMemoryRepository repository) {
        //check type
        if (repository == null) {
            throw new RuntimeException("Repository cannot be null");
        }
        if (repository.getClass().getName().equals("Repository.AppointmentMemoryRepository")) {
            this.appointmentRepository = repository;
            return true;
        }
        else {
            throw new RuntimeException("Repository must be of type AppointmentMemoryRepository");
        }
    }

    //set the person repository that the service will use
    public boolean setpersonRepository(PersonMemoryRepository repository) {
        //check type
        if (repository == null) {
            throw new RuntimeException("Repository cannot be null");
        }
        if (repository.getClass().getName().equals("Repository.PersonMemoryRepository")) {
            this.personRepository = repository;
            return true;
        }
        else {
            throw new RuntimeException("Repository must be of type PersonMemoryRepository");
        }
    }

    //method to use the fake repository for testing
    public boolean setFakeAppointmentRepository(Repository<Integer, Appointment> repository) {
        //check type
        if (repository == null) {
            throw new RuntimeException("Repository cannot be null");
        }
        if (repository.getClass().getName().equals("ServiceTest.FakeRepository")) {
            this.appointmentRepository = repository;
            return true;
        }
        else {
            throw new RuntimeException("Repository must be of type FakeRepository");
        }
    }
    public boolean setFakePersonRepository(Repository<Integer, Person> repository) {
        //check type
        if (repository == null) {
            throw new RuntimeException("Repository cannot be null");
        }
        if (repository.getClass().getName().equals("ServiceTest.FakeRepository")) {
            this.personRepository = repository;
            return true;
        }
        else {
            throw new RuntimeException("Repository must be of type FakeRepository");
        }
    }

    public boolean addNewPerson(Integer id, String surname, String name, int age) {
        //validate data
        if (id == null) {
            throw new RuntimeException("Id cannot be null");
        }
        if (id < 0) {
            throw new RuntimeException("Id cannot be negative");
        }

        //validate surname
        if (surname == null || surname.isEmpty()) {
            throw new RuntimeException("Surname cannot be empty");
        }

        //validate name
        if (name == null || name.isEmpty()) {
            throw new RuntimeException("Name cannot be empty");
        }

        //validate age
        if (age < 0) {
            throw new RuntimeException("Age cannot be negative");
        }

        //check for existing person
        if (personRepository.findById(id) != null) {
            throw new RuntimeException("Person already exists");
        }

        //create person since everything is valid
        Person person = new Person(id, surname, name, age);

        //add person to repository
        personRepository.add(id, person);
        return true;
    }

    public boolean removePerson(Integer id) {
        //validate id
        if (id == null) {
            throw new RuntimeException("Id cannot be null");
        }
        if (id < 0) {
            throw new RuntimeException("Id cannot be negative");
        }

        //check if person exists
        Person person = personRepository.findById(id);
        if (person == null) {
            throw new RuntimeException("Person does not exist");
        }

        //check if person has appointments
        for (Appointment appointment : appointmentRepository.getAll()) {
            if (appointment.getPerson().getId().equals(id)) {
                throw new RuntimeException("Person has appointments");
            }
        }

        //remove person
        personRepository.remove(id);
        return true;
    }

    public boolean updatePerson(Integer id, String surname, String name, int age) {
        //validate data
        if (id == null) {
            throw new RuntimeException("Id cannot be null");
        }
        if (id < 0) {
            throw new RuntimeException("Id cannot be negative");
        }

        //validate surname
        if (surname == null || surname.isEmpty()) {
            throw new RuntimeException("Surname cannot be empty");
        }

        //validate name
        if (name == null || name.isEmpty()) {
            throw new RuntimeException("Name cannot be empty");
        }

        //validate age
        if (age < 0) {
            throw new RuntimeException("Age cannot be negative");
        }

        //check if person exists
        Person person = personRepository.findById(id);
        if (person == null) {
            throw new RuntimeException("Person does not exist");
        }

        //create person since everything is valid
        Person newPerson = new Person(id, surname, name, age);

        //update person
        personRepository.update(id, newPerson);

        //update appointments
        for (Appointment appointment : appointmentRepository.getAll()) {
            if (appointment.getPerson().getId().equals(id)) {
                appointment.setPerson(newPerson);
            }
        }
        return true;
    }

    public Person findPersonById(Integer id) {
        //validate id
        if (id == null) {
            throw new RuntimeException("Id cannot be null");
        }
        if (id < 0) {
            throw new RuntimeException("Id cannot be negative");
        }

        //check if person exists
        Person person = personRepository.findById(id);

        return person;
    }

    // add new appointment to repository -- new person
    public boolean addNewAppointment(Integer id, Integer personId, String surname, String name, int age, int year, String month, int day, int hour, int minute, String condition, String notes) {
        
        //validate data
        ValidateData(id, personId, surname, name, age, year, month, day, hour, minute, condition, notes);

        //check for existing appointment
        if (appointmentRepository.findById(id) != null) {
            throw new RuntimeException("Appointment already exists");
        }

        //create person since everything is valid
        Person person = new Person(personId, surname, name, age);

        //add person to repository
        personRepository.add(personId, person);

        //create appointment since everything is valid
        Appointment appointment = new Appointment(id, person, year, month, day, hour, minute, condition, notes);

        //add appointment to repository
        appointmentRepository.add(id, appointment);
        return true;

    }

    //add new appointment to repository -- existing person
    public boolean addNewAppointment(Integer id, Person person, int year, String month, int day, int hour, int minute, String condition, String notes) {
        //validate id
        if (id == null) {
            throw new RuntimeException("Id cannot be null");
        }
        if (id < 0) {
            throw new RuntimeException("Id cannot be negative");
        }

        //create appointment since everything is valid
        Appointment appointment = new Appointment(id, person, year, month, day, hour, minute, condition, notes);

        //add appointment to repository
        appointmentRepository.add(id, appointment);
        return true;
    }

    //remove an appointment from the repository
    public boolean removeAppointment(Integer id) {
        //validate id
        if (id == null) {
            throw new RuntimeException("Id cannot be null");
        }
        if (id < 0) {
            throw new RuntimeException("Id cannot be negative");
        }

        //check if appointment exists
        Appointment appointment = appointmentRepository.findById(id);
        if (appointment == null) {
            throw new RuntimeException("Appointment does not exist");
        }

        //remove appointment
        appointmentRepository.remove(id);
        return true;
    }

    //function used to validate person and appointment data
    public boolean ValidateData(Integer id, Integer personId, String surname, String name, int age, int year, String month, int day, int hour, int minute, String condition, String notes){

        //validate id
        if (id == null) {
            throw new RuntimeException("Id cannot be null");
        }
        if (id < 0) {
            throw new RuntimeException("Id cannot be negative");
        }

        if (personId == null) {
            throw new RuntimeException("Person Id cannot be null");
        }
        if (personId < 0) {
            throw new RuntimeException("Person Id cannot be negative");
        }

        //validate person

        //validate surname
        if (surname == null || surname.isEmpty()) {
            throw new RuntimeException("Surname cannot be empty");
        }

        //validate name
        if (name == null || name.isEmpty()) {
            throw new RuntimeException("Name cannot be empty");
        }

        //validate age
        if (age < 0) {
            throw new RuntimeException("Age cannot be negative");
        }


        //validate appointment

        //validate year
        if (year < 0) {
            throw new RuntimeException("Year cannot be negative");
        }

        //validate month
        if (month == null || month.isEmpty()) {
            throw new RuntimeException("Month cannot be empty");
        }

        //array of months to check inputed month against
        ArrayList<String> months = new ArrayList<>(Arrays.asList("January", "February", "March", "April", "May", "June", "July", "August", "September", "October", "November", "December"));


        if (!months.contains(month)) {
            throw new RuntimeException("Never heard of a month called " + month);
        }

        if (day < 0) {
            throw new RuntimeException("Day cannot be negative");
        }
        if (day > 31) {
            throw new RuntimeException("Day cannot be greater than 31");
        }

        //gotta check for the months with 30 days
        if (day == 31 && (month.equals("April") || month.equals("June") || month.equals("September") || month.equals("November"))) {
            throw new RuntimeException("There are only 30 days in " + month);
        }

        //gotta check for February
        if (day > 29 && month.equals("February")) {
            throw new RuntimeException("February has at most 29 days");
        }

        //gotta check for leap years
        if (day == 29 && month.equals("February") && year % 4 != 0) {
            throw new RuntimeException("February has at most 28 days in a non-leap year");
        }

        if (hour < 0) {
            throw new RuntimeException("Hour cannot be negative");
        }
        if (hour > 18) {
            throw new RuntimeException("I don't work 24/7 my shift ends at 18:00");
        }
        if (minute < 0) {
            throw new RuntimeException("Minute cannot be negative");
        }
        if (minute > 59) {
            throw new RuntimeException("Minute cannot be greater than 59");
        }
        if (condition == null || condition.isEmpty()) {
            throw new RuntimeException("Condition cannot be empty");
        }

        //notes can be empty
        return true;
    }
    
    //used in ui to ckech if an appointment exists before having to input the rest of the data
    public boolean CheckAppointmentExistance(Integer id){
        
        return appointmentRepository.findById(id) != null; //if was redundant 
    }
    
    //update an existing appointment
    public boolean updateAppointment(Integer id, Integer personId, String surname, String name, int age, int year, String month, int day, int hour, int minute, String condition, String notes) {
        //validate data
        ValidateData(id, personId, surname, name, age, year, month, day, hour, minute, condition, notes);

        //check if appointment exists
        Appointment appointment = appointmentRepository.findById(id);
        if (appointment == null) {
            throw new RuntimeException("Appointment does not exist");
        }

        //create person since everything is valid
        Person person = new Person(personId, surname, name, age);

        //create appointment since everything is valid
        Appointment newAppointment = new Appointment(id, person, year, month, day, hour, minute, condition, notes);

        //update appointment
        appointmentRepository.update(id, newAppointment);
        return true;
    }

    //find an appointment by id
    public Appointment findAppointmentById(Integer id) {
        //validate id
        if (id == null) {
            throw new RuntimeException("Id cannot be null");
        }
        if (id < 0) {
            throw new RuntimeException("Id cannot be negative");
        }

        //check if appointment exists
        Appointment appointment = appointmentRepository.findById(id);
        if (appointment == null) {
            throw new RuntimeException("Appointment does not exist");
        }

        return appointment;
    }

    //filter appointments by condition using filter repository
    public Iterable<Appointment> filterAppointmentsByCondition(String condition) {
        //validate condition
        if (condition == null || condition.isEmpty()) {
            throw new RuntimeException("Condition cannot be empty");
        }

        //create filter repository
        FilterRepository filterRepository = new FilterRepository(new FilterByCondition(condition));

        //filter appointments
        return filterRepository.filterAppointments();
        
    }

    //Filter appointments by person
    public Iterable<Appointment> filterAppointmentsByPatient(Integer personId) {
        //validate id
        if (personId == null) {
            throw new RuntimeException("Person Id cannot be null");
        }
        if (personId < 0) {
            throw new RuntimeException("Person Id cannot be negative");
        }

        //check if person exists
        Person person = personRepository.findById(personId);
        if (person == null) {
            throw new RuntimeException("Person does not exist");
        }


        //create filter repository
        FilterRepository filterRepository = new FilterRepository(new FilterByPatient(person));

        //filter appointments
        return filterRepository.filterAppointments();
    }


    //get all appointments
    public Iterable<Appointment> getAllAppointments() {
        
        //check if there are any appointments
        if (appointmentRepository.getAll().spliterator().getExactSizeIfKnown() == 0) {
            throw new RuntimeException("No appointments found");
        }

        //return all appointments
        return appointmentRepository.getAll();
    }

    //get all persons
    public Iterable<Person> getAllPersons() {
        
        //check if there are any persons
        if (personRepository.getAll().spliterator().getExactSizeIfKnown() == 0) {
            throw new RuntimeException("No persons found");
        }

        //return all persons
        return personRepository.getAll();
    }

    //with stream
    //all appointments for one person and return conditions
    public Iterable<String> allAppointmentsForPerson(int id){
        ArrayList<Appointment> appointments = new ArrayList<>();
        for (Appointment appointment : appointmentRepository.getAll()){
            appointments.add(appointment);
        }
        return appointments.stream() .filter(appointment -> appointment.getPerson().getId().equals(id)) .map(Appointment :: toString).toList();
    }

    public Iterable<Integer> allAgesForSameCondition(String condition){
        ArrayList<Appointment> appointments = new ArrayList<>();
        for (Appointment appointment : appointmentRepository.getAll()){
            appointments.add(appointment);
        }
        return appointments.stream() .filter(appointment -> appointment.getCondition().equals(condition)) .map(Appointment :: getPerson) .map(Person :: getAge).toList();
    }

    public Iterable<Appointment> allAppointmentsForSameSurname(String name){
        ArrayList<Appointment> appointments = new ArrayList<>();
        for (Appointment appointment : appointmentRepository.getAll()){
            appointments.add(appointment);
        }

        return appointments.stream() .filter(appointment -> appointment.getPerson().getSurname().equals(name)).toList();
    }

    public Iterable<Person> allPersonsOverGivenAge(int age){
        ArrayList<Person> persons = new ArrayList<>();
        for (Person person : personRepository.getAll()){
            persons.add(person);
        }

        return persons.stream() .filter(person -> person.getAge() == age).toList();
    }

    //all appointments later than a given hour
    public Iterable<Appointment> allAppointmentsLaterThanHour(int hour){
        ArrayList<Appointment> appointments = new ArrayList<>();
        for (Appointment appointment : appointmentRepository.getAll()){
            appointments.add(appointment);
        }

        return appointments.stream() .filter(appointment -> appointment.getHour() == hour).toList();
    }

}




