package com.niit.backend.dao;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.niit.backend.model.Cart;

@Repository("cartDAO")
@Transactional
public class CartDAOImpl implements CartDAO {

	@Autowired
	SessionFactory sessionFactory;
	
	

	@Override
	public boolean addCart(Cart cart) {
		// TODO Auto-generated method stub
		try {
			System.out.println(cart.getUsers());
			sessionFactory.getCurrentSession().save(cart);
              return true;
		} catch (Exception e) {
			// TODO: handle exception
			System.out.println(e);
			return false;
		}
	}

	@Override
	public boolean updateCart(Cart cart) {
		// TODO Auto-generated method stub
		try {
			sessionFactory.getCurrentSession().update(cart);
			return true;
		} catch (Exception e) {
			// TODO: handle exception
			System.out.println(e);
			return false;
		}
	}

	@Override
	public boolean resetCart(int id) {
		// TODO Auto-generated method stub
		try{
		Cart cart=  getCart(id);
		cart.setGrandtotal(0);
		cart.setQty(0);
		updateCart(cart);
		return true;
		}catch (Exception e) {
			// TODO: handle exception
			System.out.println(e);
			return false;
		}
	}

	@Override
	public Cart getCart(int id) {
		// TODO Auto-generated method stub
		try {
		return	sessionFactory.getCurrentSession().createQuery("from cart where cartid=:id ",Cart.class).setParameter("id", id).getSingleResult();
		} catch (Exception e) {
			// TODO: handle exception
			System.out.println(e);
			return null;
		}
	}

	@Override
	public Cart getCartWithUserId(Integer id) {
		try {
			return sessionFactory.getCurrentSession().createQuery("from Cart where user_userid=:id",Cart.class)
										.setParameter("id", id).getSingleResult();
		} catch (Exception e) {
			// TODO: handle exception
			System.out.println(e);
			return null;
		}
	}

}
