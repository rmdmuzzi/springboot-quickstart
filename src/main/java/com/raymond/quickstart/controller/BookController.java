package com.raymond.quickstart.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.raymond.quickstart.domain.Book;
import com.raymond.quickstart.domain.R;
import com.raymond.quickstart.serivce.BookService;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.math.BigDecimal;

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
    public R addBook(@RequestBody Book book) throws IOException {
        if ("123".equals(book.getName())) {
            throw new IOException("Mock exception!");
        }
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
        return new R(true, bookService.getById(id));
    }

    @GetMapping
    public R getAll() {
        return new R(true, bookService.getAll());
    }

    @GetMapping("/{pageNo}/{pageSize}")
    public R getPage(@PathVariable("pageNo") int pageNo,
                     @PathVariable("pageSize") int pageSize,
                     @RequestParam(value = "name", required = false) String name,
                     @RequestParam(value = "price", required = false) BigDecimal price) {
        IPage<Book> page = bookService.getByPage(pageNo, pageSize, name, price);
        if (page.getCurrent() > page.getPages()) {
            page = bookService.getByPage((int) page.getPages(), pageSize, name, price);
        }
        return new R(true, page);
    }
}
