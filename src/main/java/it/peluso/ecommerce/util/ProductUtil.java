package it.peluso.ecommerce.util;

import it.peluso.ecommerce.dto.ProductDTO;
import org.springframework.stereotype.Component;

@Component
public final class ProductUtil {

    public static boolean isProductDataValid(ProductDTO product) {
        return (
                product.getName() != null &&
                product.getDescription() != null &&
                product.getPrice() != null)
                ;
    }

    public static boolean productHasQuantity(ProductDTO product) {
        return product.getQuantity() != null && product.getQuantity() > 0;
    }
}

