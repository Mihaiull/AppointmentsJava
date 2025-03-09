//this is used to check the connections between the service and the repository
//the functions only return the data that is given to them
package ServiceTest;
import java.io.Serializable;

import Domain.Identifiable;
import Repository.Repository;

public class FakeRepository <ID extends Serializable, T extends Identifiable<ID>> implements Repository<ID, T> {

    @Override
    public void add(ID id, T element) {
        System.out.println("Adding element with id " + id + " and element " + element);
    }

    @Override
    public void remove(ID id) {
        System.out.println("Removing element with id " + id);
    }

    @Override
    public void update(ID id, T element) {
        System.out.println("Updating element with id " + id + " and element " + element);
    }

    @Override
    public T findById(ID id) {
        System.out.println("Finding element with id " + id);
        return null;
    }

    @Override
    public Iterable<T> getAll() {
        System.out.println("Getting all elements");
        return null;
    }
}
