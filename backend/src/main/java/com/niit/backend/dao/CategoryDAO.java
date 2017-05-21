package com.niit.backend.dao;

import java.util.List;

import com.niit.backend.model.Category;

public interface CategoryDAO {
	public boolean saveorUpdate(Category category);
	public boolean delete(Category category);
	public Category get(int id);
	public List<Category> list();

}
