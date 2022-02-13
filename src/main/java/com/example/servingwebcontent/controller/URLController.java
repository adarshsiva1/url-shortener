package com.example.servingwebcontent.controller;

import com.example.servingwebcontent.service.URLShortenerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;


import javax.servlet.http.HttpServletRequest;
import java.util.UUID;

import com.example.servingwebcontent.model.*;


@Controller
public class URLController {

	@Autowired
	private URLShortenerService urlShortenerService;

	@GetMapping("/urlshortener")
	public String welcome(@RequestParam(name="name", required=false, defaultValue="World") String name, Model model) {
		model.addAttribute("name", name);
		UrlModel urlModel = new UrlModel();
		//userSessionId variable to record concurrent sessions for logging
		urlModel.setUserSessionId(UUID.randomUUID().toString());
		model.addAttribute("urlModel", urlModel);
		return "url";
	}

	@PostMapping("/urlshortener")
	public String urlShortener(HttpServletRequest request, @ModelAttribute UrlModel urlModel) {
		String shortenedWebpage = urlShortenerService.addUrl(urlModel.getInputURL());
		return "redirect:" + "/url-added-successfully" + "?url=" + shortenedWebpage;
	}

	@GetMapping("url-added-successfully")
	public String urlAddedSuccess(Model model,
								  @RequestParam("url") String url) {
		model.addAttribute("url", "http://localhost:8080/u?url=" + url);
		return "url-success-added";
	}

	@GetMapping("/u")
	public String redirect(Model model,
								  @RequestParam("url") String url) {
		return "redirect:" + urlShortenerService.getURL(url);
	}

}
