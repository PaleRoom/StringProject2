package ru.nicolas.controllers;

import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import ru.nicolas.dao.BookDAO;
import ru.nicolas.dao.PersonDAO;
import ru.nicolas.models.Book;
import ru.nicolas.models.Person;
import ru.nicolas.services.BookService;
import ru.nicolas.services.PeopleService;
import ru.nicolas.util.BookValidator;
import ru.nicolas.util.PersonValidator;

import java.util.Date;
import java.util.Optional;


@Controller
@RequestMapping("/book")
public class BookController {

    private final BookService bookService;
   // private final PersonDAO personDAO;
   private final PeopleService peopleService;


    private final BookValidator bookValidator;
    @Autowired
    public BookController(BookService bookService, PeopleService peopleService, BookValidator bookValidator) {
        this.bookService = bookService;
        this.bookValidator = bookValidator;
             this.peopleService = peopleService;
    }

    @GetMapping()
    public String index(Model model) {
        model.addAttribute("book", bookService.findAll());
        model.addAttribute("person", peopleService.findAll());
        return "book/index";
    }

    @GetMapping("/{id}")
    public String show(@PathVariable("id") int id, Model model, @ModelAttribute("emptyPerson") Person person) {
        model.addAttribute("book", bookService.findOne(id));
        model.addAttribute("people", peopleService.findAll());

        System.out.println(bookService.isExpired(bookService.findOne(id)));





       // model.addAttribute("customer",peopleService.findOne(2));

        //Person customer = bookService.findOne(id).getOwner();
       //if (customer!=null)
           model.addAttribute("customer",bookService.findOne(id).getOwner());
       //System.out.println(peopleService.findOne(bookService.findOne(id).getOwner().getId()));

        return "book/show";
    }


    @GetMapping("/new")
    public String newBook(@ModelAttribute("book") Book book) {
        // model.addAttribute("person", new Person());
        return "book/new";
    }


    @PostMapping()
    public String create(@ModelAttribute("book") @Valid Book book, BindingResult bindingResult) {
//        bookValidator.validate(book, bindingResult);
//        if (bindingResult.hasErrors())
           // return "book/new";
        bookService.save(book);
        return "redirect:/book";
    }

    @GetMapping("/{id}/edit")
    public String edit(Model model, @PathVariable("id") int id) {
        model.addAttribute("book",bookService.findOne(id));
        return "book/edit";
    }
    @PatchMapping("/{id}")
    public String update(@ModelAttribute("book") @Valid Book book, BindingResult bindingResult, @PathVariable("id") int id) {
//        bookValidator.validate(book, bindingResult);
//        if(bindingResult.hasErrors())
//            return "book/edit";
        bookService.update(id, book);
        return "redirect:/book";
    }

    @DeleteMapping("/{id}")
    public String delete(@PathVariable("id") int id) {
        bookService.delete(id);
        return "redirect:/book";
    }

    @PatchMapping("/{id}/makeOrder")
    public String makeOrder(@ModelAttribute("book")  @PathVariable("id") int id, Person person) {
       bookService.makeOrder(id, person);

        return "redirect:/book/{id}";
    }

    @PatchMapping("/{id}/closeOrder")
    public String closeOrder(@ModelAttribute("book")  @PathVariable("id") int id) {
        bookService.closeOrder(bookService.findOne(id));

        return "redirect:/book/{id}";
    }

}