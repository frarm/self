package com.example.self.converters;

import java.util.HashSet;
import java.util.Set;

import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import com.example.self.commands.DeveloperForm;
import com.example.self.domain.Developer;
import com.example.self.domain.Skill;

@Component
public class DeveloperFormToDeveloper implements Converter<DeveloperForm, Developer> {

    @Override
    public Developer convert(DeveloperForm developerForm) {
        Developer developer = new Developer();
        if (developerForm.getId() != null) {
            developer.setId(developerForm.getId());
        }
        developer.setFirstName(developerForm.getFirstName());
        developer.setLastName(developerForm.getLastName());
        developer.setEmail(developerForm.getEmail());

        Set<Skill> skills = new HashSet<>();
        skills.add(developerForm.getSkill());
        developer.setSkills(skills);
        return developer;
    }
}
