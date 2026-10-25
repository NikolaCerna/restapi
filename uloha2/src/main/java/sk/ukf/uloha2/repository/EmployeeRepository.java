
package sk.ukf.uloha2.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import sk.ukf.uloha2.entity.Employee;

import java.util.Optional;

public interface EmployeeRepository extends JpaRepository<Employee, Integer> {
    boolean existsByEmail(String email);

    Optional<Employee> findByEmail(String email);
}
