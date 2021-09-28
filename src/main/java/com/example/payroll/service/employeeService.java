package com.example.payroll.service;

import com.example.payroll.entity.employeeEntity;
import com.example.payroll.exception.RecordnotFoundException;
import com.example.payroll.repository.entityRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Service

public class employeeService {
    Logger log = LoggerFactory.getLogger(employeeService.class);

    @Autowired
    private entityRepository repository;

    public List<employeeEntity> getAll() {

        List<employeeEntity> emp = repository.findAll();

        if (emp.size() > 0) {
            return emp;
        } else {
            log.info("there is no employees registered yet");
            return new ArrayList<employeeEntity>();
        }
    }

    public ResponseEntity<employeeEntity> getbyId(Long id) throws RecordnotFoundException {

        Optional<employeeEntity> employee = repository.findById(id);

        if (employee.isPresent()) {
            log.info("Employee found with ID:" + id + " returning employee." +employee.get().getName());
            return new ResponseEntity<>(employee.get(), HttpStatus.OK);
        } else {
            throw new RecordnotFoundException("Employee not matched with Id please retry");
        }
    }

    public ResponseEntity<HttpStatus> deletebyId(Long id) {

        try {

            Optional<employeeEntity> employee = repository.findById(id);

            if (employee.isPresent()) {
                repository.deleteById(id);
                log.info("Employee found with ID:" + id + "deleting employee." +employee.get().getId());
            }
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    public ResponseEntity<employeeEntity> updateemployeeRecord(Long id, employeeEntity entity) {
        Optional<employeeEntity> searchrecordById = repository.findById(id);
        if (searchrecordById.isPresent()) {
            employeeEntity employeeRecord = searchrecordById.get();
            employeeRecord.setName(entity.getName());
            employeeRecord.setType(entity.getType());
            employeeRecord.setSSN(entity.getSSN());
            log.info("Employee found with ID:" + id + " updating employee." +employeeRecord.getName());
            return new ResponseEntity<>(repository.save(employeeRecord), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    public ResponseEntity<employeeEntity> createemployeeRecord(employeeEntity entity) {

        try {
            employeeEntity saveEmployee = repository.save(new employeeEntity(entity.getId(), entity.getName(), entity.getType(), entity.getSSN()));
            log.info("New employee created with Name:" + entity.getName());
            return new ResponseEntity<>(saveEmployee, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

}
