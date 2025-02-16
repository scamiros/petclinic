package org.lucius.petclinic.services;

import org.lucius.petclinic.model.Vet;

import java.util.Set;

public interface VetService {

    Vet findById(Long id);
    Vet save(Vet v);

    Set<Vet> fidnAll();
}
