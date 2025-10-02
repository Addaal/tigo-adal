package com.adal.tigo.service;

import com.adal.tigo.Exception.CreatureException;
import com.adal.tigo.dao.CreatureDao;
import com.adal.tigo.model.Creature;
import com.adal.tigo.model.Datatable;
import com.adal.tigo.model.Filters;

import java.util.List;

public interface CreatureService {


    public List<Creature> getCreatures();
    Creature getCreatureById(Long id) throws CreatureException;
    Datatable<Creature> getCreaturesByParams(Filters filters) throws CreatureException;
    public Creature newCreature(Creature creature) throws CreatureException;
}
