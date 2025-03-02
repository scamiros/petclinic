package org.lucius.petclinic.services.springdatajpa;

import org.lucius.petclinic.model.Vet;
import org.lucius.petclinic.repositories.VetRepository;
import org.lucius.petclinic.services.VetService;

import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

public class VetJpaService implements VetService {

    private final VetRepository vetRepository;

    public VetJpaService(VetRepository vetRepository) {
        this.vetRepository = vetRepository;
    }

    @Override
    public Vet findByLastName(String lastname) {
        return vetRepository.findByLastName(lastname);
    }

    @Override
    public Vet findByEmail(String email) {
        return vetRepository.findByEmail(email);
    }

    @Override
    public Vet findById(Long id) {
        return vetRepository.findById(id).orElse(null);
    }

    @Override
    public Vet save(Vet v) {
        return vetRepository.save(v);
    }

    @Override
    public void delete(Vet o) {
        vetRepository.delete(o);
    }

    @Override
    public void deleteById(Long id) {
        vetRepository.deleteById(id);
    }

    @Override
    public Set<Vet> findAll() {
        return StreamSupport.stream(vetRepository.findAll().spliterator(), false).collect(Collectors.toSet());
    }
}
