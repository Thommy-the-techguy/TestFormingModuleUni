package com.tomomoto.testformingmodule.controllers;

import com.tomomoto.testformingmodule.dto.TestSearchForm;
import com.tomomoto.testformingmodule.entities.Disciple;
import com.tomomoto.testformingmodule.repos.DiscipleRepository;
import com.tomomoto.testformingmodule.repos.TestRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Slf4j
@Controller
@RequestMapping("/tests")
@SessionAttributes("disciple")
public class TestsController {
    private final DiscipleRepository discipleRepo;
    private final TestRepository testRepo;

    public TestsController(DiscipleRepository discipleRepo, TestRepository testRepo) {
        this.discipleRepo = discipleRepo;
        this.testRepo = testRepo;
    }

//    @ModelAttribute(name = "disciple")
//    public Disciple disciple() {
//        return new Disciple();
//    }
    @ModelAttribute
    public TestSearchForm testSearchForm() {
        return new TestSearchForm();
    }

    @GetMapping("/{subject}")
    public String getTestsPage(@PathVariable String subject, Model model) {
        log.info("Parameter value: {}", subject);
        Disciple disciple = discipleRepo.findDiscipleByName(subject).orElse(null);
        model.addAttribute("disciple", disciple);
        log.info("Disciple: {}", disciple);
        return "tests";
    }

    @PostMapping("/{subject}")
    public String processTestSearch(TestSearchForm testSearchForm, @ModelAttribute Disciple disciple, @PathVariable String subject) {
        disciple.getTests().clear();
        disciple.getTests().addAll(testRepo.findTestByNameContainingIgnoreCaseAndDisciple(testSearchForm.getTestName(), disciple));
        return "tests";
    }
}
