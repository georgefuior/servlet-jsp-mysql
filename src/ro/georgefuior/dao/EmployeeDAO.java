package ro.georgefuior.dao;

import java.util.List;

import ro.georgefuior.entity.Employee;

public interface EmployeeDAO {
	List<Employee> get();
	boolean save(Employee e);
	Employee get(int id);
	boolean update(Employee e);
	boolean delete(int id);
}
