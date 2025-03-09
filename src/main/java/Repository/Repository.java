package Repository;

import Domain.Identifiable;

public interface Repository<ID,T extends Identifiable<ID>> {
    void add(ID id, T element);
    void remove(ID id);
    void update(ID id, T element);
    T findById(ID id);
    Iterable<T> getAll(); //returns an Iterable<T> instead of T because we want to be able to iterate over the elements
}