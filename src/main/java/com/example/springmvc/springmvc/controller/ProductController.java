package com.example.springmvc.springmvc.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.example.springmvc.springmvc.model.Product;
import com.example.springmvc.springmvc.repository.ProductRepository;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@Controller
@Api(value="ProductsContollerAPI", produces=MediaType.APPLICATION_JSON_VALUE)
public class ProductController {
	
	private ProductRepository productRepository;
	
	@Autowired
	public void setProductRepository(ProductRepository productRepository) {
		this.productRepository = productRepository;
	}

	@RequestMapping(path = "/")
	public String index() {
		return "index";
	}
	
	@RequestMapping(path = "/products/add", method = RequestMethod.GET)
	@ApiOperation("Gets the edit page for the product")
	public String createProduct(Model model) {
		model.addAttribute("product", new Product());
		return "edit";
	}
	
	@RequestMapping(path = "products", method = RequestMethod.POST)
	public String saveProduct(Product product) {
		productRepository.save(product);
		return "redirect:/";
	}
	
	@RequestMapping(path = "/products", method = RequestMethod.GET)
	@ApiOperation("Shows all the products")
	public String getAllProducts(Model model) {
		model.addAttribute("products", productRepository.findAll());
		return "products";
	}
	
	@RequestMapping(path = "/products/edit/{id}", method = RequestMethod.GET)
	@ApiOperation("Returns the edit page for a particular product")
	public String editProduct(Model model, @PathVariable(value = "id") String id) {
		model.addAttribute("product", productRepository.findById(id));
		return "edit";
	}
	
	@RequestMapping(path = "/products/delete/{id}", method = RequestMethod.GET)
	@ApiOperation("Deletes the product with the given id")
	public String deleteProduct(@PathVariable(name="id")String id) {
		productRepository.deleteById(id);
		return "redirect:/products";
	}
}
