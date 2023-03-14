package com.example.case_study_3.service;

import com.example.case_study_3.model.MyMoney;

public interface IMoneyService {
    MyMoney findById(Long id);
    void save(MyMoney money);
    void delete(Long id);
}
