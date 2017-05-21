package com.watchesfront.controller;

import java.io.File;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.security.Principal;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.niit.backend.dao.CartDAO;
import com.niit.backend.dao.CartItemDAO;
import com.niit.backend.dao.CategoryDAO;
import com.niit.backend.dao.ProductDAO;
import com.niit.backend.dao.SupplierDAO;
import com.niit.backend.dao.UserDAO;
import com.niit.backend.model.Cart;
import com.niit.backend.model.CartItem;
import com.niit.backend.model.Category;
import com.niit.backend.model.Product;
import com.niit.backend.model.Supplier;
import com.niit.backend.model.User;

@Controller

public class ProductController {

	@Autowired

	private ProductDAO productDAO;

	@Autowired

	private SupplierDAO supplierDAO;



	@Autowired

	private CategoryDAO categoryDAO;

	

	@Autowired

	private UserDAO userDAO;

	@Autowired

	private CartItemDAO cartItemDAO;

	@Autowired

	private CartDAO cartDAO;

	@Autowired

	HttpSession session;

	@ModelAttribute

	public Product returnObject() {
		return new Product();
	}

	@RequestMapping("/AddProducts")
	public ModelAndView showProducts() {
		ModelAndView mv = new ModelAndView("AddProducts");
		mv.addObject("productList", productDAO.list());
		mv.addObject("categoryList", categoryDAO.list());
		mv.addObject("supplierList", supplierDAO.list());
		return mv;
	}

	@RequestMapping(value = "/addprod", method = RequestMethod.POST)
	public String addprod(@Valid @ModelAttribute("product") Product product, BindingResult result, Model model,
			HttpServletRequest request) throws IOException {
		model.addAttribute("product", new Product());

		System.out.println(product.getProdname());
		System.out.println("image uploaded");
		System.out.println("myproduct controller called");
		MultipartFile image = product.getImg();

		Path path;
		path = Paths
				.get("F:/colsecondproject/Watchesproject/watchesfrontend/src/main/webapp/images/" + product.getProdname() + ".jpg");
		System.out.println("Path=" + path);
		System.out.println("File name" + product.getImg().getOriginalFilename());

		if (image != null && !image.isEmpty()) {
			try {
				image.transferTo(new File(path.toString()));
				System.out.println("Image Saved in:" + path.toString());
			} catch (Exception e) {
				e.printStackTrace();
				System.out.println("Image not saved");
			}
		}

		if (product.getProdid() == 0) {
			productDAO.saveorUpdate(product);
			System.out.println("product added");
		} else {
			productDAO.saveorUpdate(product);
			System.out.println("product updated");
			return "AddProducts";
		}

		HttpSession session = request.getSession(false);
		String username = (String) session.getAttribute("LoggedInUser");
		model.addAttribute("message", "product added successfully");
		model.addAttribute("productList", productDAO.list());
		model.addAttribute("categoryList", categoryDAO.list());
		model.addAttribute("supplierList", supplierDAO.list());

		return "AddProducts";
	}

	@RequestMapping(value = "/editproducts{id}")
	public ModelAndView updateProduct(@PathVariable("id") String id, Model model) {
		int i = Integer.parseInt(id);
		model.addAttribute("product", productDAO.get(i));
		model.addAttribute("productList", productDAO.list());
		ModelAndView mv = new ModelAndView("AddProducts");
		return mv;
	}

	@RequestMapping(value = "/deleteproduct{id}")
	public ModelAndView deleteProduct(@PathVariable("id") String id, Model model) {
		int i = Integer.parseInt(id);
		Product product = productDAO.get(i);
		productDAO.delete(product);
		model.addAttribute("productList", productDAO.list());
		ModelAndView mv = new ModelAndView("AddProducts");
		mv.addObject("AddProducts", 0);
		return mv;
	}

	@RequestMapping("/Products")
	public String showDetails(Model mp) {
		return "Products";
	}

	/*
	 * @RequestMapping("/catproducts") public @ResponseBody List<Product>
	 * allProducts(@PathVariable int id) {
	 * 
	 * return productDAO.getCatProducts(id); }
	 */

	@RequestMapping("/{id}/ViewDetails")
	public String showDetails(@PathVariable Integer id, ModelMap model) {

		model.addAttribute("product", productDAO.get(id));

		return "ViewDetails";

	}

	@RequestMapping("/allproducts")
	public @ResponseBody List<Product> productsall() {
		System.out.println("inside products all");
		return productDAO.list();

	}

	@RequestMapping("/{id}/addcart")
	public String addCart(@PathVariable Integer id, Principal principal) {
		     User user=userDAO.get(principal.getName());
		     user.setCpassword(user.getPassword());
		     Product product=productDAO.get(id);
		     Cart cart=cartDAO.getCartWithUserId(user.getUserid());
		     if(cart!=null)
		     {
		    	 cart.setUsers(user);
		    		
		    	CartItem cartItem=cartItemDAO.getExistingCartItemCount(id, cart.getCartid());
		    	if(cartItem!=null)
		    	{
		    		cartItem.setCart(cart);
		    		cartItem.setGrandtotal(cartItem.getGrandtotal()+product.getPrice());
		    		cartItem.setQty(cartItem.getQty()+1);
		    		cartItemDAO.updateCartItem(cartItem);
		    	}
		    	else{
		    		cartItem=new CartItem();
		    		cartItem.setCart(cart);
		    		cartItem.setGrandtotal(product.getPrice());
		    		cartItem.setProduct(product);
		    		cartItem.setQty(1);
		    		cartItemDAO.addCartItem(cartItem);
		    	}
		    	cart.setGrandtotal(cart.getGrandtotal()+product.getPrice());
		    	cart.setQty(cart.getQty()+1);
		    	List<CartItem> cartItems=cart.getCartitems();
		    	cartItems.add(cartItem);
		    	cart.setCartitems(cartItems);
		    	cartDAO.updateCart(cart);
		    	
		     }else
		     {
		    	 cart=new Cart();
		    	 cart.setGrandtotal(product.getPrice());
		    	 cart.setQty(1);
		    	 cart.setUsers(user);
		    	 CartItem cartItem=new CartItem();
		    	 cartItem.setCart(cart);
		    	 cartItem.setGrandtotal(product.getPrice());
		    	 cartItem.setProduct(product);
		    	 cartItem.setQty(1);
		    	 
		    	 cartDAO.addCart(cart);
		    	 cartItemDAO.addCartItem(cartItem);
		     }
		return "Products";
	}

}
