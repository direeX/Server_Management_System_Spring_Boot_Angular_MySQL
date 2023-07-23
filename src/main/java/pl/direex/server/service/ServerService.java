package pl.direex.server.service;

import pl.direex.server.model.Server;

import java.io.IOException;
import java.util.Collection;

public interface ServerService {
    Server create(Server server);         // metoda zwróci Server i utworzy go w bazie danych.
    Server ping(String ipAddress) throws IOException;       // metoda wyśle ping na podany serwer i zwróci Serwer.
    Collection<Server> list(int limit); // zwróci listę serweró. int limit - ustawi ile serwerów ma się pokazać na stronie.
    Server get(Long id);                // wyszuka serwer po id i zwróci Server.
    Server update(Server server);       // zaktualizuje serwer i zapisze informacje w bazie danych.
    Boolean delete(Long id);             // metoda usunie serwer z bazdy danych po przekazaniu id.
}
