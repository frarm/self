package com.example.self;

import javax.management.relation.Role;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.annotation.Rollback;

import com.example.self.domain.Developer;
import com.example.self.domain.Skill;
import com.example.self.repositories.DeveloperRepository;

/**
 * DeveloperRepositoryTests
 */
@DataJpaTest
@AutoConfigureTestDatabase(replace = Replace.NONE)
@Rollback(false)
public class DeveloperRepositoryTests {

    @Autowired
    private DeveloperRepository repo;

    @Autowired
    private TestEntityManager entityManager;

    @Test
    public void testCreateSkills() {
        Skill skill1 = new Skill("java", "java");
        Skill skill2 = new Skill("c#", "c#");
        Skill skill3 = new Skill("javascript", "javascript");

        entityManager.persist(skill1);
        entityManager.persist(skill2);
        entityManager.persist(skill3);
    }

    @Test
    public void testCreateNewDeveloperWithOneSkill() {
        Skill skill1 = entityManager.find(Skill.class, 1L);

        Developer developer1 = new Developer("Franco", "Romero", "Franco@gmail.com");

        developer1.addSkill(skill1);

        repo.save(developer1);
    }

    @Test
    public void testCreateNewDeveloperWithTwoSkill() {
        Skill skill2 = entityManager.find(Skill.class, 2L);
        Skill skill3 = entityManager.find(Skill.class, 3L);

        Developer developer2 = new Developer("Luis", "Poma", "Luis@gmail.com");

        developer2.addSkill(skill2);
        developer2.addSkill(skill3);

        repo.save(developer2);
    }

    @Test
    public void testAssignSkillToExistingDeveloper() {
        Developer developer = repo.findById(1L).get();
        Skill skill = entityManager.find(Skill.class, 2L);
        developer.addSkill(skill);
    }

    @Test
    public void testRemoveSkillFromExistingDeveloper() {
        Developer developer = repo.findById(1L).get();
        Skill skill = new Skill(2L);
        developer.removeSkill(skill);
    }

    @Test
    public void testCreateNewDeveloperWithNewSkill() {
        Skill skill = new Skill("python", "python");
        Developer developer = new Developer("Luis", "Castillo", "Luis@outlook.com");
        developer.addSkill(skill);

        repo.save(developer);
    }

    @Test
    public void testGetDeveloper() {
        Developer developer = repo.findById(1L).get();

        entityManager.detach(developer);

        System.out.println(developer.getFirstName());
        System.out.println(developer.getLastName());
        System.out.println(developer.getEmail());
        System.out.println(developer.getSkills());
    }

    @Test
    public void testRemoveDeveloper() {
        repo.deleteById(7L);
    }
}