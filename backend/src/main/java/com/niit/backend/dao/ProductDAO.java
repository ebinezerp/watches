package com.niit.backend.dao;

import java.util.List;

import com.niit.backend.model.Product;

public interface ProductDAO {
public boolean saveorUpdate(Product product);
public boolean delete(Product product);
public Product get(int id);
public List<Product> list();
}
