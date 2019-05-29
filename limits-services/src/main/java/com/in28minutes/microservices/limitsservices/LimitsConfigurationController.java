package com.in28minutes.microservices.limitsservices;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.in28minutes.microservices.limitsservices.bean.LimitsConfiguration;

@RestController
public class LimitsConfigurationController {
	
	@Autowired
	private Configuration configuration;
	
	@GetMapping("/limits")
	public LimitsConfiguration retrieveLimitsFromConfigurations() {
		return new LimitsConfiguration(configuration.getMax(), configuration.getMin());
	}
}
