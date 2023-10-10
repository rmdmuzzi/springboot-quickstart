package com.raymond.quickstart.dao.impl;

import com.raymond.quickstart.dao.BookDao;
import org.springframework.stereotype.Repository;

/**
 * @author raymondmuzzi
 * @since 2023-10-10 20:25:55
 */
@Repository
public class BookDaoImpl implements BookDao {

    @Override
    public void save() {
        System.out.println("save book...");
    }
}
