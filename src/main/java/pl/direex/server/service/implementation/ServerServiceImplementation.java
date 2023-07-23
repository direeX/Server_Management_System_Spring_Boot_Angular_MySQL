package pl.direex.server.service.implementation;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import pl.direex.server.enumeration.Status;
import pl.direex.server.model.Server;
import pl.direex.server.repo.ServerRepo;
import pl.direex.server.service.ServerService;

import java.io.IOException;
import java.net.InetAddress;
import java.util.Collection;
import java.util.Random;

import static java.lang.Boolean.TRUE;
//@RequiredArgsConstructor to adnotacja dostarczana przez bibliotekę Lombok w języku Java. Adnotacja ta automatycznie generuje konstruktor dla klas, który zawiera finalne pola (pola oznaczone jako final) lub pola oznaczone adnotacją @NonNull. Konstruktor ten pozwala na wstrzyknięcie wartości tych pól w momencie tworzenia obiektu.

// @Service, informuje to framework Spring, że ta klasa jest komponentem serwisu, który może być automatycznie zainicjowany, zarządzany i wstrzykiwany do innych klas, w których jest potrzebny.

//@Transactional, informuje to framework Spring, że metoda ta (lub każda metoda w oznaczonej klasie) powinna działać jako pojedyncza jednostka transakcyjna. To oznacza, że jeśli operacja zakończy się sukcesem, transakcja zostanie zatwierdzona (commit), co oznacza, że wszelkie zmiany wprowadzone w bazie danych w ramach tej transakcji zostaną trwale zapisane. Jeśli jednak metoda zakończy się niepowodzeniem lub zostanie zgłoszony wyjątek, transakcja zostanie wycofana (rollback), a wszystkie zmiany wprowadzone w ramach tej transakcji zostaną cofnięte.

// @Slf4j to adnotacja dostarczana przez bibliotekę Lombok w języku Java. Ta adnotacja automatycznie generuje kod dla logowania w klasie, co pozwala na łatwe korzystanie z mechanizmu logowania bez potrzeby pisania kodu loggera ręcznie.
@RequiredArgsConstructor
@Service
@Transactional
@Slf4j
public class ServerServiceImplementation implements ServerService {
//    klasa ServerServiceImplementation implementuje interfejs ServerService, musi nadpisać wszystkie metody.

    private final ServerRepo serverRepo; // pole typu ServerRepo z interfejsu

//@Override nadpisanie metod z interfejsu
    @Override
    public Server create(Server server) {
        log.info("Saving new server: {}", server.getName());  // jest przykładem użycia mechanizmu logowania do zapisania informacji o zapisie nowego serwera w dzienniku logów. Współpracuje z adnotacją @Slf4j dostarczaną przez Lombok.

        server.setImageUrl(setServerImageUrl());

        return serverRepo.save(server);
//      Metoda tworzy serwer, loguje do konsoli image url, zapisuje serwer do bazy danych i zwraca Server.
    }

    @Override
    public Server ping(String ipAddress) throws IOException {
        log.info("Pinging server IP: {}", ipAddress);
        Server server = serverRepo.findByIpAddress(ipAddress); // zwróci server po ip i przypisze do zmiennej server.
        InetAddress address = InetAddress.getByName(ipAddress);
// InetAddress jest klasą w języku Java, która reprezentuje adresy IP oraz nazwy hostów. Pozwala na przeprowadzenie operacji związanych z rozwiązywaniem nazw DNS na adresy IP i odwrotnie.
//W powyższym kodzie, ipAddress to zmienna przechowująca ciąg znaków reprezentujący adres IP, np. "192.168.1.1". Za pomocą statycznej metody getByName() klasy InetAddress, przekazujemy ten adres IP jako argument i otrzymujemy obiekt InetAddress, który reprezentuje ten adres IP.
        server.setStatus(address.isReachable(10000) ? Status.SERVER_UP : Status.SERVER_DOWN); // po zadanym czasie jeśli nie możemy dostać się do serwera, to kontynuuj wykonywanie programu i ustaw status na UP lub DOWN.
        serverRepo.save(server); // zapisz serwer w bazie danych
        return server;
    }

    @Override
    public Collection<Server> list(int limit) {
        log.info("Fetching all servers"); // log
        return serverRepo.findAll(PageRequest.of(0, limit)).toList(); //zwróci listę serwerów z limitem
    }

    @Override
    public Server get(Long id) {
        log.info("Fetching server by id: {}", id); // log
        return serverRepo.findById(id).get(); // zwróci serwer po zadanym id
    }

    @Override
    public Server update(Server server) {
        log.info("Updating server: {}", server.getName());
        return serverRepo.save(server);

    }

    @Override
    public Boolean delete(Long id) {
        log.info("Deleting server by ID: {}", id);
        serverRepo.deleteById(id);
        return TRUE;
    }
    private String setServerImageUrl() {
//        orazy serwerów pobrane z https://www.flaticon.com/
        String[] imageNames = { "server1.png", "server2.png","server3.png","server4.png", };
        return ServletUriComponentsBuilder.fromCurrentContextPath().path("/server/image/" + imageNames[new Random().nextInt(4)]).toUriString();
        // zwróci ścieżkę do obrazka serwera. Wybierze losowo jeden z czterech obrazów.
    }
//    <a href="https://www.flaticon.com/free-icons/server" title="server icons">Server icons created by Freepik - Flaticon</a>
}
