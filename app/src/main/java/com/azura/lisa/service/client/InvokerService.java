package com.azura.lisa.service.client;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Slf4j
@Component
public class InvokerService {

    @Autowired
    @Qualifier("appRestClient")
    private RestTemplate restTemplate;

    public <T> ResponseEntity<T> get(String url, Class<T> responseType){
        return this.restTemplate.getForEntity(url, responseType);
    }

    public <T> ResponseEntity<T> getWithHeader(String url, HttpEntity<String> entity, Class<T> responseType){
        return this.restTemplate.exchange(url,HttpMethod.GET ,entity,responseType);
    }

    public <T> ResponseEntity<T> post(String url, Object request, Class<T> responseType){
        try {
            return this.restTemplate.postForEntity(url, new HttpEntity<>(request), responseType);
        } catch (Exception e) {
            log.error("POST URL:{}, error:{}",url, e.getMessage());
        }
        return this.restTemplate.postForEntity(url, new HttpEntity<>(request), responseType);
    }

    public <T> ResponseEntity<T> postWithHeader(String url, HttpEntity<String> header, Class<T> responseType){
        return this.restTemplate.postForEntity(url, header , responseType);
    }
}
