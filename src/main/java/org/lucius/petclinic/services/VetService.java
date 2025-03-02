package org.lucius.petclinic.services;

import org.lucius.petclinic.model.Vet;

import java.util.Set;

public interface VetService {

    Vet findByLastName(String lastname);
    Vet findByEmail(String email);
    Vet findById(Long id);
    Vet save(Vet v);
    void delete(Vet o);
    void deleteById(Long id);
    Set<Vet> findAll();
}
