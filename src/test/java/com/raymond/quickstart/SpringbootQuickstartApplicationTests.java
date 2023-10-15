package com.raymond.quickstart;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.raymond.quickstart.dao.BookDao;
import com.raymond.quickstart.domain.Book;
import org.junit.jupiter.api.Test;
import org.junit.platform.commons.util.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.math.BigDecimal;
import java.util.List;

/**
 * SpringBoot test
 *
 * @author raymondmuuzi
 * @since 2023-10-10 19:00:03
 */
@SpringBootTest(classes = SpringbootQuickstartApplication.class)
class SpringbootQuickstartApplicationTests {

    @Autowired
    private BookDao bookDao;

    @Test
    void contextLoads() {
        System.out.println(bookDao.getById(1));
    }

    @Test
    void testGetPage() {
        IPage<Book> bookPage = new Page<>(1, 3);
        IPage<Book> page = bookDao.selectPage(bookPage, null);
        for (Book book : page.getRecords()) {
            System.out.println(book);
        }
    }

    @Test
    void testGetByCondition() {
        QueryWrapper<Book> bookQueryWrapper = new QueryWrapper<>();
        bookQueryWrapper.like("name", "in");
        bookQueryWrapper.between("price", new BigDecimal("10"), new BigDecimal("28"));
        List<Book> bookList = bookDao.selectList(bookQueryWrapper);
        for (Book book : bookList) {
            System.out.println(book);
        }
    }

    /**
     * lambda query wrapper
     */
    @Test
    void testGetByLambdaCondition() {
        String name = "Spring";
        LambdaQueryWrapper<Book> queryWrapper = new LambdaQueryWrapper<>();
        // The first param is to judge the condition is append or not
        queryWrapper.like(StringUtils.isNotBlank(name), Book::getName, name);
        queryWrapper.between(Book::getPrice, new BigDecimal("0"), new BigDecimal("25"));
        List<Book> bookList = bookDao.selectList(queryWrapper);
        for (Book book : bookList) {
            System.out.println(book);
        }
    }

    @Test
    void testSave() {
        Book book = new Book();
        book.setName("Mybatis Tutorial");
        book.setPrice(new BigDecimal("34.5"));
        bookDao.insert(book);
    }
}
