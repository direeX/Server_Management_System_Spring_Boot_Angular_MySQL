package pl.direex.server.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;
import lombok.experimental.SuperBuilder;
import org.springframework.http.HttpStatus;

import java.time.LocalDateTime;
import java.util.Map;

import static com.fasterxml.jackson.annotation.JsonInclude.Include.NON_NULL;

//@SuperBuilder to adnotacja dostarczana przez bibliotekę Lombok w języku Java. Ta adnotacja pozwala na generowanie zestawu specjalnych metod dla wzorców projektowych tworzenia obiektów, takich jak wzorzec projektowy "Builder".
//Wzorzec "Builder" pozwala na tworzenie obiektów w sposób bardziej czytelny i elastyczny, szczególnie w przypadkach, gdy obiekt posiada wiele opcjonalnych pól. Dzięki użyciu @SuperBuilder, Lombok automatycznie generuje kod, który pozwala na konstruowanie obiektów przy użyciu "fluent API", czyli łańcuchowych wywołań metod.


// @JsonInclude(NON_NULL) to adnotacja dostarczana przez bibliotekę Jackson w języku Java, która pozwala na kontrolę tego, jakie właściwości (pola) obiektu serializowane są do formatu JSON. Gdy obiekt jest serializowany do JSON, jego właściwości, które mają wartość null, są zazwyczaj uwzględniane w wynikowym JSONie. Jednak w niektórych przypadkach chcemy wykluczyć z JSONa właściwości, które mają wartość null, aby zmniejszyć rozmiar i poprawić czytelność JSONa. Właśnie w tym celu używamy adnotacji @JsonInclude(NON_NULL).

@Data
@SuperBuilder
@JsonInclude(NON_NULL)
public class Response {
    // klasa informacyjna na odpowiedzi aplikacji
    protected LocalDateTime timeStamp;
    protected int statusCode;
    protected HttpStatus status;
    protected String reason;
    protected String message;
    protected String developerMessage;
    protected Map<?, ?> data;
}
