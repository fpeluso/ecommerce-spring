package it.peluso.ecommerce.controller;

import java.util.List;
import java.util.Optional;

import it.peluso.ecommerce.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import it.peluso.ecommerce.dto.ProductDTO;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/api/v1/products")
public class ProductController {

	@Autowired
	ProductService productService;
	
	@GetMapping
	public ResponseEntity<List<ProductDTO>> getAllProducts(@RequestParam(required = false) String nome) {
		return productService.getAllProducts(nome);
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<ProductDTO> getProductById(@PathVariable("id") String id) {
		return productService.getProductById(id);
	}

	@PostMapping
	public ResponseEntity<ProductDTO> createProduct(@RequestBody ProductDTO product) {
		return productService.createProduct(product);
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<ProductDTO> updateProduct(@PathVariable("id") String id, @RequestBody ProductDTO product) {
		return productService.updateProduct(id, product);
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<HttpStatus> deleteProduct(@PathVariable("id") String id) {
		return productService.deleteProduct(id);
	}
	
//	@DeleteMapping
//	public ResponseEntity<HttpStatus> deleteAllProducts() {
//		try {
//		    productRepository.deleteAll();
//		    return new ResponseEntity<>(HttpStatus.NO_CONTENT);
//		  } catch (Exception e) {
//		    return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
//		  }
//	}
	
}
