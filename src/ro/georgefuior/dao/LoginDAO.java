package ro.georgefuior.dao;

import ro.georgefuior.entity.Login;

public interface LoginDAO {
	String authenticate(Login login);
}
