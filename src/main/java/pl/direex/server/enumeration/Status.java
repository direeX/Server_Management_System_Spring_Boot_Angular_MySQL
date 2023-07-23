package pl.direex.server.enumeration;
/*
 Enum w którym przechowywane są statusy serwera.

 */
public enum Status {
    SERVER_IP("SERVER_UP"),
    SERVER_DOWN("SERVER_DOWN");
    private final String status; // pole prywatne, finalne typu String status.


  //  Konstruktor
    Status(String status) {
        this.status = status;
    }

    // Getter status.
    public String getStatus(){
        return this.status;
    }



}
