package com.watchesfront.controller;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.niit.backend.dao.SupplierDAO;
import com.niit.backend.model.Supplier;

@Controller

public class SupplierController {
	
	@Autowired
	
	private SupplierDAO supplierDAO;
	


@RequestMapping("/AddSupplier")
public ModelAndView showSupplier()
{
	ModelAndView mv= new ModelAndView("AddSupplier");
	mv.addObject("supplierList", supplierDAO.list());
	mv.addObject("supplier",new Supplier());
	return mv;
}

@RequestMapping(value="/addsup",method=RequestMethod.POST)

public String addsup(@Valid @ModelAttribute("supplier")Supplier supplier, BindingResult result, Model model,HttpServletRequest request) throws IOException
{
model.addAttribute("supplier",new Supplier());
	if(supplier.getSupid()==0)
	{
		supplierDAO.saveorUpdate(supplier);
		System.out.println("supplier added");
	}
	else
	{
		supplierDAO.saveorUpdate(supplier);
		System.out.println("supplier updated");
	}
	return "redirect:/AddSupplier";
}


@RequestMapping(value="/editsupplier{id}")
public ModelAndView updateSupplier(@PathVariable("id")String id,Model model)
{
int i=Integer.parseInt(id);
model.addAttribute("supplier", supplierDAO.get(i));
model.addAttribute("supplierList", supplierDAO.list());
ModelAndView mv=new ModelAndView("AddSupplier");
return mv;
}


@RequestMapping(value="/deletesupplier{id}")
public ModelAndView deleteSupplier(@PathVariable("id")String id,Model model)
{
int i=Integer.parseInt(id);
Supplier supplier= supplierDAO.get(i);
supplierDAO.delete(supplier);
model.addAttribute("supplierList", supplierDAO.list());
ModelAndView mv=new ModelAndView("AddSupplier");
mv.addObject("AddSupplier", 0);
return mv;
}


}
