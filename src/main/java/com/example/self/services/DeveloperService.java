package com.example.self.services;

import java.util.List;

import com.example.self.commands.DeveloperForm;
import com.example.self.domain.Developer;

public interface DeveloperService {
    List<Developer> listAll();

    Developer getById(Long id);

    Developer saveOrUpdate(Developer developer);

    Developer saveOrUpdateDeveloperForm(DeveloperForm developerForm);
}
