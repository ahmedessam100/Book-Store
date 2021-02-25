package com.example.store.service;

import com.example.store.model.Books;
import com.example.store.repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.text.DateFormat;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;
import java.text.SimpleDateFormat;

@Service
public class BookService {

    @Autowired
    private BookRepository bookRepository;

    public List<Books> getAll()
    {
        List<Books> books = new ArrayList<>();

        bookRepository
                .findAll()
                .forEach(books::add);

        return books;
    }

    public List<Books> getBooksByAuthor(String authorName)
    {
        List<Books> books = new ArrayList<>();

        books.addAll(bookRepository.findBooksByAuthorName(authorName));

        return books;
    }

    public List<Books> getBooksByTitle(String title)
    {
        List<Books> books = new ArrayList<>();

        books.addAll(bookRepository.findBooksByTitle(title));

        return books;
    }

    public List<Books> getBooksByDateInterval(String startDate, String endDate) throws ParseException {

        List<Books> books = new ArrayList<>();

        DateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");

        java.util.Date startParsed = formatter.parse(startDate);
        java.sql.Date firstDate = new java.sql.Date(startParsed.getTime());

        java.util.Date endParsed = formatter.parse(endDate);
        java.sql.Date secondDate = new java.sql.Date(endParsed.getTime());

        books.addAll(bookRepository.findBooksByReleaseDateBetween(firstDate, secondDate));

        return books;
    }

    public void addBook(Books book)
    {
        bookRepository.save(book);
    }

    public void updateBook(long bookId, Books book)
    {

        Books currBookState = bookRepository.findById(bookId).orElse(null);

        if (currBookState == null)
        {
            /* For only specific fields changed if user don't provide all the data */
            if (book.getTitle() == null || book.getTitle().equals("")) { book.setTitle(currBookState.getTitle()); }

            if (book.getAuthorName() == null || book.getAuthorName().equals("")) { book.setAuthorName(currBookState.getAuthorName()); }

            if (book.getReleaseDate() == null) { book.setReleaseDate(currBookState.getReleaseDate()); }

            if (book.getDescription() == null || book.getDescription().equals("")) { book.setDescription(currBookState.getDescription()); }

        }

        book.setId(bookId);

        bookRepository.save(book);
    }

    public void deleteBook(long bookId)
    {
        bookRepository.deleteById(bookId);
    }
}
