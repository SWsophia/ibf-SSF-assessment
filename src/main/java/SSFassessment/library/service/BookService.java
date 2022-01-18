package SSFassessment.library.service;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;


import SSFassessment.library.model.Book;
import SSFassessment.library.repository.BookRepository;
import SSFassessment.library.viewmodel.BookViewModel;
import SSFassessment.library.viewmodel.SearchResultViewModel;
import jakarta.json.Json;
import jakarta.json.JsonArray;
import jakarta.json.JsonObject;
import jakarta.json.JsonReader;


@Service
public class BookService {

    private final RestTemplate restTemplate;
    private final BookRepository bookRepository;

    @Autowired
    public BookService(RestTemplate restTemplate, BookRepository bookRepository) {
        this.restTemplate = restTemplate;
        this.bookRepository = bookRepository;
    }

    public List<SearchResultViewModel> search(String searchTerm) throws IOException {
        String searchQuery = searchTerm.replaceAll(" ", "+");

        String searchResultString = this.restTemplate.getForObject("https://openlibrary.org/search.json?title={searchQuery}&limit=20&fields=title,key",
                String.class,
                Map.of("searchQuery", searchQuery));

        InputStream is = new ByteArrayInputStream(searchResultString.getBytes());
        JsonReader reader = Json.createReader(is);
        JsonObject data = reader.readObject();
        JsonArray docs = data.getJsonArray("docs");

        List<SearchResultViewModel> searchResultViewModels = new ArrayList<>();
        for (int i = 0; i < docs.size(); i++) {
            JsonObject doc = docs.getJsonObject(i);
            String workId = doc.getString("key").split("/")[2];
            searchResultViewModels.add(new SearchResultViewModel("/book/" + workId, doc.getString("title")));
        }

        return searchResultViewModels;
    }

    public BookViewModel getBookByWorkID(String workID) throws IOException {
        Optional<String> cache = this.bookRepository.getBook(workID);

        boolean isCache = cache.isPresent();
        String bookDetailsString;

        if (isCache) {
            bookDetailsString = cache.get();
        } else {
            bookDetailsString = this.restTemplate.getForObject("https://openlibrary.org/works/{workID}.json",
                    String.class,
                    Map.of("workID", workID));
            this.bookRepository.add(workID, bookDetailsString);
        }

        InputStream is = new ByteArrayInputStream(bookDetailsString.getBytes());
        JsonReader reader = Json.createReader(is);
        JsonObject data = reader.readObject();
        String excerpt = getExcerpt(data);
        Book book = new Book(data.getString("title"), data.getString("description", ""), excerpt);
        return new BookViewModel(book, isCache);
    }

    
    private String getExcerpt(JsonObject data) {
        try {
            return data.getJsonArray("excerpts").getJsonObject(0).getJsonObject("excerpt").getString("value");
        } catch (Exception e) {
            return "";
        }
    }

}