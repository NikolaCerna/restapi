
package sk.ukf.uloha2.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import sk.ukf.uloha2.entity.Employee;
import sk.ukf.uloha2.service.EmployeeService;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api")
public class EmployeeRestController {

    private final EmployeeService employeeService;

    @Autowired
    public EmployeeRestController(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    @GetMapping("/employees")
    public List<Employee> getAllEmployees() {
        return employeeService.findAll();
    }

    @GetMapping("/employees/{id}")
    public Employee getEmployeeById(@PathVariable int id) {
        Employee employee = employeeService.findById(id);

        if (employee == null) {
            throw new ResponseStatusException(
                    HttpStatus.NOT_FOUND,
                    "Employee id not found - " + id
            );
        }

        return employee;
    }

    @PostMapping("/employees")
    @ResponseStatus(HttpStatus.CREATED)
    public Employee createEmployee(@RequestBody Employee employee) {
        employee.setId(null);
        return employeeService.save(employee);
    }

    @PutMapping("/employees/{id}")
    public Employee updateEmployee(
            @PathVariable int id,
            @RequestBody Employee employee) {

        Employee existingEmployee = employeeService.findById(id);

        if (existingEmployee == null) {
            throw new ResponseStatusException(
                    HttpStatus.NOT_FOUND,
                    "Employee id not found - " + id
            );
        }

        employee.setId(id);
        return employeeService.save(employee);
    }

    @DeleteMapping("/employees/{id}")
    public Map<String, String> deleteEmployee(@PathVariable int id) {
        Employee employee = employeeService.findById(id);

        if (employee == null) {
            throw new ResponseStatusException(
                    HttpStatus.NOT_FOUND,
                    "Employee id not found - " + id
            );
        }

        employeeService.deleteById(id);

        return Map.of("message", "Deleted employee id - " + id);
    }
}
