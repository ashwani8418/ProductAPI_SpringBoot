package dev.ashwani.productservice.controllers;

import dev.ashwani.productservice.dto.ExceptionDto;
import dev.ashwani.productservice.dto.GenericProductDto;
import dev.ashwani.productservice.exceptions.NotFoundException;
import dev.ashwani.productservice.services.ProductService;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/products")
public class ProductController {
//   { Doesn't follow in industry
//    @Autowired
//    private ProductService productService;}
    private ProductService productService;

//    Constructor Injection this method followed in industry
    public ProductController(@Qualifier("fakeStoreProductService") ProductService productService){
        this.productService = productService;
    }
    @PostMapping()
    public GenericProductDto createProduct(@RequestBody GenericProductDto product){
        return productService.createProduct(product);
    }

    @GetMapping()
    public List<GenericProductDto> getAllProducts(){
        return productService.getAllProducts();
    }

    @GetMapping("/{id}")
    public GenericProductDto getProductById(@PathVariable("id")  Long id) throws NotFoundException {
        return productService.getProductById(id);
    }

    @PutMapping("/{id}")
    public String updateProductById(@PathVariable("id") Long id){
        return "Updating product for id " + id;
    }

    @DeleteMapping("/{id}")
    public GenericProductDto deleteProductById(@PathVariable("id") Long id){

        return productService.deleteProductById(id);
    }

    @ExceptionHandler(NotFoundException.class)
    public ResponseEntity<ExceptionDto> handleNotFoundException(NotFoundException notFoundException){
        return new ResponseEntity(new ExceptionDto(HttpStatus.NOT_FOUND, notFoundException.getMessage()), HttpStatus.NOT_FOUND);
    }
}
