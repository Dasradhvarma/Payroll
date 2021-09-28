package com.example.payroll.entity;

import javax.persistence.*;
import javax.persistence.Table;

@Entity
@Table(name = "EMP_DATA")

public class employeeEntity {

    @Id
    @Column
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name_emp")
    private String name;

    @Column(name = "type_emp")
    private String type;

    @Column(name = "FTI_info", nullable = false, length = 100)
    private String SSN;

    public employeeEntity() {

    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getSSN() {
        return SSN;
    }

    public void setSSN(String SSN) {
        this.SSN = SSN;
    }

    @Override
    public String toString() {
        return "employeeEntity{" +
                "name='" + name + '\'' +
                ", type='" + type + '\'' +
                ", SSN='" + SSN + '\'' +
                '}';
    }

    public employeeEntity(Long id, String name, String type, String SSN) {
        this.id = id;
        this.name = name;
        this.type = type;
        this.SSN = SSN;
    }

}
