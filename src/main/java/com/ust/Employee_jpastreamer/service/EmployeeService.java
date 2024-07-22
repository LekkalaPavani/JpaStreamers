
package com.ust.Employee_jpastreamer.service;



import com.speedment.jpastreamer.application.JPAStreamer;
import com.ust.Employee_jpastreamer.model.Employee;
import com.ust.Employee_jpastreamer.repository.Employeerepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class EmployeeService {
    @Autowired
    private Employeerepo employeeRepository;

    private final JPAStreamer jpaStreamer;

    @Autowired
    public EmployeeService(final JPAStreamer jpaStreamer) {
        this.jpaStreamer = jpaStreamer;
    }


    public Map<String, List<Employee>> groupbyEmployeeByCity() {
        return jpaStreamer.stream(Employee.class)
                .collect(Collectors.groupingBy(Employee::getCity));
    }

    public List<Employee> groupbyEmployeeByPaymentTier() {
        return employeeRepository.findAll();
    }

    public List<Employee> saveEmployee(List<Employee> employee) {
        return employeeRepository.saveAll(employee);
    }

    public List<Employee> getEmployeeByAgeBetween(int min, int max) {
        return jpaStreamer.stream(Employee.class)
                .filter(e -> e.getAge() >= min && e.getAge() <= max)
                .collect(Collectors.toList());
    }

    public long groupByEmployeesByGender(String gender) {
        return jpaStreamer.stream(Employee.class)
                .filter(e -> e.getGender().equalsIgnoreCase(gender))
                .count();

    }

    public long countEmployeesByYear(int year) {
        return jpaStreamer.stream(Employee.class)
                .filter(e->e.getJoiningYear()==year)
                .count();
    }

    public List<Employee> getEmployeesByYear(int year) {
        return jpaStreamer.stream(Employee.class)
               .filter(e->e.getJoiningYear()==year)
               .collect(Collectors.toList());
    }

    public Map<String, List<Employee>> groupByEducation() {
        return jpaStreamer.stream(Employee.class)
               .collect(Collectors.groupingBy(Employee::getEducation));
    }

    public List<Employee> FilterEmployeeByConditions(int experience, String education, int year, String gender) {
    return jpaStreamer.stream(Employee.class)
            .filter(e->(e.getExperienceInCurrentDomain()==experience)
            ||(e.getEducation().equalsIgnoreCase(education))||(e.getJoiningYear()==year)
            ||(e.getGender().equalsIgnoreCase(gender))).collect(Collectors.toList());
    }
}
