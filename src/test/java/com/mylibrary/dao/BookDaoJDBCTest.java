package com.mylibrary.dao;

import com.mylibrary.model.Book;
import com.mylibrary.enums.BookStatus;
import org.junit.jupiter.api.*;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;

public class BookDaoJDBCTest {

    private BookDaoJDBC bookDao;

    @BeforeEach
    public void setUp() {
        bookDao = new BookDaoJDBC();
    }

    @Test
    public void testInsert() {

        Book book = new Book("New Book", 2023, new ArrayList<>(), new ArrayList<>(), BookStatus.AVAILABLE);

        Book insertedBook = bookDao.insert(book);

        System.out.println("Inserted test book: " + insertedBook);

        assertNotNull(insertedBook);
        assertNotNull(insertedBook.getId());
        assertEquals("New Book", insertedBook.getTitle());
        assertEquals(2023, insertedBook.getYear());
        assertEquals(BookStatus.AVAILABLE, insertedBook.getStatus());
    }

    @Test
    public void testFindById() {

        Book book = new Book("Test Book", 2023, new ArrayList<>(), new ArrayList<>(), BookStatus.AVAILABLE);


        Book insertedBook = bookDao.insert(book);


        Book foundBook = bookDao.findById(insertedBook.getId());


        assertNotNull(foundBook);

        System.out.println("Found test book: " + foundBook);


        assertEquals(insertedBook.getId(), foundBook.getId());
        assertEquals("Test Book", foundBook.getTitle());
        assertEquals(2023, foundBook.getYear());
        assertEquals(BookStatus.AVAILABLE, foundBook.getStatus());
    }
}