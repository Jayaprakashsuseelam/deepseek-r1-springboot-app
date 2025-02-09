package com.deepseekr1.spring.controller;

import com.example.sentimentanalysis.service.DeepSeekService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/sentiment")
public class SentimentAnalysisController {

    @Autowired
    private DeepSeekService deepSeekService;

    @PostMapping("/analyze")
    public String analyzeSentiment(@RequestBody String text) {
        return deepSeekService.analyzeSentiment(text);
    }
}