package com.magnaalianza.controllers;


import com.magnaalianza.models.Coffee;
import com.magnaalianza.models.Farmland;
import com.magnaalianza.models.Producer;
import com.magnaalianza.repositories.FarmlandRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import com.magnaalianza.repositories.ProducerRepository;

import java.util.ArrayList;
import java.util.List;


@Controller
public class ProducerController {
	
	@Autowired
    ProducerRepository repo;

    @Autowired
    FarmlandRepository repoFarmland;

    /**
     * Get all the producers from the database
     * @param model
     * @return
     */
	@RequestMapping(value = "/", method = RequestMethod.GET)
    public String getAllProducers(Model model) {
        model.addAttribute("producer", repo.findAll());
        return "index";
    }
	
	/**
     * Get producer information from the form.
     *
     * @param model
     * @return
     */
	@RequestMapping(value = "/producer")
    public String saveProducer(Model model) {
		model.addAttribute("producer", new Producer());
        return "producer";
    }

	
	/**
     * Save producer to database.
     *
     * @param producer
     * @return
     */
    @RequestMapping(value = "producer", method = RequestMethod.POST)
    public String insertProducer(@ModelAttribute("producer") Producer producer) {
        repo.save(producer);       
        return "redirect:/viewproducer/" + producer.getId();
    }
    
	
	/**
     * View a specific producer by its id (the id is grab from the URI).
     *
     * @param id this is the id of the producer and its located in the URI
     * @param model
     * @return
     */
    @RequestMapping("viewproducer/{id}")
    public String showProducer(@PathVariable Long id, Model model) {
        List<Farmland> myFarmlands;
        List<Coffee> myCoffeTypes = new ArrayList<>();
        Producer myProducer = repo.findById(id).get();
    	// I'll get the entity with get()
        model.addAttribute("myProducer", myProducer);

        // Get the producer fincas
        myFarmlands = getFarmlandsByProducer(id);
        model.addAttribute("myFarmlands", myFarmlands);

        // Get the finca coffee types
        for (Farmland farmland : myFarmlands) {
            model.addAttribute("coffeByFarmland", farmland.getCoffeTypes());
        }

        return "viewproducer";
    }

    /**
     *  This edits a producer
     *
     * @param id this is the id of the producer and its located in the URI
     * @param model
     * @return
     */
    @RequestMapping("producer/edit/{id}")
    public String edit(@PathVariable Long id, Model model) {
        model.addAttribute("producer", repo.findById(id).get());
        return "producer";
    }

    /**
     * Return an ArrayList of fincas by its owner(Producer)
     * @param idProducer
     * @return
     */
    private List<Farmland> getFarmlandsByProducer(Long idProducer){
        List<Farmland> myFarmlands = new ArrayList<>();
        List<Farmland> allFarmlands;

        allFarmlands = repoFarmland.findAll();

        for (Farmland farmland : allFarmlands) {
            if(farmland.getProducer().getId() == idProducer){
                myFarmlands.add(farmland);
            }
        }

        return myFarmlands;
    }

    private List<Coffee> getCoffeeByFinca(Long idFarmland){
        List<Coffee> myCoffee = new ArrayList<>();
        List<Farmland> allFarmlands;

        allFarmlands = repoFarmland.findAll();

        for (Farmland farmland : allFarmlands) {
            for (Coffee cafe: farmland.getCoffeTypes()) {
                //if (cafe.getId() == )
            }
        }

        return myCoffee;
    }

    @RequestMapping(value = "/searchproducer")
    public String getProducerName(Model model) {
        model.addAttribute("searchProducer", new Producer());
        return "searchproducer";
    }

    @RequestMapping(value = "/searchproducer", method = RequestMethod.POST)
    public String showFoundProducer(Model model, @ModelAttribute("searchProducer") Producer producer) {

        List<Producer> allProducers =  repo.findByFirstnameLike(producer.getFirstname());

        for (Producer p: allProducers) {
            if (producer.getFirstname().equals(p.getFirstname())) {
                producer.setId(p.getId());
            }
        }
        model.addAttribute("allProducers", allProducers);
        if(producer.getId() != null){
            return "redirect:/viewproducer/" + producer.getId();
        }else{
            return "searchproducer";
        }

    }


}
























