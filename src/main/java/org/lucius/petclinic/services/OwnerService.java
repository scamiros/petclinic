package org.lucius.petclinic.services;

import org.lucius.petclinic.model.Owner;

import java.util.Set;

public interface OwnerService {

    Owner findByLastName(String lastname);
    Owner findByEmail(String email);
    Owner findById(Long id);
    Owner save(Owner o);

    Set<Owner> fidnAll();
}
