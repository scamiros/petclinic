package org.lucius.petclinic.services.springdatajpa;

import org.lucius.petclinic.model.Speciality;
import org.lucius.petclinic.repositories.SpecialityRepository;
import org.lucius.petclinic.services.SpecialityService;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Service
@Profile("springdatajpa")
public class SpecialityJpaService implements SpecialityService {

    private final SpecialityRepository specialityRepository;

    public SpecialityJpaService(SpecialityRepository specialityRepository) {
        this.specialityRepository = specialityRepository;
    }

    @Override
    public Set<Speciality> findAll() {
        return StreamSupport.stream(specialityRepository.findAll().spliterator(), false).collect(Collectors.toSet());
    }

    @Override
    public Speciality findById(Long aLong) {
        return specialityRepository.findById(aLong).orElse(null);
    }

    @Override
    public Speciality save(Speciality object) {
        return specialityRepository.save(object);
    }

    @Override
    public void delete(Speciality object) {
        specialityRepository.delete(object);
    }

    @Override
    public void deleteById(Long aLong) {
        specialityRepository.deleteById(aLong);
    }

    @Override
    public boolean existsById(Long aLong) {
        return specialityRepository.findById(aLong).isPresent();
    }

    @Override
    public long count() {
        return specialityRepository.count();
    }
}
