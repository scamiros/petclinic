package org.lucius.petclinic.services;

import org.lucius.petclinic.model.Pet;

import java.util.Set;

public interface PetService {

    Pet findById(Long id);
    Pet save(Pet p);

    Set<Pet> findAll();
}
