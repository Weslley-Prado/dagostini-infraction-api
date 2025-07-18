package br.com.grupodagostini.dagostini_infraction_api;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

import java.util.Arrays;

@SpringBootApplication
public class DagostiniInfractionApiApplication {

    public static void main(String[] args) {
        ConfigurableApplicationContext context = SpringApplication.run(DagostiniInfractionApiApplication.class, args);

        // Verificar se os beans relevantes estão sendo carregados
        System.out.println("\n=== BEANS RELEVANTES ===");
        Arrays.stream(context.getBeanDefinitionNames())
                .filter(name -> name.contains("UseCase") || name.contains("Mapper") || name.contains("MapperImpl"))
                .sorted()
                .forEach(System.out::println);
        System.out.println("=========================\n");
    }
}