package org.lucius.petclinic.controllers;

import jakarta.validation.Valid;
import org.lucius.petclinic.model.Pet;
import org.lucius.petclinic.model.Visit;
import org.lucius.petclinic.services.OwnerService;
import org.lucius.petclinic.services.PetService;
import org.lucius.petclinic.services.VisitService;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.beans.PropertyEditorSupport;
import java.time.LocalDate;
import java.util.Map;

@Controller
class VisitController {

	private final OwnerService ownerService;
	private final VisitService visitService;
	private final PetService petService;

    public VisitController(OwnerService ownerService, VisitService visitService, PetService petService) {
        this.ownerService = ownerService;
        this.visitService = visitService;
        this.petService = petService;
    }

	@InitBinder
	public void dataBinder(WebDataBinder dataBinder) {
		dataBinder.setDisallowedFields("id");

		dataBinder.registerCustomEditor(LocalDate.class, new PropertyEditorSupport() {
			@Override
			public void setAsText(String text) throws IllegalArgumentException{
				setValue(LocalDate.parse(text));
			}
		});
	}

	@ModelAttribute("visit")
	public Visit loadPetWithVisit(@PathVariable("petId") Long petId, Map<String, Object> model) {

		Pet pet = petService.findById(petId);
		model.put("pet", pet);

		Visit visit = new Visit();
		pet.getVisits().add(visit);
		visit.setPet(pet);
		return visit;
	}

	// Spring MVC calls method loadPetWithVisit(...) before initNewVisitForm is
	// called
	@GetMapping("/owners/*/pets/{petId}/visits/new")
	public String initNewVisitForm() {
		return "pets/createOrUpdateVisitForm";
	}

	// Spring MVC calls method loadPetWithVisit(...) before processNewVisitForm is
	// called
	@PostMapping("/owners/{ownerId}/pets/{petId}/visits/new")
	public String processNewVisitForm(@Valid Visit visit,
			BindingResult result, RedirectAttributes redirectAttributes) {
		if (result.hasErrors()) {
			return "pets/createOrUpdateVisitForm";
		}

		visitService.save(visit);
		return "redirect:/owners/{ownerId}";
	}

}
