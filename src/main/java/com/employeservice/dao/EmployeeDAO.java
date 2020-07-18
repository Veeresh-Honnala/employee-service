package com.employeservice.dao;


import lombok.*;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "EMP")
@Setter

@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class EmployeeDAO {
    @Id
    @GeneratedValue
    private Integer id;
    private String name;
    private Integer age;
}
