
package sk.ukf.uloha2.controller;

import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import sk.ukf.uloha2.entity.Employee;
import sk.ukf.uloha2.response.ResponseFactory;
import sk.ukf.uloha2.response.ResponseWrapper;
import sk.ukf.uloha2.service.EmployeeService;

import java.util.List;

@RestController
@RequestMapping("/api")
public class EmployeeRestController {

    private final EmployeeService employeeService;
    private final ResponseFactory responseFactory;

    public EmployeeRestController(EmployeeService employeeService, ResponseFactory responseFactory) {this.employeeService = employeeService;this.responseFactory = responseFactory;
    }

    @GetMapping("/employees")
    public ResponseEntity<ResponseWrapper<List<Employee>>> getAllEmployees() {
        return responseFactory.ok("Zoznam zamestnancov", employeeService.findAll());
    }

    @GetMapping("/employees/{id}")
    public ResponseEntity<ResponseWrapper<Employee>> getEmployeeById(@PathVariable int id) {
        Employee employee = employeeService.findById(id);
        if (employee == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Zamestnanec s ID " + id + " nebol nájdený");
        }

        return responseFactory.ok("Zamestnanec nájdený", employee);
    }

    @PostMapping("/employees")
    public ResponseEntity<ResponseWrapper<Employee>> createEmployee(@Valid @RequestBody Employee employee) {
        employee.setId(null);
        Employee savedEmployee = employeeService.save(employee);

        return responseFactory.created("Zamestnanec bol úspešne vytvorený", savedEmployee);
    }

    @PutMapping("/employees/{id}")
    public ResponseEntity<ResponseWrapper<Employee>> updateEmployee(@PathVariable int id, @Valid @RequestBody Employee employee) {

        Employee existingEmployee = employeeService.findById(id);

        if (existingEmployee == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Zamestnanec s ID " + id + " nebol nájdený");
        }

        employee.setId(id);
        Employee updatedEmployee = employeeService.save(employee);

        return responseFactory.ok(
                "Zamestnanec bol úspešne aktualizovaný",
                updatedEmployee
        );
    }

    @DeleteMapping("/employees/{id}")
    public ResponseEntity<ResponseWrapper<Void>> deleteEmployee(@PathVariable int id) {

        Employee employee = employeeService.findById(id);

        if (employee == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Zamestnanec s ID " + id + " nebol nájdený");
        }

        employeeService.deleteById(id);

        return responseFactory.ok("Zamestnanec s ID " + id + " bol úspešne vymazaný");
    }
}
