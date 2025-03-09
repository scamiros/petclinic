package org.lucius.petclinic.services.springdatajpa;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.lucius.petclinic.model.Owner;
import org.lucius.petclinic.repositories.OwnerRepository;
import org.lucius.petclinic.repositories.PetRepository;
import org.lucius.petclinic.repositories.PetTypeRepository;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class OwnerJpaServiceTest {

    Long ownerId = 1L;
    String lastname = "Smith";
    String email = "s.smith@mail.com";

    @Mock
    OwnerRepository ownerRepository;

    @Mock
    PetTypeRepository petTypeRepository;

    @Mock
    PetRepository petRepository;

    // Mockito effettua l'injection e non serve inzializzarlo nel setUp
    @InjectMocks
    OwnerJpaService ownerJpaService;

    Owner owner;

    @BeforeEach
    void setUp() {
        owner = Owner.builder().lastName(lastname).id(ownerId).email(email).build();
    }

    @Test
    void findByLastName() {

        when(ownerRepository.findByLastName(lastname)).thenReturn(owner);

        Owner byLastName = ownerJpaService.findByLastName(lastname);

        assertNotNull(byLastName);
        assertEquals(ownerId, byLastName.getId());

        // verifica che sia stato chiamato almeno una volta
        verify(ownerRepository).findByLastName(any());
    }

    @Test
    void findByEmail() {

        when(ownerRepository.findByEmail(email)).thenReturn(owner);

        Owner findOwner = ownerJpaService.findByEmail(email);

        assertNotNull(findOwner);
        assertEquals(ownerId, findOwner.getId());
        verify(ownerRepository).findByEmail(any());
    }

    @Test
    void findById() {

        when(ownerRepository.findById(anyLong())).thenReturn(Optional.of(owner));

        Owner e = ownerJpaService.findById(ownerId);

        assertNotNull(e);
        assertEquals(ownerId, e.getId());
        verify(ownerRepository).findById(any());
    }

    @Test
    void findByIdNotFound() {

        when(ownerRepository.findById(anyLong())).thenReturn(Optional.empty());

        Owner e = ownerJpaService.findById(ownerId);

        assertNull(e);
    }

    @Test
    void save() {

        when(ownerRepository.save(any())).thenReturn(owner);

        Owner e = ownerJpaService.save(owner);
        assertNotNull(e);
        assertEquals(e.getId(), ownerId);
        verify(ownerRepository).save(any());
    }

    @Test
    void delete() {

        ownerJpaService.delete(owner);

        verify(ownerRepository, times(1)).delete(any());
    }

    @Test
    void deleteById() {

        ownerJpaService.deleteById(ownerId);

        verify(ownerRepository, times(1)).deleteById(anyLong());
    }

    @Test
    void existsById() {

        when(ownerRepository.existsById(anyLong())).thenReturn(anyBoolean());
        ownerJpaService.existsById(ownerId);

        verify(ownerRepository).existsById(anyLong());
    }

    @Test
    void count() {

        long count = ownerJpaService.count();

        verify(ownerRepository).count();
    }

    @Test
    void findAll() {

        Set<Owner> setR = new HashSet<>();
        setR.add(Owner.builder().id(1L).email(email).build());
        setR.add(Owner.builder().id(2L).email(email).build());

        when(ownerRepository.findAll()).thenReturn(setR);

        Set<Owner> set = ownerJpaService.findAll();

        assertNotNull(set);
        assertEquals(set.size(), 2);
    }
}