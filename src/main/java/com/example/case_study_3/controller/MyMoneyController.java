package com.example.case_study_3.controller;

import com.example.case_study_3.model.Cash;
import com.example.case_study_3.model.MyMoney;
import com.example.case_study_3.service.IMoneyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin("*")
@RequestMapping("/account")
public class MyMoneyController {
    @Autowired
    private IMoneyService iMoneyService;
    @GetMapping
    public ResponseEntity<List<MyMoney>> findAll(){
        return new ResponseEntity<>(iMoneyService.findAll(), HttpStatus.OK);
    }
    @GetMapping("/user/{userId}")
    public ResponseEntity<List<MyMoney>> findAllByUserId(@PathVariable Long userId){
        return new ResponseEntity<>(iMoneyService.findALlByUser(userId),HttpStatus.ACCEPTED);
    }
    @GetMapping("/{id}")
    public ResponseEntity<MyMoney> findOne(@PathVariable Long id){
        MyMoney myMoney = iMoneyService.findOne(id);
        return new ResponseEntity<>(myMoney,HttpStatus.ACCEPTED);
    }
    @PostMapping
    public ResponseEntity<Void> create(@RequestBody MyMoney money){
        iMoneyService.save(money);
        return new ResponseEntity<>(HttpStatus.OK);
    }
    @PutMapping("/{id}")
    public ResponseEntity<Void> update(@PathVariable Long id,@RequestBody MyMoney money){
        MyMoney money1=iMoneyService.findById(id);
        if (money1!=null){
            iMoneyService.save(money);
            return new ResponseEntity<>(HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id){
        iMoneyService.delete(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }
    @GetMapping("/sum/{userId}")
    public ResponseEntity<Double> findMoneyByUser(@PathVariable long userId){
        return new ResponseEntity<>(iMoneyService.selectMoneyByUser(userId),HttpStatus.OK);
    }
}