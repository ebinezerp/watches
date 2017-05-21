package com.niit.backend.dao;

import java.util.List;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.niit.backend.model.Supplier;
@Repository("supplierDAO")
@Transactional
public class SupplierDAOImpl implements SupplierDAO {
	@Autowired
	SessionFactory sessionFactory;

	@Override
	public boolean saveorUpdate(Supplier supplier) {
		// TODO Auto-generated method stub
		try {
			sessionFactory.getCurrentSession().saveOrUpdate(supplier);
		return true;
		} catch (Exception e) {
			// TODO: handle exception
			System.out.println(e);
			return false;
		}
	}

	@Override
	public boolean delete(Supplier supplier) {
		// TODO Auto-generated method stub
		try {
			sessionFactory.getCurrentSession().delete(supplier);
		return true;
		} catch (Exception e) {
			// TODO: handle exception
			System.out.println(e);
			return false;
		}
	}

	@Override
	public Supplier get(int id) {
		// TODO Auto-generated method stub
		try {
		return	sessionFactory.getCurrentSession().createQuery("from supplier where supid=:id",Supplier.class).setParameter("id", id).getSingleResult();
		} catch (Exception e) {
			// TODO: handle exception
			System.out.println(e);
			return null;
		}
	}

	@Override
	public List<Supplier> list() {
		// TODO Auto-generated method stub
		try {
			return sessionFactory.getCurrentSession().createQuery("from Supplier",Supplier.class).getResultList();
		} catch (Exception e) {
			// TODO: handle exception
			System.out.println(e);
			return null;
		}
	}

}
