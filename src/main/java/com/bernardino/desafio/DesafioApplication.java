package com.bernardino.desafio;

import java.math.BigInteger;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.bernardino.desafio.domain.UniqueDigit;

@SpringBootApplication
public class DesafioApplication {

	public static void main(String[] args) {
		SpringApplication.run(DesafioApplication.class, args);

		var uniqueDigit = new UniqueDigit(BigInteger.valueOf(9875), 4).getResult();

		System.out.println("Response is" + uniqueDigit);
	}

}
