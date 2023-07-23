package pl.direex.server.resource;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pl.direex.server.model.Response;
import pl.direex.server.service.implementation.ServerServiceImplementation;

import java.util.Map;

import static java.time.LocalDateTime.now;
import static org.springframework.http.HttpStatus.OK;

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
                        .data(Map.of("servers", serverService.list(30)))
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
}
