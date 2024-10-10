package com.scaler.suman.ProductCatalogService1.client;
import com.scaler.suman.ProductCatalogService1.dtos.FakeStoreProductDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RequestCallback;
import org.springframework.web.client.ResponseExtractor;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;
import java.util.ArrayList;
import java.util.List;
@Component
public class FakeStoreAPIClient {
    @Autowired
    private RestTemplateBuilder restTemplateBuilder;

    public FakeStoreProductDto getProductById(Long id) {
        ResponseEntity<FakeStoreProductDto> fakeStoreProductDtoResponseEntity = requestForEntity(HttpMethod.GET,"https://fakestoreapi.com/products/{id}",null, FakeStoreProductDto.class,id);
        if(fakeStoreProductDtoResponseEntity.getBody()!=null &&
                fakeStoreProductDtoResponseEntity.getStatusCode().equals(HttpStatus.OK))
        {
            return fakeStoreProductDtoResponseEntity.getBody();
        }
        return null;
    }

    public List<FakeStoreProductDto> getAllProducts(){
        List<FakeStoreProductDto> fakeStoreProductDtos = new ArrayList<>();
        RestTemplate restTemplate = restTemplateBuilder.build();
        FakeStoreProductDto[] fakeStoreProductDtosResponse =
                restTemplate.getForEntity( "https://fakestoreapi.com/products", FakeStoreProductDto[].class).getBody();
        for (FakeStoreProductDto fakeStoreProductDto: fakeStoreProductDtosResponse){
            fakeStoreProductDtos.add(fakeStoreProductDto);
        }
        return fakeStoreProductDtos;
    }

    public FakeStoreProductDto replaceProduct(Long id, FakeStoreProductDto fakeStoreProductDto){
        FakeStoreProductDto fakeStoreProductDtoReplaced = requestForEntity(HttpMethod.PUT,"https://fakestoreapi.com/products/{productId}",fakeStoreProductDto, FakeStoreProductDto.class, id).getBody();
        return fakeStoreProductDtoReplaced;
    }

    public FakeStoreProductDto createProduct(FakeStoreProductDto fakeStoreProductDto){
        FakeStoreProductDto fakeStoreProductDtoCreated = requestForEntity(HttpMethod.POST,"https://fakestoreapi.com/products",fakeStoreProductDto, FakeStoreProductDto.class).getBody();
        return fakeStoreProductDtoCreated;
    }


    public <T> ResponseEntity<T> requestForEntity(HttpMethod httpMethod, String url, @Nullable Object request,
                                                  Class<T> responseType, Object... uriVariables) throws RestClientException {
        RestTemplate restTemplate = restTemplateBuilder.build();
        RequestCallback requestCallback = restTemplate.httpEntityCallback(request, responseType);
        ResponseExtractor<ResponseEntity<T>> responseExtractor = restTemplate.responseEntityExtractor(responseType);
        return restTemplate.execute(url, httpMethod, requestCallback, responseExtractor, uriVariables);
    }

}
