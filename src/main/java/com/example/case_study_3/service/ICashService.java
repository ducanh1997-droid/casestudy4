package com.example.case_study_3.service;

import com.example.case_study_3.model.Cash;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.time.LocalDateTime;
import java.util.List;

public interface ICashService {
        List<Object>  chartList( LocalDateTime start, LocalDateTime end);
        List<Object> searchByYear();
        List<Object> getDetailChart( LocalDateTime start, LocalDateTime end,String type);
        Page<Cash> findAll(Pageable pageable);
        Cash findOne(Long id);
        Double getSalary();
        List<Cash> finAllByExpense();
        void save(Cash cash);
        void delete(Long id);
        List<Cash> searchByDate(LocalDateTime startDate, LocalDateTime endDate);
        Double totalMoneyByType(String type);
        Double totalMoneyByCategoryAndType(String type,Long category,LocalDateTime start,LocalDateTime end);
}
