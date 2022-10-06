package ru.nicolas.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import ru.nicolas.dao.PersonDAO;
import ru.nicolas.models.Person;

@Controller
@RequestMapping("/startup")
public class StartupController {
    private final PersonDAO personDao;

    public StartupController(PersonDAO personDao) {
        this.personDao = personDao;
    }
//    @Autowired
//    public StartupController(PersonDAO personDao) {
//        this.personDao = personDao;
//    }

@GetMapping()
    public String Startup(Model model
//                          @ModelAttribute("person") Person person
) {
//    model.addAttribute("people", personDao.index());
        return "people/startup";
    }

}
