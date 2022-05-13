package com.in28minutes.microservices.currencyconversionservice;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;



@FeignClient(name="currency-exchange", url="localhost:8000")
public interface CurrencyExchangeProxy {
	
// the signature is copied from the currency-exchange controller
// just changed the return object to CurrencyConversion
	@GetMapping("currency-exchange/from/{from}/to/{to}")
	public CurrencyConversion retrieveExchangeValue (
			@PathVariable String from, @PathVariable String to);

}
