package com.adal.tigo.controller;


import com.adal.tigo.Exception.CreatureException;
import com.adal.tigo.model.Creature;
import com.adal.tigo.model.Datatable;
import com.adal.tigo.model.Filters;
import com.adal.tigo.service.CreatureService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class CreatureApi {

    private static final Logger logger = LoggerFactory.getLogger(CreatureApi.class);

    private final CreatureService creatureService;


    public CreatureApi(CreatureService creatureService) {
        this.creatureService = creatureService;
    }


    @GetMapping("/all")
    public List<Creature> getAllCreatures() {
        logger.info("Getting all creatures");
        return creatureService.getCreatures();
    }



    @PostMapping("/creatures")
    public Creature saveCreature(@RequestBody Creature creature) throws CreatureException {
        logger.info("Saving new creature: {}", creature.getName());
        return creatureService.newCreature(creature);
    }

    @GetMapping("/creatures/{id}")
    public Creature getCreatureById(@PathVariable Long id) throws CreatureException {
        logger.info("Getting creature by ID: {}", id);
        return creatureService.getCreatureById(id);
    }

    @GetMapping("/creatures")
    public Datatable<Creature> getCreaturesByParams(
            Filters filters
    ) throws CreatureException {
        logger.info("Getting creatures by params: {}", filters);
        return creatureService.getCreaturesByParams(filters);
    }

}
