package com.biancama.meoni;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;

@SpringBootApplication
public class ExportFattureApplication {

	public static void main(String[] args) {
		new SpringApplicationBuilder(ExportFattureApplication.class).web(false).run(args);
	}
}
