package service;

import java.util.List;

public interface IProductService<E> {
    void add(E e);

    void delete(int id);

    void update(int id, E e);

    List<E> getAll();

    E findById(int id);

}