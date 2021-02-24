package com.example.store.repository;


import com.example.store.model.Books;
import org.springframework.data.repository.CrudRepository;
import java.util.Date;
import java.util.List;

public interface BookRepository extends CrudRepository<Books, Long> {

    public List<Books> findBooksByTitle(String title);

    public List<Books> findBooksByAuthorName(String authorName);

    public List<Books> findBooksByReleaseDateBetween(Date startDate, Date endDate);
}
