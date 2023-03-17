package com.example.case_study_3.service;

import com.example.case_study_3.model.Category;
import org.springframework.stereotype.Service;

import java.util.List;

public interface ICategoryService {
    List<Category> findAll();
    List<Category> findAllByExpense();
    List<Category> findAllByIncome();
    List<Category> findAllByAccount();
    List<Category> findAllByPlan();
}
