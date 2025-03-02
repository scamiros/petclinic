package org.lucius.petclinic.services.springdatajpa;

import org.lucius.petclinic.model.Pet;
import org.lucius.petclinic.repositories.PetRepository;
import org.lucius.petclinic.services.PetService;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Service
@Profile("springdatajpa")
public class PetJpaService implements PetService {

    private final PetRepository petRepository;

    public PetJpaService(PetRepository petRepository) {
        this.petRepository = petRepository;
    }

    @Override
    public Set<Pet> findAll() {
        return StreamSupport.stream(petRepository.findAll().spliterator(), false).collect(Collectors.toSet());
    }

    @Override
    public Pet findById(Long aLong) {
        return petRepository.findById(aLong).orElse(null);
    }

    @Override
    public Pet save(Pet object) {
        return petRepository.save(object);
    }

    @Override
    public void delete(Pet object) {
        petRepository.delete(object);
    }

    @Override
    public void deleteById(Long aLong) {
        petRepository.deleteById(aLong);
    }

    @Override
    public boolean existsById(Long aLong) {
        return petRepository.findById(aLong).isPresent();
    }

    @Override
    public long count() {
        return petRepository.count();
    }
}
