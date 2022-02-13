package com.example.servingwebcontent.service;

import org.junit.Test;
import static org.junit.Assert.assertTrue;

public class URLShortenerServiceTest {

    private URLShortenerService urlShortenerService = new URLShortenerService();

    @Test
    public void testAddURL() {
        String returnedValue = urlShortenerService.addUrl("http://yahoo.com");
        assertTrue(returnedValue.equals("1"));
    }

    @Test
    public void testAddAnotherURL() {
        String returnedValue = urlShortenerService.addUrl("http://apple.com");
        assertTrue(returnedValue.equals("2"));
    }

    @Test
    public void getURL() {
        urlShortenerService.addUrl("http://yahoo.com");
        urlShortenerService.addUrl("http://apple.com");
        String returnedValue = urlShortenerService.getURL("2");
        assertTrue(returnedValue.equals("http://apple.com"));
    }

    @Test
    public void addSameURLTwice() {
        String returnedValue = urlShortenerService.addUrl("http://yahoo.com");
        assertTrue(returnedValue.equals("1"));
    }
}
