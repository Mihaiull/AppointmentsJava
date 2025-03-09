package RepositoryTest;
import Repository.TextFilePersonRepository;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;


public class TextFilePersonRepositoryTest {
    private final TextFilePersonRepository repository = new TextFilePersonRepository("src/tests/java/TestData/Text/persons.txt");

    @Test
    public void testLoadData() {
        repository.loadData();
        assertEquals(2, repository.getAll().spliterator().getExactSizeIfKnown());
    }

    @Test
    public void testSaveData() {
        repository.loadData();
        repository.saveData();
        repository.loadData();
        assertEquals(2, repository.getAll().spliterator().getExactSizeIfKnown());
    }

    @Test
    public void testAdd() {
        repository.loadData();
        assertEquals(2, repository.getAll().spliterator().getExactSizeIfKnown());
        repository.add(4, repository.getAll().iterator().next());
        assertEquals(3, repository.getAll().spliterator().getExactSizeIfKnown());
    }

    @Test
    public void testRemove() {
        repository.loadData();
        assertEquals(2, repository.getAll().spliterator().getExactSizeIfKnown());
        repository.remove(1);
        assertEquals(2, repository.getAll().spliterator().getExactSizeIfKnown());
    }

    @Test
    public void testUpdate() {
        repository.loadData();
        assertEquals(2, repository.getAll().spliterator().getExactSizeIfKnown());
        repository.update(1, repository.getAll().iterator().next());
        assertEquals(3, repository.getAll().spliterator().getExactSizeIfKnown());
    }

    @Test
    public void testGetAll() {
        repository.loadData();
        assertEquals(2, repository.getAll().spliterator().getExactSizeIfKnown());
    }
}
