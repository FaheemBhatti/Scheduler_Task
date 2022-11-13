package com.codechamps.schedular.repo;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.codechamps.model.Sensor;

@Repository
public interface SensorRepository extends CrudRepository<Sensor, Integer>{} 
