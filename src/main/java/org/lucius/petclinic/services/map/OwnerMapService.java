package org.lucius.petclinic.services.map;

import org.lucius.petclinic.model.Owner;
import org.lucius.petclinic.model.Pet;
import org.lucius.petclinic.services.OwnerService;
import org.lucius.petclinic.services.PetService;
import org.lucius.petclinic.services.PetTypeService;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

import java.util.Set;

@Service
@Profile({"default", "map"})
public class OwnerMapService extends AbstractMapService<Owner, Long> implements OwnerService {

    private final PetTypeService petTypeService;
    private final PetService petService;

    public OwnerMapService(PetTypeService petTypeService, PetService petService) {
        this.petTypeService = petTypeService;
        this.petService = petService;
    }

    @Override
    public Set<Owner> findAll() {
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
    public void delete(Owner object) {
        super.delete(object);
    }

    @Override
    public void deleteById(Long id) {
        super.deleteById(id);
    }

    @Override
    public Owner findByLastName(String lastname) {
        return this.findAll()
                .stream()
                .filter(o -> o.getLastName().equalsIgnoreCase(lastname))
                .findFirst()
                .orElse(null);
    }

    @Override
    public Owner findByEmail(String email) {

        return this.findAll().stream().filter(o -> o.getEmail().equalsIgnoreCase(email)).findFirst().orElse(null);
    }

    @Override
    public Owner findById(Long id) {
        return super.findById(id);
    }

    @Override
    public Owner save(Owner object) {

        if (object.getPets() != null) {
            object.getPets().forEach(p -> {
                if (p.getPetType() != null) {
                    if (p.getPetType().getId() == null) {
                        p.setPetType(petTypeService.save(p.getPetType()));
                    }
                } else {
                    throw new RuntimeException("Pet Type is required");
                }

                if (p.getId() == null) {
                    Pet savedPet = petService.save(p);
                    p.setId(savedPet.getId());
                }
            });
        }
        return super.save(object);
    }

}
