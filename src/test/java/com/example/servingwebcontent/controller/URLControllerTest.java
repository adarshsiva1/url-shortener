package com.example.servingwebcontent.controller;


import com.example.servingwebcontent.service.URLShortenerService;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.util.ReflectionTestUtils;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.ui.Model;

import java.util.List;

import static org.junit.Assert.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;

@WebMvcTest(controllers = URLController.class)
public class URLControllerTest {

    @Mock
    URLShortenerService urlShortenerService;

    @Mock
    Model model;

    @InjectMocks
    private URLController controller;

    private MockMvc mvc;

    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);
        mvc = MockMvcBuilders.standaloneSetup(controller).setMessageConverters().build();
    }

    @Test
    public void testHomePage() throws Exception {
        mvc.perform(MockMvcRequestBuilders.get("/urlshortener").contentType(MediaType.APPLICATION_XHTML_XML_VALUE)
        ).andExpect(MockMvcResultMatchers.status().isOk());
    }

    @Test
    public void testPostURL() throws Exception {
        Mockito.when(urlShortenerService.addUrl(anyString())).thenReturn("1");
        MvcResult result = mvc.perform(MockMvcRequestBuilders.post("/urlshortener")
                .param("inputURL", "http://apple.com")
        ).andReturn();
        assertTrue(result.getModelAndView().getViewName().contains("url-added-successfully"));
    }

    @Test
    public void testAddedPage() throws Exception {
        MvcResult result = mvc.perform(MockMvcRequestBuilders.get("/url-added-successfully")
                .param("url", "1")
        ).andReturn();
        assertTrue(result.getModelAndView().getViewName().contains("url-success-added"));
    }

    @Test
    public void testStringConcatenation() throws Exception {
        controller = new URLController();
        String url = "101";
        controller.urlAddedSuccess(model, url);
        Mockito.verify(model).addAttribute("url", "http://localhost:8080/u?url=101");
    }

    @Test
    public void testGetRedirect() throws Exception {
        Mockito.when(urlShortenerService.getURL("1")).thenReturn("http://apple.com");
        MvcResult result = mvc.perform(MockMvcRequestBuilders.get("/u")
                .param("url", "1")
        ).andReturn();
        assertTrue(result.getModelAndView().getViewName().contains("redirect:http://apple.com"));
    }

}
