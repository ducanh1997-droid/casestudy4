package com.example.case_study_3.service.impl;

import com.example.case_study_3.model.Cash;
import com.example.case_study_3.repository.CashRepository;
import com.example.case_study_3.service.ICashService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class CashService implements ICashService {
    @Autowired
    public CashRepository cashRepository;
    @Autowired
    public MyMoneyService myMoneyService;


    @Override
    public Page<Cash> findAll(Pageable pageable) {
        return cashRepository.findAll(pageable);
    }

    @Override
    public Cash findOne(Long id) {
        return cashRepository.findById(id).orElse(null);
    }

    @Override
    public Double getSalary() {
        return cashRepository.getSalary();
    }

    @Override
    public List<Cash> finAllByExpense() {
        return cashRepository.findAllByExpense();
    }

    @Override
    public void save(Cash cash) {
    cashRepository.save(cash);
    }

    @Override
    public void delete(Long id) {
    cashRepository.deleteById(id);
    }

    @Override
    public List<Cash> searchByDate(LocalDateTime startDate, LocalDateTime endDate) {
        return cashRepository.findByDateBetween(startDate,endDate);
    }

    @Override
    public Double totalMoneyByType(String type) {
        return cashRepository.getTotalMoneyByType(type);
    }

    @Override
    public Double totalMoneyByCategoryAndType(String type, Long category,LocalDateTime start ,LocalDateTime end) {
        return cashRepository.getTotalMoneyByCategoryAndType(type,category,start,end);
    }
}
