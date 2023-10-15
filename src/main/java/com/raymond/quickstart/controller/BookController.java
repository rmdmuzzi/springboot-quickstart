package com.raymond.quickstart.controller;

import com.raymond.quickstart.domain.Book;
import com.raymond.quickstart.domain.R;
import com.raymond.quickstart.serivce.BookService;
import org.springframework.web.bind.annotation.*;

/**
 * Book controller
 *
 * @author raymondmuzzi
 * @since 2023-10-09 20:35:59
 */
@RestController
@RequestMapping("/books")
public class BookController {

    private final BookService bookService;

    public BookController(BookService bookService) {
        this.bookService = bookService;
    }

    @PostMapping
    public R addBook(@RequestBody Book book) {
        return new R(bookService.save(book), null);
    }

    @DeleteMapping("{id}")
    public R deleteBook(@PathVariable Integer id) {
        return new R(bookService.delete(id), null);
    }

    @PutMapping
    public R updateBook(@RequestBody Book book) {
        return new R(bookService.update(book), null);
    }

    @GetMapping("/{id}")
    public R getById(@PathVariable("id") Integer id) {
        return new R(bookService.getById(id));
    }

    @GetMapping
    public R getAll() {
        return new R(true, bookService.getAll());
    }

    @GetMapping("/{pageNo}/{pageSize}")
    public R getPage(@PathVariable("pageNo") int pageNo,
                     @PathVariable("pageSize") int pageSize) {
        return new R(true, bookService.getByPage(pageNo, pageSize));
    }
}
