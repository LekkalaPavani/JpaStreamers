package com.ust.Employee_jpastreamer.controller;

import com.ust.Employee_jpastreamer.model.Employee;
import com.ust.Employee_jpastreamer.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/v1")
public class EmployeeController {
    @Autowired
    private EmployeeService employeeService;
    @PostMapping("/save")
    public List<Employee> saveEmployee(@RequestBody List<Employee> employee){
        return employeeService.saveEmployee(employee);
    }

    @GetMapping("/groupByCity")
    public Map<String, List<Employee>> groupbyEmployeeByCity(){
        return employeeService.groupbyEmployeeByCity();
    }
    @GetMapping("/findall")
    public List<Employee> groupbyEmployeeByPaymentTier(){
        return employeeService.groupbyEmployeeByPaymentTier();
    }
    @GetMapping("/ageRanges/{min}/{max}")
    public List<Employee> getEmployeeByAgeRanges(@PathVariable int min, @PathVariable int max){

        return employeeService.getEmployeeByAgeBetween(min, max);
    }
    @GetMapping("/countbyGender/{gender}")
    public long getEmployeesByGender(@PathVariable String gender){
        return employeeService.groupByEmployeesByGender(gender);
    }
    @GetMapping("/countGender/{year}")
    public long countEmployeesByYear(@PathVariable int year){
        return employeeService.countEmployeesByYear(year);

    }
@GetMapping("getemployeesByYear/{year}")
    public List<Employee> getEmployeesByYear(@PathVariable int year){
        return employeeService.getEmployeesByYear(year);
    }
 @GetMapping("groupbyEducation")
    public Map<String, List<Employee>> groupByEducation(){
        return employeeService.groupByEducation();
    }
    @GetMapping("filterByConditions/{experience}/{education}/{year}/{gender}")
public List<Employee> filterByConditions(int experience,String education,int year,String gender) {
        return employeeService.FilterEmployeeByConditions(experience, education, year, gender);


    }
}