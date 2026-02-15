package com.scaler.suman.ProductCatalogService1.client;

import com.scaler.suman.ProductCatalogService1.dtos.FakeStoreProductDto;

import jakarta.annotation.Nullable;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Component;
import org.springframework.web.client.HttpMessageConverterExtractor;
import org.springframework.web.client.RequestCallback;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@Component
public class FakeStoreAPIClient {

    private final RestTemplate restTemplate;
    private final String baseUrl = "https://fakestoreapi.com/products";

    @Autowired
    public FakeStoreAPIClient(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    @SuppressWarnings("null")
    public List<FakeStoreProductDto> getAllProducts() {
        HttpMethod httpMethod = HttpMethod.GET;
        return restTemplate.exchange(
            baseUrl,
            httpMethod,
            null,
            new ParameterizedTypeReference<List<FakeStoreProductDto>>() {}
        ).getBody();
    }

public FakeStoreProductDto getProductById(Long productId) {
        String url = baseUrl + "/" + productId;
        return restTemplate.getForObject(url, FakeStoreProductDto.class);
    }

    public FakeStoreProductDto createProduct(FakeStoreProductDto fakeStoreProductDto) {
        String url = baseUrl;
        return restTemplate.postForObject(url, fakeStoreProductDto, FakeStoreProductDto.class);
    }

    public FakeStoreProductDto replaceProduct(Long id, FakeStoreProductDto fakeStoreProductDto) throws RestClientException{
        FakeStoreProductDto fakeStoreProductDtoReplaced = requestForObject(HttpMethod.PUT,"https://fakestoreapi.com/products/{productId}",fakeStoreProductDto, FakeStoreProductDto.class, id);
        return fakeStoreProductDtoReplaced;
    }    
    @SuppressWarnings("null")
    private <T> T requestForObject(HttpMethod httpMethod, String url,  @Nullable Object request, Class<T> responseType, Object... uriVariables) throws RestClientException {
        RequestCallback requestCallback = restTemplate.httpEntityCallback(request, responseType);
        HttpMessageConverterExtractor<T> responseExtractor = new HttpMessageConverterExtractor(responseType, restTemplate.getMessageConverters());
        return restTemplate.execute(url, httpMethod, requestCallback, responseExtractor, (Object[])uriVariables);
    }    
}



// package com.scaler.suman.ProductCatalogService1.client;
// import com.scaler.suman.ProductCatalogService1.dtos.FakeStoreProductDto;
// import org.springframework.beans.factory.annotation.Autowired;
// import org.springframework.http.HttpMethod;
// import org.springframework.lang.Nullable;
// import org.springframework.stereotype.Component;
// import org.springframework.web.client.RequestCallback;
// import org.springframework.web.client.HttpMessageConverterExtractor;
// import org.springframework.web.client.RestClientException;
// import org.springframework.web.client.RestTemplate;

// @Component
// public class FakeStoreAPIClient {
//     @Autowired
//     private RestTemplate restTemplate;

//     public FakeStoreProductDto[] getAllProducts(){
//         String getAllProductURL = "https://fakestoreapi.com/products";
//         FakeStoreProductDto[] response = restTemplate.getForObject(getAllProductURL, FakeStoreProductDto[].class);
//         return response;
//     }

//     public FakeStoreProductDto getProductById(Long productId) {
//         String getProduct = "https://fakestoreapi.com/products/" + productId;
//         FakeStoreProductDto response = requestForObject(HttpMethod.GET, getProduct,null, FakeStoreProductDto.class);
//         return response;
//     }

//     public FakeStoreProductDto createProduct(FakeStoreProductDto fakeStoreProductDto) throws RestClientException {
//         FakeStoreProductDto fakeStoreProductDtoCreated = requestForObject(HttpMethod.POST, "https://fakestoreapi.com/products", fakeStoreProductDto, FakeStoreProductDto.class);
//         return fakeStoreProductDtoCreated;
//     }


//     public FakeStoreProductDto replaceProduct(Long id, FakeStoreProductDto fakeStoreProductDto) throws RestClientException{
//         FakeStoreProductDto fakeStoreProductDtoReplaced = requestForObject(HttpMethod.PUT,"https://fakestoreapi.com/products/{productId}",fakeStoreProductDto, FakeStoreProductDto.class, id);
//         return fakeStoreProductDtoReplaced;
//     }

//     private <T> T requestForObject(HttpMethod httpMethod, String url,  @Nullable Object request, Class<T> responseType, Object... uriVariables) throws RestClientException {
//         RequestCallback requestCallback = restTemplate.httpEntityCallback(request, responseType);
//         HttpMessageConverterExtractor<T> responseExtractor = new HttpMessageConverterExtractor(responseType, restTemplate.getMessageConverters());
//         return restTemplate.execute(url, httpMethod, requestCallback, responseExtractor, (Object[])uriVariables);
//     }


// }
