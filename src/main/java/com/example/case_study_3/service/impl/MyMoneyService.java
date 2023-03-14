package com.example.case_study_3.service.impl;

import com.example.case_study_3.model.MyMoney;
import com.example.case_study_3.repository.MyMoneyRepository;
import com.example.case_study_3.service.IMoneyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MyMoneyService implements IMoneyService {
    @Autowired
    private MyMoneyRepository myMoneyRepository;
    @Override
    public MyMoney findById(Long id) {
        return myMoneyRepository.findById(id).orElse(null);
    }

    @Override
    public void save(MyMoney money) {
    myMoneyRepository.save(money);
    }

    @Override
    public void delete(Long id) {
    myMoneyRepository.deleteById(id);
    }
}
