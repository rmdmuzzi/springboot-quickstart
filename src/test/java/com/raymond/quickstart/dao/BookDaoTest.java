package com.raymond.quickstart.dao;

import com.raymond.quickstart.domain.Book;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.math.BigDecimal;

/**
 * @author raymondmuzzi
 * @since 2023-10-10 23:09:25
 */
@SpringBootTest
class BookDaoTest {

    @Autowired
    private BookDao bookDao;

    @Test
    void testGetById() {
        System.out.println(bookDao.getById(1));
    }

    @Test
    void testGetAll() {
        for (Book book : bookDao.selectList(null)) {
            System.out.println(book);
        }
    }

    @Test
    void testSave() {
        Book book = new Book();
        book.setName("Ignite");
        book.setPrice(new BigDecimal("25.5"));
        bookDao.insert(book);
    }
}
