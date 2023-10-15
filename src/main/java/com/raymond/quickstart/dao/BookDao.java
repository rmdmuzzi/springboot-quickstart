package com.raymond.quickstart.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.raymond.quickstart.domain.Book;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

/**
 * @author raymondmuzzi
 * @since 2023-10-10 20:25:39
 */
@Mapper
@Repository
public interface BookDao extends BaseMapper<Book> {


    @Select("select * from book where id = #{id}")
    Book getById(Integer id);

}
