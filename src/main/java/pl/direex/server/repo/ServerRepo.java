package pl.direex.server.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.direex.server.model.Server;

public interface ServerRepo extends JpaRepository<Server, Long> {
    // JpaRepository jest interfejsem dostarczanym przez Spring Data JPA, który dostarcza zestaw gotowych metod do wykonywania operacji CRUD (Create, Read, Update, Delete) na encjach (tabelach) w bazie danych. Ten interfejs jest częścią Spring Data JPA, które ułatwia interakcję z bazami danych przy użyciu JPA (Java Persistence API).
    // <Server, Long> -Server  domena lub klasa którą chcemy zarządzać, Long - typ klucza głównego który podaliśmy w klasie Server.

    Server findByIpAddress(String ipAddress); // zwróci Server. findBy.. JPA automatycznie sprawdzi co ma wyszukać na podstawie ip.

    Server findByName(String name); // zwróci Server na podstawie name.
}
