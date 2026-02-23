package Memoria;

// eccezzione personalizzata per la mancata serializzazione
public class ErroreSerializzazioneNoteException extends Exception {

    // standard
    public ErroreSerializzazioneNoteException(String messaggio) {
        super(messaggio);
    }

    //con più info
    public ErroreSerializzazioneNoteException(String messaggio, Throwable causa) {
        super(messaggio, causa);
    }
}
