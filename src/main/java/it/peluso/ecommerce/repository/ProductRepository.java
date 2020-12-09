package it.peluso.ecommerce.repository;

import java.util.List;
import org.springframework.data.mongodb.repository.MongoRepository;
import it.peluso.ecommerce.dto.ProductDTO;

public interface ProductRepository extends MongoRepository<ProductDTO, String> {
	  List<ProductDTO> findByName(String name);
	  List<ProductDTO> findByDescription(String description);
}
