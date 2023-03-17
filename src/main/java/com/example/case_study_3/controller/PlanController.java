package com.example.case_study_3.controller;

import com.example.case_study_3.model.Cash;
import com.example.case_study_3.model.Plan;
import com.example.case_study_3.service.IPlanService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin("*")
@RequestMapping("/plan")

public class PlanController {
    @Autowired
    private IPlanService iPlanService;
    @GetMapping
    public ResponseEntity<List<Plan>> findALl(){
        return new ResponseEntity<>(iPlanService.findAll(), HttpStatus.OK);
    }
    @GetMapping("/{id}")
    public ResponseEntity<Plan> findOne(@PathVariable Long id){
        Plan plan= iPlanService.findOne(id);
        return new ResponseEntity<>(plan,HttpStatus.ACCEPTED);
    }
    @PostMapping
    public ResponseEntity<Void> create(@RequestBody Plan plan){
        iPlanService.save(plan);
        return new ResponseEntity<>(HttpStatus.OK);
    }
    @PutMapping("/{id}")
    public ResponseEntity<Void> update(@PathVariable Long id,@RequestBody Plan plan){
        Plan plan1=iPlanService.findOne(id);
        if (plan1!=null){
            iPlanService.save(plan);
            return new ResponseEntity<>(HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletePlan(@PathVariable Long id){
        iPlanService.delete(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping("/categories/salary/get/{id}")
    public ResponseEntity<Double> getMoneyById(@PathVariable Long id){
        return new ResponseEntity<>(iPlanService.getMoneyById(id),HttpStatus.OK);
    }

}