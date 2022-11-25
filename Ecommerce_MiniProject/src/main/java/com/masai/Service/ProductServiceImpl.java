package com.masai.Service;


import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.masai.Exception.CategoryNotFoundException;
import com.masai.Exception.ProductNotFoundException;
import com.masai.Exception.UserException;
import com.masai.Model.Category;
import com.masai.Model.CurrentAdminSession;
import com.masai.Model.Product;
import com.masai.Repository.AdminSessionRepository;
import com.masai.Repository.CategoryRepository;
import com.masai.Repository.ProductRepository;

@Service
public class ProductServiceImpl implements ProductService{
	
	@Autowired
	private ProductRepository prodRepo;
	
	@Autowired
	private CategoryRepository  catRepo;
	
	@Autowired
    private AdminSessionRepository adminRepo;
	
	
	@Override
	public Product addProduct(Product product,String key,Integer categoryId) throws CategoryNotFoundException, UserException {
	  CurrentAdminSession existAdmin	= adminRepo.findByUuid(key);
	  if(existAdmin==null) throw new UserException("please Admin log in first");
		Category existCat =catRepo.findByCatId(categoryId);
		if(existCat==null) throw new CategoryNotFoundException("Please add the category first");
		 product.setCategory(catRepo.findByCatId(categoryId));
		return prodRepo.save(product);
	}


	@Override
	public Product ViewProductById(Integer prodId) {
		// TODO Auto-generated method stub
		return null;
	}


	@Override
	public List<Product> ViewProductByName(String prodName) {
		// TODO Auto-generated method stub
		return null;
	}


	@Override
	public List<Product> ViewAllProduct() throws ProductNotFoundException {
		List<Product> podList =  prodRepo.findAll();
		if(podList.size()==0) throw new ProductNotFoundException("No product found");
		return podList;
	}


	@Override
	public Product addQuntityOfProduct(Integer prodId, Integer qty) throws ProductNotFoundException {
	  Optional<Product> productOPT	=prodRepo.findById(prodId);
	  if(!productOPT.isPresent()) throw new ProductNotFoundException("No product found");
	  Product product =  productOPT.get();
	  product.setProdQty(product.getProdQty()+qty);
	  prodRepo.save(product);
		return product;
	}


	@Override
	public Product removeQuntityOfProduct(Integer prodId, Integer qty) throws ProductNotFoundException {
		 Optional<Product> productOPT	=prodRepo.findById(prodId);
		  if(!productOPT.isPresent()) throw new ProductNotFoundException("No product found");
		  Product product =  productOPT.get();
		  product.setProdQty(product.getProdQty()-qty);
		  prodRepo.save(product);
			return product;
	}

}
