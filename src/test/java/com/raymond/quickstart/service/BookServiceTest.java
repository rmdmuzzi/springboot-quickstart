package com.raymond.quickstart.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.raymond.quickstart.domain.Book;
import com.raymond.quickstart.serivce.BookService;
import com.raymond.quickstart.util.JsonUtil;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.math.BigDecimal;
import java.util.List;

/**
 * @author raymondmuzzi
 * @since 2023-10-12 22:46:45
 */
@SpringBootTest
class BookServiceTest {

    private static final Logger LOGGER = LoggerFactory.getLogger(BookServiceTest.class);

    @Autowired
    private BookService bookService;

    @Test
    void testSave() {
        Book book = new Book();
        book.setName("Apache Doris");
        book.setPrice(new BigDecimal("50"));
        bookService.save(book);
    }

    @Test
    void testDeleteById() {
        bookService.delete(9);
    }

    @Test
    void testUpdate() {
        Book book = new Book();
        book.setId(5);
        book.setPrice(new BigDecimal("3"));
        bookService.update(book);
    }

    @Test
    void testGetById() {
        Book book = bookService.getById(3);
        LOGGER.info("test get by id -> {}", JsonUtil.getJsonString(book));
    }

    @Test
    void testGetAll() {
        List<Book> bookList = bookService.getAll();
        for (Book book : bookList) {
            LOGGER.info("test get all -> {}", JsonUtil.getJsonString(book));
        }
    }

    @Test
    void testGetByPage() {
        Book book = new Book();
        IPage<Book> bookList = bookService.getByPage(2, 3, book.getName(), book.getPrice());
        for (Book b : bookList.getRecords()) {
            LOGGER.info("test get by page -> {}", JsonUtil.getJsonString(b));
        }
    }
}
