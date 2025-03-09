package DomainTest;

import org.junit.jupiter.api.BeforeEach;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.*;

import Domain.Person;

public class PersonTest {
    private Person person1;
    private Person person2;
    private Person person3;

    @BeforeEach
    public void setUp() {
        person1 = new Person(1, "mock", "mock", 14);
        person2 = new Person(2, "yeyea", "mock", 15);
        person3 = new Person(3, "notmock", "mock", 12);
    }

    @Test
    public void testGetId() {
        assertEquals(1, person1.getId());
        assertEquals(2, person2.getId());
        assertEquals(3, person3.getId());
    }

    @Test
    public void testGetFirstName() {
        assertEquals("mock", person1.getSurname());
        assertEquals("yeyea", person2.getSurname());
        assertEquals("notmock", person3.getSurname());
    }

    @Test
    public void testGetLastName() {
        assertEquals("mock", person1.getName());
        assertEquals("mock", person2.getName());
        assertEquals("mock", person3.getName());
    }

    @Test
    public void testGetAge() {
        assertEquals(14, person1.getAge());
        assertEquals(15, person2.getAge());
        assertEquals(12, person3.getAge());
    }

    @Test
    public void testSetId() {
        person1.setId(4);
        assertEquals(4, person1.getId());
    }

    @Test
    public void testSetFirstName() {
        person1.setSurname("newmock");
        assertEquals("newmock", person1.getSurname());
    }

    @Test
    public void testSetLastName() {
        person1.setName("newmock");
        assertEquals("newmock", person1.getName());
    }

    @Test
    public void testSetAge() {
        person1.setAge(20);
        assertEquals(20, person1.getAge());
    }
}
