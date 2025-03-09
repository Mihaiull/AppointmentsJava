package RepositoryTest;
import Repository.BinaryFilePersonRepository;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;


public class BinaryFilePersonRepositoryTest {
    private final BinaryFilePersonRepository repository = new BinaryFilePersonRepository("src/tests/java/TestData/Binary/persons.bin");

    @Test
    public void testLoadData() {
        repository.loadData();
        assertEquals(6, repository.getAll().spliterator().getExactSizeIfKnown());
    }

    @Test
    public void testSaveData() {
        repository.loadData();
        repository.saveData();
        repository.loadData();
        assertEquals(6, repository.getAll().spliterator().getExactSizeIfKnown());
    }

    @Test
    public void testAdd() {
        repository.loadData();
        assertEquals(6, repository.getAll().spliterator().getExactSizeIfKnown());
        repository.add(999, repository.getAll().iterator().next());
        assertEquals(6, repository.getAll().spliterator().getExactSizeIfKnown());
    }

    @Test
    public void testRemove() {
        repository.loadData();
        assertEquals(6, repository.getAll().spliterator().getExactSizeIfKnown());
        repository.remove(1);
        assertEquals(5, repository.getAll().spliterator().getExactSizeIfKnown());
    }

    @Test
    public void testUpdate() {
        repository.loadData();
        assertEquals(5, repository.getAll().spliterator().getExactSizeIfKnown());
        repository.update(1, repository.getAll().iterator().next());
        assertEquals(6, repository.getAll().spliterator().getExactSizeIfKnown());
    }

    @Test
    public void testGetAll() {
        repository.loadData();
        assertEquals(6, repository.getAll().spliterator().getExactSizeIfKnown());
    }
}
