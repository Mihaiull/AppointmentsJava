package DomainTest;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.*;
import Domain.Appointment;
import Domain.Person;

public class AppointmentTest {
    private Appointment appointment1;
    private Appointment appointment2;
    private Appointment appointment3;

    @BeforeEach
    public void setUp() {
        appointment1 = new Appointment(1, new Person(1, "Ion", "Popescu", 23), 2024, "May", 28, 15, 15, "moare", "");
        appointment2 = new Appointment(2, new Person(2, "Maria", "Popescu", 23), 2024, "June", 28, 16, 17, "moare", "");
        appointment3 = new Appointment(3, new Person(3, "Vasile", "Popescu", 23), 2024, "July", 28, 14, 19, "moare si el", "");
    }

    @Test
    public void testGetId() {
        assertEquals(1, appointment1.getId());
        assertEquals(2, appointment2.getId());
        assertEquals(3, appointment3.getId());
    }

    @Test
    public void testGetPerson() {
        assertEquals(new Person(1, "Ion", "Popescu", 23), appointment1.getPerson());
        assertEquals(new Person(2, "Maria", "Popescu", 23), appointment2.getPerson());
        assertEquals(new Person(3, "Vasile", "Popescu", 23), appointment3.getPerson());
    }

    @Test
    public void testGetYear() {
        assertEquals(2024, appointment1.getYear());
        assertEquals(2024, appointment2.getYear());
        assertEquals(2024, appointment3.getYear());
    }

    @Test
    public void testGetMonth() {
        assertEquals("May", appointment1.getMonth());
        assertEquals("June", appointment2.getMonth());
        assertEquals("July", appointment3.getMonth());
    }

    @Test
    public void testGetDay() {
        assertEquals(28, appointment1.getDay());
        assertEquals(28, appointment2.getDay());
        assertEquals(28, appointment3.getDay());
    }

    @Test
    public void testGetHour() {
        assertEquals(15, appointment1.getHour());
        assertEquals(16, appointment2.getHour());
        assertEquals(14, appointment3.getHour());
    }

    @Test
    public void testGetMinute() {
        assertEquals(15, appointment1.getMinute());
        assertEquals(17, appointment2.getMinute());
        assertEquals(19, appointment3.getMinute());
    }

    @Test
    public void testGetCondition() {
        assertEquals("moare", appointment1.getCondition());
        assertEquals("moare", appointment2.getCondition());
        assertEquals("moare si el", appointment3.getCondition());
    }

    @Test
    public void testSetId() {
        appointment1.setId(4);
        assertEquals(4, appointment1.getId());
    }

    @Test
    public void testSetPerson() {
        appointment1.setPerson(new Person(4, "Gigel", "Popescu", 23));
        assertEquals(new Person(4, "Gigel", "Popescu", 23), appointment1.getPerson());
    }

    @Test
    public void testSetYear() {
        appointment1.setYear(2025);
        assertEquals(2025, appointment1.getYear());
    }

    @Test
    public void testSetMonth() {
        appointment1.setMonth("June");
        assertEquals("June", appointment1.getMonth());
    }

    @Test
    public void testSetDay() {
        appointment1.setDay(29);
        assertEquals(29, appointment1.getDay());
    }

    @Test
    public void testSetHour() {
        appointment1.setHour(16);
        assertEquals(16, appointment1.getHour());
    }

    @Test
    public void testSetMinute() {
        appointment1.setMinute(16);
        assertEquals(16, appointment1.getMinute());
    }

    @Test
    public void testSetCondition() {
        appointment1.setCondition("moare si el");
        assertEquals("moare si el", appointment1.getCondition());
    }

    @Test
    public void testSetNotes() {
        appointment1.setNotes("moare si el");
        assertEquals("moare si el", appointment1.getNotes());
    }

}
