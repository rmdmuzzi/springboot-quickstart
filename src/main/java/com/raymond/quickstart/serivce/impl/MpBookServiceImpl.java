package com.raymond.quickstart.serivce.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.raymond.quickstart.dao.BookDao;
import com.raymond.quickstart.domain.Book;
import com.raymond.quickstart.serivce.IBookService;
import org.springframework.stereotype.Service;

/**
 * Mybatis plus provided ServiceImpl
 *
 * @author raymondmuzzi
 * @since 2023-10-14 12:12:30
 */
@Service
public class MpBookServiceImpl extends ServiceImpl<BookDao, Book>
        implements IBookService {
}
