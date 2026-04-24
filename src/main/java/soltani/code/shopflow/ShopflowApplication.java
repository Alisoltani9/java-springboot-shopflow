package soltani.code.shopflow;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

@SpringBootApplication
@EnableCaching
public class ShopflowApplication {

	public static void main(String[] args) {
		SpringApplication.run(ShopflowApplication.class, args);
	}

}
