package sk.ukf.uloha2.dao;

import sk.ukf.uloha2.entity.Employee;

import java.util.List;

public interface EmployeeDAO {
    List<Employee> findAll();
}
