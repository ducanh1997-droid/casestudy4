package com.example.case_study_3.repository;

import com.example.case_study_3.model.Plan;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
@Transactional
public interface PlanRepository extends JpaRepository<Plan,Long> {
    @Query(value = "select c.money from Plan c where c.id = :id")
    Double getMoneyById(@Param("id") Long id );
}