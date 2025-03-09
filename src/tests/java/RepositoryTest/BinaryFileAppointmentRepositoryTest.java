package RepositoryTest;
import Repository.BinaryFileAppointmentRepository;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;


public class BinaryFileAppointmentRepositoryTest {
    private final BinaryFileAppointmentRepository repository = new BinaryFileAppointmentRepository("src/tests/java/TestData/Binary/appointments.bin");

    @Test
    public void testLoadData() {
        repository.loadData();
        assertEquals(8, repository.getAll().spliterator().getExactSizeIfKnown());
    }

    @Test
    public void testSaveData() {
        repository.loadData();
        repository.saveData();
        repository.loadData();
        assertEquals(8, repository.getAll().spliterator().getExactSizeIfKnown());
    }

    @Test
    public void testAdd() {
        repository.loadData();
        assertEquals(8, repository.getAll().spliterator().getExactSizeIfKnown());
        repository.add(999, repository.getAll().iterator().next());
        assertEquals(8, repository.getAll().spliterator().getExactSizeIfKnown());
    }

    @Test
    public void testRemove() {
        repository.loadData();
        assertEquals(8, repository.getAll().spliterator().getExactSizeIfKnown());
        repository.remove(1);
        assertEquals(7, repository.getAll().spliterator().getExactSizeIfKnown());
    }

    @Test
    public void testUpdate() {
        repository.loadData();
        assertEquals(7, repository.getAll().spliterator().getExactSizeIfKnown());
        repository.update(1, repository.getAll().iterator().next());
        assertEquals(8, repository.getAll().spliterator().getExactSizeIfKnown());
    }

}
