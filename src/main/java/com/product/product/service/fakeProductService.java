package com.product.product.service;

import com.product.product.dto.FakeStoreApiDto;
import com.product.product.dto.GenericProductDto;
import com.product.product.exceptions.ProductNotFoundException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class fakeProductService implements ProductService {
    @Value("${FakeStoreApi.Url}")
    private String apiUrl;
    @Value("${FakeStoreApi.Path}")
    private  String apiEndPoint;

    String specificApiPath = "https://fakestoreapi.com/products/{id}";

    String commonApiPath = "https://fakestoreapi.com/products/";
    RestTemplateBuilder restTemplateBuilder;

    public fakeProductService(RestTemplateBuilder restTemplateBuilder) {
        this.restTemplateBuilder = restTemplateBuilder;
    }
    @Override
    public GenericProductDto getProductById(long id) throws ProductNotFoundException {
        RestTemplate restTemplate = restTemplateBuilder.build();
        String completeUrl = apiUrl + apiEndPoint + "{id}";
        ResponseEntity<FakeStoreApiDto> responseEntity = restTemplate.getForEntity(completeUrl, FakeStoreApiDto.class, id);
        if (responseEntity.getBody() == null) {
            throw new ProductNotFoundException("Given Product Id is not valid");
        }
        return addGenericDetail(responseEntity.getBody());
    }

    @Override
    public FakeStoreApiDto[] getAllProducts() {
        RestTemplate restTemplate = restTemplateBuilder.build();
        String completeUrl = apiUrl + apiEndPoint;
        ResponseEntity<FakeStoreApiDto[]> responseEntity = restTemplate.getForEntity(completeUrl, FakeStoreApiDto[].class);
        FakeStoreApiDto[] fakeStoreApiDto = responseEntity.getBody();
        return fakeStoreApiDto;
    }

    @Override
    public FakeStoreApiDto addProduct(FakeStoreApiDto fakeStoreApiDto) {
        RestTemplate restTemplate = restTemplateBuilder.build();
        String completeUrl = apiUrl + apiEndPoint;
        ResponseEntity<FakeStoreApiDto> responseEntity = restTemplate.postForEntity(completeUrl, fakeStoreApiDto, FakeStoreApiDto.class);
        FakeStoreApiDto fakeStoreApiDto1 = responseEntity.getBody();
        return fakeStoreApiDto1;
    }
    @Override
    public FakeStoreApiDto deleteProductById(long id) {
        RestTemplate restTemplate = restTemplateBuilder.build();
        String completeUrl = apiUrl + apiEndPoint + "{id}";
        ResponseEntity<FakeStoreApiDto> responseEntity = restTemplate.getForEntity(completeUrl, FakeStoreApiDto.class, id);
        return responseEntity.getBody();
    }

    public GenericProductDto addGenericDetail(FakeStoreApiDto fakeStoreApiDto) {
        GenericProductDto genericProductDto = new GenericProductDto();
        genericProductDto.setId(fakeStoreApiDto.getId());
        genericProductDto.setTitle(fakeStoreApiDto.getTitle());
        genericProductDto.setPrice(fakeStoreApiDto.getPrice());
        genericProductDto.setDescription(fakeStoreApiDto.getDescription());
        genericProductDto.setImage(fakeStoreApiDto.getImage());

        return genericProductDto;
    }

    @Override
    public GenericProductDto updateProduct(long id, FakeStoreApiDto fakeStoreApiDto) {
        RestTemplate restTemplate = restTemplateBuilder.build();
        String completeUrl = apiUrl + apiEndPoint + "{id}";
        ResponseEntity<FakeStoreApiDto> responseEntity = restTemplate.exchange(
                completeUrl,
                HttpMethod.PUT,
                new HttpEntity<>(fakeStoreApiDto),
                FakeStoreApiDto.class,
                id
        );
        return addGenericDetail(responseEntity.getBody());
    }
}
