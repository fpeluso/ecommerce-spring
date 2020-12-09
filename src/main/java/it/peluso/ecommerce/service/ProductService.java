package it.peluso.ecommerce.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import it.peluso.ecommerce.exception.DataValidationException;
import it.peluso.ecommerce.util.ProductUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

import it.peluso.ecommerce.dto.ProductDTO;
import it.peluso.ecommerce.repository.ProductRepository;

@Service
public class ProductService {

    @Autowired
    ProductRepository productRepository;

    public ResponseEntity<List<ProductDTO>> getAllProducts(String name) {
        try {
            List<ProductDTO> products = new ArrayList<ProductDTO>();

            if (name == null)
                products.addAll(productRepository.findAll());
            else
                products.addAll(productRepository.findByName(name));

            if (products.isEmpty()) {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }

            return new ResponseEntity<>(products, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    public ResponseEntity<ProductDTO> getProductById(String id) {
        Optional<ProductDTO> productData = productRepository.findById(id);

        return productData.map(productDTO -> new ResponseEntity<>(productDTO, HttpStatus.OK)).orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    public ResponseEntity<ProductDTO> createProduct(ProductDTO product) {
        try {
            ProductDTO newProduct;
            if(ProductUtil.isProductDataValid(product)) {
                if(ProductUtil.productHasQuantity(product)) {
                    newProduct = new ProductDTO(product.getName(), product.getDescription(), product.getPrice(), product.getQuantity());
                } else {
                    newProduct = new ProductDTO(product.getName(), product.getDescription(), product.getPrice());
                }
                ProductDTO _product = productRepository.save(newProduct);
                return new ResponseEntity<>(_product, HttpStatus.CREATED);
            } else {
                throw new DataValidationException("Invalid data");
            }
        } catch (DataValidationException dve) {
            return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
        }catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    public ResponseEntity<ProductDTO> updateProduct(String id, ProductDTO product) {
        Optional<ProductDTO> productData = productRepository.findById(id);
        //TODO: validate data
        if (productData.isPresent()) {
            ProductDTO _product = productData.get();
            _product.setName(product.getName());
            _product.setDescription(product.getDescription());
            _product.setPrice(product.getPrice());
            if(product.getQuantity() != null) {
                _product.setQuantity(product.getQuantity());
            }
            return new ResponseEntity<>(productRepository.save(_product), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    public ResponseEntity<HttpStatus> deleteProduct(String id) {
        try {
            productRepository.deleteById(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
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
