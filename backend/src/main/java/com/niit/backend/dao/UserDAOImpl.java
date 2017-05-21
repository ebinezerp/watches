package com.niit.backend.dao;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.niit.backend.model.User;
@Repository("userDAO")
@Transactional
public class UserDAOImpl implements UserDAO {
	
	@Autowired
	SessionFactory sessionFactory;

	@Override
	public boolean saveorUpdate(User user) {
		// TODO Auto-generated method stub
		try {
			sessionFactory.getCurrentSession().saveOrUpdate(user);
			return true;
		} catch (Exception e) {
			// TODO: handle exception
			
			System.out.println(e);
			return false;
		}
	}

	@Override
	public boolean delete(User user) {
		// TODO Auto-generated method stub
		try {
			sessionFactory.getCurrentSession().delete(user);
			return true;
		} catch (Exception e) {
			// TODO: handle exception
			System.out.println(e);
			return false;
		}
	}

	@Override
	public User get(String id) {
		// TODO Auto-generated method stub
		try {
		return	sessionFactory.getCurrentSession().createQuery("from User where emailid=:id",User.class).setParameter("id", id).getSingleResult();
		} catch (Exception e) {
			// TODO: handle exception
			System.out.println(e);
			return null;
		}
	}

}
