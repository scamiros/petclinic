package org.lucius.petclinic.bootstrap;

import org.lucius.petclinic.model.*;
import org.lucius.petclinic.services.*;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.time.LocalDate;

@Component
public class DataLoader implements CommandLineRunner {

    private final OwnerService ownerService;
    private final PetService petService;
    private final VetService vetService;
    private final PetTypeService petTypeService;
    private final SpecialityService specialitiesService;
    private final VisitService visitService;

    public DataLoader(OwnerService ownerService, PetService petService, VetService vetService, PetTypeService petTypeService, SpecialityService specialitiesService, VisitService visitService) {
        this.ownerService = ownerService;
        this.petService = petService;
        this.vetService = vetService;
        this.petTypeService = petTypeService;
        this.specialitiesService = specialitiesService;
        this.visitService = visitService;
    }

    @Override
    public void run(String... args) throws Exception {

        int count = petService.findAll().size();

        if (count == 0)
            loadData();
    }

    private void loadData() {

        PetType dog = new PetType();
        dog.setName("Dog");
        PetType savedDog = petTypeService.save(dog);
        PetType cat = new PetType();
        cat.setName("Cat");
        PetType savedCat = petTypeService.save(cat);

        System.out.println("Saved PetType");

        Speciality rad = new Speciality();
        rad.setDescription("radiology");
        Speciality surg = new Speciality();
        surg.setDescription("surgery");
        Speciality dent = new Speciality();
        dent.setDescription("dentistry");

        Speciality sRad = specialitiesService.save(rad);
        Speciality ssurg = specialitiesService.save(surg);
        Speciality sdent = specialitiesService.save(dent);

        System.out.println("Saved Speciality");

        Owner owner1 = new Owner();
        owner1.setFirstName("Giovanni");
        owner1.setLastName("Rossi");
        owner1.setEmail("giovanni.rossi@example.com");
        owner1.setAddress("Via Roma 123, Milano");
        owner1.setTelephone("123-456-7890");

        Pet fuffy = new Pet();
        fuffy.setPetType(savedDog);
        fuffy.setOwner(owner1);
        fuffy.setName("Fuffy");
        fuffy.setBirthDate(LocalDate.now());
        owner1.getPets().add(fuffy);

        Owner owner2 = new Owner();
        owner2.setFirstName("Maria");
        owner2.setLastName("Bianchi");
        owner2.setEmail("maria.bianchi@example.com");
        owner2.setAddress("Corso Vittorio 456, Roma");
        owner2.setTelephone("234-567-8901");

        Pet fiona = new Pet();
        fiona.setPetType(savedDog);
        fiona.setOwner(owner2);
        fiona.setName("Fiona");
        fiona.setBirthDate(LocalDate.now());
        owner2.getPets().add(fiona);

        Owner owner3 = new Owner();
        owner3.setFirstName("Luca");
        owner3.setLastName("Verdi");
        owner3.setEmail("luca.verdi@example.com");
        owner3.setAddress("Piazza Garibaldi 789, Napoli");
        owner3.setTelephone("345-678-9012");

        Pet theo = new Pet();
        theo.setPetType(savedDog);
        theo.setOwner(owner3);
        theo.setName("Theo");
        theo.setBirthDate(LocalDate.now());
        owner3.getPets().add(theo);

        Owner owner4 = new Owner();
        owner4.setFirstName("Elena");
        owner4.setLastName("Ferrari");
        owner4.setEmail("elena.ferrari@example.com");
        owner4.setAddress("Via Dante 101, Torino");
        owner4.setTelephone("456-789-0123");

        Pet chloe = new Pet();
        chloe.setPetType(savedDog);
        chloe.setOwner(owner4);
        chloe.setName("Chloe");
        chloe.setBirthDate(LocalDate.now());
        owner4.getPets().add(chloe);

        Pet livio = new Pet();
        livio.setPetType(savedCat);
        livio.setOwner(owner4);
        livio.setName("Livio");
        livio.setBirthDate(LocalDate.now());
        owner4.getPets().add(livio);

        Owner owner5 = new Owner();
        owner5.setFirstName("Davide");
        owner5.setLastName("Moretti");
        owner5.setEmail("davide.moretti@example.com");
        owner5.setAddress("Viale Mazzini 202, Firenze");
        owner5.setTelephone("567-890-1234");

        Pet sarah = new Pet();
        sarah.setPetType(savedCat);
        sarah.setOwner(owner5);
        sarah.setName("Sarah");
        sarah.setBirthDate(LocalDate.now());
        owner5.getPets().add(sarah);

        ownerService.save(owner1);
        ownerService.save(owner2);
        ownerService.save(owner3);
        ownerService.save(owner4);
        ownerService.save(owner5);

        Visit v = new Visit();
        v.setDate(LocalDate.now());
        v.setPet(fiona);
        v.setDescription("General Visit");

        visitService.save(v);

        System.out.println("Saved Owners");

        Vet vet1 = new Vet();
        vet1.setFirstName("Marco");
        vet1.setLastName("Esposito");
        vet1.setEmail("marco.esposito@example.com");
        vet1.setAddress("Via Veneto 12, Roma");
        vet1.setTelephone("678-123-4567");
        vet1.getSpecialities().add(sRad);

        Vet vet2 = new Vet();
        vet2.setFirstName("Sara");
        vet2.setLastName("Conti");
        vet2.setEmail("sara.conti@example.com");
        vet2.setAddress("Piazza Duomo 45, Milano");
        vet2.setTelephone("789-234-5678");
        vet2.getSpecialities().add(ssurg);

        Vet vet3 = new Vet();
        vet3.setFirstName("Antonio");
        vet3.setLastName("Ricci");
        vet3.setEmail("antonio.ricci@example.com");
        vet3.setAddress("Corso Italia 78, Napoli");
        vet3.setTelephone("890-345-6789");
        vet3.getSpecialities().add(sdent);

        vetService.save(vet1);
        vetService.save(vet2);
        vetService.save(vet3);

        System.out.println("Saved Vets");
    }
}
