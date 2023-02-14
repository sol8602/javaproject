package com.example.solproject.miniproject.mapper;

import org.apache.ibatis.annotations.Mapper;

import com.example.solproject.miniproject.model.User;

@Mapper
public interface UserMapper {
    public void join(User user);

    public String getPw(String id);
    
    public User selectUser(String id);

    public void withdraw(String userId);
    
}
