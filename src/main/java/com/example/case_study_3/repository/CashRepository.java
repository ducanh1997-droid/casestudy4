package com.example.case_study_3.repository;
import com.example.case_study_3.model.Cash;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

@Repository
@Transactional
public interface CashRepository extends JpaRepository<Cash,Long> {
    @Query(value = "select c from Cash c where c.type like :type and c.category.id = :category")
    Page<Cash>searchT(@Param("type") String type,@Param("category") Long category,Pageable pageable);
    List<Cash> findByDateBetween(LocalDateTime startDate, LocalDateTime endDate);

    @Query(value = "SELECT  SUM(c.money) AS total_money FROM Cash c where c.type =:type")
    Double getTotalMoneyByType(@Param("type") String type);
    @Query(value = "select sum (c.money) from Cash  c where c.type=:type and c.category.id=:category and c.date>=:start and c.date<=:end")
    Double getTotalMoneyByCategoryAndType(@Param("type") String type,@Param("category") Long category ,@Param("start") LocalDateTime start,@Param("end") LocalDateTime end);



}
