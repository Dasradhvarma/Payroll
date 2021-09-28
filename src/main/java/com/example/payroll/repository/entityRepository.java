package com.example.payroll.repository;

import com.example.payroll.entity.employeeEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface entityRepository extends JpaRepository<employeeEntity,Long> {
}
