package it.peluso.ecommerce.repository;

import java.util.List;
import org.springframework.data.mongodb.repository.MongoRepository;
import it.peluso.ecommerce.model.Product;

public interface ProductRepository extends MongoRepository<Product, String> {
	  List<Product> findByNome(String nome);
	  List<Product> findByDescrizione(String descrizione);
}
