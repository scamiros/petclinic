package org.lucius.petclinic.services;

import org.lucius.petclinic.model.Owner;

import java.util.List;

public interface OwnerService extends CrudService<Owner, Long> {

    Owner findByLastName(String lastname);
    Owner findByEmail(String email);
    List<Owner> findAllByLastNameLike(String lastName);
}
