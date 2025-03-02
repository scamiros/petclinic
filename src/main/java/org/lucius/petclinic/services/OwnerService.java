package org.lucius.petclinic.services;

import org.lucius.petclinic.model.Owner;

public interface OwnerService extends CrudService<Owner, Long> {

    Owner findByLastName(String lastname);
    Owner findByEmail(String email);
}
