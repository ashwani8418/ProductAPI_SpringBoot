package dev.ashwani.productservice.services;

import com.github.dockerjava.api.exception.NotFoundException;
import dev.ashwani.productservice.dto.GenericProductDto;

import java.util.List;

public interface ProductService {
    GenericProductDto getProductById(Long id) throws NotFoundException;
    GenericProductDto createProduct(GenericProductDto productDto);

    List<GenericProductDto> getAllProducts();
    GenericProductDto deleteProductById(Long id);
}
