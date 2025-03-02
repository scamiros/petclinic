package org.lucius.petclinic.services;

import org.lucius.petclinic.model.Vet;

public interface VetService extends CrudService<Vet, Long> {

    Vet findByLastName(String lastname);
    Vet findByEmail(String email);
}
