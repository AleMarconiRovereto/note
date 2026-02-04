package Memoria;

import Note.Nota;
import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import utils.LoggerClass;

public class Memoria {

    public String MEMORIA_FILE_PATH = "note.ser";

    //salva lista di note su file tramite serializzazione.
    private void salvaTutteLeNote(List<Nota> note) throws Exception {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(MEMORIA_FILE_PATH))) {
            oos.writeObject(note);
            LoggerClass.debug("Note serializzate correttamente su ", MEMORIA_FILE_PATH);
        } catch (IOException ex) {
            LoggerClass.error("Errore durante la serializzazione delle note", ex);
            throw ex;
        }
    }

    // legge la lista di note dal file tramite deserializzazione.
     
    public List<Nota> caricaNote() throws Exception {
        File file = new File(MEMORIA_FILE_PATH);
        if (!file.exists()) {
            return new ArrayList<>();
        }

        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(file))) {
            return (List<Nota>) ois.readObject();
            
        } catch (IOException | ClassNotFoundException ex) {
            LoggerClass.error("Errore durante la deserializzazione delle note", ex);
            return new ArrayList<>();
        }
    }

    public void salvaNota(Nota notaDaSalvare) throws Exception {
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

    public void rimuoviNota(Nota notaDaRimuovere) throws Exception {
        List<Nota> note = caricaNote();
        // uso di lambda
        List<Nota> filtrate = note.stream().filter(notaArgomento -> !notaArgomento.titolo.equals(notaDaRimuovere.titolo)).collect(Collectors.toList());
        
        if (filtrate.size() == note.size()) {
            LoggerClass.warn("Nessuna nota trovata con titolo: ", notaDaRimuovere.titolo);
        } else {
            
            salvaTutteLeNote(filtrate);
            LoggerClass.info("Nota rimossa dalla memoria: ", notaDaRimuovere.titolo);
        }
    }

}
