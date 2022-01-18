package SSFassessment.library.viewmodel;

import SSFassessment.library.model.Book;

public class BookViewModel {
    private Book book;
    private boolean isCache;

    public BookViewModel(Book book, boolean isCache) {
        this.book = book;
        this.isCache = isCache;
    }

    public Book getBook() {
        return book;
    }

    public void setBook(Book book) {
        this.book = book;
    }

    public boolean isCache() {
        return isCache;
    }

    public void setCache(boolean cache) {
        isCache = cache;
    }
    
}
