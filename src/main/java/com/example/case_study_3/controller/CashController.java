package com.example.case_study_3.controller;

import com.example.case_study_3.model.Cash;
import com.example.case_study_3.model.Category;
import com.example.case_study_3.service.ICashService;
import com.example.case_study_3.service.ICategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@CrossOrigin("*")
@RequestMapping("/cash")
public class CashController {

    @Autowired
    public ICashService iCashService;

    @Autowired
    public ICategoryService iCategoryService;
    @GetMapping
    public ResponseEntity<Page<Cash>> findAll(@PageableDefault(value = 3)Pageable pageable){
        return new ResponseEntity<>(iCashService.findAll(pageable), HttpStatus.OK);
    }
    @GetMapping("/{id}")
    public ResponseEntity<Cash> findOne(@PathVariable Long id){
        Cash cash=iCashService.findOne(id);
        return new ResponseEntity<>(cash,HttpStatus.ACCEPTED);
    }
    @GetMapping("/categories")
    public ResponseEntity<List<Category>> findALl(){
        return new ResponseEntity<>(iCategoryService.findAll(),HttpStatus.OK);
    }
    @PostMapping
    public ResponseEntity<Void> create(@RequestBody Cash cash){
        iCashService.save(cash);
        return new ResponseEntity<>(HttpStatus.OK);
    }
    @PutMapping("/{id}")
    public ResponseEntity<Void> update(@PathVariable Long id,@RequestBody Cash cash) {
        Cash cash1 = iCashService.findOne(id);
        if (cash1 != null) {
            iCashService.save(cash);
            return new ResponseEntity<>(HttpStatus.ACCEPTED);
        } else {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id){
        iCashService.delete(id);
        return new ResponseEntity<>(HttpStatus.CONTINUE);
    }
    @GetMapping("/search/{start}/{end}")
    public ResponseEntity<List<Cash>> searchByDate(@PathVariable String start,@PathVariable String end){
        LocalDateTime starDate=  LocalDateTime.parse(start);
        LocalDateTime endDate=LocalDateTime.parse(end);
        return new ResponseEntity<>(iCashService.searchByDate(starDate,endDate),HttpStatus.OK);
    }
    @GetMapping("/total/{type}")
    public ResponseEntity<Double> getTotalByType(@PathVariable String type){
        return new ResponseEntity<>(iCashService.totalMoneyByType(type),HttpStatus.OK);
    }
    @GetMapping("/total/{type}/{category}/{start}/{end}")
    public ResponseEntity<Double> getTotalByTypeAndCategory(@PathVariable String type,@PathVariable Long category,String start,String end){
        LocalDateTime starDate=  LocalDateTime.parse(start);
        LocalDateTime endDate=LocalDateTime.parse(end);
        return new ResponseEntity<>(iCashService.totalMoneyByCategoryAndType(type,category,starDate,endDate),HttpStatus.OK);
    }


}
