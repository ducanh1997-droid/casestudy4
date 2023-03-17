package com.example.case_study_3.service;

import com.example.case_study_3.model.User;

import java.util.List;

public interface IUserService {
    List<User> findALl();
    User findOne(Long id);
    void save(User user);
    void delete(Long id);
    User findByUserNameAndPassWord(String username,String passWord);
}
