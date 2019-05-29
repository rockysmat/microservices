package com.kswaughs.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.oxm.jaxb.Jaxb2Marshaller;
import org.springframework.ws.client.core.WebServiceTemplate;

@Configuration
public class WebSvcClientConfig {
	
	@Value("${books.svc.url}")
	private String url;
	
	@Bean
	Jaxb2Marshaller jaxb2Marshaller() {
		Jaxb2Marshaller jaxb2Marshaller = new Jaxb2Marshaller();
        jaxb2Marshaller.setContextPath("com.kswaughs.services.booksvc");
        return jaxb2Marshaller;
	}
	
	@Bean
	public WebServiceTemplate webServiceTemplate() {
		WebServiceTemplate webServiceTemplate = new WebServiceTemplate();
        webServiceTemplate.setMarshaller(jaxb2Marshaller());
        webServiceTemplate.setUnmarshaller(jaxb2Marshaller());
        webServiceTemplate.setDefaultUri(url);
        return webServiceTemplate;
	}
}
