package RepositoryTest;
import Repository.PersonMemoryRepository;
import Domain.Person;
import org.junit.jupiter.api.*;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class PersonMemoryRepositoryTest {
    private PersonMemoryRepository personrepo;

    @BeforeEach
    public void setUp(){
        personrepo = new PersonMemoryRepository();
    }

    @Test
    public void testFindById(){
        Person person = new Person(1, "John", "Doe", 24);
        personrepo.add(1, person);
        assertEquals(personrepo.findById(1), person);
    }

    @Test
    public void testAdd(){
        Person person = new Person(1, "John", "Doe", 24);
        personrepo.add(1, person);
        assertEquals(personrepo.findById(1), person);
    }

    @Test
    public void testRemove(){
        Person person = new Person(1, "John", "Doe", 24);
        personrepo.add(1, person);
        personrepo.remove(1);
        assertEquals(personrepo.findById(1), null);
    }

    @Test
    public void testUpdate(){
        Person person = new Person(1, "John", "Doe", 24);
        personrepo.add(1, person);
        Person newperson = new Person(1, "Jane", "Doe", 24);
        personrepo.update(1, newperson);
        assertEquals(personrepo.findById(1), newperson);
    }

    @Test
    public void testGetAll(){
        Person person = new Person(1, "John", "Doe", 24);
        personrepo.add(1, person);
        Person person2 = new Person(2, "Jane", "Doe", 24);
        personrepo.add(2, person2);
        assertEquals(personrepo.getAll().spliterator().getExactSizeIfKnown(), 2); //spliterator is a Java class that helps in traversing the elements of a source
    }                                                                                    //i do be googling stuff :) (and immidiately forgetting it)
 

    @Test
    public void testGetHashMap(){
        Person person = new Person(1, "John", "Doe", 24);
        personrepo.add(1, person);
        assertEquals(personrepo.getHashMap().get(1), person);
    }
    
    @AfterEach
    public void tearDown(){
        personrepo = null;
    }
}
