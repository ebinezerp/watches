package com.niit.backend.dao;

import java.util.List;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.niit.backend.model.Category;
@Repository("categoryDAO")
@Transactional
public class CategoryDAOImpl implements CategoryDAO {

	 @Autowired
	 SessionFactory sessionFactory;
	@Override
	public boolean saveorUpdate(Category category) {
		// TODO Auto-generated method stub
		try {
			sessionFactory.getCurrentSession().saveOrUpdate(category);
			return true;
		} catch (Exception e) {
			// TODO: handle exception
			System.out.println(e);
			return false;
		}
	}

	@Override
	public boolean delete(Category category) {
		// TODO Auto-generated method stub
		try {
			sessionFactory.getCurrentSession().delete(category);
			return true;
		} catch (Exception e) {
			// TODO: handle exception
			System.out.println(e);
			return false;
		}
	}

	@Override
	public Category get(int id) {
		// TODO Auto-generated method stub
		try {
			return sessionFactory.getCurrentSession().createQuery("from Category where categoryid=:id",Category.class).setParameter("id", id).getSingleResult();
			} catch (Exception e) {
				// TODO: handle exception
				System.out.println(e);
				return null;
			}
	}

	@Override
	public List<Category> list() {
		// TODO Auto-generated method stub
		try {
			return sessionFactory.getCurrentSession().createQuery("from Category",Category.class).getResultList();
		} catch (Exception e) {
			// TODO: handle exception
			System.out.println(e);
			return null;
		}
	}

}
