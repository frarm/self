package com.example.self.services;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.self.commands.DeveloperForm;
import com.example.self.converters.DeveloperFormToDeveloper;
import com.example.self.domain.Developer;
import com.example.self.repositories.DeveloperRepository;

@Service
public class DeveloperServiceImpl implements DeveloperService {

    private DeveloperRepository developerRepository;
    private DeveloperFormToDeveloper developerFormToDeveloper;

    @Autowired
    public DeveloperServiceImpl(DeveloperRepository developerRepository,
            DeveloperFormToDeveloper developerFormToDeveloper) {
        this.developerRepository = developerRepository;
        this.developerFormToDeveloper = developerFormToDeveloper;
    }

    @Override
    public List<Developer> listAll() {
        List<Developer> developers = new ArrayList<>();
        developerRepository.findAll().forEach(developers::add); // fun with Java 8
        return developers;
    }

    @Override
    public Developer getById(Long id) {
        return developerRepository.findById(id).orElse(null);
    }

    @Override
    public Developer saveOrUpdate(Developer developer) {
        developerRepository.save(developer);
        return developer;
    }

    @Override
    public Developer saveOrUpdateDeveloperForm(DeveloperForm developerForm) {
        Developer savedDeveloper = saveOrUpdate(developerFormToDeveloper.convert(developerForm));

        System.out.println("Saved Developer Id: " + savedDeveloper.getId());
        return savedDeveloper;
    }
}