package com.example.store.controller;

import com.example.store.contoller.RequestController;
import com.example.store.model.Books;
import com.example.store.service.BookService;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.junit.Assert;
import static org.mockito.BDDMockito.given;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import static org.hamcrest.core.Is.is;
import static java.util.Collections.singletonList;
import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.hamcrest.collection.IsCollectionWithSize.hasSize;


@RunWith(SpringRunner.class)
@WebMvcTest(controllers = RequestController.class)
class RequestControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private RequestController requestController;

    @MockBean
    private BookService bookService;

    private List<Books> books = new ArrayList<Books>(){{
       add(new Books("Cracking the coding interview", "Gayle Laakmann McDowell", new Date(), ""));
       add(new Books("Designing data intensive applications", "Martin Kleppmann", new Date(), ""));
       add(new Books("Clean Architecture", "Robert Cecil Martin", new Date(), ""));
    }};

    @Test
    public void init()
    {
        assertThat(requestController).isNotNull();

        assertThat(bookService).isNotNull();
    }

    @Test
    public void getAllBooks() throws Exception {

        given(requestController.getBooks()).willReturn(books);

        /* Building the request */
        RequestBuilder requestBuilder = MockMvcRequestBuilders.get("/getBooks")
                .accept(MediaType.APPLICATION_JSON);


        MvcResult mvcResult = mockMvc.perform(requestBuilder)
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(3)))
                .andExpect(jsonPath("$[0].title", is(books.get(0).getTitle())))
                .andExpect(jsonPath("$[0].authorName", is(books.get(0).getAuthorName())))
                .andReturn();

        /* Checking for successful response */
        Assert.assertEquals(200, mvcResult.getResponse().getStatus());

        /* Checking for non-empty body */
        Assert.assertTrue(mvcResult.getResponse().getContentAsString().length() > 0);
    }

    @Test
    void getBooksByTitle() throws Exception {

        List<Books> booksList = singletonList(books.get(0));

        given(requestController.getBooksByTitle(books.get(0).getTitle())).willReturn(booksList);

        /* Building the request */
        RequestBuilder requestBuilder = MockMvcRequestBuilders.get("/getBooksByTitle/Cracking the coding interview")
                .accept(MediaType.APPLICATION_JSON);

        MvcResult mvcResult = mockMvc.perform(requestBuilder)
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(1)))
                .andExpect(jsonPath("$[0].title", is(books.get(0).getTitle())))
                .andExpect(jsonPath("$[0].authorName", is(books.get(0).getAuthorName())))
                .andReturn();

        /* Checking for successful response */
        Assert.assertEquals(200, mvcResult.getResponse().getStatus());

        /* Checking for non-empty body */
        Assert.assertTrue(mvcResult.getResponse().getContentAsString().length() > 0);
    }

    @Test
    void getBooksByAuthor() throws Exception {

        List<Books> booksList = singletonList(books.get(0));

        given(requestController.getBooksByAuthorName(books.get(0).getAuthorName())).willReturn(booksList);

        /* Building the request */
        RequestBuilder requestBuilder = MockMvcRequestBuilders.get("/getBooksByAuthor/Gayle Laakmann McDowell")
                .accept(MediaType.APPLICATION_JSON);

        MvcResult mvcResult = mockMvc.perform(requestBuilder)
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(1)))
                .andExpect(jsonPath("$[0].title", is(books.get(0).getTitle())))
                .andExpect(jsonPath("$[0].authorName", is(books.get(0).getAuthorName())))
                .andReturn();

        /* Checking for successful response */
        Assert.assertEquals(200, mvcResult.getResponse().getStatus());

        /* Checking for non-empty body */
        Assert.assertTrue(mvcResult.getResponse().getContentAsString().length() > 0);
    }
}

