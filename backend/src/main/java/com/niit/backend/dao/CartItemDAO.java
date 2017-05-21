package com.niit.backend.dao;

import java.util.List;

import com.niit.backend.model.CartItem;

public interface CartItemDAO {
	public boolean addCartItem(CartItem cartItem);
	public List<CartItem> getAll(int id);
	public boolean deleteCartItem(CartItem cartItem);
	public CartItem getCartItem(int id);
	public boolean deleteAll(int cart_id);
	public CartItem getExistingCartItemCount(int prodid, int cart_id);
	public boolean updateCartItem(CartItem cartItem);

}
