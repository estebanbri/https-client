package com.example.sslclient;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.client.RestTemplate;

@SpringBootApplication
public class SslClientApplication implements CommandLineRunner {

	@Autowired
	private RestTemplate restTemplate;

	public static void main(String[] args) {
		SpringApplication.run(SslClientApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {

		String result = restTemplate.getForObject("https://localhost:8443/", String.class);
		System.out.println(result);

	}
}
