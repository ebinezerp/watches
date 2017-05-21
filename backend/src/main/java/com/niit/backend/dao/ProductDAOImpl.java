package com.niit.backend.dao;

import java.util.List;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.niit.backend.model.Product;
@Repository("productDAO")
@Transactional
public class ProductDAOImpl implements ProductDAO{

	@Autowired
	SessionFactory sessionFactory;
	@Override
	public boolean saveorUpdate(Product product) {
		// TODO Auto-generated method stub
		try {
			sessionFactory.getCurrentSession().saveOrUpdate(product);
			return true;
		} catch (Exception e) {
			// TODO: handle exception
			System.out.println(e);
			return false;
		}
	}

	@Override
	public boolean delete(Product product) {
		try {
			sessionFactory.getCurrentSession().delete(product);
			return true;
		} catch (Exception e) {
			// TODO: handle exception
			System.out.println(e);
			return false;
		}
	}

	@Override
	public Product get(int id) {
		// TODO Auto-generated method stub
		try {
		return sessionFactory.getCurrentSession().createQuery("from Product where prodid=:id",Product.class).setParameter("id", id).getSingleResult();
		} catch (Exception e) {
			// TODO: handle exception
			System.out.println(e);
			return null;
		}
	}

	@Override
	public List<Product> list() {
		// TODO Auto-generated method stub
		try {
			return sessionFactory.getCurrentSession().createQuery("from Product",Product.class).getResultList();
		} catch (Exception e) {
			// TODO: handle exception
			System.out.println(e);
			return null;
		}
		//return null;
	}

}
