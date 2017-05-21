package com.niit.backend.dao;

import com.niit.backend.model.Cart;

public interface CartDAO {

	public boolean addCart(Cart cart);

	public boolean updateCart(Cart cart);

	public boolean resetCart(int id);
    
	
	Cart getCart(int id);
	
	Cart getCartWithUserId(Integer id);
}
