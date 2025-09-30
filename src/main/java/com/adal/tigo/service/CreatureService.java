package com.adal.tigo.service;

import com.adal.tigo.Exception.CreatureException;
import com.adal.tigo.dao.CreatureDao;
import com.adal.tigo.model.Creature;
import com.adal.tigo.model.Datatable;

import java.util.List;

public interface CreatureService {


    public List<Creature> getCreatures();
    Creature getCreatureById(Long id) throws CreatureException;
    Datatable<Creature> getCreaturesByParams(String name, Boolean active,  String orderBy, Integer offset);
    public Creature newCreature(Creature creature) throws CreatureException;
}
