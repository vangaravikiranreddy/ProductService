package com.product.product.service;

import com.product.product.dto.FakeStoreApiDto;
import com.product.product.dto.GenericProductDto;
import com.product.product.exceptions.ProductNotFoundException;

public interface ProductService {
    GenericProductDto getProductById(long id) throws ProductNotFoundException;
    FakeStoreApiDto[] getAllProducts();

    FakeStoreApiDto addProduct(FakeStoreApiDto fakeStoreApiDto);
    FakeStoreApiDto deleteProductById(long id);
    GenericProductDto updateProduct(long id, FakeStoreApiDto fakeStoreApiDto);
}
