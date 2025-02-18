package org.lucius.petclinic.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller("/vets")
public class VetController {

    @RequestMapping({"", "/", "/index", "/index.html"})
    public String listVets() {
        return "vets/index";
    }
}
