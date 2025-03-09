package RepositoryTest;
import Repository.AppointmentMemoryRepository;
import Domain.Appointment;
import Domain.Person;
import org.junit.jupiter.api.*;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class AppointmentMemoryRepositoryTest {
    private AppointmentMemoryRepository appointmentrepo;

    @BeforeEach
    public void setUp(){
        appointmentrepo = new AppointmentMemoryRepository();
    }

    @Test
    public void testFindById(){
        Person person = new Person(1, "John", "Doe", 24);
        Appointment appointment = new Appointment(1, person, 2021, "May", 24, 12, 30, "Headache", "Take a pill");
        appointmentrepo.add(1, appointment);
        assertEquals(appointmentrepo.findById(1), appointment);
    }

    @Test
    public void testAdd(){
        Person person = new Person(1, "John", "Doe", 24);
        Appointment appointment = new Appointment(1, person, 2021, "May", 24, 12, 30, "Headache", "Take a pill");
        appointmentrepo.add(1, appointment);
        assertEquals(appointmentrepo.findById(1), appointment);
    }

    @Test
    public void testRemove(){
        Person person = new Person(1, "John", "Doe", 24);
        Appointment appointment = new Appointment(1, person, 2021, "May", 24, 12, 30, "Headache", "Take a pill");
        appointmentrepo.add(1, appointment);
        appointmentrepo.remove(1);
        assertEquals(appointmentrepo.findById(1), null);
    }

    @Test
    public void testUpdate(){
        Person person = new Person(1, "John", "Doe", 24);
        Appointment appointment = new Appointment(1, person, 2021, "May", 24, 12, 30, "Headache", "Take a pill");
        appointmentrepo.add(1, appointment);
        Appointment newappointment = new Appointment(1, person, 2021, "May", 24, 12, 30, "Headache", "Take a pill");
        appointmentrepo.update(1, newappointment);
        assertEquals(appointmentrepo.findById(1), newappointment);
    }

    @Test
    public void testGetAll(){
        Person person = new Person(1, "John", "Doe", 24);
        Appointment appointment = new Appointment(1, person, 2021, "May", 24, 12, 30, "Headache", "Take a pill");
        appointmentrepo.add(1, appointment);
        Person person2 = new Person(2, "Jane", "Doe", 24);
        Appointment appointment2 = new Appointment(2, person2, 2021, "May", 24, 12, 30, "Headache", "Take a pill");
        appointmentrepo.add(2, appointment2);
        assertEquals(appointmentrepo.getAll().spliterator().getExactSizeIfKnown(), 2);
    }

    @Test
    public void testGetHashMap(){
        Person person = new Person(1, "John", "Doe", 24);
        Appointment appointment = new Appointment(1, person, 2021, "May", 24, 12, 30, "Headache", "Take a pill");
        appointmentrepo.add(1, appointment);
        assertEquals(appointmentrepo.getHashMap().get(1), appointment);
    }

    @AfterEach
    public void tearDown(){
        appointmentrepo = null;
    }
}
