package com.example.case_study_3.repository;

import com.example.case_study_3.model.Cash;
import com.example.case_study_3.model.Category;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
@Transactional
public interface CategoryRepository extends JpaRepository<Category,Long> {
    @Query(value = "select c from Category c where c.type = 'expense'")
    List<Category> findAllByExpense();

    @Query(value = "select c from Category c where c.type = 'income'")
    List<Category> findAllByIncome();
    @Query(value = "select c from Category c where c.type = 'account'")
    List<Category> findAllByAccount();

    @Query(value = "select c from Category c where c.type = 'plan'")
    List<Category> findAllByPlan();
}
