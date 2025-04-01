package org.lucius.petclinic.controllers;

import jakarta.validation.Valid;
import org.lucius.petclinic.model.Owner;
import org.lucius.petclinic.model.Pet;
import org.lucius.petclinic.model.PetType;
import org.lucius.petclinic.services.OwnerService;
import org.lucius.petclinic.services.PetService;
import org.lucius.petclinic.services.PetTypeService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.util.StringUtils;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.time.LocalDate;
import java.util.Collection;

@Controller
@RequestMapping("/owners/{ownerId}")
public class PetsController {

    private static final String VIEWS_PETS_CREATE_OR_UPDATE_FORM = "pets/createOrUpdatePetForm";

    private final OwnerService ownerService;
    private final PetService petService;
    private final PetTypeService petTypeService;

    public PetsController(OwnerService ownerService, PetService petService, PetTypeService petTypeService) {
        this.ownerService = ownerService;
        this.petService = petService;
        this.petTypeService = petTypeService;
    }

    @ModelAttribute("types")
    public Collection<PetType> populatePetTypes() {
        return this.petTypeService.findAll();
    }

    @ModelAttribute("owner")
    public Owner findOwner(@PathVariable("ownerId") Long ownerId) {
        return this.ownerService.findById(ownerId);
    }

    @InitBinder("owner")
    public void initOwnerBinder(WebDataBinder dataBinder) {
        dataBinder.setDisallowedFields("id");
    }

    @GetMapping("/pets/new")
    public String initCreationForm(Owner owner, ModelMap model) {
        Pet pet = new Pet();
        owner.getPets().add(pet);
        model.addAttribute("pet", pet);
        return VIEWS_PETS_CREATE_OR_UPDATE_FORM;
    }

    @PostMapping("/pets/new")
    public String processCreationForm(Owner owner, @Valid Pet pet, BindingResult result,
                                      RedirectAttributes redirectAttributes) {

        if (StringUtils.hasText(pet.getName()) && pet.isNew() && owner.getPet(pet.getName(), true) != null)
            result.rejectValue("name", "duplicate", "already exists");

        LocalDate currentDate = LocalDate.now();
        if (pet.getBirthDate() != null && pet.getBirthDate().isAfter(currentDate)) {
            result.rejectValue("birthDate", "typeMismatch.birthDate");
        }

        if (result.hasErrors()) {
            return VIEWS_PETS_CREATE_OR_UPDATE_FORM;
        }

        owner.getPets().add(pet);
        ownerService.save(owner);
        redirectAttributes.addFlashAttribute("message", "New Pet has been Added");
        return "redirect:/owners/" + owner.getId();
    }

    @GetMapping("/pets/{petId}/edit")
    public String initUpdateForm(@PathVariable Long petId, Model model) {

        Pet pet = petService.findById(petId);
        model.addAttribute("pet", pet);

        return VIEWS_PETS_CREATE_OR_UPDATE_FORM;
    }

    @PostMapping("/pets/{petId}/edit")
    public String processUpdateForm(Owner owner, @Valid Pet pet, BindingResult result,
                                    RedirectAttributes redirectAttributes) {

        String petName = pet.getName();

        // checking if the pet name already exist for the owner
        if (StringUtils.hasText(petName)) {
            Pet existingPet = owner.getPet(petName, false);
            if (existingPet != null && !existingPet.getId().equals(pet.getId())) {
                result.rejectValue("name", "duplicate", "already exists");
            }
        }

        LocalDate currentDate = LocalDate.now();
        if (pet.getBirthDate() != null && pet.getBirthDate().isAfter(currentDate)) {
            result.rejectValue("birthDate", "typeMismatch.birthDate");
        }

        if (result.hasErrors()) {
            return VIEWS_PETS_CREATE_OR_UPDATE_FORM;
        }

        owner.getPets().add(pet);
        this.ownerService.save(owner);
        redirectAttributes.addFlashAttribute("message", "Pet details has been edited");
        return "redirect:/owners/" + owner.getId();
    }

}
