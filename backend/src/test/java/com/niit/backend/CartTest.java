package com.niit.backend;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.List;

import org.junit.BeforeClass;
import org.junit.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import com.niit.backend.dao.CartDAO;
import com.niit.backend.dao.CartItemDAO;
import com.niit.backend.dao.ProductDAO;
import com.niit.backend.dao.UserDAO;
import com.niit.backend.model.Cart;
import com.niit.backend.model.CartItem;
import com.niit.backend.model.Product;
import com.niit.backend.model.User;

public class CartTest {
	
	private static AnnotationConfigApplicationContext context;
	private static ProductDAO productDAO;
	private static UserDAO userDAO;
	private static CartDAO cartDAO;
	private static CartItemDAO cartItemDAO;
	private Cart cart;
	private User user;
	private Product product;
	private CartItem cartItem;
	
	@BeforeClass
	public static void inti()
	{
		context=new AnnotationConfigApplicationContext();
		context.scan("com.niit.backend");
		context.refresh();
		userDAO=(UserDAO)context.getBean("userDAO");
		productDAO=(ProductDAO)context.getBean("productDAO");
		cartDAO=(CartDAO)context.getBean("cartDAO");
		cartItemDAO=(CartItemDAO)context.getBean("cartItemDAO");
	}
	
	
	@Test
	public void cartTest()
	{
		user=userDAO.get("ebinezer.p87@gmail.com");
		System.out.println("user object::::::::::"+user);
		product=productDAO.get(3);
		System.out.println(product);
		cart=new Cart();
		cart.setGrandtotal(product.getPrice());
		cart.setQty(25);
		cart.setUsers(user);
		
		cartItem=new CartItem();
		cartItem.setCart(cart);
		cartItem.setGrandtotal(product.getPrice());
		cartItem.setProduct(product);
		cartItem.setQty(1);
		List<CartItem> cartItems=new ArrayList<CartItem>();
		cartItems.add(cartItem);
		cart.setCartitems(cartItems);
		
		assertEquals("success",true,cartDAO.addCart(cart));
		assertEquals("success",true,cartItemDAO.addCartItem(cartItem));
		
		
	}

}
