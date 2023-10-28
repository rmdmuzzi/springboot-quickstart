package com.raymond.quickstart.serivce.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.raymond.quickstart.dao.BookDao;
import com.raymond.quickstart.domain.Book;
import com.raymond.quickstart.serivce.BookService;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;

/**
 * Book service
 *
 * @author raymondmuzzi
 * @since 2023-10-12 21:48:41
 */
@Service
public class BookServiceImpl implements BookService {

    private final BookDao bookDao;

    public BookServiceImpl(BookDao bookDao) {
        this.bookDao = bookDao;
    }

    @Override
    public boolean save(Book book) {
        if (book == null) {
            throw new RuntimeException("Book cannot be null!");
        }
        return bookDao.insert(book) > 0;
    }

    @Override
    public boolean update(Book book) {
        if (book.getId() == null) {
            throw new RuntimeException("Book id cannot be null!");
        }
        LambdaQueryWrapper<Book> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(Book::getId, book.getId());
        return bookDao.update(book, wrapper) > 0;
    }

    @Override
    public boolean delete(Integer id) {
        if (id == null) {
            throw new RuntimeException("Delete failed, id cannot be null!");
        }
        return bookDao.deleteById(id) > 0;
    }

    @Override
    public Book getById(Integer id) {
        if (id == null) {
            throw new RuntimeException("Get book failed, id cannot be null!");
        }
        return bookDao.selectById(id);
    }

    @Override
    public List<Book> getAll() {
        return bookDao.selectList(null);
    }

    @Override
    public IPage<Book> getByPage(int pageNo, int pageSize,
                                 String name, BigDecimal price) {
        IPage<Book> bookPage = new Page<>(pageNo, pageSize);
        LambdaQueryWrapper<Book> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.like(StringUtils.isNotBlank(name), Book::getName, name);
        queryWrapper.eq(price != null, Book::getPrice, price);
        bookDao.selectPage(bookPage, queryWrapper);
        return bookPage;
    }
}
