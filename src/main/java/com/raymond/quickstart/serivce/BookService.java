package com.raymond.quickstart.serivce;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.raymond.quickstart.domain.Book;

import java.math.BigDecimal;
import java.util.List;

/**
 * book service
 *
 * @author raymondmuzzi
 * @since 2023-10-12 20:52:54
 */
public interface BookService {
    /**
     * save a new book
     *
     * @param book {@link Book}
     * @return added or not
     */
    boolean save(Book book);

    /**
     * update a book by id
     *
     * @param book {@link Book}
     * @return update or not
     */
    boolean update(Book book);

    /**
     * delete a book by id
     *
     * @param id book id
     * @return delete or not
     */
    boolean delete(Integer id);

    /**
     * get book by book id
     *
     * @param id book id
     * @return {@link Book}
     */
    Book getById(Integer id);

    /**
     * get all books
     *
     * @return {@link Book} book list
     */
    List<Book> getAll();

    /**
     * get book list by page
     *
     * @param pageNo   page no
     * @param pageSize page size
     * @param name     book name
     * @param price    book price
     * @return book page list
     */
    IPage<Book> getByPage(int pageNo, int pageSize, String name, BigDecimal price);
}
