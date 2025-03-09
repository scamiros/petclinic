package org.lucius.petclinic.services.map;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.lucius.petclinic.model.Owner;

import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

class OwnerMapServiceTest {

    OwnerMapService ownerMapService;
    Long ownerId = 1L;
    String lastname = "Smith";
    String email = "s.smith@mail.com";

    @BeforeEach
    void setUp() {
        ownerMapService = new OwnerMapService(new PetTypeMapService(), new PetMapService());
        ownerMapService.save(Owner.builder().id(ownerId).lastName(lastname).email(email).build());
    }

    @Test
    void findAll() {
        Set<Owner> all = ownerMapService.findAll();
        assertEquals(all.size(), 1);

    }

    @Test
    void count() {
        long count = ownerMapService.count();
        assertEquals(count, 1);
    }

    @Test
    void existsById() {
        boolean b = ownerMapService.existsById(ownerId);
        assertEquals(b, true);
    }

    @Test
    void delete() {
        ownerMapService.delete(ownerMapService.findById(ownerId));
        assertEquals(0, ownerMapService.findAll().size());
    }

    @Test
    void deleteById() {

        ownerMapService.deleteById(ownerId);
        assertEquals(0, ownerMapService.findAll().size());
    }

    @Test
    void findByLastName() {
        Owner byLastName = ownerMapService.findByLastName(lastname);
        assertNotNull(byLastName);
        assertEquals(ownerId, byLastName.getId());
    }

    @Test
    void findByEmail() {
        Owner byEmail = ownerMapService.findByEmail(email);
        assertNotNull(byEmail);
        assertEquals(ownerId, byEmail.getId());
    }

    @Test
    void findById() {
        Owner byId = ownerMapService.findById(ownerId);
        assertNotNull(byId);
        assertEquals(byId.getId(), ownerId);
    }

    @Test
    void saveExistingId() {
        Owner save = ownerMapService.save(Owner.builder().id(2L).build());
        assertEquals(2L, save.getId());

    }

    @Test
    void saveNoId() {

        Owner save = ownerMapService.save(Owner.builder().build());
        assertNotNull(save);
        assertNotNull(save.getId());

    }
}