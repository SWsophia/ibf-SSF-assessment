package SSFassessment.library.viewmodel;

public class SearchResultViewModel {

    private String bookLink;
    private String title;

    public SearchResultViewModel(String key, String title) {
        this.bookLink = key;
        this.title = title;
    }

    public String getBookLink() {
        return bookLink;
    }

    public void setBookLink(String bookLink) {
        this.bookLink = bookLink;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
    
}
