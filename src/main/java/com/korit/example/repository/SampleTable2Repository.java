package com.korit.example.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.korit.example.entity.SampleTable2Entity;

@Repository
public interface SampleTable2Repository
extends JpaRepository<SampleTable2Entity, Integer>{
    
}
