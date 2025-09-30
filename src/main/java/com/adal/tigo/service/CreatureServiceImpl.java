package com.adal.tigo.service;


import com.adal.tigo.Exception.CreatureException;
import com.adal.tigo.Exception.ErrorCodes;
import com.adal.tigo.controller.CreatureApi;
import com.adal.tigo.dao.CreatureDao;
import com.adal.tigo.model.Creature;
import com.adal.tigo.model.Datatable;
import com.adal.tigo.validator.ValidateCreature;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.security.Timestamp;
import java.util.List;

@Service
public class CreatureServiceImpl implements CreatureService{

    private static final Logger logger = LoggerFactory.getLogger(CreatureServiceImpl.class);

    private final CreatureDao creatureDao;


    public CreatureServiceImpl(CreatureDao creatureDao) {
        this.creatureDao = creatureDao;
    }

    @Override
    public List<Creature> getCreatures() {
        return creatureDao.findAll();
    }

    @Override
    public Creature getCreatureById(Long id) throws CreatureException {
        return creatureDao.findById(id).orElseThrow(() ->
                new CreatureException(
                        java.time.Instant.now().toString(),
                        ErrorCodes.REQUEST,
                        "Creature not found",
                        404
                )
        );
    }

    @Override
    public Datatable<Creature> getCreaturesByParams(String name, Boolean active, String orderBy, Integer offset) {
        List<Creature> creatures;
        if(orderBy == null){
            orderBy = "desc";
        }
        if(offset == null || offset == 0){
            offset = 5;
        }

        Sort sort = orderBy.equalsIgnoreCase("asc") ? Sort.by("createdAt").ascending() : Sort.by("createdAt").descending();
        Pageable pageable = PageRequest.of(0, offset, sort);

        Page<Creature> pageResult;
        if (name != null && active != null) {
            pageResult = creatureDao.findByNameContainingIgnoreCaseAndActive(name, active, pageable);
        } else if (name != null) {
            pageResult = creatureDao.findByNameContainingIgnoreCase(name, pageable);
        } else if (active != null) {
            pageResult = creatureDao.findByActive(active, pageable);
        } else {
            pageResult = creatureDao.findAll(pageable);
        }

        creatures = pageResult.getContent();


        return new Datatable<>(
                creatures,
                (int) pageResult.getTotalElements(),
                creatures.size(),
                offset

        );

    }

    @Override
    public Creature newCreature(Creature creature) throws CreatureException {
        logger.info("We are creating/updating a creature {}", creature);

        ValidateCreature.validate(creature);

        if (creature.getId() != null) {
            Creature existing = creatureDao.findById(creature.getId()).orElse(null);
            if (existing != null) {
                creature.setCreatedAt(existing.getCreatedAt());
            }else {
                throw new CreatureException(
                        java.time.Instant.now().toString(),
                        ErrorCodes.REQUEST,
                       "Entry not found. Cannot Update",
                        400
                );
            }
        }

        try {
            return creatureDao.save(creature);
        } catch (Exception e) {
            throw new CreatureException(
                    java.time.Instant.now().toString(),
                    ErrorCodes.COMMUNICATION,
                    e.getMessage(),
                    400
            );
        }
    }

}
