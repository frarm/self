package com.example.self.repositories;

import org.springframework.data.repository.CrudRepository;

import com.example.self.domain.Developer;

public interface DeveloperRepository extends CrudRepository<Developer, Long> {

}
