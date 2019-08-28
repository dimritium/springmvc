package com.example.springmvc.springmvc.boot;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import com.example.springmvc.springmvc.model.Product;
import com.example.springmvc.springmvc.repository.ProductRepository;

@Component
public class DataLoader implements CommandLineRunner{

	
	private ProductRepository productRepository;
	
	@Autowired
	public void setProductRepository(ProductRepository productRepository) {
		this.productRepository = productRepository;
	}

	@Override
	public void run(String... args) throws Exception {
		
		Product p1 = new Product();
		p1.setName("Milky bar");
		p1.setDescription("Chocolate and hazlenut");
		p1.setType("CANDIES");
		p1.setCategory("BARS");
		p1.setPrice(1.99);
		
		productRepository.save(p1);
		
		Product p2 = new Product();
		p2.setName("Almond bar");
		p2.setDescription("Choco");
		p2.setType("CANDIES");
		p2.setCategory("BARS");
		p2.setPrice(2.99);
		
		productRepository.save(p2);
	}
	
}
