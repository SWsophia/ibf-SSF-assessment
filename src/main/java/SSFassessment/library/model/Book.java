package SSFassessment.library.model;

public class Book {

    private String title;
    private String description;
    private String excerpt;


    public Book(String title, String description, String excerpt) {
        this.title = title;
        this.description = description;
        this.excerpt = excerpt;
    }

    
    public String getTitle() {
        return title;
    }

    
    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    
    public void setDescription(String description) {
        this.description= description;
    }

    public String getExcerpt() {
        return excerpt;
    }

    public void setExcerpt(String excerpt) {
        this.excerpt = excerpt;
    }

    
}
