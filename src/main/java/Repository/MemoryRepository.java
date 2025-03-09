package Repository;

import Domain.Identifiable;
import java.io.Serializable;
import java.util.HashMap;

public class MemoryRepository <ID extends Serializable, T extends Identifiable<ID>> implements Repository<ID, T>{

    private final HashMap<ID, T> HashMap = new HashMap<>();

    @Override
    public void add(ID id, T element) {
        HashMap.put(id, element);
    }

    @Override
    public void remove(ID id) {
        HashMap.remove(id);
    }

    @Override
    public void update(ID id, T element) {
        HashMap.put(id, element);
    }

    @Override
    public T findById(ID id) {
        if (HashMap.get(id) == null)
            return null;
        return HashMap.get(id);
    }

    @Override
    public Iterable<T> getAll() {
        return HashMap.values();
    }

    public HashMap<ID, T> getHashMap() {
        return HashMap;
    }

}
