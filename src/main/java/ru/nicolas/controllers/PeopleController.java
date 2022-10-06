package ru.nicolas.controllers;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import ru.nicolas.dao.BookDAO;
import ru.nicolas.dao.PersonDAO;
import ru.nicolas.models.Person;
import ru.nicolas.services.BookService;
import ru.nicolas.services.PeopleService;
import ru.nicolas.util.PersonValidator;


@Controller
@RequestMapping("/people")
public class PeopleController {

    private final PeopleService peopleService;
    private final BookService bookService;


    private final PersonValidator personValidator;

    @Autowired
    public PeopleController(PeopleService peopleService, PersonValidator personValidator
            , BookService bookService
    ) {
        this.peopleService = peopleService;

        this.personValidator = personValidator;
        this.bookService = bookService;
    }

    @GetMapping()
    public String index(Model model) {
        model.addAttribute("people", peopleService.findAll());
        //model.addAttribute("book", bookDAO.index());
        return "people/index";
    }

    @GetMapping("/{id}")
    public String show(@PathVariable("id") int id, Model model) {
        model.addAttribute("person", peopleService.findOne(id));

        model.addAttribute("orderedBooks", bookService.showOrdered(peopleService.findOne(id)));

        return "people/show";
    }


    @GetMapping("/new")
    public String newPerson(@ModelAttribute("person") Person person) {
        //model.addAttribute("person", new Person());
        return "people/new";
    }


//    @PostMapping()
//    public String create(@ModelAttribute("person") @Valid Person person, BindingResult bindingResult) {
//        personValidator.validate(person, bindingResult);
//       // if (bindingResult.hasErrors())
//         //   return "people/new";
//        personDAO.save(person);
//        return "redirect:/people";
//     }

    @PostMapping()
    public String create(@ModelAttribute("person") @Valid Person person,
                         BindingResult bindingResult) {
        if (bindingResult.hasErrors())
            return "people/new";

        peopleService.save(person);
        return "redirect:/people";
    }


    @GetMapping("/{id}/edit")
    public String edit(Model model, @PathVariable("id") int id) {
        model.addAttribute("person", peopleService.findOne(id));
        return "people/edit";
    }

    @PatchMapping("/{id}")
    public String update(@ModelAttribute("person") @Valid Person person, BindingResult bindingResult, @PathVariable("id") int id) {
        // personValidator.validate(person, bindingResult);
        if (bindingResult.hasErrors())
            return "people/edit";
        peopleService.update(id, person);
        return "redirect:/people";
    }

    @DeleteMapping("/{id}")
    public String delete(@PathVariable("id") int id) {
        peopleService.delete(id);
        return "redirect:/people";
    }

}
