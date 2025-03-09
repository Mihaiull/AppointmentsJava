package RepositoryTest;
import Repository.FilterRepository;
import Domain.Appointment;
import Domain.Person;
import Filter.*;
import org.junit.jupiter.api.*;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class FilterRepositoryTest {
    private FilterRepository filterrepo1;
    private FilterRepository filterrepo2;

    @BeforeEach
    public void setUp(){
        
        FilterByCondition filterbycondition = new FilterByCondition("Headache");
        Person person = new Person(1, "John", "Doe", 24); //i don't really have the same person repeating a lot
        FilterByPatient filterbypatient = new FilterByPatient(person);
        filterrepo1 = new FilterRepository(filterbycondition);
        filterrepo2 = new FilterRepository(filterbypatient);
    }

    @Test
    public void testFilterAppointments(){
        Person person = new Person(1, "John", "Doe", 24);
        Appointment appointment = new Appointment(1, person, 2021, "May", 24, 12, 30, "Headache", "Take a pill");
        filterrepo1.add(1, appointment);
        assertEquals(filterrepo1.filterAppointments().spliterator().getExactSizeIfKnown(), 1);
        filterrepo2.add(1, appointment);
        assertEquals(filterrepo2.filterAppointments().spliterator().getExactSizeIfKnown(), 0);
    }
}
