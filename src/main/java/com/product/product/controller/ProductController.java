package com.product.product.controller;

import com.product.product.dto.FakeStoreApiDto;
import com.product.product.dto.GenericProductDto;
import com.product.product.exceptions.ProductNotFoundException;
import com.product.product.service.ProductService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/products")
public class ProductController {

    ProductService productService;

    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping("/{id}")
    public GenericProductDto getProductById(@PathVariable("id") long id) throws ProductNotFoundException {
        return productService.getProductById(id);
    }
    @GetMapping("")
    public FakeStoreApiDto[] getAllProducts() {
        return productService.getAllProducts();
    }
    @PostMapping("")
    public FakeStoreApiDto addProduct(@RequestBody FakeStoreApiDto fakeStoreApiDto) {
        return productService.addProduct(fakeStoreApiDto);
    }
    @DeleteMapping("/{id}")
    public FakeStoreApiDto deleteProductById(@PathVariable("id") long id) {
        return productService.deleteProductById(id);
    }
    @PutMapping("/{id}")
    public GenericProductDto updateProduct(@PathVariable("id") long id, @RequestBody FakeStoreApiDto fakeStoreApiDto) {
        return productService.updateProduct(id, fakeStoreApiDto);
    }
}
