package com.niit.backend.dao;

import java.util.List;

import com.niit.backend.model.Supplier;

public interface SupplierDAO {
	public boolean saveorUpdate(Supplier supplier);
	public boolean delete(Supplier supplier);
	public Supplier get(int id);
	public List<Supplier> list();

}
