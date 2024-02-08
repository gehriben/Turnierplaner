package ch.zhaw.psit4.turnier.persistence.service;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Service;

@Service
public abstract class BaseService<T, ID> {

    public abstract CrudRepository<T, ID> getRepository();

    public T createObject(T object) {
        return getRepository().save(object);
    }

    public Iterable<T> getAllObjects() {
        return getRepository().findAll();
    }

    public T getObjectById(ID id) throws Exception {
        return getRepository().findById(id).orElseThrow(Exception::new);
    }

    public T updateObject(T object) {
        return getRepository().save(object);
    }

    public void deleteById(ID id) {
        getRepository().deleteById(id);
    }
}
