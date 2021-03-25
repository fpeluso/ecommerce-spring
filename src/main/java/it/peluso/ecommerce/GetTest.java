package it.peluso.ecommerce;

import it.peluso.ecommerce.dto.ProductDTO;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/")
public class GetTest {

    @GetMapping
    public ResponseEntity<String> helloWorld(@RequestParam(required = false) String nome) {
        return new ResponseEntity<>("Hello " + (nome != null ? nome : "world") + "!", HttpStatus.OK);
    }
}
