package com.example.demo.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.models.Volo;

@Repository
public interface VoloRepository extends CrudRepository<Volo, Integer>{

}
