package com.deepseekr1.spring.service;

import com.example.sentimentanalysis.config.DeepSeekConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;
import java.util.Map;

@Service
public class DeepSeekService {

    @Autowired
    private DeepSeekConfig deepSeekConfig;

    @Autowired
    private RestTemplate restTemplate;

    public String analyzeSentiment(String text) {
        String url = deepSeekConfig.getApiUrl();

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.set("Authorization", "Bearer " + deepSeekConfig.getApiKey());

        Map<String, String> requestBody = new HashMap<>();
        requestBody.put("text", text);

        HttpEntity<Map<String, String>> request = new HttpEntity<>(requestBody, headers);

        ResponseEntity<String> response = restTemplate.postForEntity(url, request, String.class);

        if (response.getStatusCode() == HttpStatus.OK) {
            return response.getBody();
        } else {
            throw new RuntimeException("Failed to analyze sentiment: " + response.getStatusCode());
        }
    }
}