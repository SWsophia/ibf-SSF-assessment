package SSFassessment.library.controllers;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;


import SSFassessment.library.service.BookService;
import SSFassessment.library.viewmodel.BookViewModel;

@Controller
public class BookController {

    private final BookService bookService;

    @Autowired
    public BookController(BookService bookService) {
        this.bookService = bookService;
    }

    @GetMapping("/book/{worksID}")
    public String getBookDetails(@PathVariable String worksID, Model model) throws IOException {
        BookViewModel book = this.bookService.getBookByWorkID(worksID);
        model.addAttribute("title", book.getBook().getTitle());
        model.addAttribute("description", book.getBook().getDescription());
        model.addAttribute("excerpt", book.getBook().getExcerpt());
        model.addAttribute("cache", book.isCache());
        return "book";
    }
}
