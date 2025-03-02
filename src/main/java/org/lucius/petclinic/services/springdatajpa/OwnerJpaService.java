package org.lucius.petclinic.services.springdatajpa;

import org.lucius.petclinic.model.Owner;
import org.lucius.petclinic.repositories.OwnerRepository;
import org.lucius.petclinic.repositories.PetRepository;
import org.lucius.petclinic.repositories.PetTypeRepository;
import org.lucius.petclinic.services.OwnerService;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Service
@Profile("springdatajpa")
public class OwnerJpaService implements OwnerService {

    private final OwnerRepository ownerRepository;
    private final PetTypeRepository petTypeRepository;
    private final PetRepository petRepository;

    public OwnerJpaService(OwnerRepository ownerRepository, PetTypeRepository petTypeRepository, PetRepository petRepository) {
        this.ownerRepository = ownerRepository;
        this.petTypeRepository = petTypeRepository;
        this.petRepository = petRepository;
    }

    @Override
    public Owner findByLastName(String lastname) {
        return ownerRepository.findByLastName(lastname);
    }

    @Override
    public Owner findByEmail(String email) {
        return ownerRepository.findByEmail(email);
    }

    @Override
    public Owner findById(Long id) {
        return ownerRepository.findById(id).orElse(null);
    }

    @Override
    public Owner save(Owner o) {
        return ownerRepository.save(o);
    }

    @Override
    public void delete(Owner o) {
        ownerRepository.delete(o);
    }

    @Override
    public void deleteById(Long id) {
        ownerRepository.deleteById(id);
    }

    @Override
    public Set<Owner> findAll() {
        return StreamSupport.stream(ownerRepository.findAll().spliterator(), false)
                .collect(Collectors.toSet());
    }
}
