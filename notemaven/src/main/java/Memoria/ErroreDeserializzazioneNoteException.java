package Memoria;

// eccezzione per la deserializzazione
public class ErroreDeserializzazioneNoteException extends Exception {

    //standard
    public ErroreDeserializzazioneNoteException(String messaggio) {
        super(messaggio);
    }

    //con più info
    public ErroreDeserializzazioneNoteException(String messaggio, Throwable causa) {
        super(messaggio, causa);
    }
}
