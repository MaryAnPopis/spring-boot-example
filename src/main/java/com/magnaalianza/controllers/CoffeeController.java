package com.magnaalianza.controllers;


import com.magnaalianza.models.Coffee;
import com.magnaalianza.repositories.CoffeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class CoffeeController {

    @Autowired
    CoffeeRepository repo;

    /**
     * Get coffee type information from the form.
     *
     * @param model
     * @return
     */
    @RequestMapping(value = "/coffee")
    public String saveCoffee(Model model) {
        model.addAttribute("coffee", new Coffee());
        return "coffee";
    }


    /**
     * Save coffee type to database.
     *
     * @param coffee
     * @return
     */
    @RequestMapping(value = "coffee", method = RequestMethod.POST)
    public String insertCoffee(@ModelAttribute("coffee") Coffee coffee) {
        repo.save(coffee);
        return "redirect:/";
    }

}
