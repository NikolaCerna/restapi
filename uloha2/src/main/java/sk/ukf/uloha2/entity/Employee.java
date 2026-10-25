
package sk.ukf.uloha2.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;

import java.util.Date;

@Entity
@Table(name = "employee")
public class Employee {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    @NotBlank(message = "Meno je povinné.")
    @Size(min = 2, max = 50, message = "Meno musí mať 2-50 znakov")
    @Column(name = "first_name", nullable = false, length = 50)
    private String firstName;

    @NotBlank(message = "Priezvisko je povinné.")
    @Size(min = 2, max = 50, message = "Priezvisko musí mať 2-50 znakov")
    @Column(name = "last_name", nullable = false, length = 50)
    private String lastName;

    @NotNull(message = "Dátum narodenia je povinný.")
    @Past(message = "Dátum narodenia musí byť v minulosti.")
    @Column(name = "birth_date")
    private Date birthDate;

    @NotBlank(message = "Email je povinný.")
    @Pattern(
            regexp = "^[A-Za-z0-9._%+-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,}$",
            message = "Email nemá správny formát."
    )
    @Column(name = "email")
    private String email;

    @Pattern(
            regexp = "^(\\+421|0)9[0-9]{8}$",
            message = "Telefónne číslo musí byť v správnom formáte."
    )
    @Column(name = "phone")
    private String phone;

    @NotBlank(message = "Pracovná pozícia je povinná.")
    @Size(min = 2, max = 50, message = "Pracovná pozícia musí mať 2-50 znakov.")
    @Column(name = "job_title")
    private String jobTitle;

    @NotNull(message = "Plat je povinný.")
    @PositiveOrZero(message = "Plat nemôže byť záporný.")
    @Column(name = "salary")
    private Integer salary;

    @Column(name = "full_time")
    private boolean fullTime;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public Date getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(Date birthDate) {
        this.birthDate = birthDate;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getJobTitle() {
        return jobTitle;
    }

    public void setJobTitle(String jobTitle) {
        this.jobTitle = jobTitle;
    }

    public Integer getSalary() {
        return salary;
    }

    public void setSalary(Integer salary) {
        this.salary = salary;
    }

    public boolean isFullTime() {
        return fullTime;
    }

    public void setFullTime(boolean fullTime) {
        this.fullTime = fullTime;
    }
}
