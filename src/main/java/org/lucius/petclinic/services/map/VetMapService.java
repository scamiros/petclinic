package org.lucius.petclinic.services.map;

import org.lucius.petclinic.model.Speciality;
import org.lucius.petclinic.model.Vet;
import org.lucius.petclinic.services.SpecialitiesService;
import org.lucius.petclinic.services.VetService;
import org.springframework.stereotype.Service;

import java.util.Set;

@Service
public class VetMapService extends AbstractMapService<Vet, Long> implements VetService {

    private final SpecialitiesService specialitiesService;

    public VetMapService(SpecialitiesService specialitiesService) {
        this.specialitiesService = specialitiesService;
    }

    @Override
    public Set<Vet> findAll() {
        return super.findAll();
    }

    @Override
    public long count() {
        return super.count();
    }

    @Override
    public boolean existsById(Long id) {
        return super.existsById(id);
    }

    @Override
    public void delete(Vet object) {
        super.delete(object);
    }

    @Override
    public void deleteById(Long id) {
        super.deleteById(id);
    }

    @Override
    public Vet findByLastName(String lastname) {
        return null;
    }

    @Override
    public Vet findByEmail(String email) {
        return null;
    }

    @Override
    public Vet findById(Long id) {
        return super.findById(id);
    }

    @Override
    public Vet save(Vet object) {

        if (object.getSpecialities().size() > 0) {
            for (Speciality speciality : object.getSpecialities()) {
                if (speciality.getId() == null) {
                    Speciality saved = specialitiesService.save(speciality);
                    speciality.setId(saved.getId());
                }
            }
        }
        return super.save(object);
    }
}
