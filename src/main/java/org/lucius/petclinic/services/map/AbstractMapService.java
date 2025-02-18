package org.lucius.petclinic.services.map;

import org.lucius.petclinic.model.BaseEntity;

import java.util.*;

public abstract class AbstractMapService<T extends BaseEntity, ID extends Long> {

    protected Map<Long, T> map = new HashMap<>();

    Set<T> findAll() {
        return new HashSet<>(map.values());
    }

    T findById(ID id) {
        return map.get(id);
    }

    T save(T object) {

        if (object != null) {
            if (object.getId() == null) {
                object.setId(getNextId());
            }

            map.put(object.getId(), object);
        } else {
            throw new RuntimeException("Object can't be null");
        }

        return object;
    }

    void deleteById(ID id) {
        map.remove(id);
    }

    void delete(T object) {
        map.entrySet().removeIf(entry -> entry.getValue().equals(object));
    }

    boolean existsById(ID id) {
        return map.containsKey(id);
    }

    long count() {
        return map.size();
    }

    private Long getNextId() {

        if (map.size() == 0)
            return 1L;
        else
            return Collections.max(map.keySet()) + 1;
    }
}
