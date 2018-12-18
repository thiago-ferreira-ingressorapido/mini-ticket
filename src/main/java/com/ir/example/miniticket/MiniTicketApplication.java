package com.ir.example.miniticket;

import com.google.common.base.Strings;
import com.ir.example.miniticket.config.AppPropertiesArchaius;
import com.ir.example.miniticket.config.AppPropertiesProvider;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan({"com.ir.example.miniticket"})
public class MiniTicketApplication {

    static {
        System.setProperty("archaius.configurationSource.defaultFileName","application.properties") ;
        AppPropertiesProvider.initialize(new AppPropertiesArchaius());

    }

	public static void main(String[] args) {
		SpringApplication.run(MiniTicketApplication.class, args);
	}
}
