package SSFassessment.library.controllers;

import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import SSFassessment.library.service.BookService;
import SSFassessment.library.viewmodel.SearchResultViewModel;

@Controller
public class SearchController {

    private final BookService bookService;

    @Autowired
    public SearchController(BookService bookService) {
        this.bookService = bookService;
    }

    @GetMapping(value = "/search/{title}", produces = { "text/html" })
    public String search(@PathVariable String title, Model model) throws IOException {
        model.addAttribute("searchTitle", title);
        List<SearchResultViewModel> searchResultViewModels = this.bookService.search(title);
        model.addAttribute("searchResultList", searchResultViewModels);
        return "search";
    }

}