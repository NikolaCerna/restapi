
package sk.ukf.uloha2.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import sk.ukf.uloha2.entity.Employee;
import sk.ukf.uloha2.exception.EmailAlreadyExistsException;
import sk.ukf.uloha2.exception.ObjectNotFoundException;
import sk.ukf.uloha2.repository.EmployeeRepository;

import java.util.List;
import java.util.Objects;

@Service
public class EmployeeServiceImpl implements EmployeeService {

    private final EmployeeRepository employeeRepository;

    public EmployeeServiceImpl(EmployeeRepository employeeRepository) {
        this.employeeRepository = employeeRepository;
    }

    @Override
    public List<Employee> findAll() {
        return employeeRepository.findAll();
    }

    @Override
    public Employee findById(int id) {
        return employeeRepository.findById(id)
                .orElseThrow(() -> new ObjectNotFoundException("Zamestnanec", id));
    }

    @Transactional
    @Override
    public Employee save(Employee employee) {

        // Kontrola duplicitného emailu pri vytváraní nového zamestnanca
        if (employee.getId() == null) {
            if (employeeRepository.existsByEmail(employee.getEmail())) {
                throw new EmailAlreadyExistsException(employee.getEmail());
            }
        } else {
            // Pri aktualizácii skontroluje, či email nepatrí inému zamestnancovi
            Employee existingWithEmail = employeeRepository
                    .findByEmail(employee.getEmail())
                    .orElse(null);

            if (existingWithEmail != null
                    && !Objects.equals(existingWithEmail.getId(), employee.getId())) {
                throw new EmailAlreadyExistsException(employee.getEmail());
            }
        }

        return employeeRepository.save(employee);
    }

    @Transactional
    @Override
    public void deleteById(int id) {

        // Skontroluje, či zamestnanec existuje
        if (!employeeRepository.existsById(id)) {
            throw new ObjectNotFoundException("Zamestnanec", id);
        }

        employeeRepository.deleteById(id);
    }
}
