package Memoria;

import Note.Nota;
import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import utils.LoggerClass;

public class MemoriaPrincipale {

    private final String MEMORIA_FILE_PATH;

    public MemoriaPrincipale(String filePath) {
        this.MEMORIA_FILE_PATH = filePath;
    }

    // salva lista di note su file tramite serializzazione.
    private void salvaTutteLeNote(List<Nota> note) throws ErroreSerializzazioneNoteException {
        
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(MEMORIA_FILE_PATH))) {
            oos.writeObject(note);
            LoggerClass.debug("Note serializzate correttamente su ", MEMORIA_FILE_PATH);
            
        } catch (IOException ex) {
            
            LoggerClass.error("Errore durante la serializzazione delle note", ex);   
            throw new ErroreSerializzazioneNoteException("Non è stato possibile salvare le note su file: " + MEMORIA_FILE_PATH, ex);
        }
    }

    // legge la lista di note dal file tramite deserializzazione.
    public List<Nota> caricaNote() throws ErroreDeserializzazioneNoteException {
        
        File file = new File(MEMORIA_FILE_PATH);
        if (!file.exists()) {
            return new ArrayList<>();
        }

        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(file))) {
            return (List<Nota>) ois.readObject();

        } catch (IOException | ClassNotFoundException ex) {
            
            LoggerClass.error("Errore durante la deserializzazione delle note", ex);
            throw new ErroreDeserializzazioneNoteException("Non è stato possibile leggere le note dal file: " + MEMORIA_FILE_PATH, ex);
        }
    }

    // carica le note dal file filtrandole 
    public List<Nota> caricaNoteFiltrate(FiltroNota filtro) throws ErroreDeserializzazioneNoteException {
        
        List<Nota> tutteLeNote = caricaNote();
        List<Nota> filtrate = new ArrayList<>();

        for (Nota notaCorrente : tutteLeNote) {
            
            if (filtro.accetta(notaCorrente)) {
                filtrate.add(notaCorrente);
            }
        }
        return filtrate;
    }

    // salva le note 
    public void salvaNota(Nota notaDaSalvare) throws ErroreDeserializzazioneNoteException, ErroreSerializzazioneNoteException {
        List<Nota> note = caricaNote();
        for (Nota nota : note) {
            if (nota.titolo.equals(notaDaSalvare.titolo)) {
                LoggerClass.warn("Titolo già esistente: ", notaDaSalvare.titolo);
                return;
            }
        }
        note.add(notaDaSalvare);
        salvaTutteLeNote(note);
        LoggerClass.info("Nota salvata in memoria: ", notaDaSalvare.titolo);
    }

    // rimuove una nota
    public void rimuoviNota(Nota notaDaRimuovere) throws ErroreDeserializzazioneNoteException, ErroreSerializzazioneNoteException {
        
        List<Nota> note = caricaNote();
        List<Nota> filtrate = note.stream().filter(notaArgomento -> !notaArgomento.titolo.equals(notaDaRimuovere.titolo)).collect(Collectors.toList());

        if (filtrate.size() == note.size()) {
            LoggerClass.warn("Nessuna nota trovata con titolo: ", notaDaRimuovere.titolo);
        } else {

            salvaTutteLeNote(filtrate);
            LoggerClass.info("Nota rimossa dalla memoria: ", notaDaRimuovere.titolo);
        }
    }

}
