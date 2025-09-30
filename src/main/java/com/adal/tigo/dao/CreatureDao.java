package com.adal.tigo.dao;

import com.adal.tigo.model.Creature;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


import java.util.List;

@Repository
public interface CreatureDao extends JpaRepository<Creature, Long> {

    Page<Creature> findByNameContainingIgnoreCase(String name, Pageable pageable);
    Page<Creature> findByActive(Boolean active, Pageable pageable);
    Page<Creature> findByNameContainingIgnoreCaseAndActive(String name, Boolean active, Pageable pageable);


}