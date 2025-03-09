package Repository;

import Domain.Identifiable;
import java.io.Serializable;

public abstract class FileRepository<ID extends Serializable, T extends Identifiable<ID>> extends MemoryRepository<ID, T> {
    protected String fileName;

    public FileRepository(String fileName) {
        this.fileName = fileName;
        this.loadData();
    }

    @Override
    public void add(ID id, T elem) {
        super.add(id, elem);
        this.saveData();
    }

    public abstract void loadData();
    public abstract void saveData();
}