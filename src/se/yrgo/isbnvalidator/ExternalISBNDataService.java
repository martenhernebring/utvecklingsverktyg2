package se.yrgo.isbnvalidator;

public interface ExternalISBNDataService {
    public Book lookup(String isbn);
}
