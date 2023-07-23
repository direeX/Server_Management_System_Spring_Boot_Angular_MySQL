package pl.direex.server.service.implementation;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import pl.direex.server.model.Server;
import pl.direex.server.service.ServerService;

import java.util.Collection;
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
    //@Override nadpisanie metod z interfejsu
    @Override
    public Server create(Server server) {
        return null;
    }

    @Override
    public Server ping(String ipAddress) {
        return null;
    }

    @Override
    public Collection<Server> list(int limit) {
        return null;
    }

    @Override
    public Server get(Long id) {
        return null;
    }

    @Override
    public Server update(Server server) {
        return null;
    }

    @Override
    public Boolean delete(Long id) {
        return null;
    }
}
