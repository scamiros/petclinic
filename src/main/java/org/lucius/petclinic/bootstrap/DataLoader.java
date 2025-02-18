package org.lucius.petclinic.bootstrap;

import org.lucius.petclinic.model.Owner;
import org.lucius.petclinic.model.Vet;
import org.lucius.petclinic.services.OwnerService;
import org.lucius.petclinic.services.PetService;
import org.lucius.petclinic.services.VetService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class DataLoader implements CommandLineRunner {

    private final OwnerService ownerService;
    private final PetService petService;
    private final VetService vetService;

    public DataLoader(OwnerService ownerService, PetService petService, VetService vetService) {
        this.ownerService = ownerService;
        this.petService = petService;
        this.vetService = vetService;
    }

    @Override
    public void run(String... args) throws Exception {

        Owner owner1 = new Owner();
        owner1.setId(1L);
        owner1.setFirstName("Giovanni");
        owner1.setLastName("Rossi");
        owner1.setEmail("giovanni.rossi@example.com");
        owner1.setAddress("Via Roma 123, Milano");
        owner1.setTelephone("123-456-7890");

        Owner owner2 = new Owner();
        owner2.setId(2L);
        owner2.setFirstName("Maria");
        owner2.setLastName("Bianchi");
        owner2.setEmail("maria.bianchi@example.com");
        owner2.setAddress("Corso Vittorio 456, Roma");
        owner2.setTelephone("234-567-8901");

        Owner owner3 = new Owner();
        owner3.setId(3L);
        owner3.setFirstName("Luca");
        owner3.setLastName("Verdi");
        owner3.setEmail("luca.verdi@example.com");
        owner3.setAddress("Piazza Garibaldi 789, Napoli");
        owner3.setTelephone("345-678-9012");

        Owner owner4 = new Owner();
        owner4.setId(4L);
        owner4.setFirstName("Elena");
        owner4.setLastName("Ferrari");
        owner4.setEmail("elena.ferrari@example.com");
        owner4.setAddress("Via Dante 101, Torino");
        owner4.setTelephone("456-789-0123");

        Owner owner5 = new Owner();
        owner5.setId(5L);
        owner5.setFirstName("Davide");
        owner5.setLastName("Moretti");
        owner5.setEmail("davide.moretti@example.com");
        owner5.setAddress("Viale Mazzini 202, Firenze");
        owner5.setTelephone("567-890-1234");

        ownerService.save(owner1);
        ownerService.save(owner2);
        ownerService.save(owner3);
        ownerService.save(owner4);
        ownerService.save(owner5);

        Vet vet1 = new Vet();
        vet1.setId(1L);
        vet1.setFirstName("Marco");
        vet1.setLastName("Esposito");
        vet1.setEmail("marco.esposito@example.com");
        vet1.setAddress("Via Veneto 12, Roma");
        vet1.setTelephone("678-123-4567");

        Vet vet2 = new Vet();
        vet2.setId(2L);
        vet2.setFirstName("Sara");
        vet2.setLastName("Conti");
        vet2.setEmail("sara.conti@example.com");
        vet2.setAddress("Piazza Duomo 45, Milano");
        vet2.setTelephone("789-234-5678");

        Vet vet3 = new Vet();
        vet3.setId(3L);
        vet3.setFirstName("Antonio");
        vet3.setLastName("Ricci");
        vet3.setEmail("antonio.ricci@example.com");
        vet3.setAddress("Corso Italia 78, Napoli");
        vet3.setTelephone("890-345-6789");

        vetService.save(vet1);
        vetService.save(vet2);
        vetService.save(vet3);
    }
}
