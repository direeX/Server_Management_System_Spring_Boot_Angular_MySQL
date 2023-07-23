package pl.direex.server;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import pl.direex.server.enumeration.Status;
import pl.direex.server.model.Server;
import pl.direex.server.repo.ServerRepo;

@SpringBootApplication
public class ServerApplication {

	public static void main(String[] args) {
		SpringApplication.run(ServerApplication.class, args);

	}

	@Bean
	CommandLineRunner run(ServerRepo serverRepo){
		return args -> {
			serverRepo.save(new Server(null, "192.168.1.160", "Ubuntu Linux", "16 GB", "Personal PC", "http://localhost:8080/server/image/server1.png", Status.SERVER_UP));
			serverRepo.save(new Server(null, "192.168.1.58", "Dell Tower", "16 GB", "Personal PC", "http://localhost:8080/server/image/server2.png", Status.SERVER_UP));
			serverRepo.save(new Server(null, "192.168.1.21", "Web Server", "32 GB", "Personal PC", "http://localhost:8080/server/image/server3.png", Status.SERVER_UP));
			serverRepo.save(new Server(null, "192.168.1.14", "Mail Server", "64 GB", "Personal PC", "http://localhost:8080/server/image/server4.png", Status.SERVER_UP));
		};
	}
/*
@Bean to adnotacja dostarczana przez Spring Framework w języku Java. Adnotacja @Bean jest używana w klasach konfiguracyjnych, czyli w klasach oznaczonych adnotacją @Configuration. Ta adnotacja wskazuje na to, że metoda, do której jest przypisana, jest odpowiedzialna za tworzenie i konfigurację beana - obiektu, który będzie zarządzany przez kontener Springa.
Kiedy metoda w klasie konfiguracyjnej jest oznaczona adnotacją @Bean, oznacza to, że ta metoda będzie tworzyć i zwracać instancję obiektu, który zostanie zarejestrowany w kontenerze Springa jako bean. Dzięki temu, w innych miejscach w aplikacji, gdzie wymagany jest ten bean, będzie można go wstrzyknąć i używać bezpośrednio.
 */

}
