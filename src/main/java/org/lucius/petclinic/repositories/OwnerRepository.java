package org.lucius.petclinic.repositories;

import org.lucius.petclinic.model.Owner;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface OwnerRepository extends CrudRepository<Owner, Long> {
    Owner findByLastName(String lastName);
    Owner findByEmail(String email);
    List<Owner> findByLastNameIgnoreCaseLike(String lastName);
}
