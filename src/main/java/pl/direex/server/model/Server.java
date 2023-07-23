package pl.direex.server.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import pl.direex.server.enumeration.Status;

@Entity // do oznaczania klas, które reprezentują encje (tabela) w bazie danych.
@Data // to adnotacja biblioteki Lombok używana w kontekście języka Java do automatycznego generowania standardowych metod takich jak getter, setter, toString, equals i hashCode dla pól klasy
@NoArgsConstructor // lombok utworzy konstruktor bezargumentowy
@AllArgsConstructor // lombok utworzy konstruktor
public class Server {

    @Id //do oznaczania pola w klasie, które jest używane jako klucz główny (identyfikator) encji w bazie danych.
    @GeneratedValue(strategy = GenerationType.AUTO) //do automatycznego generowania wartości dla klucza głównego (identyfikatora) encji.
    private Long id;

    @Column(unique = true) //do określenia, że wartość w danej kolumnie w bazie danych powinna być unikalna.
    @NotEmpty(message = "Ip Address cannot be empty or null") //do określenia, że pole nie powinno być puste
    private String ipAddress;

    private String name;
    private String memory;
    private String type;
    private String imageUrl;
    private Status status;

}
