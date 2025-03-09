package ServiceTest;

import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import Domain.Appointment;
import Domain.Person;
import Service.Service;

public class ServiceTest {
    private FakeRepository<Integer, Appointment> fakeAppointmentRepository;
    private FakeRepository<Integer, Person> fakePersonRepository;
    private Service service;

    @BeforeEach
    public void setUp() {
        fakeAppointmentRepository = new FakeRepository<>();
        fakePersonRepository = new FakeRepository<>();
        service = new Service();
        service.setFakeAppointmentRepository(fakeAppointmentRepository);
        service.setFakePersonRepository(fakePersonRepository);
    }

    //don't be an idiot and forget that fakerepo doesn't actually do shit
    //it just prints what you give to it

    @Test
    public void testAddNewPerson(){
        assertTrue(service.addNewPerson(1, "Mihai", "Yeyea", 21));
    }

    @Test
    public void testAddAppointment(){
        assertTrue(service.addNewAppointment(1, new Person(1, "ye", "yea", 24), 2024, "May", 23, 13, 30, "Boala", ""));
    }

}
