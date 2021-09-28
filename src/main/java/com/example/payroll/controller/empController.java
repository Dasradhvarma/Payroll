package com.example.payroll.controller;

import com.example.payroll.entity.employeeEntity;
import com.example.payroll.exception.RecordnotFoundException;
import com.example.payroll.repository.entityRepository;
import com.example.payroll.service.employeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController

@RequestMapping("/employee")
public class empController {

    @Autowired
    entityRepository repository;

    @Autowired
    employeeService empservice;

    @GetMapping(value = "/allemployees")
    public ResponseEntity<List<employeeEntity>> getAll() {

        List<employeeEntity> allemployes = empservice.getAll();
        System.out.println("output2:" + allemployes);
        return new ResponseEntity<>(allemployes, new HttpHeaders(), HttpStatus.OK);

    }

    @GetMapping(value = "/getemployee/{id}")
    public ResponseEntity<employeeEntity> getemployeebyID(@PathVariable("id") Long id) throws RecordnotFoundException {

        return empservice.getbyId(id);

    }

    @PutMapping(value = "/updateRecord/{id}")
    public ResponseEntity<employeeEntity> updateemployeebyID(@PathVariable("id") Long id, @RequestBody employeeEntity entity) {

        ResponseEntity<employeeEntity> response = empservice.updateemployeeRecord(id, entity);
        if (response.getStatusCode() == HttpStatus.OK) {
            return new ResponseEntity<employeeEntity>(HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @PostMapping(value = "/saveRecord")
    public ResponseEntity<employeeEntity> updateemployeebyID(@RequestBody employeeEntity entity) {

        ResponseEntity<employeeEntity> response = empservice.createemployeeRecord(entity);
        if (response.getStatusCode() == HttpStatus.CREATED) {
            return new ResponseEntity<>(HttpStatus.CREATED);
        }
        return new ResponseEntity<>(HttpStatus.NOT_ACCEPTABLE);
    }

    @DeleteMapping(value = "/deleteRecord/{id}")
    public ResponseEntity<Long> deleteemployeebyID(@PathVariable("id") Long id) {
        ResponseEntity<HttpStatus> isRemoved = empservice.deletebyId(id);

        if (isRemoved.equals(null)) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(id, HttpStatus.OK);

    }

}

