package com.example.demo.mapper;

import com.example.demo.model.User;
import java.util.List;

public interface UserMapper {
    int deleteByPrimaryKey(Long userId);
    int insert(User record);
    User selectByName(String userName);
    List<User> selectAll();
    int updateByPrimaryKey(User record);
}