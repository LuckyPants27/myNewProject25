package pro.sky.mynewproject25.service;

import org.springframework.stereotype.Service;
import pro.sky.mynewproject25.exception.EmployeeAlreadyAddedException;
import pro.sky.mynewproject25.exception.EmployeeNotFoundException;
import pro.sky.mynewproject25.exception.EmployeeStorageIsFullException;
import pro.sky.mynewproject25.model.Employee;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Service
public class EmployeeService {

    private final int maxEmployees = 10;
    private final List<Employee> employees = new ArrayList<Employee>();

    public Employee add(String firstName, String lastName) {
        Employee employee = new Employee(firstName, lastName);
        if (employees.contains(employee)) {
            throw new EmployeeAlreadyAddedException();
        }
        if (employees.size() >= maxEmployees) {
            throw new EmployeeStorageIsFullException();
        }
        employees.add(employee);
        return employee;
    }

    public Employee remove(String firstName, String lastName) {
        Employee employee = new Employee(firstName, lastName);
        if (!employees.contains(employee)) {
            throw new EmployeeNotFoundException();
        }
        employees.remove(employee);
        return employee;
    }

    public Employee find(String firstName, String lastName) {
        Employee employee = new Employee(firstName, lastName);
        if (!employees.contains(employee)) {
            throw new EmployeeNotFoundException();
        }
        return employee;
    }

    public List<Employee> findAll() {
        return Collections.unmodifiableList(employees);
    }
}
