package com.niit.backend.dao;

import java.util.List;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.niit.backend.model.CartItem;
@Repository("cartItemDAO")
@Transactional
public class CartItemDAOImpl implements CartItemDAO {

	 @Autowired
	 SessionFactory sessionFactory;
	@Override
	public boolean addCartItem(CartItem cartItem) {
		// TODO Auto-generated method stub
		try {
			sessionFactory.getCurrentSession().saveOrUpdate(cartItem);
			return true;
		} catch (Exception e) {
			// TODO: handle exception
			System.out.println(e);
			return false;
		}
	}

	@Override
	public List<CartItem> getAll(int id) {
		// TODO Auto-generated method stub
		try {
			return sessionFactory.getCurrentSession().createQuery("from CartItem",CartItem.class).getResultList();
		} catch (Exception e) {
			// TODO: handle exception
			System.out.println(e);
			return null;
		}
	}

	@Override
	public boolean deleteCartItem(CartItem cartItem) {
		// TODO Auto-generated method stub
		try {
			sessionFactory.getCurrentSession().delete(cartItem);
			return true;
		} catch (Exception e) {
			// TODO: handle exception
			System.out.println(e);
			return false;
		}
	}

	@Override
	public CartItem getCartItem(int id) {
		// TODO Auto-generated method stub
		try {
			return sessionFactory.getCurrentSession().createQuery("from CartItem where cartitemid=:id",CartItem.class).setParameter("id", id).getSingleResult();
			} catch (Exception e) {
				// TODO: handle exception
				System.out.println(e);
				return null;
			}
	}

	@Override
	public boolean deleteAll(int cart_id) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public CartItem getExistingCartItemCount(int prodid, int cart_id) {
		// TODO Auto-generated method stub
		try {
			return sessionFactory.getCurrentSession().createQuery("from CartItem where product_prodid=:prodid and cart_cartid=:cartid",CartItem.class)
									.setParameter("prodid", prodid)
									.setParameter("cartid", cart_id)
									.getSingleResult();
		} catch (Exception e) {
			// TODO: handle exception
			System.out.println(e);
			return null;
		}
	}

	@Override
	public boolean updateCartItem(CartItem cartItem) {
		// TODO Auto-generated method stub
		try {
			sessionFactory.getCurrentSession().update(cartItem);
			return true;
		} catch (Exception e) {
			// TODO: handle exception
			System.out.println(e);
			return false;
		}
	}

}
