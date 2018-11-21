package com.magnaalianza.controllers;

import com.magnaalianza.models.Coffee;
import com.magnaalianza.models.Farmland;
import com.magnaalianza.models.Producer;
import com.magnaalianza.repositories.CoffeeRepository;
import com.magnaalianza.repositories.FarmlandRepository;


import com.magnaalianza.repositories.ProducerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;


import java.util.List;


@Controller
public class FincaController {
    @Autowired
    FarmlandRepository repo;

    @Autowired
    CoffeeRepository repoCoffee;

    @Autowired
    ProducerRepository repoProd;

    /**
     * Get finca information from the form.
     *
     * @param model
     * @return
     */
    @RequestMapping("/farmland/{id}")
    public String saveFinca(Model model, @PathVariable int id) {
        Farmland myFarmland = new Farmland();

        // Set the producer id that comes from the URI
        myFarmland.setIdProducer(id);
        model.addAttribute("farmland", myFarmland);

        // Get the coffee types
        model.addAttribute("coffee", repoCoffee.findAll());

        return "farmland";
    }


    /**
     * Save finca to database.
     *
     * @param listCoffee get the selected checkboxes
     * @param myFarmland get the name from the form
     * @return
     */
    @RequestMapping(value = "farmland", method = RequestMethod.POST)
    public String insertFinca(@ModelAttribute("farmland") Farmland myFarmland, @RequestParam("id") List<String> listCoffee) {
        Long nextFincaId = getLastIdFromTFarmland();
        Coffee myCafe;
        Producer myProducer;

        //Get the producer by Id and stored to the producer object
        myProducer = repoProd.findById((long) myFarmland.getIdProducer()).get();
        myFarmland.setProducer(myProducer);

        // Set the last id inserted in tfarmland
        myFarmland.setId(nextFincaId);

        // Get my cafe type
        // Add all the selected coffee types into the list of coffee type in Farmland
        for (String cofID : listCoffee) {
            myCafe = repoCoffee.findById(Long.valueOf(cofID)).get();
            myFarmland.getCoffeTypes().add(myCafe);
        }

        // Insert finca to table tfarmland and insert into tfarmland_x_tcoffee table
        repo.save(myFarmland);

        return "redirect:/viewproducer/"+ myFarmland.getIdProducer();
    }

    /**
     * Get the last id inserted in the databse tfarmland
     * @return last id
     */
    private Long getLastIdFromTFarmland(){
        Long nextFincaId;

        // Get the last record inserted
        Farmland f = repo.findTopByOrderByIdDesc();
        if (f == null){
            nextFincaId = (long) 1;
        }else{
            nextFincaId = f.getId() + 1;
        }

        return nextFincaId;
    }

}
