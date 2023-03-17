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

    @Query(value = "select SUM(c.money) from Cash c where  c.category.id = 8")
    Double getSalary();

    @Query(value = "select c from Cash c where c.category.type = 'expense'")
    List<Cash> findAllByExpense();


    @Query(value = "select c.type,DATE(c.date) ,sum(c.money) as money from  Cash c where c.date>=:start and c.date<=:end group by c.type,DATE(c.date)  order by DATE(c.date) asc")
    List<Object>  chartList( @Param("start") LocalDateTime start,@Param("end") LocalDateTime end);
    @Query(value = "select c.type,year (c.date),sum(c.money) as money from Cash c group by Year(c.date),c.type order by year(c.date) asc")
    List<Object> searchByYear();
    @Query(value = "select c.category.name,sum(c.money) as money from Cash c  where c.type=:type and c.date>=:start and c.date<=:end group by  c.category,c.type order by money" )
    List<Object> getDetailChart( @Param("start") LocalDateTime start,@Param("end") LocalDateTime end,@Param("type") String type);
}
