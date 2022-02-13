package com.example.servingwebcontent.service;

import org.springframework.stereotype.Component;

import java.util.Map;
import java.util.HashMap;

@Component
public class URLShortenerService {

    static int requestNumber = 0;
    static Map<String, String> shortToLongMapper = new HashMap<>();
    static Map<String, String> longToShortMapper = new HashMap<>();

    //make method synchronized
    public synchronized String addUrl(String fullUrl) {
        if (longToShortMapper.containsKey(fullUrl)) {
            return longToShortMapper.get(fullUrl);
        }
        String shortenedUrl;
        requestNumber++;
        shortenedUrl = String.valueOf(requestNumber);
        shortToLongMapper.put(shortenedUrl,fullUrl);
        longToShortMapper.put(fullUrl, shortenedUrl);
        return shortenedUrl;
    }

    public String getURL(String shortURL) {
        return shortToLongMapper.get(shortURL);
    }
}
