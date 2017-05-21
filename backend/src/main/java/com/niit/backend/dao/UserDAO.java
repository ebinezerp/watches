package com.niit.backend.dao;

import com.niit.backend.model.User;

public interface UserDAO {
	public boolean saveorUpdate(User user);
	public boolean delete(User user);
	public User get(String id);


}
