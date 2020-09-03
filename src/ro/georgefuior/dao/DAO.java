package ro.georgefuior.dao;

import java.util.List;

import ro.georgefuior.entity.Employee;

public interface DAO<T> {
	List<T> get();
	boolean save(T e);
	T get(int id);
	boolean update(T e);
	boolean delete(int id);
}
