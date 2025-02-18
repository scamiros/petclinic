package org.lucius.petclinic.services.map;

import org.lucius.petclinic.model.Owner;
import org.lucius.petclinic.services.OwnerService;
import org.springframework.stereotype.Service;

import java.util.Set;

@Service
public class OwnerServiceMap extends AbstractMapService<Owner, Long> implements OwnerService {

    @Override
    public Set<Owner> findAll() {
        return super.findAll();
    }

    @Override
    public long count() {
        return super.count();
    }

    @Override
    public boolean existsById(Long id) {
        return super.existsById(id);
    }

    @Override
    public void delete(Owner object) {
        super.delete(object);
    }

    @Override
    public void deleteById(Long id) {
        super.deleteById(id);
    }

    @Override
    public Owner findByLastName(String lastname) {
        return null;
    }

    @Override
    public Owner findByEmail(String email) {
        return null;
    }

    @Override
    public Owner findById(Long id) {
        return super.findById(id);
    }

    @Override
    public Owner save(Owner object) {
        return super.save(object.getId(), object);
    }

}
