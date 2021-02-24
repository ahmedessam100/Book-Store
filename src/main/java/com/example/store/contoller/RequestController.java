package com.example.store.contoller;

import com.example.store.model.Books;
import com.example.store.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.text.ParseException;
import java.util.List;

@RestController
public class RequestController {

    @Autowired
    private BookService bookService;

    @GetMapping("/getBooks")
    public List<Books> getBooks()
    {
        return bookService.getAll();
    }

    @GetMapping("/getBooksByAuthor/{authorName}")
    public List<Books> getBooksByAuthorName(@PathVariable String authorName) { return bookService.getBooksByAuthor(authorName); }

    @GetMapping("/getBooksByTitle/{title}")
    public List<Books> getBooksByTitle(@PathVariable String title)
    {
        return bookService.getBooksByTitle(title);
    }

    @GetMapping("/getBooksByDateInterval/{startDate}/{endDate}")
    public List<Books> getBooksByAuthorName(@PathVariable String startDate, @PathVariable String endDate) throws ParseException
    {
        return bookService.getBooksByDateInterval(startDate, endDate);
    }

    @PostMapping("/addBook")
    public void insertBook(@RequestBody Books book)
    {
        try
        {
            bookService.addBook(book);
        }
        catch (Exception e)
        {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Missing required fields!", e);
        }
    }

    @PutMapping("/editBook/{bookId}")
    public void editBook(@RequestBody Books book, @PathVariable long bookId)
    {
        bookService.updateBook(bookId, book);
    }

    @DeleteMapping("/removeBook/{bookId}")
    public void deleteBook(@PathVariable long bookId)
    {
        bookService.deleteBook(bookId);
    }
}