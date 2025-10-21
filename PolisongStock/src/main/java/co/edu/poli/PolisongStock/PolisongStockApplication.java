package co.edu.poli.PolisongStock;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class PolisongStockApplication {

	public static void main(String[] args) {
		System.out.println("Inicio del servidor");
		SpringApplication.run(PolisongStockApplication.class, args);
		System.out.println("Servidor corre con exito!");
	}

}
