package pl.direex.server.resource;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pl.direex.server.enumeration.Status;
import pl.direex.server.model.Response;
import pl.direex.server.model.Server;
import pl.direex.server.service.implementation.ServerServiceImplementation;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Map;

import static java.time.LocalDateTime.now;
import static java.util.Map.*;
import static org.springframework.http.HttpStatus.CREATED;
import static org.springframework.http.HttpStatus.OK;
import static org.springframework.http.MediaType.IMAGE_PNG_VALUE;
import static pl.direex.server.enumeration.Status.SERVER_UP;

//@RestController to adnotacja dostarczana przez Spring Framework w języku Java, która pełni rolę kontrolera w architekturze aplikacji opartych na Spring. Kontroler jest odpowiedzialny za obsługę żądań HTTP od klienta i zwracanie odpowiedzi.
// Kiedy klasa jest oznaczona adnotacją @RestController, informuje to Spring, że ta klasa jest kontrolerem, który będzie odpowiedzialny za obsługę żądań HTTP, a wynik działania metody zostanie automatycznie przekształcony na odpowiedź HTTP, np. w formacie JSON lub XML.

//@RequestMapping to adnotacja dostarczana przez Spring Framework w języku Java, która służy do mapowania żądań HTTP na metody kontrolera. Ta adnotacja pozwala na określenie, jakie żądania HTTP (GET, POST, PUT, DELETE itp.) oraz ścieżki URL powinny być przypisane do konkretnej metody w klasie kontrolera.
//Wcześniej w Spring 3.x, @RequestMapping było używane jako ogólna adnotacja do mapowania żądań HTTP. Od Spring 4.3, dostępne są bardziej specyficzne adnotacje związane z konkretnymi typami żądań, takie jak @GetMapping, @PostMapping, @PutMapping, @DeleteMapping i @PatchMapping, które można stosować zamiast @RequestMapping w zależności od konkretnego typu żądania.

//@RequiredArgsConstructor to adnotacja dostarczana przez bibliotekę Lombok w języku Java. Ta adnotacja automatycznie generuje konstruktor dla pól oznaczonych jako final, co pozwala na wstrzykiwanie wartości tych pól w momencie tworzenia obiektu. Gdy klasa jest oznaczona adnotacją @RequiredArgsConstructor, Lombok automatycznie generuje konstruktor, który zawiera tylko finalne pola jako argumenty. Wstrzykiwanie wartości do tych pól odbywa się poprzez konstruktor, co oznacza, że te pola nie mogą być zmieniane po utworzeniu obiektu. To jest szczególnie przydatne w kontekście wstrzykiwania zależności w Springu, gdzie klasy wstrzykiwane przez konstruktor zazwyczaj powinny być finalne.

@RestController
@RequestMapping("/server")
@RequiredArgsConstructor
public class ServerResource {
    private final ServerServiceImplementation serverService;

    @GetMapping("/list")
    public ResponseEntity<Response> getServers() {
        return ResponseEntity.ok(
                Response.builder()
                        .timeStamp(now())
                        .data(of("servers", serverService.list(30)))
                        .message(("Servers retrieved"))
                        .status(OK)
                        .statusCode(OK.value())
                        .build()
        );
    }
// W powyższym przykładzie mamy metodę kontrolera oznaczoną adnotacją @GetMapping("/list"), która obsługuje żądania HTTP typu GET na ścieżce "/list". Metoda ta zwraca obiekt typu ResponseEntity<Response>. Kod metody tworzy obiekt klasy ResponseEntity, który zawiera odpowiedź HTTP. Obiekt ResponseEntity pozwala na kontrolowanie różnych aspektów odpowiedzi HTTP, takich jak status, nagłówki i treść odpowiedzi. Metoda korzysta z klasy Response, która prawdopodobnie jest klasą reprezentującą odpowiedź, zawierającą różne pola takie jak timeStamp, data, message, status i statusCode. W przykładzie używamy wzorca "Builder", aby zwięzło skonstruować obiekt Response.
//    Klasa Response zawiera różne pola, w tym timeStamp typu LocalDateTime, data typu Map<String, Object>, message typu String, status typu HttpStatus, oraz statusCode typu int. Następnie w metodzie używamy metody now() klasy LocalDateTime, aby uzyskać aktualny czas, oraz metody serverService.list(30) aby pobrać listę serwerów. Następnie umieszczamy te informacje w obiekcie Response za pomocą wzorca "Builder".
//W końcu, metoda zwraca odpowiedź HTTP typu ResponseEntity<Response> za pomocą metody ResponseEntity.ok(), co oznacza, że żądanie zostało wykonane pomyślnie. Odpowiedź HTTP zostanie zwrócona w formacie JSON, ponieważ Response prawdopodobnie jest automatycznie serializowany do JSONa dzięki mechanizmowi serializacji i deserializacji, który jest wbudowany w Spring.
//Ten kod jest typowym przykładem metody kontrolera, która zwraca dane w formacie JSON w odpowiedzi na żądanie HTTP GET. Zwrócona odpowiedź zawiera listę serwerów wraz z informacjami o czasie wykonania i statusie żądania.


    @GetMapping("/ping/{ipAddress}")
    public ResponseEntity<Response> pingServer(@PathVariable("ipAddress") String ipAddress) throws IOException {
        Server server = serverService.ping(ipAddress);
        return ResponseEntity.ok(
                Response.builder()
                        .timeStamp(now())
                        .data(of("server", server))
                        .message((server.getStatus() == SERVER_UP ? "Ping success" : "Ping failed"))
                        .status(OK)
                        .statusCode(OK.value())
                        .build()
        );
    }
/*
W powyższym przykładzie mamy metodę kontrolera oznaczoną adnotacją @GetMapping("/ping/{ipAddress}"), która obsługuje żądania HTTP typu GET na ścieżce "/ping/{ipAddress}". W tej ścieżce, {ipAddress} to zmienna ścieżki, która będzie odczytana i przekazana do metody kontrolera jako parametr ipAddress.
    Metoda ta przyjmuje jako parametr adres IP (ipAddress), który zostanie odczytany z URL żądania HTTP, dzięki użyciu adnotacji @PathVariable("ipAddress").
    Następnie w ciele metody, wywołujemy metodę ping() klasy serverService, przekazując jako argument adres IP, aby sprawdzić dostępność serwera o danym adresie IP. W wyniku otrzymujemy obiekt klasy Server, który zawiera informacje o stanie serwera po pingu.
    Następnie tworzymy odpowiedź HTTP, którą zwrócimy klientowi. Odpowiedź ta jest obiektem typu ResponseEntity<Response>, co pozwala na kontrolowanie różnych aspektów odpowiedzi HTTP, takich jak status, nagłówki i treść odpowiedzi.
    Obiekt ResponseEntity używa metody ResponseEntity.ok() do utworzenia odpowiedzi z kodem statusu 200 OK, co oznacza, że żądanie zostało wykonane pomyślnie.
    Odpowiedź JSON, która zostanie zwrócona w odpowiedzi na żądanie, zawiera obiekt Response, który będzie zawierał informacje o czasie wykonania (timeStamp), informacje o serwerze (data), wiadomość o sukcesie lub niepowodzeniu (message), status odpowiedzi (status) oraz kod statusu (statusCode).
    Warto zauważyć, że odpowiedź JSON jest zbudowana za pomocą wzorca "Builder", co pozwala na bardziej czytelny i zwięzły sposób tworzenia obiektu Response.
    Cały kod jest typowym przykładem metody kontrolera, która przyjmuje adres IP jako parametr, wykonuje ping serwera za pomocą usługi serverService, a następnie zwraca odpowiedź JSON z wynikiem pingowania i dodatkowymi informacjami o czasie wykonania i statusie odpowiedzi.

*/
    @PostMapping("/save")
    public ResponseEntity<Response> saveServer(@RequestBody @Valid Server server) {
        return ResponseEntity.ok(
                Response.builder()
                        .timeStamp(now())
                        .data(of("server", serverService.create(server)))
                        .message(("Server created"))
                        .status(CREATED)
                        .statusCode(CREATED.value())
                        .build()
        );
    }
/*
W powyższym przykładzie mamy metodę kontrolera oznaczoną adnotacją @PostMapping("/save"), która obsługuje żądania HTTP typu POST na ścieżce "/save". Metoda ta przyjmuje jako parametr obiekt Server, który jest przesłany w ciele żądania jako JSON, dzięki użyciu adnotacji @RequestBody.
Dodatkowo, obiekt Server jest oznaczony adnotacją @Valid, co oznacza, że Spring wykonuje walidację tego obiektu na podstawie zdefiniowanych ograniczeń walidacji (np. za pomocą adnotacji @NotNull, @Size, @Pattern itp.), jeśli są one zdefiniowane w klasie Server.
Jeśli walidacja obiektu Server przebiegnie pomyślnie, wtedy metoda saveServer() przekazuje ten obiekt do usługi serverService.create(server), która zapisuje serwer do bazy danych lub wykonuje odpowiednie akcje związanymi z zapisem.
Następnie, metoda tworzy odpowiedź HTTP za pomocą obiektu ResponseEntity, który pozwala na kontrolowanie różnych aspektów odpowiedzi HTTP, takich jak status, nagłówki i treść odpowiedzi.
Obiekt ResponseEntity używa metody ResponseEntity.ok() do utworzenia odpowiedzi z kodem statusu 200 OK, co oznacza, że żądanie zostało wykonane pomyślnie.
Odpowiedź JSON, która zostanie zwrócona w odpowiedzi na żądanie, zawiera obiekt Response, który będzie zawierał informacje o czasie wykonania (timeStamp), informacje o zapisanym serwerze (data), wiadomość o sukcesie zapisu (message), status odpowiedzi (status) oraz kod statusu (statusCode).


 */

    @GetMapping("/get/{id}")
    public ResponseEntity<Response> getServer(@PathVariable("id") Long id) {
        return ResponseEntity.ok(
                Response.builder()
                        .timeStamp(now())
                        .data(of("server", serverService.get(id)))
                        .message(("Server retrieved"))
                        .status(OK)
                        .statusCode(OK.value())
                        .build()
        );
    }

    /*
    W powyższym przykładzie mamy metodę kontrolera oznaczoną adnotacją @GetMapping("/get/{id}"), która obsługuje żądania HTTP typu GET na ścieżce "/get/{id}". W tej ścieżce, {id} to zmienna ścieżki, która będzie odczytana i przekazana do metody kontrolera jako parametr id.
Metoda ta przyjmuje jako parametr identyfikator (id) serwera, który będzie odczytany z URL żądania HTTP, dzięki użyciu adnotacji @PathVariable("id").
Następnie w ciele metody, wywołujemy metodę get() klasy serverService, przekazując jako argument identyfikator serwera (id) w celu pobrania informacji o serwerze o danym identyfikatorze.




     */

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Response> deleteServer(@PathVariable("id") Long id) {
        return ResponseEntity.ok(
                Response.builder()
                        .timeStamp(now())
                        .data(of("deleted", serverService.delete(id)))
                        .message(("Server deleted"))
                        .status(OK)
                        .statusCode(OK.value())
                        .build()
        );
//    Metoda delete() zwraca informację, czy serwer został poprawnie usunięty, co najprawdopodobniej jest reprezentowane przez wartość logiczną (true lub false).
    }

    @GetMapping(path = "/image/{fileName}", produces = IMAGE_PNG_VALUE)
    public byte[] getServerImage(@PathVariable("fileName") String fileName) throws IOException {
        return Files.readAllBytes(Paths.get(System.getProperty("user.home") + "/Desktop/Spring/Server_Management_System/" + fileName));
    }
    /*
    W powyższym przykładzie mamy metodę kontrolera oznaczoną adnotacją @GetMapping(path = "/image/{fileName}", produces = IMAGE_PNG_VALUE), która obsługuje żądania HTTP typu GET na ścieżce "/image/{fileName}". W tej ścieżce, {fileName} to zmienna ścieżki, która będzie odczytana i przekazana do metody kontrolera jako parametr fileName.
Metoda ta przyjmuje jako parametr nazwę pliku (fileName) obrazu, który będzie odczytany z URL żądania HTTP, dzięki użyciu adnotacji @PathVariable("fileName").
Następnie w ciele metody, wywołujemy metodę Files.readAllBytes() z klasy Files, aby odczytać wszystkie bajty z pliku o podanej ścieżce.
Ścieżka pliku jest zbudowana na podstawie nazwy pliku (fileName) oraz ścieżki do katalogu Desktop/Spring/Server_Management_System w katalogu domowym użytkownika (System.getProperty("user.home")). Następnie odczytujemy wszystkie bajty z pliku przy użyciu Files.readAllBytes(), co zwraca tablicę bajtów reprezentujących zawartość pliku obrazu.
Ponieważ metoda zwraca tablicę bajtów, a nie obiekt JSON czy inny typ odpowiedzi, ustawiamy również produces = IMAGE_PNG_VALUE, co oznacza, że odpowiedź będzie miała nagłówek Content-Type ustawiony na wartość image/png. Dzięki temu przeglądarka wie, że otrzymuje dane obrazu w formacie PNG, a nie JSON czy inny typ danych.
     */
}
