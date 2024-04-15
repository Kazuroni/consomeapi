package br.edu.fatec.emailmkt;

import br.edu.fatec.emailmkt.services.consomeapi;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.ArrayList;
import java.util.List;

@SpringBootApplication
public class EmailMktApplication implements CommandLineRunner {

	public static void main(String[] args) {
		SpringApplication.run(EmailMktApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		consomeapi lerapi = new consomeapi();
		String dados = lerapi.lerjson("https://api.escuelajs.co/api/v1/products");
		ObjectMapper objMapper = new ObjectMapper();
		JsonNode jsonNode = objMapper.readTree(dados);
		List<String> emailList = new ArrayList<>();

		jsonNode.forEach(node -> {
			double price = node.get("price").asDouble();
			if (price < 30) {
				String name = node.get("title").asText().toUpperCase();
				emailList.add(name);
			}
		});

		emailList.forEach(System.out::println);
		System.out.println("Total de produtos imperdíveis: " + emailList.size());
	}
}
