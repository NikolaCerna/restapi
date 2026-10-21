package sk.ukf.uloha2.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import sk.ukf.uloha2.entity.Employee;

public interface EmployeeRepository extends JpaRepository<Employee, Integer> {
}
